package nl.entreco.elchrono

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalMediaQueryApi
import androidx.compose.ui.UiMediaScope
import androidx.compose.ui.mediaQuery

@OptIn(ExperimentalMediaQueryApi::class)
@Composable
actual fun isKeyboardAvailable(): Boolean =
    mediaQuery { keyboardKind != UiMediaScope.KeyboardKind.None }
