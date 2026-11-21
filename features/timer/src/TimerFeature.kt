package nl.entreco.elchrono.features.timer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
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
import nl.entreco.elchrono.core.localization.resources.timer_clock_minutes
import nl.entreco.elchrono.core.localization.resources.timer_clock_seconds
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
    ringThicknessPercent: Int,
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
    val clockLabels = remember(theme, detail, configuredDuration?.totalMinutes) {
        if (theme == TimerTheme.CLOCK) {
            clockDurationLabels(configuredDuration?.totalMinutes ?: 0, detail)
        } else {
            emptyList()
        }
    }

    BoxWithConstraints(
        modifier = modifier.fillMaxSize().background(Color(backgroundColorArgb)),
    ) {
        val overlayInset = minOf(maxHeight * 0.22f, 112.dp * LocalDensity.current.fontScale)
        TimerVisualization(
            theme = theme,
            fractionRemaining = fraction,
            detail = detail,
            ringThicknessPercent = ringThicknessPercent,
            clockLabels = clockLabels,
            clockLabelEnvelope = remember(configuredDuration?.totalMinutes) {
                clockLabelEnvelope(configuredDuration?.totalMinutes ?: 0)
            },
            font = font,
            visualState = visualState,
            backgroundColor = Color(backgroundColorArgb),
            foregroundColor = Color(foregroundColorArgb),
            stateIndicatorColor = Color(textColorArgb),
            stateDescription = stateDescription,
            progressDescription = progressDescription,
            modifier = Modifier.fillMaxSize().padding(vertical = overlayInset),
        )

        Column(
            modifier = Modifier.align(Alignment.TopCenter).fillMaxWidth()
                .heightIn(max = overlayInset).verticalScroll(rememberScrollState()),
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
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                .padding(horizontal = 48.dp)
                .heightIn(max = overlayInset).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (theme == TimerTheme.CLOCK &&
                detail > 0 &&
                configuredDuration?.totalMinutes?.let { it > 0 } == true
            ) {
                val unit = when (clockDurationUnit(configuredDuration.totalMinutes)) {
                    ClockDurationUnit.SECONDS -> Strings.string.timer_clock_seconds
                    ClockDurationUnit.MINUTES -> Strings.string.timer_clock_minutes
                }
                Text(
                    stringResource(unit),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(textColorArgb),
                        fontFamily = font.toFontFamily(),
                    ),
                )
            }

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

        if (showsTextFreeStateCue(detail)) {
            Canvas(
                Modifier.align(Alignment.BottomEnd).padding(LocalDimens.current.spaceS).size(40.dp)
                    .semantics { contentDescription = stateDescription },
            ) {
                drawStateIndicator(visualState, Color(textColorArgb))
            }
        }
    }
}

