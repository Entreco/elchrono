package nl.entreco.elchrono.core.design

import androidx.compose.ui.text.font.FontFamily

enum class TimerTheme {
    CLOCK,
    BATTERY,
    FAN,
    RING,
}

enum class TimerFont {
    DEFAULT,
    ROUNDED,
    MONOSPACE,
}

fun TimerFont.toFontFamily(): FontFamily = when (this) {
    TimerFont.DEFAULT -> FontFamily.Default
    TimerFont.ROUNDED -> FontFamily.SansSerif
    TimerFont.MONOSPACE -> FontFamily.Monospace
}
