package nl.entreco.elchrono.core.design

import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

fun Modifier.elChronoFocusOutline(shape: Shape): Modifier = composed {
    var hasFocus by remember { mutableStateOf(false) }
    val focusColor = MaterialTheme.colorScheme.tertiary
    onFocusChanged { hasFocus = it.hasFocus }
        .then(if (hasFocus) Modifier.border(2.dp, focusColor, shape) else Modifier)
}
