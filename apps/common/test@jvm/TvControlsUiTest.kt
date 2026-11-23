package nl.entreco.elchrono

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.pressKey
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.counter.TimerEngine
import nl.entreco.elchrono.core.design.ElChronoTheme
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.persistence.RestoredSettings
import nl.entreco.elchrono.core.persistence.SettingsRepository
import nl.entreco.elchrono.core.persistence.StorageResult
import nl.entreco.elchrono.core.persistence.TimerSettings

@OptIn(ExperimentalTestApi::class)
class TvControlsUiTest {

    @Test
    fun `remote moves between app bar and timer action`() = runDesktopComposeUiTest {
        mainClock.autoAdvance = false
        val viewModel = viewModel()
        setContent {
            ElChronoTheme {
                Box(Modifier.size(1280.dp, 720.dp)) {
                    AppContent(viewModel)
                }
            }
        }
        mainClock.advanceTimeBy(300)
        onNodeWithContentDescription("Open Settings").assertIsFocused().performKeyInput {
            pressKey(Key.DirectionDown)
        }
        onNodeWithContentDescription("Resume").assertIsFocused().performKeyInput {
            pressKey(Key.DirectionUp)
        }
        onNodeWithContentDescription("Open Settings").assertIsFocused()
        assertFalse(viewModel.uiState.value.isTicking)
    }

    @Test
    fun `remote activates the focused timer action`() = runDesktopComposeUiTest {
        val viewModel = viewModel()
        setContent {
            ElChronoTheme {
                Box(Modifier.size(1280.dp, 720.dp)) {
                    AppContent(viewModel)
                }
            }
        }

        onNodeWithContentDescription("Open Settings").assertIsFocused()
        onNodeWithContentDescription("Resume").assertExists()
        onNodeWithContentDescription("Open Settings").assertIsFocused()
        assertFalse(viewModel.uiState.value.isTicking)

        onNodeWithContentDescription("Open Settings").performKeyInput {
            pressKey(Key.DirectionDown)
        }
        onNodeWithContentDescription("Resume").assertIsFocused()
        onRoot().performKeyInput { pressKey(Key.Enter) }
        assertTrue(viewModel.uiState.value.isTicking)
        viewModel.onTimerAction()
        assertFalse(viewModel.uiState.value.isTicking)
    }

    @Test
    fun `settings expose editable duration fields without an app keypad`() = runDesktopComposeUiTest {
        val viewModel = viewModel()
        setContent {
            ElChronoTheme {
                Box(Modifier.size(1280.dp, 720.dp)) {
                    AppContent(viewModel)
                }
            }
        }

        onNodeWithContentDescription("Open Settings").performClick()
        assertEquals(0, onAllNodesWithText("Clear").fetchSemanticsNodes().size)
        assertEquals(2, onAllNodes(hasSetTextAction(), useUnmergedTree = true).fetchSemanticsNodes().size)

        assertEquals(120, viewModel.uiState.value.settings.duration.totalMinutes)
    }

    @Test
    fun `remote can move focus between the duration fields`() = runDesktopComposeUiTest {
        val viewModel = viewModel()
        setContent {
            ElChronoTheme {
                Box(Modifier.size(1280.dp, 720.dp)) {
                    AppContent(viewModel)
                }
            }
        }
        onNodeWithContentDescription("Open Settings").performClick()
        onNodeWithText("Hours").performSemanticsAction(SemanticsActions.RequestFocus)
        onNodeWithText("Hours").assertIsFocused().performKeyInput { pressKey(Key.DirectionRight) }
        onNodeWithText("Minutes").assertIsFocused()
    }

    @Test
    fun `remote selects theme and adjusts slider without moving focus`() = runDesktopComposeUiTest {
        val viewModel = viewModel()
        setContent {
            ElChronoTheme {
                Box(Modifier.size(1280.dp, 720.dp)) {
                    AppContent(viewModel)
                }
            }
        }
        onNodeWithContentDescription("Open Settings").performKeyInput { pressKey(Key.Enter) }
        onNodeWithText("Ring").performSemanticsAction(SemanticsActions.RequestFocus)
        onNodeWithText("Ring").assertIsFocused().performKeyInput { pressKey(Key.Enter) }
        assertEquals(TimerTheme.RING, viewModel.uiState.value.settings.theme)
        val before = viewModel.uiState.value.settings.ringThicknessPercent
        onNodeWithContentDescription("Ring thickness").performSemanticsAction(SemanticsActions.RequestFocus)
        onNodeWithContentDescription("Ring thickness").assertIsFocused().performKeyInput {
            pressKey(Key.DirectionRight)
        }
        onNodeWithContentDescription("Ring thickness").assertIsFocused()
        assertTrue(viewModel.uiState.value.settings.ringThicknessPercent > before)
    }

    @Test
    fun `remote returns from about to settings`() = runDesktopComposeUiTest {
        val viewModel = viewModel()
        setContent {
            ElChronoTheme {
                Box(Modifier.size(1280.dp, 720.dp)) {
                    AppContent(viewModel)
                }
            }
        }
        onNodeWithContentDescription("Open Settings").performKeyInput { pressKey(Key.Enter) }
        onNodeWithText("About").performSemanticsAction(SemanticsActions.RequestFocus)
        onNodeWithText("About").assertIsFocused().performKeyInput { pressKey(Key.Enter) }
        onNodeWithContentDescription("Back").assertIsFocused().performKeyInput { pressKey(Key.Enter) }
        onNodeWithContentDescription("Hide Settings").assertIsFocused()
        onNodeWithContentDescription("Resume").assertDoesNotExist()
    }

    private fun viewModel(): AppViewModel {
        val settings = TimerSettings(
            duration = checkNotNull(TimerDuration.fromMinutes(120)),
            theme = TimerTheme.CLOCK,
        )
        return AppViewModel(
            TimerEngine(),
            object : SettingsRepository {
                override fun load() = RestoredSettings(settings, null)
                override fun save(settings: TimerSettings): StorageResult<Unit> =
                    StorageResult.Success(Unit)
            },
            settings,
            null,
        )
    }
}
