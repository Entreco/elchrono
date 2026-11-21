package nl.entreco.elchrono.core.design

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ElChronoThemeTest {
    @Test
    fun themeColorsMaintainContrastForCoreContent() {
        val colors = ElChronoColorScheme
        val textPairs = listOf(
            colors.onBackground to colors.background,
            colors.onSurface to colors.surface,
            colors.onSurfaceVariant to colors.surfaceVariant,
            colors.onPrimary to colors.primary,
            colors.onPrimaryContainer to colors.primaryContainer,
            colors.onSecondaryContainer to colors.secondaryContainer,
            colors.onError to colors.error,
            colors.onErrorContainer to colors.errorContainer,
        )
        textPairs.forEach { (foreground, background) ->
            assertTrue(
                contrast(foreground, background) >= 4.5,
                "Insufficient text contrast for $foreground on $background",
            )
        }
        assertTrue(contrast(colors.primary, colors.surfaceVariant) >= 3.0)
        assertTrue(contrast(ElChronoColors.Focus, colors.background) >= 3.0)
        assertTrue(contrast(ElChronoColors.Focus, colors.surfaceVariant) >= 3.0)
        assertEquals(ElChronoColors.Focus, colors.tertiary)
    }

    @Test
    fun typographyAndShapeTokensAreExplicit() {
        assertEquals(FontFamily.SansSerif, ElChronoTypography.bodyLarge.fontFamily)
        assertEquals(16.sp, ElChronoTypography.bodyLarge.fontSize)
        assertEquals(RoundedCornerShape(8.dp), ElChronoShapes.small)
        assertEquals(RoundedCornerShape(16.dp), ElChronoShapes.large)
    }

    @Test
    fun spacingTokensPreserveCurrentDimensions() {
        val dimens = Dimens()
        assertEquals(
            listOf(2.dp, 4.dp, 6.dp, 8.dp, 12.dp, 16.dp, 20.dp, 24.dp),
            listOf(
                dimens.spaceXXS,
                dimens.spaceXS,
                dimens.spaceS,
                dimens.spaceM,
                dimens.spaceL,
                dimens.spaceXL,
                dimens.spaceXXL,
                dimens.spaceXXXL,
            ),
        )
    }

    @Test
    fun navigationTransitionsReuseDirectionalPaneTransitions() {
        assertEquals(ElChronoMotion.enterFromLeft, ElChronoMotion.forward.targetContentEnter)
        assertEquals(ElChronoMotion.exitToRight, ElChronoMotion.forward.initialContentExit)
        assertEquals(ElChronoMotion.enterFromRight, ElChronoMotion.backward.targetContentEnter)
        assertEquals(ElChronoMotion.exitToLeft, ElChronoMotion.backward.initialContentExit)
    }

    private fun contrast(first: Color, second: Color): Double {
        val firstLuminance = first.luminance().toDouble()
        val secondLuminance = second.luminance().toDouble()
        return (maxOf(firstLuminance, secondLuminance) + 0.05) /
            (minOf(firstLuminance, secondLuminance) + 0.05)
    }
}
