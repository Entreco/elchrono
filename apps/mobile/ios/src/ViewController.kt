package nl.entreco.elchrono

import androidx.compose.ui.window.ComposeUIViewController
import nl.entreco.elchrono.di.initApp

fun ViewController() = run {
    initApp()
    ComposeUIViewController { App() }
}