@Composable
private fun TimerVisualization(
    theme: TimerTheme,
    fractionRemaining: Float,
    detail: Int,
    ringThicknessPercent: Int,
    clockLabels: List<ClockDurationLabel>,
    clockLabelEnvelope: List<ClockDurationLabel>,
    font: TimerFont,
    visualState: TimerVisualState,
    backgroundColor: Color,
    foregroundColor: Color,
    stateIndicatorColor: Color,
    stateDescription: String,
    progressDescription: String,
    modifier: Modifier = Modifier,
) {
    val idle = visualState == TimerVisualState.IDLE
    val showClockLabels = theme == TimerTheme.CLOCK && clockLabels.isNotEmpty()
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()

    BoxWithConstraints(
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
        val baseFontSize = minOf(28f, maxHeight.value * 0.12f)
        val clockLabelStyle = MaterialTheme.typography.titleLarge.copy(
            color = stateIndicatorColor,
            fontFamily = font.toFontFamily(),
            fontWeight = FontWeight.Bold,
            fontSize = baseFontSize.sp,
        )
        val largestClockLabelSize = remember(clockLabelEnvelope, clockLabelStyle, textMeasurer) {
            clockLabelEnvelope
                .map { textMeasurer.measure(it.value, style = clockLabelStyle).size }
                .maxByOrNull { it.width }
        }
        val labelWidth = largestClockLabelSize?.width?.let { with(density) { it.toDp().value } } ?: 0f
        val labelHeight = largestClockLabelSize?.height?.let { with(density) { it.toDp().value } } ?: 0f
        val clockLayout = clockLayoutGeometry(
            width = maxWidth.value,
            height = maxHeight.value,
            labelWidth = labelWidth,
            labelHeight = labelHeight,
            diskLabelGap = minOf(20f, maxHeight.value * 0.04f),
        )
        val clockLabelFontSize = if (clockLabels.size > 2 && clockLayout.labelOrbitRadius > 0f) {
            val chord = 2f * clockLayout.labelOrbitRadius *
                sin(kotlin.math.PI.toFloat() / clockLabels.size)
            (baseFontSize * minOf(1f, chord * 0.8f / labelWidth.coerceAtLeast(1f)))
                .coerceAtLeast(minOf(14f, baseFontSize))
        } else {
            baseFontSize
        }
        val clockDiskRadiusPx = with(density) { clockLayout.diskRadius.dp.toPx() }
        val clockLabelOrbitRadiusPx = with(density) { clockLayout.labelOrbitRadius.dp.toPx() }

        Canvas(Modifier.fillMaxSize()) {
            val track = backgroundColor
            val remaining = foregroundColor
            val boundaries = visualizationBoundaryFractions(theme, detail)
            val center = Offset(size.width / 2f, size.height / 2f)
            val radians = kotlin.math.PI.toFloat() / 180f

            when (theme) {
                TimerTheme.CLOCK -> {
                    val radius = clockDiskRadiusPx
                    val diameter = radius * 2f
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
                    drawCircle(track, radius * 0.09f, center)
                }

                TimerTheme.BATTERY -> {
                    val geometry = batteryGeometry(size.width, size.height, 126.dp.toPx())
                    val bodyHeight = geometry.bodyHeight
                    val bodyWidth = geometry.bodyWidth
                    val bodyTop = geometry.top
                    val bodyLeft = geometry.left
                    val stroke = minOf(2.dp.toPx(), bodyHeight * 0.04f)
                    val inset = bodyHeight * 10f / 126f
                    val corner = CornerRadius(bodyHeight * 17f / 126f)
                    drawRoundRect(
                        color = track,
                        topLeft = Offset(bodyLeft, bodyTop),
                        size = Size(bodyWidth, bodyHeight),
                        cornerRadius = corner,
                    )
                    drawRoundRect(
                        color = remaining,
                        topLeft = Offset(bodyLeft + bodyWidth, center.y - bodyHeight / 6f),
                        size = Size(geometry.terminalWidth, bodyHeight / 3f),
                        cornerRadius = CornerRadius(bodyHeight * 5f / 126f),
                    )
                    val inner = Rect(
                        bodyLeft + inset,
                        bodyTop + inset,
                        bodyLeft + bodyWidth - inset,
                        bodyTop + bodyHeight - inset,
                    )
                    val clip = Path().apply { addRoundRect(RoundRect(inner, CornerRadius(bodyHeight * 7f / 126f))) }
                    clipPath(clip) {
                        drawRect(remaining, inner.topLeft, Size(inner.width * fractionRemaining, inner.height))
                        boundaries.forEach { fraction ->
                            val x = inner.left + inner.width * fraction
                            drawLine(track, Offset(x, inner.top), Offset(x, inner.bottom), 2.dp.toPx())
                        }
                    }
                    drawRoundRect(
                        color = remaining,
                        topLeft = Offset(bodyLeft + stroke / 2f, bodyTop + stroke / 2f),
                        size = Size(bodyWidth - stroke, bodyHeight - stroke),
                        cornerRadius = corner,
                        style = Stroke(stroke),
                    )
                }

                TimerTheme.FAN -> {
                    val geometry = fanGeometry(size.width, size.height)
                    val radius = geometry.radius
                    val fanCenter = Offset(center.x, geometry.centerY)
                    val topLeft = Offset(fanCenter.x - radius, fanCenter.y - radius)
                    val shapeSize = Size(radius * 2f, radius * 2f)
                    drawArc(
                        color = remaining,
                        startAngle = 180f,
                        sweepAngle = 180f * fractionRemaining,
                        useCenter = true,
                        topLeft = topLeft,
                        size = shapeSize,
                    )
                    boundaries.filter { it <= fractionRemaining }.forEach { fraction ->
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
                    val geometry = ringGeometry(size.minDimension * 0.46f, ringThicknessPercent)
                    val solid = ringThicknessPercent == 100
                    val arcRadius = if (solid) geometry.outerRadius else geometry.centerlineRadius
                    val topLeft = Offset(center.x - arcRadius, center.y - arcRadius)
                    val shapeSize = Size(arcRadius * 2f, arcRadius * 2f)
                    val arc = remainingArc(fractionRemaining)
                    if (solid) {
                        drawCircle(track, geometry.outerRadius, center)
                    } else {
                        drawCircle(track, geometry.centerlineRadius, center, style = Stroke(geometry.strokeWidth))
                    }
                    drawArc(
                        color = remaining,
                        startAngle = arc.startAngleDegrees,
                        sweepAngle = arc.sweepAngleDegrees,
                        useCenter = solid,
                        topLeft = topLeft,
                        size = shapeSize,
                        style = if (solid) androidx.compose.ui.graphics.drawscope.Fill else {
                            Stroke(width = geometry.strokeWidth, cap = StrokeCap.Butt)
                        },
                    )
                    boundaries.forEach { fraction ->
                        val angle = (-90f + 360f * fraction) * radians
                        val inner = geometry.innerRadius
                        val outer = geometry.outerRadius
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

        if (showClockLabels) {
            clockLabels.forEach { label ->
                val angle = label.angleDegrees * (kotlin.math.PI.toFloat() / 180f)
                Text(
                    text = label.value,
                    modifier = Modifier.offset {
                        IntOffset(
                            x = (cos(angle) * clockLabelOrbitRadiusPx).roundToInt(),
                            y = (sin(angle) * clockLabelOrbitRadiusPx).roundToInt(),
                        )
                    },
                    style = clockLabelStyle.copy(fontSize = clockLabelFontSize.sp),
                    maxLines = 1,
                )
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
