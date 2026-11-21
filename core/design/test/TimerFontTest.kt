package nl.entreco.elchrono.core.design

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import kotlin.test.Test
import kotlin.test.assertEquals

class TimerFontTest {
    @Test
    fun `typography applies the supplied family without changing sizes`() {
        val typography = Typography()
        listOf(FontFamily.Default, FontFamily.SansSerif, FontFamily.Monospace, FontFamily.Serif)
            .forEach { family ->
            val updated = typography.withFontFamily(family)
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
