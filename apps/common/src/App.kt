package nl.entreco.elchrono

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlin.time.Duration
import nl.entreco.elchrono.core.design.ElChronoTheme
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.about
import nl.entreco.elchrono.core.localization.resources.app_title
import nl.entreco.elchrono.core.localization.resources.battery
import nl.entreco.elchrono.core.localization.resources.brand_wordmark
import nl.entreco.elchrono.core.localization.resources.choose_duration_message
import nl.entreco.elchrono.core.localization.resources.clear
import nl.entreco.elchrono.core.localization.resources.clock
import nl.entreco.elchrono.core.localization.resources.company_logo_gap
import nl.entreco.elchrono.core.localization.resources.delete
import nl.entreco.elchrono.core.localization.resources.duration
import nl.entreco.elchrono.core.localization.resources.duration_entry_help
import nl.entreco.elchrono.core.localization.resources.fan
import nl.entreco.elchrono.core.localization.resources.hours
import nl.entreco.elchrono.core.localization.resources.intended_use_heading
import nl.entreco.elchrono.core.localization.resources.intended_use_summary
import nl.entreco.elchrono.core.localization.resources.interruption_warning
import nl.entreco.elchrono.core.localization.resources.invalid_duration
import nl.entreco.elchrono.core.localization.resources.licence_information_gap
import nl.entreco.elchrono.core.localization.resources.limitations_heading
import nl.entreco.elchrono.core.localization.resources.limitations_summary
import nl.entreco.elchrono.core.localization.resources.main
import nl.entreco.elchrono.core.localization.resources.minutes
import nl.entreco.elchrono.core.localization.resources.open_settings
import nl.entreco.elchrono.core.localization.resources.privacy_policy_gap
import nl.entreco.elchrono.core.localization.resources.remaining_time
import nl.entreco.elchrono.core.localization.resources.remaining_time_accessibility
import nl.entreco.elchrono.core.localization.resources.ring
import nl.entreco.elchrono.core.localization.resources.save_and_start
import nl.entreco.elchrono.core.localization.resources.selected_theme_format
import nl.entreco.elchrono.core.localization.resources.settings
import nl.entreco.elchrono.core.localization.resources.support_information_gap
import nl.entreco.elchrono.core.localization.resources.theme
import nl.entreco.elchrono.core.localization.resources.timer_interrupted
import nl.entreco.elchrono.core.localization.resources.timer_not_configured
import nl.entreco.elchrono.core.localization.resources.timer_paused
import nl.entreco.elchrono.core.localization.resources.timer_running
import nl.entreco.elchrono.core.localization.resources.version_build_gap
import nl.entreco.elchrono.features.timer.TimerDuration
import nl.entreco.elchrono.features.timer.TimerEngine
import nl.entreco.elchrono.features.timer.TimerSnapshot
import nl.entreco.elchrono.features.timer.TimerStatus
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

private enum class Screen {
    MAIN,
    SETTINGS,
    ABOUT,
}

private enum class TimerTheme(val label: StringResource) {
    CLOCK(Strings.string.clock),
    BATTERY(Strings.string.battery),
    FAN(Strings.string.fan),
    RING(Strings.string.ring),
}

