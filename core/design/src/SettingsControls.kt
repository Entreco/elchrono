package nl.entreco.elchrono.core.design

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FilterChip
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SettingsControlRow(
    label: String,
    modifier: Modifier = Modifier,
    stackBelow: Dp = 360.dp,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(modifier.fillMaxWidth()) {
        if (maxWidth < stackBelow) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(label, style = MaterialTheme.typography.titleSmall)
                Box(Modifier.fillMaxWidth()) {
                    content()
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    label,
                    modifier = Modifier.widthIn(min = 112.dp, max = 168.dp),
                    style = MaterialTheme.typography.titleSmall,
                )
                Box(Modifier.weight(1f)) {
                    content()
                }
            }
        }
    }
}

@Composable
fun DurationValueField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    unitLabel: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(label, style = MaterialTheme.typography.labelMedium)
        Spacer(Modifier.size(4.dp))
        Surface(
            modifier = Modifier.fillMaxWidth().elChronoFocusOutline(MaterialTheme.shapes.small),
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.surfaceVariant,
            border = androidx.compose.foundation.BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.outlineVariant,
            ),
        ) {
            Row(
                modifier = Modifier.heightIn(min = 48.dp).padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .weight(1f)
                        .semantics { contentDescription = label },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
                )
                Spacer(Modifier.width(8.dp))
                Text(unitLabel, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

@Composable
fun DurationInput(
    hours: String,
    onHoursChange: (String) -> Unit,
    hoursLabel: String,
    hoursUnitLabel: String,
    minutes: String,
    onMinutesChange: (String) -> Unit,
    minutesLabel: String,
    minutesUnitLabel: String,
    modifier: Modifier = Modifier,
    stackBelow: Dp = 220.dp,
) {
    BoxWithConstraints(modifier.fillMaxWidth()) {
        val fieldModifier = Modifier.fillMaxWidth()
        if (maxWidth < stackBelow) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                DurationValueField(hours, onHoursChange, hoursLabel, hoursUnitLabel, fieldModifier)
                DurationValueField(minutes, onMinutesChange, minutesLabel, minutesUnitLabel, fieldModifier)
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                DurationValueField(
                    hours,
                    onHoursChange,
                    hoursLabel,
                    hoursUnitLabel,
                    Modifier.weight(1f),
                )
                DurationValueField(
                    minutes,
                    onMinutesChange,
                    minutesLabel,
                    minutesUnitLabel,
                    Modifier.weight(1f),
                )
            }
        }
    }
}

data class DesignChoice<T>(
    val value: T,
    val label: String,
)

@Composable
fun <T> ChoicePicker(
    choices: List<DesignChoice<T>>,
    selected: T,
    onSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        choices.forEach { choice ->
            FilterChip(
                modifier = Modifier
                    .defaultMinSize(minHeight = 48.dp)
                    .elChronoFocusOutline(MaterialTheme.shapes.small),
                selected = choice.value == selected,
                onClick = { onSelected(choice.value) },
                label = { Text(choice.label) },
            )
        }
    }
}

data class DesignColorSwatch(
    val id: String,
    val label: String,
    val color: Color,
)

@Composable
fun ColorSwatchPicker(
    swatches: List<DesignColorSwatch>,
    selectedId: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        swatches.forEach { swatch ->
            val selected = swatch.id == selectedId
            IconToggleButton(
                checked = selected,
                onCheckedChange = { checked -> if (checked) onSelected(swatch.id) },
                modifier = Modifier
                    .size(48.dp)
                    .elChronoFocusOutline(CircleShape)
                    .semantics { contentDescription = swatch.label },
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(swatch.color, CircleShape)
                        .border(
                            width = if (selected) 2.dp else 1.dp,
                            color = if (selected) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.outline
                            },
                            shape = CircleShape,
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    if (selected) {
                        Text(
                            text = "✓",
                            color = if (swatch.color.luminance() > 0.179f) Color.Black else Color.White,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LabeledSlider(
    label: String,
    value: Float,
    valueLabel: String,
    onValueChange: (Float) -> Unit,
    minimumLabel: String,
    maximumLabel: String,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
) {
    Column(modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(label, style = MaterialTheme.typography.labelMedium)
            Text(valueLabel, style = MaterialTheme.typography.labelMedium)
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .elChronoFocusOutline(MaterialTheme.shapes.small)
                .semantics { contentDescription = label },
            valueRange = valueRange,
            steps = steps,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(minimumLabel, style = MaterialTheme.typography.labelSmall)
            Text(maximumLabel, style = MaterialTheme.typography.labelSmall)
        }
    }
}
