package nl.entreco.elchrono.features.timer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import kotlin.time.Duration
import nl.entreco.elchrono.core.counter.TimerSnapshot
import nl.entreco.elchrono.core.counter.TimerStatus
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.design.LocalDimens
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.battery
import nl.entreco.elchrono.core.localization.resources.choose_duration_message
import nl.entreco.elchrono.core.localization.resources.clock
import nl.entreco.elchrono.core.localization.resources.fan
import nl.entreco.elchrono.core.localization.resources.interruption_warning
import nl.entreco.elchrono.core.localization.resources.remaining_time
import nl.entreco.elchrono.core.localization.resources.remaining_time_accessibility
import nl.entreco.elchrono.core.localization.resources.ring
import nl.entreco.elchrono.core.localization.resources.timer_interrupted
import nl.entreco.elchrono.core.localization.resources.timer_not_configured
import nl.entreco.elchrono.core.localization.resources.timer_paused
import nl.entreco.elchrono.core.localization.resources.timer_running
import org.jetbrains.compose.resources.stringResource

@Composable
fun TimerFeature(
    snapshot: TimerSnapshot,
    theme: TimerTheme,
    interrupted: Boolean,
    modifier: Modifier = Modifier,
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

    Column(
        modifier = modifier,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
    ) {
        Text(stateText, style = MaterialTheme.typography.titleLarge)
        if (interrupted) {
            Spacer(Modifier.height(LocalDimens.current.spaceM))
            Text(stringResource(Strings.string.interruption_warning), textAlign = TextAlign.Center)
        }
        if (remaining != null && totalMinutes != null) {
            Spacer(Modifier.height(LocalDimens.current.spaceM))
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
            Spacer(Modifier.height(LocalDimens.current.spaceXXXL))
            Text(
                stringResource(Strings.string.choose_duration_message),
                textAlign = TextAlign.Center,
            )
        }
        Spacer(Modifier.height(LocalDimens.current.spaceL))
        Text(stringResource(theme.label), style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
private fun TimerVisualization(
    theme: TimerTheme,
    fractionRemaining: Float,
    modifier: Modifier = Modifier,
) {
    val track = MaterialTheme.colorScheme.surfaceVariant
    val remaining = MaterialTheme.colorScheme.primary
    Canvas(modifier = modifier.padding(LocalDimens.current.spaceXXXL)) {
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
                        size = androidx.compose.ui.geometry.Size(
                            bodyWidth * fractionRemaining,
                            bodyHeight,
                        ),
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

private fun roundedUpMinutes(duration: Duration): Int {
    val minuteNanos = 60_000_000_000L
    val nanos = duration.inWholeNanoseconds.coerceAtLeast(0)
    return ((nanos + minuteNanos - 1) / minuteNanos).toInt()
}

private val TimerTheme.label: org.jetbrains.compose.resources.StringResource
    get() = when (this) {
        TimerTheme.CLOCK -> Strings.string.clock
        TimerTheme.BATTERY -> Strings.string.battery
        TimerTheme.FAN -> Strings.string.fan
        TimerTheme.RING -> Strings.string.ring
    }
