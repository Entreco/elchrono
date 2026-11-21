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
}
