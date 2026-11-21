package nl.entreco.elchrono.features.timer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.time.Duration
import nl.entreco.elchrono.core.counter.TimerSnapshot
import nl.entreco.elchrono.core.design.LocalDimens
import nl.entreco.elchrono.core.design.TimerFont
import nl.entreco.elchrono.core.design.toFontFamily
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.design.resources.Res as DesignResources
import nl.entreco.elchrono.core.design.resources.material_pause
import nl.entreco.elchrono.core.design.resources.material_play
import nl.entreco.elchrono.core.design.resources.material_restart
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.choose_duration_message
import nl.entreco.elchrono.core.localization.resources.remaining_time
import nl.entreco.elchrono.core.localization.resources.remaining_time_accessibility
import nl.entreco.elchrono.core.localization.resources.pause_timer
import nl.entreco.elchrono.core.localization.resources.resume_timer
import nl.entreco.elchrono.core.localization.resources.restart_timer
import nl.entreco.elchrono.core.localization.resources.timer_running_accessibility
import nl.entreco.elchrono.core.localization.resources.timer_paused_accessibility
import nl.entreco.elchrono.core.localization.resources.timer_interrupted_accessibility
import nl.entreco.elchrono.core.localization.resources.timer_idle_accessibility
import nl.entreco.elchrono.core.localization.resources.timer_zero_duration_accessibility
import nl.entreco.elchrono.core.localization.resources.timer_finished_accessibility
import nl.entreco.elchrono.core.localization.resources.interruption_warning
import org.jetbrains.compose.resources.painterResource
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
    onTimerAction: () -> Unit,
    modifier: Modifier = Modifier,
    topContentInset: Dp = 0.dp,
    visualizationModifier: Modifier = Modifier,
    remainingTimeDecoration: @Composable (@Composable () -> Unit) -> Unit = { content -> content() },
    controlsEnabled: Boolean = true,
) {
    val remaining = snapshot.remaining
    val configuredDuration = snapshot.configuredDuration
    val visualState = timerVisualState(snapshot, interrupted)
    val idle = visualState == TimerVisualState.IDLE
    val description = stringResource(
        when (visualState) {
            TimerVisualState.RUNNING -> Strings.string.timer_running_accessibility
            TimerVisualState.PAUSED -> Strings.string.timer_paused_accessibility
            TimerVisualState.INTERRUPTED -> Strings.string.timer_interrupted_accessibility
            TimerVisualState.FINISHED -> Strings.string.timer_finished_accessibility
            TimerVisualState.IDLE -> if (configuredDuration?.totalMinutes == 0) {
                Strings.string.timer_zero_duration_accessibility
            } else {
                Strings.string.timer_idle_accessibility
            }
        },
    )
    val remainingDescription = stringResource(Strings.string.remaining_time_accessibility)
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

    val clockLabels = remember(theme, detail, configuredDuration?.totalMinutes) {
        if (theme == TimerTheme.CLOCK) {
            clockDurationLabels(configuredDuration?.totalMinutes ?: 0, detail)
        } else {
            emptyList()
        }
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(Color(backgroundColorArgb))
            .semantics { stateDescription = description },
    ) {
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
            backgroundColor = Color(backgroundColorArgb),
            foregroundColor = Color(foregroundColorArgb),
            stateIndicatorColor = Color(textColorArgb),
            modifier = Modifier.fillMaxSize().then(visualizationModifier),
        )

        if (!idle && remaining != null && showsTimerText(detail)) {
            Box(
                Modifier
                    .align(Alignment.Center)
                    .padding(LocalDimens.current.spaceM)
                    .semantics { contentDescription = remainingDescription }
                    .testTag("remaining-time-panel"),
            ) {
                remainingTimeDecoration {
                    val totalMinutes = roundedUpMinutes(remaining)
                    Text(
                        text = stringResource(
                            Strings.string.remaining_time,
                            totalMinutes / 60,
                            totalMinutes % 60,
                        ),
                        modifier = Modifier
                            .padding(LocalDimens.current.spaceM)
                            .width(IntrinsicSize.Max),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = Color(textColorArgb),
                            fontFamily = font.toFontFamily(),
                        ),
                    )
                }
            }
        } else if (configuredDuration == null && showsTimerText(detail)) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(LocalDimens.current.spaceM),
                text = stringResource(Strings.string.choose_duration_message),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color(textColorArgb),
                    fontFamily = font.toFontFamily(),
                ),
            )
        }

        if (idle) {
            Canvas(
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(LocalDimens.current.spaceS)
                    .size(40.dp),
            ) {
                drawStateIndicator(visualState, Color(textColorArgb))
            }
        } else if (controlsEnabled) {
            val restart = visualState == TimerVisualState.FINISHED || visualState == TimerVisualState.INTERRUPTED
            val label = stringResource(
                when {
                    restart -> Strings.string.restart_timer
                    visualState == TimerVisualState.RUNNING -> Strings.string.pause_timer
                    else -> Strings.string.resume_timer
                },
            )
            val icon = when {
                restart -> DesignResources.drawable.material_restart
                visualState == TimerVisualState.RUNNING -> DesignResources.drawable.material_pause
                else -> DesignResources.drawable.material_play
            }
            val actionModifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(LocalDimens.current.spaceS)
                .testTag("timer-action")
            if (restart && detail > 0) {
                TextButton(onClick = onTimerAction, modifier = actionModifier.sizeIn(minHeight = 48.dp)) {
                    Icon(painterResource(icon), contentDescription = null, tint = Color(textColorArgb))
                    Text(label, color = Color(textColorArgb), fontFamily = font.toFontFamily())
                }
            } else {
                IconButton(onClick = onTimerAction, modifier = actionModifier.size(48.dp)) {
                    Icon(painterResource(icon), contentDescription = label, tint = Color(textColorArgb))
                }
            }
        }
        if (visualState == TimerVisualState.INTERRUPTED) {
            Canvas(
                Modifier.align(Alignment.BottomStart)
                    .padding(LocalDimens.current.spaceS).size(40.dp),
            ) {
                drawStateIndicator(TimerVisualState.INTERRUPTED, Color(textColorArgb))
            }
            if (detail > 0) {
                Text(
                    stringResource(Strings.string.interruption_warning),
                    Modifier.align(Alignment.TopCenter)
                        .padding(top = topContentInset + LocalDimens.current.spaceM)
                        .padding(horizontal = LocalDimens.current.spaceM),
                    color = Color(textColorArgb),
                    fontFamily = font.toFontFamily(),
                )
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
    backgroundColor: Color,
    foregroundColor: Color,
    stateIndicatorColor: Color,
    modifier: Modifier = Modifier,
) {
    val showClockLabels = theme == TimerTheme.CLOCK && clockLabels.isNotEmpty()
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()

    BoxWithConstraints(
        modifier = modifier,
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
        val labelWidth =
            largestClockLabelSize?.width?.let { with(density) { it.toDp().value } } ?: 0f
        val labelHeight =
            largestClockLabelSize?.height?.let { with(density) { it.toDp().value } } ?: 0f
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
                    val geometry = batteryGeometry(size.width, size.height)
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
                    val clip = Path().apply {
                        addRoundRect(
                            RoundRect(
                                inner,
                                CornerRadius(bodyHeight * 7f / 126f)
                            )
                        )
                    }
                    clipPath(clip) {
                        drawRect(
                            remaining,
                            inner.topLeft,
                            Size(inner.width * fractionRemaining, inner.height)
                        )
                        boundaries.forEach { fraction ->
                            val x = inner.left + inner.width * fraction
                            drawLine(
                                track,
                                Offset(x, inner.top),
                                Offset(x, inner.bottom),
                                2.dp.toPx()
                            )
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
                        drawCircle(
                            track,
                            geometry.centerlineRadius,
                            center,
                            style = Stroke(geometry.strokeWidth)
                        )
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
        TimerVisualState.FINISHED -> Unit
    }
}

private fun roundedUpMinutes(duration: Duration): Int {
    val minuteNanos = 60_000_000_000L
    val nanos = duration.inWholeNanoseconds.coerceAtLeast(0)
    return ((nanos + minuteNanos - 1) / minuteNanos).toInt()
}
