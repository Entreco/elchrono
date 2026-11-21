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
import nl.entreco.elchrono.core.design.TimerFont
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle

@Composable
fun TimerFeature(
    snapshot: TimerSnapshot,
    theme: TimerTheme,
    backgroundColorArgb: Long,
    foregroundColorArgb: Long,
    textColorArgb: Long,
    font: TimerFont,
    detail: Int,
    interrupted: Boolean,
    modifier: Modifier = Modifier,
) {
    val remaining = snapshot.remaining
    val totalMinutes = remaining?.let(::roundedUpMinutes)
    val configuredDuration = snapshot.configuredDuration
    val fraction = if (configuredDuration != null &&
        remaining != null &&
        configuredDuration.totalMinutes > 0
    ) {
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
        if (detail > 0) {
            Text(
                stateText,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color(textColorArgb),
                    fontFamily = font.toFontFamily(),
                ),
            )
        }
        if (interrupted) {
            Spacer(Modifier.height(LocalDimens.current.spaceM))
            Text(stringResource(Strings.string.interruption_warning), textAlign = TextAlign.Center)
        }
        if (remaining != null && totalMinutes != null) {
            Spacer(Modifier.height(LocalDimens.current.spaceM))
            if (detail > 0) Text(
                text = stringResource(
                    Strings.string.remaining_time,
                    totalMinutes / 60,
                    totalMinutes % 60,
                ),
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color(textColorArgb),
                    fontFamily = font.toFontFamily(),
                ),
            )
            TimerVisualization(
                theme = theme,
                fractionRemaining = fraction,
                detail = detail,
                backgroundColor = Color(backgroundColorArgb),
                foregroundColor = Color(foregroundColorArgb),
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
            if (detail > 0) {
                Text(
                    stringResource(Strings.string.choose_duration_message),
                    textAlign = TextAlign.Center,
                    color = Color(textColorArgb),
                )
            }
        }
        if (detail > 0) {
            Spacer(Modifier.height(LocalDimens.current.spaceL))
            Text(
                stringResource(theme.label),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color(textColorArgb),
                    fontFamily = font.toFontFamily(),
                ),
            )
        }
    }
}

@Composable
private fun TimerVisualization(
    theme: TimerTheme,
    fractionRemaining: Float,
    detail: Int,
    backgroundColor: Color,
    foregroundColor: Color,
    modifier: Modifier = Modifier,
) {
    val track = backgroundColor
    val remaining = foregroundColor
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
            if (detail > 0) {
                val center = androidx.compose.ui.geometry.Offset(size.width / 2f, size.height / 2f)
                val radius = size.minDimension * 0.36f
                when (theme) {
                    TimerTheme.CLOCK, TimerTheme.RING -> {
                        repeat(detail) { index ->
                            val angle = (-90f + 360f * index / detail.toFloat()) *
                                kotlin.math.PI.toFloat() / 180f
                            val inner = radius * 0.82f
                            drawLine(
                                color = remaining,
                                start = androidx.compose.ui.geometry.Offset(
                                    center.x + kotlin.math.cos(angle) * inner,
                                    center.y + kotlin.math.sin(angle) * inner,
                                ),
                                end = androidx.compose.ui.geometry.Offset(
                                    center.x + kotlin.math.cos(angle) * radius,
                                    center.y + kotlin.math.sin(angle) * radius,
                                ),
                                strokeWidth = 2.dp.toPx(),
                            )
                        }
                    }
                    TimerTheme.BATTERY -> {
                        val bodyWidth = size.width * 0.72f
                        val bodyHeight = size.height * 0.52f
                        val bodyTop = (size.height - bodyHeight) / 2f
                        val bodyLeft = (size.width - bodyWidth) / 2f
                        repeat(detail - 1) { index ->
                            val x = bodyLeft + bodyWidth * (index + 1) / detail.toFloat()
                            drawLine(
                                remaining,
                                androidx.compose.ui.geometry.Offset(x, bodyTop),
                                androidx.compose.ui.geometry.Offset(x, bodyTop + bodyHeight),
                                2.dp.toPx(),
                            )
                        }
                    }
                    TimerTheme.FAN -> {
                        repeat(detail) { index ->
                            val angle = (180f + 180f * index / detail.toFloat()) *
                                kotlin.math.PI.toFloat() / 180f
                            drawLine(
                                remaining,
                                center,
                                androidx.compose.ui.geometry.Offset(
                                    center.x + kotlin.math.cos(angle) * radius,
                                    center.y + kotlin.math.sin(angle) * radius,
                                ),
                                2.dp.toPx(),
                            )
                        }
                    }
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

private fun TimerFont.toFontFamily(): FontFamily = when (this) {
    TimerFont.DEFAULT -> FontFamily.Default
    TimerFont.ROUNDED -> FontFamily.SansSerif
    TimerFont.MONOSPACE -> FontFamily.Monospace
}

private val TimerTheme.label: org.jetbrains.compose.resources.StringResource
    get() = when (this) {
        TimerTheme.CLOCK -> Strings.string.clock
        TimerTheme.BATTERY -> Strings.string.battery
        TimerTheme.FAN -> Strings.string.fan
        TimerTheme.RING -> Strings.string.ring
    }
