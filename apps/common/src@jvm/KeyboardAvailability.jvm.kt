package nl.entreco.elchrono

import androidx.compose.runtime.Composable

@Composable
// Compose Desktop does not provide LocalUiMediaScope in the pinned version.
actual fun isKeyboardAvailable(): Boolean = true
