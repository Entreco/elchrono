package nl.entreco.elchrono.features.timer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.time.Duration
import nl.entreco.elchrono.core.counter.TimerSnapshot
import nl.entreco.elchrono.core.design.LocalDimens
import nl.entreco.elchrono.core.design.TimerFont
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.battery
import nl.entreco.elchrono.core.localization.resources.choose_duration_message
import nl.entreco.elchrono.core.localization.resources.clock
import nl.entreco.elchrono.core.localization.resources.fan
import nl.entreco.elchrono.core.localization.resources.interruption_warning
import nl.entreco.elchrono.core.localization.resources.remaining_time
import nl.entreco.elchrono.core.localization.resources.remaining_time_accessibility
import nl.entreco.elchrono.core.localization.resources.ring
import nl.entreco.elchrono.core.localization.resources.timer_idle_accessibility
import nl.entreco.elchrono.core.localization.resources.timer_interrupted
import nl.entreco.elchrono.core.localization.resources.timer_interrupted_accessibility
import nl.entreco.elchrono.core.localization.resources.timer_not_configured
import nl.entreco.elchrono.core.localization.resources.timer_paused
import nl.entreco.elchrono.core.localization.resources.timer_paused_accessibility
import nl.entreco.elchrono.core.localization.resources.timer_running
import nl.entreco.elchrono.core.localization.resources.timer_running_accessibility
import nl.entreco.elchrono.core.localization.resources.timer_zero_duration_accessibility
import org.jetbrains.compose.resources.stringResource

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
    val configuredDuration = snapshot.configuredDuration
    val visualState = timerVisualState(snapshot, interrupted)
    val idle = visualState == TimerVisualState.IDLE
    val fraction = if (!idle &&
        configuredDuration != null &&
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
    val stateText = when (visualState) {
        TimerVisualState.RUNNING -> stringResource(Strings.string.timer_running)
        TimerVisualState.PAUSED -> stringResource(Strings.string.timer_paused)
        TimerVisualState.INTERRUPTED -> stringResource(Strings.string.timer_interrupted)
        TimerVisualState.IDLE -> stringResource(Strings.string.timer_not_configured)
    }
    val stateDescription = when (visualState) {
        TimerVisualState.RUNNING -> stringResource(Strings.string.timer_running_accessibility)
        TimerVisualState.PAUSED -> stringResource(Strings.string.timer_paused_accessibility)
        TimerVisualState.INTERRUPTED -> stringResource(Strings.string.timer_interrupted_accessibility)
        TimerVisualState.IDLE -> stringResource(
            if (configuredDuration?.totalMinutes == 0) {
                Strings.string.timer_zero_duration_accessibility
            } else {
                Strings.string.timer_idle_accessibility
            },
        )
    }
    val progressDescription = stringResource(Strings.string.remaining_time_accessibility)

    Column(
        modifier = modifier.fillMaxSize().background(Color(backgroundColorArgb)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (showsTimerText(detail)) {
            Text(
                stateText,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color(textColorArgb),
                    fontFamily = font.toFontFamily(),
                ),
            )
        }
        if (interrupted && showsTimerText(detail)) {
            Spacer(Modifier.height(LocalDimens.current.spaceM))
            Text(
                stringResource(Strings.string.interruption_warning),
                textAlign = TextAlign.Center,
                color = Color(textColorArgb),
            )
        }
        if (!idle && remaining != null) {
            val totalMinutes = roundedUpMinutes(remaining)
            Spacer(Modifier.height(LocalDimens.current.spaceM))
            if (showsTimerText(detail)) {
                Text(
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
            }
        } else if (configuredDuration == null && showsTimerText(detail)) {
            Spacer(Modifier.height(LocalDimens.current.spaceM))
            Text(
                stringResource(Strings.string.choose_duration_message),
                textAlign = TextAlign.Center,
                color = Color(textColorArgb),
            )
        }

        TimerVisualization(
            theme = theme,
            fractionRemaining = fraction,
            detail = detail,
            visualState = visualState,
            backgroundColor = Color(backgroundColorArgb),
            foregroundColor = Color(foregroundColorArgb),
            stateIndicatorColor = Color(textColorArgb),
            stateDescription = stateDescription,
            progressDescription = progressDescription,
            modifier = Modifier.fillMaxWidth().weight(1f),
        )

        if (showsTimerText(detail)) {
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
    visualState: TimerVisualState,
    backgroundColor: Color,
    foregroundColor: Color,
    stateIndicatorColor: Color,
    stateDescription: String,
    progressDescription: String,
    modifier: Modifier = Modifier,
) {
    val idle = visualState == TimerVisualState.IDLE
    Box(
        modifier = modifier.semantics {
            contentDescription = if (showsTextFreeStateCue(detail) || idle) {
                stateDescription
            } else {
                progressDescription
            }
            if (!idle) {
                progressBarRangeInfo = ProgressBarRangeInfo(fractionRemaining, 0f..1f)
            }
        },
        contentAlignment = Alignment.Center,
    ) {
        Canvas(Modifier.fillMaxSize()) {
            val track = backgroundColor
            val remaining = foregroundColor
            val boundaries = segmentBoundaryFractions(detail)
            val center = Offset(size.width / 2f, size.height / 2f)
            val radians = kotlin.math.PI.toFloat() / 180f

            when (theme) {
                TimerTheme.CLOCK -> {
                    val diameter = size.minDimension * 0.92f
                    val radius = diameter / 2f
                    val topLeft = Offset(center.x - radius, center.y - radius)
                    val shapeSize = Size(diameter, diameter)
                    val arc = remainingArc(fractionRemaining)
                    drawCircle(track, radius, center)
                    drawArc(
                        color = remaining,
                        startAngle = arc.startAngleDegrees,
                        sweepAngle = arc.sweepAngleDegrees,
                        useCenter = true,
                        topLeft = topLeft,
                        size = shapeSize,
                    )
                    boundaries.forEach { fraction ->
                        drawCircle(
                            color = track,
                            radius = radius * fraction,
                            center = center,
                            style = Stroke(width = 2.dp.toPx()),
                        )
                    }
                }

                TimerTheme.BATTERY -> {
                    val availableWidth = size.width * 0.9f
                    val availableHeight = size.height * 0.76f
                    val bodyHeight = minOf(availableHeight, availableWidth / 1.55f)
                    val capWidth = bodyHeight * 0.12f
                    val bodyWidth = minOf(availableWidth - capWidth, bodyHeight * 1.55f)
                    val bodyTop = center.y - bodyHeight / 2f
                    val bodyLeft = center.x - (bodyWidth + capWidth) / 2f
                    val corner = androidx.compose.ui.geometry.CornerRadius(bodyHeight * 0.12f)
                    drawRoundRect(
                        color = track,
                        topLeft = Offset(bodyLeft, bodyTop),
                        size = Size(bodyWidth, bodyHeight),
                        cornerRadius = corner,
                    )
                    drawRoundRect(
                        color = track,
                        topLeft = Offset(bodyLeft + bodyWidth, center.y - bodyHeight * 0.15f),
                        size = Size(capWidth, bodyHeight * 0.3f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(capWidth * 0.3f),
                    )
                    if (fractionRemaining > 0f) {
                        drawRoundRect(
                            color = remaining,
                            topLeft = Offset(bodyLeft, bodyTop),
                            size = Size(bodyWidth * fractionRemaining, bodyHeight),
                            cornerRadius = corner,
                        )
                    }
                    boundaries.forEach { fraction ->
                        val x = bodyLeft + bodyWidth * fraction
                        drawLine(
                            track,
                            Offset(x, bodyTop),
                            Offset(x, bodyTop + bodyHeight),
                            2.dp.toPx(),
                        )
                    }
                }

                TimerTheme.FAN -> {
                    val radius = minOf(size.width * 0.46f, size.height * 0.72f)
                    val fanCenter = Offset(center.x, center.y - radius * 0.35f)
                    val topLeft = Offset(fanCenter.x - radius, fanCenter.y - radius)
                    val shapeSize = Size(radius * 2f, radius * 2f)
                    drawArc(
                        color = track,
                        startAngle = 180f,
                        sweepAngle = 180f,
                        useCenter = true,
                        topLeft = topLeft,
                        size = shapeSize,
                    )
                    drawArc(
                        color = remaining,
                        startAngle = 180f,
                        sweepAngle = 180f * fractionRemaining,
                        useCenter = true,
                        topLeft = topLeft,
                        size = shapeSize,
                    )
                    boundaries.forEach { fraction ->
                        val angle = (180f + 180f * fraction) * radians
                        drawLine(
                            track,
                            fanCenter,
                            Offset(
                                fanCenter.x + kotlin.math.cos(angle) * radius,
                                fanCenter.y + kotlin.math.sin(angle) * radius,
                            ),
                            2.dp.toPx(),
                        )
                    }
                }

                TimerTheme.RING -> {
                    val diameter = size.minDimension * 0.92f
                    val radius = diameter / 2f
                    val stroke = diameter * 0.12f
                    val ringRadius = radius - stroke / 2f
                    val topLeft = Offset(center.x - radius, center.y - radius)
                    val shapeSize = Size(diameter, diameter)
                    val arc = remainingArc(fractionRemaining)
                    drawCircle(
                        color = track,
                        radius = ringRadius,
                        center = center,
                        style = Stroke(width = stroke),
                    )
                    drawArc(
                        color = remaining,
                        startAngle = arc.startAngleDegrees,
                        sweepAngle = arc.sweepAngleDegrees,
                        useCenter = false,
                        topLeft = topLeft,
                        size = shapeSize,
                        style = Stroke(width = stroke, cap = StrokeCap.Butt),
                    )
                    boundaries.forEach { fraction ->
                        val angle = (-90f + 360f * fraction) * radians
                        val inner = ringRadius - stroke / 2f
                        val outer = ringRadius + stroke / 2f
                        drawLine(
                            track,
                            Offset(
                                center.x + kotlin.math.cos(angle) * inner,
                                center.y + kotlin.math.sin(angle) * inner,
                            ),
                            Offset(
                                center.x + kotlin.math.cos(angle) * outer,
                                center.y + kotlin.math.sin(angle) * outer,
                            ),
                            2.dp.toPx(),
                        )
                    }
                }
            }
        }

        if (showsTextFreeStateCue(detail)) {
            Canvas(
                Modifier
                    .align(Alignment.TopCenter)
                    .padding(LocalDimens.current.spaceS)
                    .size(40.dp),
            ) {
                drawStateIndicator(visualState, stateIndicatorColor)
            }
        }
    }
}

private fun DrawScope.drawStateIndicator(
    state: TimerVisualState,
    color: Color,
) {
    val side = size.minDimension * 0.72f
    val left = (size.width - side) / 2f
    val top = (size.height - side) / 2f
    val center = Offset(size.width / 2f, size.height / 2f)
    val stroke = side * 0.1f
    when (state) {
        TimerVisualState.RUNNING -> {
            val path = Path().apply {
                moveTo(left + side * 0.28f, top + side * 0.12f)
                lineTo(left + side * 0.86f, center.y)
                lineTo(left + side * 0.28f, top + side * 0.88f)
                close()
            }
            drawPath(path, color)
        }

        TimerVisualState.PAUSED -> {
            drawRoundRect(
                color = color,
                topLeft = Offset(left + side * 0.2f, top + side * 0.12f),
                size = Size(side * 0.22f, side * 0.76f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(stroke),
            )
            drawRoundRect(
                color = color,
                topLeft = Offset(left + side * 0.58f, top + side * 0.12f),
                size = Size(side * 0.22f, side * 0.76f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(stroke),
            )
        }

        TimerVisualState.INTERRUPTED -> {
            val path = Path().apply {
                moveTo(center.x, top)
                lineTo(left + side, top + side * 0.88f)
                lineTo(left, top + side * 0.88f)
                close()
            }
            drawPath(path, color, style = Stroke(width = stroke))
            drawLine(
                color,
                Offset(center.x, top + side * 0.3f),
                Offset(center.x, top + side * 0.58f),
                stroke,
            )
            drawCircle(color, stroke / 2f, Offset(center.x, top + side * 0.73f))
        }

        TimerVisualState.IDLE -> drawCircle(
            color = color,
            radius = side * 0.34f,
            center = center,
            style = Stroke(width = stroke),
        )
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
