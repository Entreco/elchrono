package nl.entreco.elchrono.features.settings

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import kotlin.test.Test
import kotlin.test.assertEquals
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.design.TimerFont
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.persistence.TimerSettings

@OptIn(ExperimentalTestApi::class)
class SettingsAppearanceUiTest {
    @Test
    fun `appearance theme recomposes with the selected colors and font`() = runDesktopComposeUiTest {
        var settings by mutableStateOf(
            TimerSettings(checkNotNull(TimerDuration.fromMinutes(10)), TimerTheme.CLOCK),
        )
        var background = Color.Unspecified
        var foreground = Color.Unspecified
        var text = Color.Unspecified
        var font: FontFamily? = null
        setContent {
            SettingsAppearanceTheme(settings) {
                val scheme = MaterialTheme.colorScheme
                val family = MaterialTheme.typography.bodyLarge.fontFamily
                SideEffect {
                    background = scheme.background
                    foreground = scheme.primary
                    text = scheme.onSurface
                    font = family
                }
            }

        }

        runOnIdle {
            settings = settings.copy(
                backgroundColorArgb = 0xFFE58A3A,
                foregroundColorArgb = 0xFF3D7A57,
                textColorArgb = 0xFF5B5BD6,
                font = TimerFont.MONOSPACE,
            )
        }
        runOnIdle {
            assertEquals(Color(0xFFE58A3A), background)
            assertEquals(Color(0xFF3D7A57), foreground)
            assertEquals(Color(0xFF5B5BD6), text)
            assertEquals(FontFamily.Monospace, font)
        }
    }

    @Test
    fun `appearance controls publish changes and preserve earlier selections`() = runDesktopComposeUiTest {
        var settings by mutableStateOf(
            TimerSettings(checkNotNull(TimerDuration.fromMinutes(10)), TimerTheme.CLOCK),
        )
        setContent {
            SettingsPanel(
                initialSettings = settings,
                onChange = { settings = it },
                onShowAbout = {},
                keyboardInputEnabled = true,
                keyboardAvailable = true,
            )
        }

        onAllNodesWithContentDescription("Orange")[0].performScrollTo().performClick()
        onAllNodesWithContentDescription("Green")[1].performScrollTo().performClick()
        onAllNodesWithContentDescription("Blue")[2].performScrollTo().performClick()
        onNodeWithText("Monospace").performScrollTo().performClick()
        onNodeWithText("Ring").performScrollTo().performClick()
        runOnIdle {
            assertEquals(0xFFE58A3A, settings.backgroundColorArgb)
            assertEquals(0xFF3D7A57, settings.foregroundColorArgb)
            assertEquals(0xFF5B5BD6, settings.textColorArgb)
            assertEquals(TimerFont.MONOSPACE, settings.font)
            assertEquals(TimerTheme.RING, settings.theme)
            assertEquals(10, settings.duration.totalMinutes)
        }
    }
}
