package nl.entreco.elchrono

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import nl.entreco.elchrono.di.initApp

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initApp()
    ComposeViewport {
        App()
    }
}