@Composable
@Preview
fun App() {
    ElChronoTheme {
        val engine = remember { TimerEngine() }
        var update by remember { mutableStateOf(engine.current()) }
        var interrupted by remember { mutableStateOf(false) }
        var screen by remember { mutableStateOf(Screen.MAIN) }
        var selectedTheme by remember { mutableStateOf(TimerTheme.CLOCK) }
        var pendingTheme by remember { mutableStateOf(TimerTheme.CLOCK) }
        var durationEntry by remember {
            mutableStateOf(
                DurationDigitEntry.fromDuration(
                    checkNotNull(TimerDuration.fromMinutes(DEFAULT_DURATION_MINUTES)),
                ),
            )
        }

        LaunchedEffect(engine, interrupted, update.snapshot.status) {
            if (!interrupted && update.snapshot.status == TimerStatus.RUNNING) {
                while (true) {
                    delay(250)
                    if (consumeAppInterruption()) {
                        update = engine.openSettings()
                        interrupted = true
                        break
                    }
                    update = engine.current()
                }
            }
        }
        LaunchedEffect(engine) {
            initializeAppInterruptionTracking()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(Strings.string.app_title),
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Screen.entries.forEach { destination ->
                    TextButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            if (destination == Screen.SETTINGS &&
                                update.snapshot.status == TimerStatus.RUNNING
                            ) {
                                update = engine.openSettings()
                            }
                            if (destination == Screen.SETTINGS) pendingTheme = selectedTheme
                            screen = destination
                        },
                    ) {
                        Text(
                            text = stringResource(destination.label),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            when (screen) {
                Screen.MAIN -> MainScreen(
                    snapshot = update.snapshot,
                    theme = selectedTheme,
                    interrupted = interrupted,
                    onOpenSettings = {
                        if (update.snapshot.status == TimerStatus.RUNNING) {
                            update = engine.openSettings()
                        }
                        pendingTheme = selectedTheme
                        screen = Screen.SETTINGS
                    },
                )

                Screen.SETTINGS -> SettingsScreen(
                    entry = durationEntry,
                    selectedTheme = pendingTheme,
                    onAppendDigit = { durationEntry = durationEntry.append(it) },
                    onDeleteDigit = { durationEntry = durationEntry.deleteLast() },
                    onClearDigits = { durationEntry = durationEntry.clear() },
                    onSelectTheme = { pendingTheme = it },
                    onSave = {
                        durationEntry.duration?.let { duration ->
                            update = engine.saveSettings(duration)
                            selectedTheme = pendingTheme
                            interrupted = false
                            screen = Screen.MAIN
                        }
                    },
                )

                Screen.ABOUT -> AboutScreen()
            }
        }
    }
}

private val Screen.label: StringResource
    get() = when (this) {
        Screen.MAIN -> Strings.string.main
        Screen.SETTINGS -> Strings.string.settings
        Screen.ABOUT -> Strings.string.about
    }

@Composable
private fun MainScreen(
    snapshot: TimerSnapshot,
    theme: TimerTheme,
    interrupted: Boolean,
    onOpenSettings: () -> Unit,
) {
    val remaining = snapshot.remaining
    val totalMinutes = remaining?.let(::roundedUpMinutes)
    val configuredDuration = snapshot.configuredDuration
    val fraction = if (configuredDuration != null && remaining != null) {
        (remaining.inWholeNanoseconds.toDouble() /
            configuredDuration.asDuration.inWholeNanoseconds.toDouble())
            .coerceIn(0.0, 1.0)
            .toFloat()
    } else {
        0f
    }

    val stateText = when {
        interrupted -> stringResource(Strings.string.timer_interrupted)
        snapshot.status == TimerStatus.RUNNING -> stringResource(Strings.string.timer_running)
        snapshot.status == TimerStatus.PAUSED -> stringResource(Strings.string.timer_paused)
        else -> stringResource(Strings.string.timer_not_configured)
    }
    val progressDescription = stringResource(Strings.string.remaining_time_accessibility)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stateText, style = MaterialTheme.typography.titleLarge)
        if (interrupted) {
            Spacer(Modifier.height(8.dp))
            Text(stringResource(Strings.string.interruption_warning), textAlign = TextAlign.Center)
        }
        if (remaining != null && totalMinutes != null) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(
                    Strings.string.remaining_time,
                    totalMinutes / 60,
                    totalMinutes % 60,
                ),
                style = MaterialTheme.typography.headlineSmall,
            )
            TimerVisualization(
                theme = theme,
                fractionRemaining = fraction,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .semantics {
                        progressBarRangeInfo = ProgressBarRangeInfo(fraction, 0f..1f)
                        contentDescription = progressDescription
                    },
            )
        } else {
            Spacer(Modifier.height(24.dp))
            Text(stringResource(Strings.string.choose_duration_message), textAlign = TextAlign.Center)
        }
        Spacer(Modifier.height(12.dp))
        Text(stringResource(theme.label), style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(20.dp))
        Button(onClick = onOpenSettings) {
            Text(stringResource(Strings.string.open_settings))
        }
    }
}

