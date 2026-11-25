package nl.entreco.elchrono

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.click
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.pressKey
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import nl.entreco.elchrono.core.counter.MonotonicClock
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.counter.TimerEngine
import nl.entreco.elchrono.core.counter.TimerStatus
import nl.entreco.elchrono.core.design.ElChronoTheme
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.persistence.RestoredSettings
import nl.entreco.elchrono.core.persistence.SettingsRepository
import nl.entreco.elchrono.core.persistence.StorageResult
import nl.entreco.elchrono.core.persistence.TimerSettings

@OptIn(ExperimentalTestApi::class)
class TimerControlsUiTest {
    @Test
    fun `pause resume and restart remain usable at every detail level`() = runUiTest {
        val clock = TestClock()
        val viewModel = viewModel(clock)
        setContent { ElChronoTheme { Box(Modifier.size(320.dp, 640.dp)) { AppContent(viewModel) } } }
        (0..12).forEach { detail ->
            runOnIdle {
                viewModel.updateSettings(settings.copy(detail = detail))
                if (viewModel.uiState.value.timer.snapshot.status == TimerStatus.FINISHED) {
                    viewModel.onTimerAction()
                    viewModel.onTimerAction()
                }
            }
            val resume = onNodeWithContentDescription("Resume")
            resume.assertIsDisplayed().performClick()
            assertTrue(viewModel.uiState.value.isTicking)
            onNodeWithContentDescription("Pause").assertIsDisplayed().performClick()
            assertFalse(viewModel.uiState.value.isTicking)
            onNodeWithContentDescription("Resume").performClick()
            runOnIdle {
                clock.advance(1.minutes)
                viewModel.tick()
            }
            assertEquals(TimerStatus.FINISHED, viewModel.uiState.value.timer.snapshot.status)
            assertFalse(viewModel.uiState.value.isTicking)
            if (detail == 0) {
                onNodeWithText("Restart").assertDoesNotExist()
                onNodeWithContentDescription("Restart").assertIsDisplayed().performClick()
            } else {
                onNodeWithText("Restart").assertIsDisplayed().performClick()
            }
            assertEquals(1.minutes, viewModel.uiState.value.timer.snapshot.remaining)
            onNodeWithContentDescription("Pause").performClick()
        }
        runOnIdle { viewModel.updateSettings(settings.copy(duration = checkNotNull(TimerDuration.fromMinutes(0)))) }
        onNodeWithTag("timer-action").assertDoesNotExist()
    }

    @Test
    fun `timer action supports keyboard focus and activation`() = runUiTest {
        val viewModel = viewModel(TestClock())
        setContent { ElChronoTheme { AppContent(viewModel) } }
        val action = onNodeWithTag("timer-action")
        repeat(6) {
            if (action.fetchSemanticsNode().config.getOrNull(SemanticsProperties.Focused) != true) {
                onRoot().performKeyInput { pressKey(Key.Tab) }
            }
        }
        action.assertIsFocused().performKeyInput { pressKey(Key.Enter) }
        assertTrue(viewModel.uiState.value.isTicking)
        onNodeWithContentDescription("Pause").assertIsDisplayed()
    }

    @Test
    fun `interruption offers restart and zero duration never offers an enabled action`() = runUiTest {
        val clock = TestClock()
        val viewModel = viewModel(clock)
        setContent { ElChronoTheme { AppContent(viewModel) } }
        onNodeWithContentDescription("Resume").performClick()
        runOnIdle { viewModel.onAppBackgrounded() }
        onNodeWithText("Restart").assertIsDisplayed()
        onNodeWithText("Timer interrupted. Progress may be out of date. Restart to start again.").assertIsDisplayed()
        runOnIdle {
            viewModel.openSettings()
            viewModel.updateSettings(settings.copy(duration = checkNotNull(TimerDuration.fromMinutes(0))))
            viewModel.closeSettings()
        }
        onNodeWithTag("timer-action").assertDoesNotExist()
        runOnIdle {
            viewModel.openSettings()
            viewModel.updateSettings(settings)
            viewModel.closeSettings()
        }
        onNodeWithText("Restart").performClick()
        assertFalse(viewModel.uiState.value.interrupted)
        assertTrue(viewModel.uiState.value.isTicking)
    }

    @Test
    fun `expanded overlay blocks pointer and semantics through exit animation`() = runUiTest {
        val viewModel = viewModel(TestClock())
        setContent { ElChronoTheme { Box(Modifier.size(1200.dp, 720.dp)) { AppContent(viewModel) } } }
        val actionCenter = onNodeWithTag("timer-action").fetchSemanticsNode().boundsInRoot.center
        onNodeWithContentDescription("Open Settings").performClick()
        onNodeWithTag("timer-action", useUnmergedTree = true).assertDoesNotExist()
        onRoot().performTouchInput { click(actionCenter) }
        assertFalse(viewModel.uiState.value.isTicking)
        mainClock.autoAdvance = false
        onNodeWithContentDescription("Hide Settings").performClick()
        mainClock.advanceTimeBy(80)
        onNodeWithTag("timer-action", useUnmergedTree = true).assertDoesNotExist()
        onRoot().performTouchInput { click(actionCenter) }
        assertFalse(viewModel.uiState.value.isTicking)
        mainClock.advanceTimeBy(1000)
        onNodeWithContentDescription("Resume").assertIsDisplayed().performClick()
        assertTrue(viewModel.uiState.value.isTicking)
        viewModel.onTimerAction()
        assertFalse(viewModel.uiState.value.isTicking)
        mainClock.autoAdvance = true
    }

    @Test
    fun `finished restart is unclipped at compact width and large text`() = runUiTest {
        val clock = TestClock()
        val viewModel = viewModel(clock)
        setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, 2f)) {
                ElChronoTheme { Box(Modifier.size(320.dp, 640.dp)) { AppContent(viewModel) } }
            }
        }
        runOnIdle { viewModel.onTimerAction(); clock.advance(1.minutes); viewModel.tick() }
        val timer = onNodeWithTag("timer-layer").fetchSemanticsNode()
        val action = onNodeWithTag("timer-action").fetchSemanticsNode()
        onNodeWithText("Restart").assertIsDisplayed()
        assertTrue(action.boundsInRoot.left >= timer.boundsInRoot.left)
        assertTrue(action.boundsInRoot.right <= timer.boundsInRoot.right)
        assertTrue(action.boundsInRoot.bottom <= timer.boundsInRoot.bottom)
        assertTrue(action.boundsInRoot.height >= 48f)
        assertNotNull(action.config.getOrNull(SemanticsActions.OnClick))
        onNode(
            SemanticsMatcher.expectValue(
                SemanticsProperties.StateDescription,
                "Timer has finished. Restart to begin the configured duration again.",
            ),
        ).assertExists()
        onNodeWithText("Restart").performClick()
        assertTrue(viewModel.uiState.value.isTicking)
    }

    private val settings = TimerSettings(checkNotNull(TimerDuration.fromMinutes(1)), TimerTheme.CLOCK)

    private fun viewModel(clock: TestClock) = AppViewModel(
        TimerEngine(clock),
        object : SettingsRepository {
            override fun load() = RestoredSettings(settings, null)
            override fun save(settings: TimerSettings): StorageResult<Unit> = StorageResult.Success(Unit)
        },
        settings,
        null,
    )

    private class TestClock : MonotonicClock {
        private var now = 0L
        override fun nowNanos() = now
        fun advance(duration: Duration) { now += duration.inWholeNanoseconds }
    }
}
