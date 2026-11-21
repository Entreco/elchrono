package nl.entreco.elchrono.core.design

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith

object ElChronoMotion {
    val enterFromLeft: EnterTransition = slideInHorizontally { -it }
    val exitToLeft: ExitTransition = slideOutHorizontally { -it }
    val enterFromRight: EnterTransition = slideInHorizontally { it }
    val exitToRight: ExitTransition = slideOutHorizontally { it }

    val fade = fadeIn() togetherWith fadeOut()
    val forward = enterFromLeft togetherWith exitToRight
    val backward = enterFromRight togetherWith exitToLeft
}
