package nl.entreco.elchrono.core.design

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ElChronoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ElChronoColorScheme,
        typography = ElChronoTypography,
        shapes = ElChronoShapes,
        content = content,
    )
}
