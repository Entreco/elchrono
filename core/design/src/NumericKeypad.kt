package nl.entreco.elchrono.core.design

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NumericKeypad(
    clearLabel: String,
    deleteLabel: String,
    onDigit: (Int) -> Unit,
    onClear: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        KEYPAD_ROWS.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                row.forEach { key ->
                    Button(
                        modifier = Modifier.weight(1f).heightIn(min = 48.dp),
                        onClick = {
                            when (key) {
                                is Key.Digit -> onDigit(key.value)
                                Key.Clear -> onClear()
                                Key.Delete -> onDelete()
                            }
                        },
                    ) {
                        Text(
                            when (key) {
                                is Key.Digit -> key.value.toString()
                                Key.Clear -> clearLabel
                                Key.Delete -> deleteLabel
                            },
                        )
                    }
                }
            }
        }
    }
}

private sealed interface Key {
    data class Digit(val value: Int) : Key
    data object Clear : Key
    data object Delete : Key
}

private val KEYPAD_ROWS = listOf(
    listOf(Key.Digit(1), Key.Digit(2), Key.Digit(3)),
    listOf(Key.Digit(4), Key.Digit(5), Key.Digit(6)),
    listOf(Key.Digit(7), Key.Digit(8), Key.Digit(9)),
    listOf(Key.Clear, Key.Digit(0), Key.Delete),
)
