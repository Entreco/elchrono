package nl.entreco.elchrono.features.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Slider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import nl.entreco.elchrono.core.counter.DurationDigitEntry
import nl.entreco.elchrono.core.design.NumericKeypad
import nl.entreco.elchrono.core.design.DesignChoice
import nl.entreco.elchrono.core.design.ChoicePicker
import nl.entreco.elchrono.core.design.ColorSwatchPicker
import nl.entreco.elchrono.core.design.DesignColorSwatch
import nl.entreco.elchrono.core.design.LocalDimens
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.design.TimerFont
import nl.entreco.elchrono.core.persistence.TimerSettings
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.about
import nl.entreco.elchrono.core.localization.resources.battery
import nl.entreco.elchrono.core.localization.resources.clear
import nl.entreco.elchrono.core.localization.resources.clock
import nl.entreco.elchrono.core.localization.resources.delete
import nl.entreco.elchrono.core.localization.resources.background
import nl.entreco.elchrono.core.localization.resources.colors
import nl.entreco.elchrono.core.localization.resources.detail
import nl.entreco.elchrono.core.localization.resources.font
import nl.entreco.elchrono.core.localization.resources.foreground
import nl.entreco.elchrono.core.localization.resources.text
import nl.entreco.elchrono.core.localization.resources.duration
import nl.entreco.elchrono.core.localization.resources.duration_entry_help
import nl.entreco.elchrono.core.localization.resources.fan
import nl.entreco.elchrono.core.localization.resources.hours
import nl.entreco.elchrono.core.localization.resources.invalid_duration
import nl.entreco.elchrono.core.localization.resources.invalid_duration_including_zero
import nl.entreco.elchrono.core.localization.resources.minutes
import nl.entreco.elchrono.core.localization.resources.ring
import nl.entreco.elchrono.core.localization.resources.save_and_start
import nl.entreco.elchrono.core.localization.resources.selected_theme_format
import nl.entreco.elchrono.core.localization.resources.theme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsPanel(
    initialSettings: TimerSettings,
    onChange: (TimerSettings) -> Unit,
    onShowAbout: () -> Unit,
    keyboardInputEnabled: Boolean,
    keyboardAvailable: Boolean,
    persistenceFailure: String? = null,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 2.dp,
    ) {
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(LocalDimens.current.spaceXL),
        ) {
            SettingsScreen(
                initialSettings = initialSettings,
                onChange = onChange,
                onShowAbout = onShowAbout,
                keyboardInputEnabled = keyboardInputEnabled,
                keyboardAvailable = keyboardAvailable,
            )
            persistenceFailure?.let {
                Spacer(Modifier.height(LocalDimens.current.spaceM))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun SettingsScreen(
    initialSettings: TimerSettings,
    onChange: (TimerSettings) -> Unit,
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
    var backgroundColor by remember(initialSettings) { mutableStateOf(initialSettings.backgroundColorArgb) }
    var foregroundColor by remember(initialSettings) { mutableStateOf(initialSettings.foregroundColorArgb) }
    var textColor by remember(initialSettings) { mutableStateOf(initialSettings.textColorArgb) }
    var font by remember(initialSettings) { mutableStateOf(initialSettings.font) }
    var detail by remember(initialSettings) { mutableStateOf(initialSettings.detail) }

    fun publish(updatedEntry: DurationDigitEntry = entry) {
        updatedEntry.duration?.let {
            onChange(
                TimerSettings(
                    it,
                    selectedTheme,
                    backgroundColor,
                    foregroundColor,
                    textColor,
                    font,
                    detail,
                ),
            )
        }
    }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Strings.string.duration), style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(LocalDimens.current.spaceL))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(LocalDimens.current.spaceL),
        ) {
            OutlinedTextField(
                value = entry.hours.toString().padStart(2, '0'),
                onValueChange = { edited ->
                    entry = entry.applyTextEdit(
                        previousText = entry.hours.toString().padStart(2, '0'),
                        editedText = edited,
                    ).also(::publish)
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
                    ).also(::publish)
                },
                modifier = Modifier.weight(1f),
                readOnly = inputMode.fieldsReadOnly,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(stringResource(Strings.string.minutes)) },
                singleLine = true,
            )
        }
        Spacer(Modifier.height(LocalDimens.current.spaceM))
        Text(
            stringResource(Strings.string.duration_entry_help),
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(LocalDimens.current.spaceL))
        if (inputMode.showNumericKeypad) {
            NumericKeypad(
                clearLabel = stringResource(Strings.string.clear),
                deleteLabel = stringResource(Strings.string.delete),
                onDigit = { val updated = entry.append(it); entry = updated; publish(updated) },
                onDelete = { val updated = entry.deleteLast(); entry = updated; publish(updated) },
                onClear = { val updated = entry.clear(); entry = updated },
            )
        }
        if (entry.duration == null) {
            Spacer(Modifier.height(LocalDimens.current.spaceM))
            Text(
                text = stringResource(Strings.string.invalid_duration_including_zero),
                color = MaterialTheme.colorScheme.error,
            )
        }
        Spacer(Modifier.height(LocalDimens.current.spaceXXL))
        Text(stringResource(Strings.string.theme), style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(LocalDimens.current.spaceM))
        ChoicePicker(
            choices = TimerTheme.entries.map { theme ->
                DesignChoice(theme, stringResource(theme.label))
            },
            selected = selectedTheme,
            onSelected = { theme ->
                selectedTheme = theme
                publish()
            }
        )
        Spacer(Modifier.height(LocalDimens.current.spaceXL))
        Text(stringResource(Strings.string.colors), style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(LocalDimens.current.spaceM))
        val swatches = listOf(
            DesignColorSwatch("paper", "Paper", Color(0xFFF7F7F2)),
            DesignColorSwatch("ink", "Ink", Color(0xFF17171C)),
            DesignColorSwatch("blue", "Blue", Color(0xFF5B5BD6)),
            DesignColorSwatch("orange", "Orange", Color(0xFFE58A3A)),
            DesignColorSwatch("green", "Green", Color(0xFF3D7A57)),
            DesignColorSwatch("red", "Red", Color(0xFFC54A4A)),
        )
        Text(stringResource(Strings.string.background), style = MaterialTheme.typography.titleSmall)
        ColorSwatchPicker(swatches, swatches.firstOrNull { it.color.toArgb().toLong() and 0xffffffffL == backgroundColor }?.id ?: "", onSelected = { id ->
            val selected = swatches.first { it.id == id }
            backgroundColor = selected.color.toArgb().toLong() and 0xffffffffL
            publish()
        })
        Spacer(Modifier.height(LocalDimens.current.spaceM))
        Text(stringResource(Strings.string.foreground), style = MaterialTheme.typography.titleSmall)
        ColorSwatchPicker(swatches, swatches.firstOrNull { it.color.toArgb().toLong() and 0xffffffffL == foregroundColor }?.id ?: "", onSelected = { id ->
            val selected = swatches.first { it.id == id }
            foregroundColor = selected.color.toArgb().toLong() and 0xffffffffL
            publish()
        })
        Spacer(Modifier.height(LocalDimens.current.spaceM))
        Text(stringResource(Strings.string.text), style = MaterialTheme.typography.titleSmall)
        ColorSwatchPicker(swatches, swatches.firstOrNull { it.color.toArgb().toLong() and 0xffffffffL == textColor }?.id ?: "", onSelected = { id ->
            val selected = swatches.first { it.id == id }
            textColor = selected.color.toArgb().toLong() and 0xffffffffL
            publish()
        })
        Spacer(Modifier.height(LocalDimens.current.spaceXL))
        Text(stringResource(Strings.string.font), style = MaterialTheme.typography.titleLarge)
        ChoicePicker(
            choices = TimerFont.entries.map { fontChoice ->
                DesignChoice(fontChoice, fontChoice.name.lowercase().replaceFirstChar(Char::uppercase))
            },
            selected = font,
            onSelected = {
                font = it
                publish()
            },
        )
        Spacer(Modifier.height(LocalDimens.current.spaceXL))
        Text(stringResource(Strings.string.detail), style = MaterialTheme.typography.titleLarge)
        Slider(
            value = detail.toFloat(),
            onValueChange = {
                detail = it.toInt()
                publish()
            },
            valueRange = 0f..12f,
            steps = 11,
        )
        Text("$detail")
        Spacer(Modifier.height(LocalDimens.current.spaceL))
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
