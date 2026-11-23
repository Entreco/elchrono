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
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import nl.entreco.elchrono.core.counter.DurationDigitEntry
import nl.entreco.elchrono.core.design.NumericKeypad
import nl.entreco.elchrono.core.design.DesignChoice
import nl.entreco.elchrono.core.design.elChronoFocusOutline
import nl.entreco.elchrono.core.design.ChoicePicker
import nl.entreco.elchrono.core.design.ColorSwatchPicker
import nl.entreco.elchrono.core.design.DesignColorSwatch
import nl.entreco.elchrono.core.design.LocalDimens
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.design.TimerFont
import nl.entreco.elchrono.core.design.appColorScheme
import nl.entreco.elchrono.core.design.LabeledSlider
import nl.entreco.elchrono.core.design.toFontFamily
import nl.entreco.elchrono.core.design.withFontFamily
import kotlin.math.roundToInt
import nl.entreco.elchrono.core.localization.resources.ring_thickness
import nl.entreco.elchrono.core.localization.resources.ring_thickness_value
import nl.entreco.elchrono.core.localization.resources.ring_thin
import nl.entreco.elchrono.core.localization.resources.ring_solid
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
import nl.entreco.elchrono.core.localization.resources.font_default
import nl.entreco.elchrono.core.localization.resources.font_rounded
import nl.entreco.elchrono.core.localization.resources.font_monospace
import nl.entreco.elchrono.core.localization.resources.font_serif
import nl.entreco.elchrono.core.localization.resources.color_paper
import nl.entreco.elchrono.core.localization.resources.color_ink
import nl.entreco.elchrono.core.localization.resources.color_blue
import nl.entreco.elchrono.core.localization.resources.color_orange
import nl.entreco.elchrono.core.localization.resources.color_green
import nl.entreco.elchrono.core.localization.resources.color_red
import nl.entreco.elchrono.core.localization.resources.color_white
import nl.entreco.elchrono.core.localization.resources.color_black
import nl.entreco.elchrono.core.localization.resources.color_gray
import nl.entreco.elchrono.core.localization.resources.color_yellow
import nl.entreco.elchrono.core.localization.resources.color_lime
import nl.entreco.elchrono.core.localization.resources.color_teal
import nl.entreco.elchrono.core.localization.resources.color_cyan
import nl.entreco.elchrono.core.localization.resources.color_navy
import nl.entreco.elchrono.core.localization.resources.color_purple
import nl.entreco.elchrono.core.localization.resources.color_pink
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
    SettingsAppearanceTheme(initialSettings) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .then(Modifier.focusProperties { canFocus = false } )
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
                    Surface(
                        color = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer,
                        shape = MaterialTheme.shapes.small,
                    ) {
                        Text(it, Modifier.padding(LocalDimens.current.spaceM))
                    }
                }
            }
        }
    }
}
@Composable
internal fun SettingsAppearanceTheme(settings: TimerSettings, content: @Composable () -> Unit) {
    val backgroundColor = Color(settings.backgroundColorArgb)
    val accent = settingsAccentColors(Color(settings.foregroundColorArgb))
    MaterialTheme(
        colorScheme = appColorScheme(
            background = backgroundColor,
            textColor = Color(settings.textColorArgb),
        ).copy(
            primary = accent.foreground,
            onPrimary = accent.onForeground,
            secondaryContainer = accent.foreground.copy(alpha = 0.2f).compositeOver(backgroundColor),
            onSecondaryContainer = Color(settings.textColorArgb),
        ),
        typography = MaterialTheme.typography.withFontFamily(settings.font.toFontFamily()),
        content = content,
    )
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
    var entry by rememberSaveable(stateSaver = DurationEntrySaver) {
        mutableStateOf(DurationDigitEntry.fromDuration(initialSettings.duration))
    }
    val accent = settingsAccentColors(Color(initialSettings.foregroundColorArgb))
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = accent.foreground,
        focusedLabelColor = accent.foreground,
        cursorColor = accent.foreground,
        selectionColors = TextSelectionColors(
            handleColor = accent.foreground,
            backgroundColor = accent.foreground.copy(alpha = 0.4f),
        ),
    )

    fun publish(updatedEntry: DurationDigitEntry) {
        updatedEntry.duration?.let { onChange(initialSettings.copy(duration = it)) }
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
                    ).also { publish(it) }
                },
                modifier = Modifier.weight(1f)
                    .then(Modifier.focusProperties { canFocus = true }),
                readOnly = inputMode.fieldsReadOnly,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(stringResource(Strings.string.hours)) },
                colors = fieldColors,
                singleLine = true,
            )
            OutlinedTextField(
                value = entry.minutes.toString().padStart(2, '0'),
                onValueChange = { edited ->
                    entry = entry.applyTextEdit(
                        previousText = entry.minutes.toString().padStart(2, '0'),
                        editedText = edited,
                    ).also { publish(it) }
                },
                modifier = Modifier.weight(1f)
                    .then(Modifier.focusProperties { canFocus = true }),
                readOnly = inputMode.fieldsReadOnly,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(stringResource(Strings.string.minutes)) },
                colors = fieldColors,
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
                onClear = { val updated = entry.clear(); entry = updated; publish(updated) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = accent.foreground,
                    contentColor = accent.onForeground,
                ),
            )
        }
        if (entry.duration == null) {
            Spacer(Modifier.height(LocalDimens.current.spaceM))
            Surface(
                color = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
                shape = MaterialTheme.shapes.small,
            ) {
                Text(
                    text = stringResource(Strings.string.invalid_duration_including_zero),
                    modifier = Modifier.padding(LocalDimens.current.spaceM),
                )
            }
        }
        Spacer(Modifier.height(LocalDimens.current.spaceXXL))
        Text(stringResource(Strings.string.theme), style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(LocalDimens.current.spaceM))
        ChoicePicker(
            choices = TimerTheme.entries.map { theme ->
                DesignChoice(theme, stringResource(theme.label))
            },
            selected = initialSettings.theme,
            onSelected = { onChange(initialSettings.copy(theme = it)) },
        )
        if (initialSettings.theme == TimerTheme.RING) {
            Spacer(Modifier.height(LocalDimens.current.spaceXL))
            LabeledSlider(
                label = stringResource(Strings.string.ring_thickness),
                value = initialSettings.ringThicknessPercent.toFloat(),
                valueLabel = stringResource(Strings.string.ring_thickness_value, initialSettings.ringThicknessPercent),
                onValueChange = { onChange(initialSettings.copy(ringThicknessPercent = it.roundToInt())) },
                minimumLabel = stringResource(Strings.string.ring_thin),
                maximumLabel = stringResource(Strings.string.ring_solid),
                valueRange = 1f..100f,
                steps = 98,
                showTicks = false,
            )
        }
        Spacer(Modifier.height(LocalDimens.current.spaceXL))
        Text(stringResource(Strings.string.colors), style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(LocalDimens.current.spaceM))
        val swatches = settingsColorPresets.map {
            DesignColorSwatch(it.id, stringResource(it.label), Color(it.argb))
        }
        Text(stringResource(Strings.string.background), style = MaterialTheme.typography.titleSmall)
        ColorSwatchPicker(swatches, swatches.firstOrNull { it.color.toArgb().toLong() and 0xffffffffL == initialSettings.backgroundColorArgb }?.id ?: "", onSelected = { id ->
            val selected = swatches.first { it.id == id }
            onChange(initialSettings.copy(backgroundColorArgb = selected.color.toArgb().toLong() and 0xffffffffL))
        })
        Spacer(Modifier.height(LocalDimens.current.spaceM))
        Text(stringResource(Strings.string.foreground), style = MaterialTheme.typography.titleSmall)
        ColorSwatchPicker(swatches, swatches.firstOrNull { it.color.toArgb().toLong() and 0xffffffffL == initialSettings.foregroundColorArgb }?.id ?: "", onSelected = { id ->
            val selected = swatches.first { it.id == id }
            onChange(initialSettings.copy(foregroundColorArgb = selected.color.toArgb().toLong() and 0xffffffffL))
        })
        Spacer(Modifier.height(LocalDimens.current.spaceM))
        Text(stringResource(Strings.string.text), style = MaterialTheme.typography.titleSmall)
        ColorSwatchPicker(swatches, swatches.firstOrNull { it.color.toArgb().toLong() and 0xffffffffL == initialSettings.textColorArgb }?.id ?: "", onSelected = { id ->
            val selected = swatches.first { it.id == id }
            onChange(initialSettings.copy(textColorArgb = selected.color.toArgb().toLong() and 0xffffffffL))
        })
        Spacer(Modifier.height(LocalDimens.current.spaceXL))
        Text(stringResource(Strings.string.font), style = MaterialTheme.typography.titleLarge)
        ChoicePicker(
            choices = TimerFont.entries.map { fontChoice ->
                DesignChoice(fontChoice, stringResource(fontChoice.label))
            },
            selected = initialSettings.font,
            onSelected = { onChange(initialSettings.copy(font = it)) },
        )
        Spacer(Modifier.height(LocalDimens.current.spaceXL))
        val detailLabel = stringResource(Strings.string.detail)
        Text(detailLabel, style = MaterialTheme.typography.titleLarge)
        val detailColors = SliderDefaults.colors(
            thumbColor = accent.foreground,
            activeTrackColor = accent.foreground,
            activeTickColor = accent.onForeground,
        )
        Slider(
            value = initialSettings.detail.toFloat(),
            onValueChange = { onChange(initialSettings.copy(detail = it.roundToInt())) },
            modifier = Modifier
                .elChronoFocusOutline(MaterialTheme.shapes.small)
                .semantics { contentDescription = detailLabel },
            valueRange = 0f..12f,
            steps = 11,
            colors = detailColors,
            track = { state ->
                if (initialSettings.detail == 0) {
                    SliderDefaults.Track(
                        sliderState = state,
                        colors = detailColors,
                        drawStopIndicator = null,
                        drawTick = { _, _ -> },
                    )
                } else {
                    SliderDefaults.Track(sliderState = state, colors = detailColors)
                }
            },
        )
        Text("${initialSettings.detail}")
        Spacer(Modifier.height(LocalDimens.current.spaceL))
        TextButton(
            onClick = onShowAbout,
            modifier = Modifier.elChronoFocusOutline(MaterialTheme.shapes.small),
        ) {
            Text(stringResource(Strings.string.about))
        }

    }
}

