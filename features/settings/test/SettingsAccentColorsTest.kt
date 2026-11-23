package nl.entreco.elchrono.features.settings

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SettingsAccentColorsTest {
    @Test
    fun `selected foreground is preserved and label color remains legible`() {
        assertEquals(16, settingsColorPresets.size)
        assertEquals(16, settingsColorPresets.map { it.id }.distinct().size)
        assertEquals(16, settingsColorPresets.map { it.argb }.distinct().size)
        settingsColorPresets.forEach {
            val foreground = Color(it.argb)
            val colors = settingsAccentColors(foreground)
            assertEquals(foreground, colors.foreground)
            val luminances = listOf(foreground.luminance(), colors.onForeground.luminance()).sorted()
            assertTrue(
                (luminances[1] + 0.05f) / (luminances[0] + 0.05f) >= 4.5f,
                "Insufficient accent text contrast for $foreground",
            )
        }
    }

    @Test
    fun `changing foreground changes accent and suitable content color`() {
        val dark = settingsAccentColors(Color.Black)
        val light = settingsAccentColors(Color.White)
        assertEquals(Color.Black, dark.foreground)
        assertEquals(Color.White, dark.onForeground)
        assertEquals(Color.White, light.foreground)
        assertEquals(Color.Black, light.onForeground)
    }
}
