package nl.entreco.elchrono

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.pressKey
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.counter.TimerEngine
import nl.entreco.elchrono.core.design.ElChronoTheme
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.persistence.RestoredSettings
import nl.entreco.elchrono.core.persistence.SettingsRepository
import nl.entreco.elchrono.core.persistence.StorageResult
import nl.entreco.elchrono.core.persistence.TimerSettings
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class SettingsOverlayTest {
    @Test
    fun `compact navigation overlays the stationary timer`() = runDesktopComposeUiTest {
        val viewModel = testViewModel()
        setContent {
            ElChronoTheme {
                Box(Modifier.size(360.dp, 640.dp)) { AppContent(true, viewModel) }
            }
        }
        val timerNode = onNodeWithTag("timer-layer").fetchSemanticsNode()
        val timerBounds = timerNode.boundsInRoot
        onNodeWithContentDescription("Open Settings").performClick()
        onNodeWithText("Hours").performScrollTo().assertIsDisplayed()
        assertEquals(timerBounds, onNodeWithTag("timer-layer").fetchSemanticsNode().boundsInRoot)
        assertEquals(timerNode.id, onNodeWithTag("timer-layer").fetchSemanticsNode().id)
        onNodeWithContentDescription("Remaining waiting time").assertDoesNotExist()
        onNodeWithText("About").performScrollTo().performClick()
        onNodeWithContentDescription("Entreco logo").assertExists()
        onNodeWithText("Hours").assertDoesNotExist()
        assertEquals(timerBounds, onNodeWithTag("timer-layer").fetchSemanticsNode().boundsInRoot)
        onNodeWithContentDescription("Back").performClick()
        onNodeWithText("Hours").performScrollTo().assertIsDisplayed()
        onNodeWithContentDescription("Hide Settings").performClick()
        onNodeWithContentDescription("Open Settings").assertIsDisplayed()
        assertEquals(timerBounds, onNodeWithTag("timer-layer").fetchSemanticsNode().boundsInRoot)
    }

    @Test
    fun `expanded settings slide from left without moving timer`() = runDesktopComposeUiTest {
        val viewModel = testViewModel()
        setContent {
            ElChronoTheme {
                Box(Modifier.size(1200.dp, 720.dp)) { AppContent(true, viewModel) }
            }
        }
        val timerBounds = onNodeWithTag("timer-layer").fetchSemanticsNode().boundsInRoot
        mainClock.autoAdvance = false
        onNodeWithContentDescription("Open Settings").performClick()
        mainClock.advanceTimeBy(80)
        val entering = onNodeWithTag("settings-layer", useUnmergedTree = true)
            .fetchSemanticsNode().layoutInfo.coordinates!!.positionInRoot()
        assertTrue(entering.x < timerBounds.left, "Entering panel position: $entering")
        assertEquals(timerBounds, onNodeWithTag("timer-layer").fetchSemanticsNode().boundsInRoot)
        mainClock.advanceTimeBy(1000)
        val open = onNodeWithTag("settings-layer", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        assertEquals(timerBounds.left, open.left)
        assertTrue(open.width < timerBounds.width)
        onNodeWithContentDescription("Hide Settings").performClick()
        mainClock.advanceTimeBy(80)
        val leaving = onNodeWithTag("settings-layer", useUnmergedTree = true)
            .fetchSemanticsNode().layoutInfo.coordinates!!.positionInRoot()
        assertTrue(leaving.x < open.left, "Leaving panel position: $leaving")
        assertEquals(timerBounds, onNodeWithTag("timer-layer").fetchSemanticsNode().boundsInRoot)
        mainClock.autoAdvance = true
    }

    @Test
    fun `compact width fills the window and rapid reopening reverses exit`() = runDesktopComposeUiTest {
        val viewModel = testViewModel()
        setContent {
            ElChronoTheme {
                Box(Modifier.size(500.dp, 900.dp)) { AppContent(true, viewModel) }
            }
        }
        val timerBounds = onNodeWithTag("timer-layer").fetchSemanticsNode().boundsInRoot
        onNodeWithContentDescription("Open Settings").performClick()
        val open = onNodeWithTag("settings-layer", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        assertEquals(timerBounds.width, open.width)
        mainClock.autoAdvance = false
        onNodeWithContentDescription("Hide Settings").performClick()
        mainClock.advanceTimeBy(80)
        onNodeWithContentDescription("Open Settings").performClick()
        mainClock.advanceTimeBy(1000)
        assertEquals(open, onNodeWithTag("settings-layer", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot)
        assertEquals(timerBounds, onNodeWithTag("timer-layer").fetchSemanticsNode().boundsInRoot)
        mainClock.autoAdvance = true
    }

    @Test
    fun `about slides left over settings and leaves the timer stationary`() = runDesktopComposeUiTest {
        val viewModel = testViewModel()
        setContent {
            ElChronoTheme {
                Box(Modifier.size(1200.dp, 720.dp)) { AppContent(true, viewModel) }
            }
        }
        onNodeWithContentDescription("Open Settings").performClick()
        val timerBounds = onNodeWithTag("timer-layer").fetchSemanticsNode().boundsInRoot
        mainClock.autoAdvance = false
        runOnIdle { viewModel.showAbout() }
        mainClock.advanceTimeBy(80)
        val entering = onNodeWithTag("about-layer", useUnmergedTree = true)
            .fetchSemanticsNode().layoutInfo.coordinates!!.positionInRoot()
        assertTrue(entering.x < timerBounds.left, "Entering About position: $entering")
        mainClock.advanceTimeBy(1000)
        onNodeWithText("Hours").assertDoesNotExist()
        onNodeWithContentDescription("Back").performClick()
        mainClock.advanceTimeBy(80)
        val leaving = onNodeWithTag("about-layer", useUnmergedTree = true)
            .fetchSemanticsNode().layoutInfo.coordinates!!.positionInRoot()
        assertTrue(leaving.x < timerBounds.left, "Leaving About position: $leaving")
        assertEquals(timerBounds, onNodeWithTag("timer-layer").fetchSemanticsNode().boundsInRoot)
        mainClock.autoAdvance = true
    }

    @Test
    fun `settings fields do not retain keyboard focus behind about`() = runDesktopComposeUiTest {
        val viewModel = testViewModel()
        setContent { ElChronoTheme { AppContent(true, viewModel) } }
        onNodeWithContentDescription("Open Settings").performClick()
        onNodeWithText("Hours").performClick()
        runOnIdle { viewModel.showAbout() }
        repeat(5) { onRoot().performKeyInput { pressKey(Key.Tab) } }
        onAllNodes(hasSetTextAction(), useUnmergedTree = true)[0].assertIsNotFocused()
        onAllNodes(hasSetTextAction(), useUnmergedTree = true)[1].assertIsNotFocused()
    }

    @Test
    fun `resizing with about open keeps navigation and panel width`() = runDesktopComposeUiTest {
        val viewModel = testViewModel()
        var width by mutableStateOf(500.dp)
        setContent {
            ElChronoTheme {
                Box(Modifier.size(width, 720.dp)) { AppContent(true, viewModel) }
            }
        }
        onNodeWithContentDescription("Open Settings").performClick()
        runOnIdle { viewModel.showAbout() }
        val compact = onNodeWithTag("about-layer").fetchSemanticsNode().boundsInRoot
        assertEquals(onNodeWithTag("timer-layer").fetchSemanticsNode().boundsInRoot.width, compact.width)
        runOnIdle { width = 1200.dp }
        val expanded = onNodeWithTag("about-layer").fetchSemanticsNode().boundsInRoot
        assertTrue(expanded.width < onNodeWithTag("timer-layer").fetchSemanticsNode().boundsInRoot.width)
        onNodeWithContentDescription("Back").performClick()
        onNodeWithText("Hours").assertIsDisplayed()
    }

    private fun testViewModel(): AppViewModel {
        val settings = TimerSettings(
            duration = checkNotNull(TimerDuration.fromMinutes(120)),
            theme = TimerTheme.CLOCK,
        )
        return AppViewModel(TimerEngine(), TestRepository(settings), settings, null)
    }

    private class TestRepository(private val settings: TimerSettings) : SettingsRepository {
        override fun load() = RestoredSettings(settings, null)
        override fun save(settings: TimerSettings): StorageResult<Unit> = StorageResult.Success(Unit)
    }
}