@Composable
private fun SettingsScreen(
    entry: DurationDigitEntry,
    selectedTheme: TimerTheme,
    onAppendDigit: (Int) -> Unit,
    onDeleteDigit: () -> Unit,
    onClearDigits: () -> Unit,
    onSelectTheme: (TimerTheme) -> Unit,
    onSave: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
        Text(stringResource(Strings.string.duration_entry_help), textAlign = TextAlign.Center)
        Spacer(Modifier.height(12.dp))
        DigitKeypad(onAppendDigit, onDeleteDigit, onClearDigits)
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
                TextButton(onClick = { onSelectTheme(theme) }) {
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
        Button(onClick = onSave, enabled = entry.duration != null) {
            Text(stringResource(Strings.string.save_and_start))
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

@Composable
private fun TimerVisualization(
    theme: TimerTheme,
    fractionRemaining: Float,
    modifier: Modifier = Modifier,
) {
    val track = MaterialTheme.colorScheme.surfaceVariant
    val remaining = MaterialTheme.colorScheme.primary
    Canvas(modifier = modifier.padding(24.dp)) {
        val diameter = size.minDimension * 0.72f
        val left = (size.width - diameter) / 2f
        val top = (size.height - diameter) / 2f
        val stroke = diameter * 0.11f
        inset(left, top, left, top) {
            when (theme) {
                TimerTheme.CLOCK -> {
                    drawCircle(track)
                    drawArc(
                        color = remaining,
                        startAngle = -90f,
                        sweepAngle = 360f * fractionRemaining,
                        useCenter = true,
                    )
                }

                TimerTheme.BATTERY -> {
                    val bodyWidth = size.width * 0.72f
                    val bodyHeight = size.height * 0.52f
                    val bodyTop = (size.height - bodyHeight) / 2f
                    val bodyLeft = (size.width - bodyWidth) / 2f
                    drawRoundRect(
                        color = track,
                        topLeft = androidx.compose.ui.geometry.Offset(bodyLeft, bodyTop),
                        size = androidx.compose.ui.geometry.Size(bodyWidth, bodyHeight),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(bodyHeight * 0.12f),
                    )
                    drawRoundRect(
                        color = remaining,
                        topLeft = androidx.compose.ui.geometry.Offset(bodyLeft, bodyTop),
                        size = androidx.compose.ui.geometry.Size(bodyWidth * fractionRemaining, bodyHeight),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(bodyHeight * 0.12f),
                    )
                }

                TimerTheme.FAN -> {
                    drawArc(
                        color = track,
                        startAngle = 180f,
                        sweepAngle = 180f,
                        useCenter = true,
                    )
                    drawArc(
                        color = remaining,
                        startAngle = 180f,
                        sweepAngle = 180f * fractionRemaining,
                        useCenter = true,
                    )
                }

                TimerTheme.RING -> {
                    drawCircle(
                        color = track,
                        radius = diameter / 2f - stroke / 2f,
                        style = Stroke(width = stroke),
                    )
                    drawArc(
                        color = remaining,
                        startAngle = -90f,
                        sweepAngle = 360f * fractionRemaining,
                        useCenter = false,
                        style = Stroke(width = stroke, cap = StrokeCap.Butt),
                    )
                }
            }
        }
    }
}

@Composable
private fun AboutScreen() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(stringResource(Strings.string.brand_wordmark), style = MaterialTheme.typography.headlineLarge)
        AboutItem(Strings.string.company_logo_gap)
        AboutItem(Strings.string.version_build_gap)
        AboutItem(Strings.string.support_information_gap)
        AboutItem(Strings.string.privacy_policy_gap)
        AboutItem(Strings.string.licence_information_gap)
        Spacer(Modifier.height(8.dp))
        Text(stringResource(Strings.string.intended_use_heading), style = MaterialTheme.typography.titleLarge)
        Text(stringResource(Strings.string.intended_use_summary), textAlign = TextAlign.Center)
        Text(stringResource(Strings.string.limitations_heading), style = MaterialTheme.typography.titleLarge)
        Text(stringResource(Strings.string.limitations_summary), textAlign = TextAlign.Center)
    }
}

@Composable
private fun AboutItem(resource: org.jetbrains.compose.resources.StringResource) {
    Text(
        text = stringResource(resource),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
    )
}

private fun roundedUpMinutes(duration: Duration): Int {
    val minuteNanos = 60_000_000_000L
    val nanos = duration.inWholeNanoseconds.coerceAtLeast(0)
    return ((nanos + minuteNanos - 1) / minuteNanos).toInt()
}

private const val DEFAULT_DURATION_MINUTES = 120
