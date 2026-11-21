package nl.entreco.elchrono.core.design

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import nl.entreco.elchrono.core.design.resources.Res
import nl.entreco.elchrono.core.design.resources.nunito
import org.jetbrains.compose.resources.Font

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
    SERIF,
}

@Composable
fun TimerFont.toFontFamily(): FontFamily = when (this) {
    TimerFont.DEFAULT -> FontFamily.Default
    TimerFont.ROUNDED -> FontFamily(Font(Res.font.nunito))
    TimerFont.MONOSPACE -> FontFamily.Monospace
    TimerFont.SERIF -> FontFamily.Serif
}
