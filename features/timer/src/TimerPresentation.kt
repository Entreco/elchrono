package nl.entreco.elchrono.features.timer

import kotlin.math.roundToInt
import nl.entreco.elchrono.core.counter.TimerSnapshot
import nl.entreco.elchrono.core.counter.TimerStatus
import nl.entreco.elchrono.core.design.TimerTheme

internal enum class ClockDurationUnit {
    SECONDS,
    MINUTES,
}

internal data class ClockDurationLabel(
    val value: String,
    val angleDegrees: Float,
)

internal data class ClockLayoutGeometry(
    val diskRadius: Float,
    val labelOrbitRadius: Float,
)

internal data class FanGeometry(
    val radius: Float,
    val centerY: Float,
)

internal data class BatteryGeometry(
    val bodyWidth: Float,
    val bodyHeight: Float,
    val terminalWidth: Float,
    val left: Float,
    val top: Float,
)

internal data class RingGeometry(
    val outerRadius: Float,
    val innerRadius: Float,
    val centerlineRadius: Float,
    val strokeWidth: Float,
)

internal fun ringGeometry(outerRadius: Float, thicknessPercent: Int): RingGeometry {
    require(outerRadius >= 0f && thicknessPercent in 1..100)
    val stroke = (outerRadius * thicknessPercent / 100f).coerceAtLeast(1f).coerceAtMost(outerRadius)
    val innerRadius = outerRadius - stroke
    return RingGeometry(outerRadius, innerRadius, (outerRadius + innerRadius) / 2f, stroke)
}

internal fun batteryGeometry(width: Float, height: Float, preferredHeight: Float = 126f): BatteryGeometry {
    require(width >= 0f && height >= 0f && preferredHeight > 0f)
    val availableWidth = width * 0.9f
    val bodyHeight = minOf(preferredHeight, height * 0.76f, availableWidth / (3.6f + 13f / 126f))
    val terminalWidth = bodyHeight * 13f / 126f
    return BatteryGeometry(
        bodyWidth = availableWidth - terminalWidth,
        bodyHeight = bodyHeight,
        terminalWidth = terminalWidth,
        left = (width - availableWidth) / 2f,
        top = (height - bodyHeight) / 2f,
    )
}

internal enum class TimerVisualState {
    RUNNING,
    PAUSED,
    INTERRUPTED,
    IDLE,
}

internal data class RemainingArc(
    val startAngleDegrees: Float,
    val sweepAngleDegrees: Float,
)

internal fun timerVisualState(
    snapshot: TimerSnapshot,
    interrupted: Boolean,
): TimerVisualState = when {
    interrupted -> TimerVisualState.INTERRUPTED
    snapshot.status == TimerStatus.RUNNING -> TimerVisualState.RUNNING
    snapshot.status == TimerStatus.PAUSED -> TimerVisualState.PAUSED
    else -> TimerVisualState.IDLE
}

internal fun remainingArc(fractionRemaining: Float): RemainingArc {
    val fraction = fractionRemaining.coerceIn(0f, 1f)
    return RemainingArc(
        startAngleDegrees = -90f + 360f * (1f - fraction),
        sweepAngleDegrees = 360f * fraction,
    )
}

internal fun showsTimerText(detail: Int): Boolean = detail > 0

internal fun showsTimerStateText(detail: Int, state: TimerVisualState): Boolean =
    showsTimerText(detail) && (state == TimerVisualState.INTERRUPTED || state == TimerVisualState.IDLE)

internal fun showsTimerStateIndicator(detail: Int, state: TimerVisualState): Boolean =
    if (detail == 0) {
        state == TimerVisualState.INTERRUPTED || state == TimerVisualState.IDLE
    } else {
        state == TimerVisualState.RUNNING || state == TimerVisualState.PAUSED
    }

internal fun segmentBoundaryFractions(detail: Int): List<Float> {
    require(detail in 0..12)
    return (1 until detail).map { it.toFloat() / detail }
}

internal fun visualizationBoundaryFractions(theme: TimerTheme, detail: Int): List<Float> =
    if (theme == TimerTheme.CLOCK) {
        require(detail in 0..12)
        emptyList()
    } else {
        segmentBoundaryFractions(detail)
    }

internal fun clockDurationUnit(totalMinutes: Int): ClockDurationUnit {
    require(totalMinutes in 0..1440)
    return if (totalMinutes in 1..9) ClockDurationUnit.SECONDS else ClockDurationUnit.MINUTES
}

internal fun clockDurationLabels(totalMinutes: Int, detail: Int): List<ClockDurationLabel> {
    require(totalMinutes in 0..1440)
    require(detail in 0..12)
    if (totalMinutes == 0 || detail == 0) return emptyList()

    val useSeconds = clockDurationUnit(totalMinutes) == ClockDurationUnit.SECONDS
    val totalUnits = if (useSeconds) totalMinutes * 60 else totalMinutes
    return (0 until detail).map { index ->
        val fraction = ((detail - index) % detail).toFloat() / detail
        val value = totalUnits * fraction
        val formatted = if (useSeconds) {
            value.roundToInt().toString()
        } else {
            val tenths = (value * 10f).roundToInt()
            if (tenths % 10 == 0) {
                (tenths / 10).toString()
            } else {
                "${tenths / 10}.${tenths % 10}"
            }
        }
        ClockDurationLabel(
            value = formatted,
            angleDegrees = index.toFloat() / detail * 360f - 90f,
        )
    }
}

internal fun clockLabelEnvelope(totalMinutes: Int): List<ClockDurationLabel> =
    (1..12).flatMap { clockDurationLabels(totalMinutes.coerceAtLeast(1), it) }
        .distinctBy { it.value }

internal fun clockLayoutGeometry(
    width: Float,
    height: Float,
    labelWidth: Float,
    labelHeight: Float,
    edgeMargin: Float = 8f,
    diskLabelGap: Float = 20f,
): ClockLayoutGeometry {
    require(width >= 0f && height >= 0f && labelWidth >= 0f && labelHeight >= 0f)
    require(edgeMargin >= 0f && diskLabelGap >= 0f)
    val labelOrbitRadius = minOf(
        width / 2f - labelWidth / 2f - edgeMargin,
        height / 2f - labelHeight / 2f - edgeMargin,
    ).coerceAtLeast(0f)
    return ClockLayoutGeometry(
        diskRadius = (labelOrbitRadius - diskLabelGap).coerceAtLeast(0f),
        labelOrbitRadius = labelOrbitRadius,
    )
}

internal fun fanGeometry(width: Float, height: Float): FanGeometry {
    require(width > 0f && height > 0f)
    val radius = minOf(width * 0.48f, height * 0.9f)
    return FanGeometry(
        radius = radius,
        centerY = (height + radius) / 2f,
    )
}
