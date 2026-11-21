package nl.entreco.elchrono.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import nl.entreco.elchrono.core.design.ChoicePicker
import nl.entreco.elchrono.core.design.ColorSwatchPicker
import nl.entreco.elchrono.core.design.DesignChoice
import nl.entreco.elchrono.core.design.DesignColorSwatch
import nl.entreco.elchrono.core.design.DesignPreviewCard
import nl.entreco.elchrono.core.design.DesignScreenHeader
import nl.entreco.elchrono.core.design.DesignStatusMessage
import nl.entreco.elchrono.core.design.DurationInput
import nl.entreco.elchrono.core.design.DurationValueField
import nl.entreco.elchrono.core.design.ElChronoTheme
import nl.entreco.elchrono.core.design.ElChronoColors
import nl.entreco.elchrono.core.design.LabeledSlider
import nl.entreco.elchrono.core.design.NavigationHint
import nl.entreco.elchrono.core.design.NavigationHintBar
import nl.entreco.elchrono.core.design.NumericKeypad
import nl.entreco.elchrono.core.design.SettingsControlRow

private val WidthChoices = listOf(
    DesignChoice(240.dp, "Compact (240 dp)"),
    DesignChoice(600.dp, "Wide (600 dp)"),
    DesignChoice(1000.dp, "Extra wide (1000 dp)"),
)

@Composable
fun DesignCatalog() {
    var previewWidth by remember { mutableStateOf(600.dp) }

    ElChronoTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text("Design catalog (development)", style = MaterialTheme.typography.headlineMedium)
                Text(
                    "Development-only examples. Resize the window or choose a preview width.",
                    style = MaterialTheme.typography.bodyMedium,
                )
                ChoicePicker(
                    choices = WidthChoices,
                    selected = previewWidth,
                    onSelected = { previewWidth = it },
                )
                ThemeExample(previewWidth)
                KeypadExample(previewWidth)
                ControlRowExample(previewWidth)
                DurationFieldExample(previewWidth)
                DurationInputExample(previewWidth)
                ChoicePickerExample(previewWidth)
                ColorPickerExample(previewWidth)
                SliderExample(previewWidth)
                HeaderExample(previewWidth)
                PreviewCardExample(previewWidth)
                StatusExample(previewWidth)
                HintsExample(previewWidth)
            }
        }
    }
}

@Composable
private fun Example(
    name: String,
    maxWidth: Dp,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(name, style = MaterialTheme.typography.titleMedium)
        Surface(
            modifier = Modifier.widthIn(max = maxWidth).fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                content()
            }
        }
    }
}

@Composable
private fun ThemeExample(width: Dp) {
    Example("ElChronoTheme", width) {
        Text("All catalog examples use ElChronoTheme.", style = MaterialTheme.typography.titleMedium)
        Text("Body text and supporting labels", style = MaterialTheme.typography.bodyMedium)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            ThemeSwatch("Background", ElChronoColors.Background)
            ThemeSwatch("Surface", ElChronoColors.Surface)
            ThemeSwatch("Selected", ElChronoColors.Selection)
            ThemeSwatch("Primary", ElChronoColors.Purple)
            ThemeSwatch("Focus", ElChronoColors.Focus)
            ThemeSwatch("Preview", ElChronoColors.Preview)
        }
        Surface(
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.primaryContainer,
        ) {
            Text("Small shape", Modifier.padding(12.dp))
        }
        Surface(
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.primaryContainer,
        ) {
            Text("Large shape", Modifier.padding(12.dp))
        }
    }
}

