package nl.entreco.elchrono.core.design

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

object ElChronoColors {
    val Background = Color(0xFF151517)
    val Surface = Color(0xFF1D1D21)
    val SurfaceRaised = Color(0xFF212025)
    val Selection = Color(0xFF322A3B)
    val Purple = Color(0xFFBB94F1)
    val Focus = Color(0xFFFCB843)
    val Preview = Color(0xFF072A2E)
    val Text = Color(0xFFF1F1F1)
    val TextMuted = Color(0xFFB5B5B6)
    val Outline = Color(0xFF77717D)
}

val ElChronoColorScheme = darkColorScheme(
    primary = ElChronoColors.Purple,
    onPrimary = Color(0xFF25113D),
    primaryContainer = ElChronoColors.Selection,
    onPrimaryContainer = ElChronoColors.Text,
    secondary = Color(0xFFD1BCE8),
    onSecondary = Color(0xFF352A40),
    secondaryContainer = ElChronoColors.Selection,
    onSecondaryContainer = ElChronoColors.Text,
    tertiary = ElChronoColors.Focus,
    onTertiary = Color(0xFF402A00),
    tertiaryContainer = Color(0xFF574015),
    onTertiaryContainer = Color(0xFFFFDEA1),
    background = ElChronoColors.Background,
    onBackground = ElChronoColors.Text,
    surface = ElChronoColors.Background,
    onSurface = ElChronoColors.Text,
    surfaceVariant = ElChronoColors.Surface,
    onSurfaceVariant = ElChronoColors.TextMuted,
    surfaceContainerLowest = ElChronoColors.Background,
    surfaceContainerLow = ElChronoColors.Background,
    surfaceContainer = ElChronoColors.Surface,
    surfaceContainerHigh = ElChronoColors.SurfaceRaised,
    surfaceContainerHighest = ElChronoColors.Selection,
    outline = ElChronoColors.Outline,
    outlineVariant = Color(0xFF514D56),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    inverseSurface = ElChronoColors.Text,
    inverseOnSurface = ElChronoColors.Background,
    inversePrimary = Color(0xFF68449B),
    scrim = Color.Black,
)

fun appColorScheme(background: Color, textColor: Color? = null) =
    (if (background.luminance() > 0.179f) lightColorScheme(
        primary = Color(0xFF5B5BD6),
        secondary = Color(0xFF57466F),
        tertiary = Color(0xFF765400),
    ) else ElChronoColorScheme).copy(
        background = background,
        surface = background,
        onBackground = textColor ?: if (background.luminance() > 0.179f) Color.Black else Color.White,
        onSurface = textColor ?: if (background.luminance() > 0.179f) Color.Black else Color.White,
        onSurfaceVariant = textColor ?: if (background.luminance() > 0.179f) Color.Black else Color.White,
    )
