package nl.entreco.elchrono.core.design

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimens(
    val spaceXXS: Dp = 2.dp,
    val spaceXS: Dp = 4.dp,
    val spaceS: Dp = 6.dp,
    val spaceM: Dp = 8.dp,
    val spaceL: Dp = 12.dp,
    val spaceXL: Dp = 16.dp,
    val spaceXXL: Dp = 20.dp,
    val spaceXXXL: Dp = 24.dp,
)

val LocalDimens = staticCompositionLocalOf { Dimens() }
