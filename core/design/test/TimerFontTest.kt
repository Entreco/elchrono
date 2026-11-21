package nl.entreco.elchrono.core.design

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import kotlin.test.Test
import kotlin.test.assertEquals

class TimerFontTest {
    @Test
    fun `settings typography uses the same font mapping as the timer`() {
        val typography = Typography()
        val expected = mapOf(
            TimerFont.DEFAULT to FontFamily.Default,
            TimerFont.ROUNDED to FontFamily.SansSerif,
            TimerFont.MONOSPACE to FontFamily.Monospace,
        )
        expected.forEach { (font, family) ->
            assertEquals(family, font.toFontFamily())
            val updated = typography.withFontFamily(font.toFontFamily())
            listOf(
                updated.displayLarge, updated.displayMedium, updated.displaySmall,
                updated.headlineLarge, updated.headlineMedium, updated.headlineSmall,
                updated.titleLarge, updated.titleMedium, updated.titleSmall,
                updated.bodyLarge, updated.bodyMedium, updated.bodySmall,
                updated.labelLarge, updated.labelMedium, updated.labelSmall,
            ).forEach { assertEquals(family, it.fontFamily) }
            assertEquals(typography.bodyLarge.fontSize, updated.bodyLarge.fontSize)
        }
    }
}
