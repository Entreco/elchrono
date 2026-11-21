package nl.entreco.elchrono.features.timer

import nl.entreco.elchrono.core.counter.TimerSnapshot
import nl.entreco.elchrono.core.counter.TimerStatus

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

internal fun showsTextFreeStateCue(detail: Int): Boolean = detail == 0

internal fun segmentBoundaryFractions(detail: Int): List<Float> {
    require(detail in 0..12)
    return (1 until detail).map { it.toFloat() / detail }
}
