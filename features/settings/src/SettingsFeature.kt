package nl.entreco.elchrono.features.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun SettingsFeature(
    visible: Boolean,
    initialSettings: TimerSettings,
    onSave: (TimerSettings) -> Unit,
    onShowAbout: () -> Unit,
) {
    var entry by remember(initialSettings) {
        mutableStateOf(DurationDigitEntry.fromDuration(initialSettings.duration))
    }
    var selectedTheme by remember(initialSettings) {
        mutableStateOf(initialSettings.theme)
    }
    if (!visible) return

    Column(
        modifier = Modifier.fillMaxWidth(),
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
                onValueChange = {},
                modifier = Modifier.weight(1f),
                readOnly = true,
                label = { Text(stringResource(Strings.string.hours)) },
                singleLine = true,
            )
            OutlinedTextField(
                value = entry.minutes.toString().padStart(2, '0'),
                onValueChange = {},
                modifier = Modifier.weight(1f),
                readOnly = true,
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
        DigitKeypad(
            onAppendDigit = { entry = entry.append(it) },
            onDeleteDigit = { entry = entry.deleteLast() },
            onClearDigits = { entry = entry.clear() },
        )
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

@Composable
private fun DigitKeypad(
    onAppendDigit: (Int) -> Unit,
    onDeleteDigit: () -> Unit,
    onClearDigits: () -> Unit,
) {
    val rows = listOf(
        listOf(Key.Digit(1), Key.Digit(2), Key.Digit(3)),
        listOf(Key.Digit(4), Key.Digit(5), Key.Digit(6)),
        listOf(Key.Digit(7), Key.Digit(8), Key.Digit(9)),
        listOf(Key.Clear, Key.Digit(0), Key.Delete),
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                row.forEach { key ->
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            when (key) {
                                is Key.Digit -> onAppendDigit(key.value)
                                Key.Clear -> onClearDigits()
                                Key.Delete -> onDeleteDigit()
                            }
                        },
                    ) {
                        Text(
                            when (key) {
                                is Key.Digit -> key.value.toString()
                                Key.Clear -> stringResource(Strings.string.clear)
                                Key.Delete -> stringResource(Strings.string.delete)
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

private val TimerTheme.label: StringResource
    get() = when (this) {
        TimerTheme.CLOCK -> Strings.string.clock
        TimerTheme.BATTERY -> Strings.string.battery
        TimerTheme.FAN -> Strings.string.fan
        TimerTheme.RING -> Strings.string.ring
    }
