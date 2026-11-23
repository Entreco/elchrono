package nl.entreco.elchrono.features.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.LocalSaveableStateRegistry
import androidx.compose.runtime.saveable.SaveableStateRegistry
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.pressKey
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import androidx.compose.ui.text.TextRange
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.persistence.TimerSettings
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class DesktopDurationInputTest {
    @Test
    fun `duration draft restores without republishing a setting`() = runDesktopComposeUiTest {
        val settings = settingsWithDuration(0)
        var registry by mutableStateOf(SaveableStateRegistry(null) { true })
        var visible by mutableStateOf(true)
        var publications = 0
        setContent {
            if (visible) {
                CompositionLocalProvider(LocalSaveableStateRegistry provides registry) {
                    SettingsScreen(settings, { publications++ }, {})
                }
            }
        }

        onNodeWithText("Minutes").performClick()
        runOnIdle { }
        onNodeWithText("Minutes").performTextInput("2")
        onNodeWithText("Minutes").performTextInput("0")
        onNodeWithText("Minutes").assertTextContains("20")
        val saved = runOnIdle {
            assertEquals(2, publications)
            registry.performSave().also { visible = false }
        }
        runOnIdle {
            registry = SaveableStateRegistry(saved) { true }
            visible = true
        }
        onNodeWithText("Minutes").assertTextContains("20")
        runOnIdle { assertEquals(2, publications) }

        onNodeWithText("Minutes").performClick()
        runOnIdle { }
        onNodeWithText("Minutes").performTextInput("5")
        onNodeWithText("Minutes").assertTextContains("5")
        onNodeWithText("Hours").assertTextContains("00")
    }

    @Test
    fun `fields edit independently select on focus and pad on blur without an app keypad`() =
        runDesktopComposeUiTest {
            var settings by mutableStateOf(settingsWithDuration(125))
            setContent {
                SettingsScreen(
                    initialSettings = settings,
                    onChange = { settings = it },
                    onShowAbout = {},
                )
            }

            assertEquals(0, onAllNodesWithText("Clear").fetchSemanticsNodes().size)
            onNodeWithText("Hours").performClick()
            runOnIdle { }
            assertEquals(
                TextRange(0, 2),
                onNodeWithText("Hours").fetchSemanticsNode().config[SemanticsProperties.TextSelectionRange],
            )
            onNodeWithText("Hours").performTextInput("3")
            onNodeWithText("Hours").assertTextContains("3")
            onNodeWithText("Minutes").assertTextContains("05")
            runOnIdle { assertEquals(185, settings.duration.totalMinutes) }

            onNodeWithText("Minutes").performClick()
            runOnIdle { }
            onNodeWithText("Minutes").performTextInput("6")
            onNodeWithText("Minutes").assertTextContains("6")
            onNodeWithText("Hours").assertTextContains("03")
            runOnIdle { assertEquals(186, settings.duration.totalMinutes) }
        }

    @Test
    fun `empty focused field immediately contributes zero and pads when focus leaves`() =
        runDesktopComposeUiTest {
            var settings by mutableStateOf(settingsWithDuration(125))
            setContent {
                SettingsScreen(
                    initialSettings = settings,
                    onChange = { settings = it },
                    onShowAbout = {},
                )
            }

            onNodeWithText("Minutes").performClick()
            runOnIdle { }
            onNodeWithText("Minutes").performKeyInput { pressKey(Key.Backspace) }
                .assertTextContains("")
            runOnIdle { assertEquals(120, settings.duration.totalMinutes) }

            onNodeWithText("Hours").performClick()
            onNodeWithText("Minutes").assertTextContains("00")
        }

    @Test
    fun `desktop fields accept backspace key events`() = runDesktopComposeUiTest {
        var settings by mutableStateOf(settingsWithDuration(125))
        setContent {
            SettingsScreen(
                initialSettings = settings,
                onChange = { settings = it },
                onShowAbout = {},
            )
        }

        onNodeWithText("Minutes").performClick()
        runOnIdle { }
        onNodeWithText("Minutes").performKeyInput { pressKey(Key.Backspace) }
        onNodeWithText("Minutes").assertTextContains("")
        runOnIdle { assertEquals(120, settings.duration.totalMinutes) }
    }

    private fun settingsWithDuration(totalMinutes: Int) = TimerSettings(
        checkNotNull(TimerDuration.fromMinutes(totalMinutes)),
        TimerTheme.CLOCK,
    )
}