private val DurationEntrySaver = Saver<DurationDigitEntry, String>(
    save = { it.hours.toString().padStart(2, '0') + it.minutes.toString().padStart(2, '0') },
    restore = { DurationDigitEntry.empty().applyTextEdit("", it) },
)

private val TimerTheme.label: StringResource
    get() = when (this) {
        TimerTheme.CLOCK -> Strings.string.clock
        TimerTheme.BATTERY -> Strings.string.battery
        TimerTheme.FAN -> Strings.string.fan
        TimerTheme.RING -> Strings.string.ring
    }

private val TimerFont.label: StringResource
    get() = when (this) {
        TimerFont.DEFAULT -> Strings.string.font_default
        TimerFont.ROUNDED -> Strings.string.font_rounded
        TimerFont.MONOSPACE -> Strings.string.font_monospace
        TimerFont.SERIF -> Strings.string.font_serif
    }

internal data class SettingsColorPreset(val id: String, val label: StringResource, val argb: Long)

internal val settingsColorPresets = listOf(
    // Neutrals from light to dark, then colors around the hue wheel.
    SettingsColorPreset("white", Strings.string.color_white, 0xFFFFFFFF),
    SettingsColorPreset("paper", Strings.string.color_paper, 0xFFF7F7F2),
    SettingsColorPreset("gray", Strings.string.color_gray, 0xFF808080),
    SettingsColorPreset("ink", Strings.string.color_ink, 0xFF17171C),
    SettingsColorPreset("black", Strings.string.color_black, 0xFF000000),
    SettingsColorPreset("yellow", Strings.string.color_yellow, 0xFFF2C94C),
    SettingsColorPreset("orange", Strings.string.color_orange, 0xFFE58A3A),
    SettingsColorPreset("red", Strings.string.color_red, 0xFFC54A4A),
    SettingsColorPreset("pink", Strings.string.color_pink, 0xFFD66C98),
    SettingsColorPreset("purple", Strings.string.color_purple, 0xFF9055B5),
    SettingsColorPreset("navy", Strings.string.color_navy, 0xFF273C75),
    SettingsColorPreset("blue", Strings.string.color_blue, 0xFF5B5BD6),
    SettingsColorPreset("cyan", Strings.string.color_cyan, 0xFF45B8D1),
    SettingsColorPreset("teal", Strings.string.color_teal, 0xFF168A86),
    SettingsColorPreset("green", Strings.string.color_green, 0xFF3D7A57),
    SettingsColorPreset("lime", Strings.string.color_lime, 0xFF8CBF3F),
)
