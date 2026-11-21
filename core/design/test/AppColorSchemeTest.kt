package nl.entreco.elchrono.core.design

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AppColorSchemeTest {
    @Test
    fun `global background retains readable foreground on every preset`() {
        listOf(0xFFF7F7F2, 0xFF17171C, 0xFF5B5BD6, 0xFFE58A3A, 0xFF3D7A57, 0xFFC54A4A).forEach {
            val background = Color(it)
            val scheme = appColorScheme(background)
            assertEquals(background, scheme.background)
            assertEquals(background, scheme.surface)
            assertEquals(scheme.onSurface, scheme.onSurfaceVariant)
            val luminances = listOf(background.luminance(), scheme.onSurface.luminance()).sorted()
            assertTrue((luminances[1] + 0.05f) / (luminances[0] + 0.05f) >= 4.5f)
        }
    }

    @Test
    fun `explicit text color is used for surface content without changing semantic colors`() {
        val background = Color(0xFFF7F7F2)
        val textColor = Color(0xFF17171C)
        val defaultScheme = appColorScheme(background)
        val scheme = appColorScheme(background, textColor)

        assertEquals(textColor, scheme.onBackground)
        assertEquals(textColor, scheme.onSurface)
        assertEquals(textColor, scheme.onSurfaceVariant)
        assertEquals(defaultScheme.primary, scheme.primary)
        assertEquals(defaultScheme.error, scheme.error)
        assertEquals(defaultScheme.onErrorContainer, scheme.onErrorContainer)
    }
}
