package nl.entreco.elchrono.catalog

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "El Chrono Design Catalog",
    ) {
        DesignCatalog()
    }
}