@Composable
private fun ThemeSwatch(name: String, color: Color) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Surface(
            modifier = Modifier.size(48.dp),
            color = color,
            shape = MaterialTheme.shapes.small,
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        ) {}
        Text(name, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
private fun KeypadExample(width: Dp) {
    var digits by remember { mutableStateOf("") }
    Example("NumericKeypad", width) {
        Text("Input: ${digits.ifEmpty { "—" }}")
        NumericKeypad(
            clearLabel = "Clear",
            deleteLabel = "Delete",
            onDigit = { if (digits.length < 8) digits += it.toString() },
            onClear = { digits = "" },
            onDelete = { digits = digits.dropLast(1) },
        )
    }
}

@Composable
private fun ControlRowExample(width: Dp) {
    var selected by remember { mutableStateOf("Ring") }
    Example("SettingsControlRow", width) {
        SettingsControlRow(label = "Shape") {
            ChoicePicker(
                choices = listOf(DesignChoice("Ring", "Ring"), DesignChoice("Fan", "Fan")),
                selected = selected,
                onSelected = { selected = it },
            )
        }
    }
}

@Composable
private fun DurationFieldExample(width: Dp) {
    var value by remember { mutableStateOf("25") }
    Example("DurationValueField", width) {
        DurationValueField(
            value = value,
            onValueChange = { value = it },
            label = "Minutes",
            unitLabel = "min",
        )
    }
}

@Composable
private fun DurationInputExample(width: Dp) {
    var hours by remember { mutableStateOf("00") }
    var minutes by remember { mutableStateOf("25") }
    Example("DurationInput", width) {
        DurationInput(
            hours = hours,
            onHoursChange = { hours = it },
            hoursLabel = "Hours",
            hoursUnitLabel = "hr",
            minutes = minutes,
            onMinutesChange = { minutes = it },
            minutesLabel = "Minutes",
            minutesUnitLabel = "min",
        )
    }
}

@Composable
private fun ChoicePickerExample(width: Dp) {
    var selected by remember { mutableStateOf("Rounded") }
    Example("ChoicePicker", width) {
        ChoicePicker(
            choices = listOf(
                DesignChoice("Sans", "Sans"),
                DesignChoice("Rounded", "Rounded"),
                DesignChoice("Clear", "Clear"),
            ),
            selected = selected,
            onSelected = { selected = it },
        )
        Text("Selected: $selected")
    }
}

@Composable
private fun ColorPickerExample(width: Dp) {
    var selected by remember { mutableStateOf("purple") }
    Example("ColorSwatchPicker", width) {
        ColorSwatchPicker(
            swatches = listOf(
                DesignColorSwatch("light", "Light", Color.LightGray),
                DesignColorSwatch("teal", "Teal", Color(0xFF167D6F)),
                DesignColorSwatch("purple", "Purple", Color(0xFF9933FF)),
                DesignColorSwatch("yellow", "Yellow", Color(0xFFFFC43D)),
            ),
            selectedId = selected,
            onSelected = { selected = it },
        )
        Text("Selected: $selected")
    }
}

@Composable
private fun SliderExample(width: Dp) {
    var value by remember { mutableFloatStateOf(0.6f) }
    Example("LabeledSlider", width) {
        LabeledSlider(
            label = "Details",
            value = value,
            valueLabel = "${(value * 100).toInt()}%",
            onValueChange = { value = it },
            minimumLabel = "Minimal",
            maximumLabel = "Full",
        )
    }
}

@Composable
private fun HeaderExample(width: Dp) {
    var backCount by remember { mutableIntStateOf(0) }
    Example("DesignScreenHeader", width) {
        DesignScreenHeader(
            brandLabel = "EL CHRONO",
            title = "Settings",
            backLabel = "Back",
            onBack = { backCount++ },
            supportingText = "Make time your own.",
        )
        Text("Back pressed: $backCount")
    }
}

@Composable
private fun PreviewCardExample(width: Dp) {
    Example("DesignPreviewCard", width) {
        DesignPreviewCard(
            title = "Preview",
            details = "Sample details",
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Sample content", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
private fun StatusExample(width: Dp) {
    Example("DesignStatusMessage", width) {
        DesignStatusMessage(
            message = "Sample status",
            leadingContent = { Text("✓") },
        )
    }
}

@Composable
private fun HintsExample(width: Dp) {
    Example("NavigationHintBar", width) {
        NavigationHintBar(
            hints = listOf(
                NavigationHint("← →", "Move focus"),
                NavigationHint("Enter", "Select"),
                NavigationHint("Esc", "Return"),
            ),
        )
    }
}
