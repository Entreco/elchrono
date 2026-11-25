package nl.entreco.elchrono

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import nl.entreco.elchrono.desktop.resources.Res
import nl.entreco.elchrono.desktop.resources.app_icon
import org.jetbrains.compose.resources.painterResource
import nl.entreco.elchrono.di.initApp
import nl.entreco.elchrono.di.startupBackgroundArgb

fun main() {
    initApp()
    val backgroundArgb = startupBackgroundArgb()
    application {
        var visible by remember { mutableStateOf(false) }
        Window(
            onCloseRequest = ::exitApplication,
            title = "El Chrono",
            icon = painterResource(Res.drawable.app_icon),
            visible = visible,
        ) {
            SideEffect {
                if (!visible) {
                    window.background = java.awt.Color(backgroundArgb.toInt())
                    visible = true
                }
            }
            App(onBackgroundChanged = { window.background = java.awt.Color(it.toInt()) })
        }
    }
}
