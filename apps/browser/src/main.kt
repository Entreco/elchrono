package nl.entreco.elchrono

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import nl.entreco.elchrono.di.initApp
import nl.entreco.elchrono.di.startupBackgroundArgb

@OptIn(kotlin.js.ExperimentalWasmJsInterop::class)
@JsFun("(color) => { document.body.style.backgroundColor = color; }")
private external fun setLoadingBackground(color: String)

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initApp()
    val argb = startupBackgroundArgb()
    setLoadingBackground(
        "rgba(${(argb shr 16) and 255}, ${(argb shr 8) and 255}, ${argb and 255}, ${((argb shr 24) and 255).toDouble() / 255.0})",
    )
    ComposeViewport {
        App()
    }
}
