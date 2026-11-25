package nl.entreco.elchrono

import androidx.compose.ui.window.ComposeUIViewController
import nl.entreco.elchrono.di.initApp
import nl.entreco.elchrono.di.startupBackgroundArgb
import platform.UIKit.UIColor

fun ViewController() = run {
    initApp()
    val argb = startupBackgroundArgb()
    ComposeUIViewController { App() }.apply {
        view.backgroundColor = UIColor(
            red = ((argb shr 16) and 255).toDouble() / 255.0,
            green = ((argb shr 8) and 255).toDouble() / 255.0,
            blue = (argb and 255).toDouble() / 255.0,
            alpha = ((argb shr 24) and 255).toDouble() / 255.0,
        )
    }
}
