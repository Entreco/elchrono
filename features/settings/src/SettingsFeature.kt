package nl.entreco.elchrono.features.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.entreco.elchrono.core.counter.DurationDigitEntry
import nl.entreco.elchrono.core.design.NumericKeypad
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.about
import nl.entreco.elchrono.core.localization.resources.battery
import nl.entreco.elchrono.core.localization.resources.clear
import nl.entreco.elchrono.core.localization.resources.clock
import nl.entreco.elchrono.core.localization.resources.delete
import nl.entreco.elchrono.core.localization.resources.duration
import nl.entreco.elchrono.core.localization.resources.duration_entry_help
import nl.entreco.elchrono.core.localization.resources.fan
import nl.entreco.elchrono.core.localization.resources.hours
import nl.entreco.elchrono.core.localization.resources.invalid_duration
import nl.entreco.elchrono.core.localization.resources.minutes
import nl.entreco.elchrono.core.localization.resources.ring
import nl.entreco.elchrono.core.localization.resources.save_and_start
import nl.entreco.elchrono.core.localization.resources.selected_theme_format
import nl.entreco.elchrono.core.localization.resources.theme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsScreen(
    initialSettings: TimerSettings,
    onSave: (TimerSettings) -> Unit,
    onShowAbout: () -> Unit,
    keyboardInputEnabled: Boolean,
    keyboardAvailable: Boolean,
    modifier: Modifier = Modifier,
) {
    val inputMode = DurationEntryInputMode(
        keyboardInputEnabled = keyboardInputEnabled,
        keyboardAvailable = keyboardAvailable,
    )
    var entry by remember(initialSettings) {
        mutableStateOf(DurationDigitEntry.fromDuration(initialSettings.duration))
    }
    var selectedTheme by remember(initialSettings) {
        mutableStateOf(initialSettings.theme)
    }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Strings.string.duration), style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            OutlinedTextField(
                value = entry.hours.toString().padStart(2, '0'),
                onValueChange = { edited ->
                    entry = entry.applyTextEdit(
                        previousText = entry.hours.toString().padStart(2, '0'),
                        editedText = edited,
                    )
                },
                modifier = Modifier.weight(1f),
                readOnly = inputMode.fieldsReadOnly,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(stringResource(Strings.string.hours)) },
                singleLine = true,
            )
            OutlinedTextField(
                value = entry.minutes.toString().padStart(2, '0'),
                onValueChange = { edited ->
                    entry = entry.applyTextEdit(
                        previousText = entry.minutes.toString().padStart(2, '0'),
                        editedText = edited,
                    )
                },
                modifier = Modifier.weight(1f),
                readOnly = inputMode.fieldsReadOnly,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(stringResource(Strings.string.minutes)) },
                singleLine = true,
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            stringResource(Strings.string.duration_entry_help),
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(12.dp))
        if (inputMode.showNumericKeypad) {
            NumericKeypad(
                clearLabel = stringResource(Strings.string.clear),
                deleteLabel = stringResource(Strings.string.delete),
                onDigit = { entry = entry.append(it) },
                onDelete = { entry = entry.deleteLast() },
                onClear = { entry = entry.clear() },
            )
        }
        if (entry.duration == null) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(Strings.string.invalid_duration),
                color = MaterialTheme.colorScheme.error,
            )
        }
        Spacer(Modifier.height(20.dp))
        Text(stringResource(Strings.string.theme), style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        TimerTheme.entries.forEach { theme ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                TextButton(onClick = { selectedTheme = theme }) {
                    val label = stringResource(theme.label)
                    val display = if (theme == selectedTheme) {
                        stringResource(Strings.string.selected_theme_format, label)
                    } else {
                        label
                    }
                    Text(display, fontSize = 18.sp)
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = {
                entry.duration?.let { onSave(TimerSettings(it, selectedTheme)) }
            },
            enabled = entry.duration != null,
        ) {
            Text(stringResource(Strings.string.save_and_start))
        }
        TextButton(onClick = onShowAbout) {
            Text(stringResource(Strings.string.about))
        }
    }
}

private val TimerTheme.label: StringResource
    get() = when (this) {
        TimerTheme.CLOCK -> Strings.string.clock
        TimerTheme.BATTERY -> Strings.string.battery
        TimerTheme.FAN -> Strings.string.fan
        TimerTheme.RING -> Strings.string.ring
    }
