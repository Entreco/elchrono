package nl.entreco.elchrono.features.settings

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

internal data class SettingsAccentColors(
    val foreground: Color,
    val onForeground: Color,
)

internal fun settingsAccentColors(foreground: Color): SettingsAccentColors =
    SettingsAccentColors(
        foreground = foreground,
        onForeground = if (foreground.luminance() > 0.179f) Color.Black else Color.White,
    )
