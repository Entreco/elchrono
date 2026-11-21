package nl.entreco.elchrono.core.counter

import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds

enum class TimerStatus {
    NOT_CONFIGURED,
    RUNNING,
    PAUSED,
}

data class TimerSnapshot(
    val status: TimerStatus,
    val configuredDuration: TimerDuration?,
    val remaining: Duration?,
)

data class TimerUpdate(
    val snapshot: TimerSnapshot,
    val completedCycles: Long,
)

class TimerEngine(
    private val clock: MonotonicClock = systemMonotonicClock,
) {
    private var status = TimerStatus.NOT_CONFIGURED
    private var configuredDuration: TimerDuration? = null
    private var runningSinceNanos = 0L
    private var pausedRemaining: Duration? = null
    private var observedCycles = 0L

    fun current(): TimerUpdate {
        if (status != TimerStatus.RUNNING) return TimerUpdate(snapshot(), 0)

        val duration = checkNotNull(configuredDuration).asDuration.inWholeNanoseconds
        val elapsed = (clock.nowNanos() - runningSinceNanos).coerceAtLeast(0)
        val elapsedCycles = elapsed / duration
        val completedCycles = (elapsedCycles - observedCycles).coerceAtLeast(0)
        observedCycles = elapsedCycles

        val elapsedInCycle = elapsed % duration
        val remainingNanos = if (elapsedInCycle == 0L) duration else duration - elapsedInCycle
        return TimerUpdate(
            snapshot = TimerSnapshot(status, configuredDuration, remainingNanos.nanoseconds),
            completedCycles = completedCycles,
        )
    }

    fun openSettings(): TimerUpdate {
        val update = current()
        if (status == TimerStatus.RUNNING) {
            pausedRemaining = update.snapshot.remaining
            status = TimerStatus.PAUSED
        }
        return update.copy(snapshot = snapshot())
    }

    fun saveSettings(duration: TimerDuration): TimerUpdate {
        configuredDuration = duration
        status = if (duration.totalMinutes == 0) TimerStatus.NOT_CONFIGURED else TimerStatus.RUNNING
        val now = clock.nowNanos()
        runningSinceNanos = now
        pausedRemaining = null
        observedCycles = 0
        return TimerUpdate(
            snapshot = TimerSnapshot(
                status,
                configuredDuration,
                if (duration.totalMinutes == 0) Duration.ZERO else duration.asDuration,
            ),
            completedCycles = 0,
        )
    }

    private fun snapshot(): TimerSnapshot {
        val remaining = when (status) {
            TimerStatus.NOT_CONFIGURED ->
                configuredDuration?.takeIf { it.totalMinutes == 0 }?.let { Duration.ZERO }
            TimerStatus.PAUSED -> pausedRemaining
            TimerStatus.RUNNING -> {
                val duration = checkNotNull(configuredDuration).asDuration.inWholeNanoseconds
                val elapsed = (clock.nowNanos() - runningSinceNanos).coerceAtLeast(0)
                val elapsedInCycle = elapsed % duration
                (if (elapsedInCycle == 0L) duration else duration - elapsedInCycle).nanoseconds
            }
        }
        return TimerSnapshot(status, configuredDuration, remaining)
    }
}
