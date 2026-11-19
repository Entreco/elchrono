package nl.entreco.elchrono

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import nl.entreco.elchrono.desktop.resources.Res
import nl.entreco.elchrono.desktop.resources.app_icon
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ElChrono",
        icon = painterResource(Res.drawable.app_icon),
    ) {
        App()
    }
}
