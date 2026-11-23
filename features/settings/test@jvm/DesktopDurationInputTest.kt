package nl.entreco.elchrono.features.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.LocalSaveableStateRegistry
import androidx.compose.runtime.saveable.SaveableStateRegistry
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.pressKey
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.persistence.TimerSettings
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class DesktopDurationInputTest {
    @Test
    fun `duration draft restores without republishing a setting`() = runDesktopComposeUiTest {
        val settings = TimerSettings(checkNotNull(TimerDuration.fromMinutes(0)), TimerTheme.CLOCK)
        var registry by mutableStateOf(SaveableStateRegistry(null) { true })
        var visible by mutableStateOf(true)
        var publications = 0
        setContent {
            if (visible) {
                CompositionLocalProvider(LocalSaveableStateRegistry provides registry) {
                    SettingsScreen(settings, { publications++ }, {}, true, true)
                }
            }
        }
        onNodeWithText("Minutes").performClick().performTextInput("2")
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
        onNodeWithText("Minutes").performClick().performTextInput("5")
        onNodeWithText("Hours").assertTextContains("02")
        onNodeWithText("Minutes").assertTextContains("05")
    }

    @Test
    fun `desktop fields accept sequential input without an app keypad`() = runDesktopComposeUiTest {
        var settings by mutableStateOf(
            TimerSettings(checkNotNull(TimerDuration.fromMinutes(0)), TimerTheme.CLOCK),
        )
        setContent {
            SettingsScreen(
                initialSettings = settings,
                onChange = { settings = it },
                onShowAbout = {},
                keyboardInputEnabled = true,
                keyboardAvailable = true,
            )
        }

        onNodeWithText("Clear").assertDoesNotExist()
        onNodeWithText("Minutes").performClick().performTextInput("2")
        onNodeWithText("Minutes").assertTextContains("02")
        onNodeWithText("Minutes").performTextInput("0")
        onNodeWithText("Minutes").assertTextContains("20")
        onNodeWithText("Hours").performClick().performTextInput("5")
        onNodeWithText("Hours").assertTextContains("02")
        onNodeWithText("Minutes").assertTextContains("05")
    }

    @Test
    fun `desktop fields accept backspace key events`() = runDesktopComposeUiTest {
        var settings by mutableStateOf(
            TimerSettings(checkNotNull(TimerDuration.fromMinutes(0)), TimerTheme.CLOCK),
        )
        setContent {
            SettingsScreen(
                initialSettings = settings,
                onChange = { settings = it },
                onShowAbout = {},
                keyboardInputEnabled = true,
                keyboardAvailable = true,
            )
        }

        onNodeWithText("Minutes").performClick().performTextInput("2")
        onNodeWithText("Minutes").performTextInput("0")
        onNodeWithText("Minutes").performKeyInput { pressKey(Key.Backspace) }
        onNodeWithText("Minutes").assertTextContains("02")
    }

    @Test
    fun `changing keyboard availability preserves the current duration`() = runDesktopComposeUiTest {
        var keyboardAvailable by mutableStateOf(true)
        var settings by mutableStateOf(
            TimerSettings(checkNotNull(TimerDuration.fromMinutes(0)), TimerTheme.CLOCK),
        )
        setContent {
            SettingsScreen(
                initialSettings = settings,
                onChange = { settings = it },
                onShowAbout = {},
                keyboardInputEnabled = true,
                keyboardAvailable = keyboardAvailable,
            )
        }

        onNodeWithText("Minutes").performClick().performTextInput("2")
        runOnIdle { keyboardAvailable = false }
        onNodeWithText("Minutes").assertTextContains("02")
        onNodeWithText("Clear").assertExists()
        runOnIdle { keyboardAvailable = true }
        onNodeWithText("Clear").assertDoesNotExist()
        onNodeWithText("Minutes").assertTextContains("02")
    }

}
