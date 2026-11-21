package nl.entreco.elchrono.core.counter

import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds

enum class TimerStatus {
    NOT_CONFIGURED,
    RUNNING,
    PAUSED,
    FINISHED,
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

    fun current(): TimerUpdate {
        if (status != TimerStatus.RUNNING) return TimerUpdate(snapshot(), 0)

        val elapsed = (clock.nowNanos() - runningSinceNanos).coerceAtLeast(0)
        val remaining = checkNotNull(pausedRemaining) - elapsed.nanoseconds
        if (remaining <= Duration.ZERO) {
            status = TimerStatus.FINISHED
            pausedRemaining = Duration.ZERO
            return TimerUpdate(snapshot(), 1)
        }
        return TimerUpdate(TimerSnapshot(status, configuredDuration, remaining), 0)
    }

    fun pause(): TimerUpdate {
        val update = current()
        if (status == TimerStatus.RUNNING) {
            pausedRemaining = update.snapshot.remaining
            status = TimerStatus.PAUSED
        }
        return update.copy(snapshot = snapshot())
    }

    fun reset(duration: TimerDuration): TimerUpdate {
        configuredDuration = duration
        status = if (duration.totalMinutes == 0) TimerStatus.NOT_CONFIGURED else TimerStatus.PAUSED
        pausedRemaining = duration.asDuration
        return TimerUpdate(snapshot(), 0)
    }

    fun resume(): TimerUpdate {
        if (status == TimerStatus.PAUSED) {
            runningSinceNanos = clock.nowNanos()
            status = TimerStatus.RUNNING
        }
        return current()
    }

    fun restart(): TimerUpdate {
        val duration = configuredDuration ?: return current()
        reset(duration)
        return resume()
    }

    private fun snapshot(): TimerSnapshot {
        val remaining = when (status) {
            TimerStatus.NOT_CONFIGURED ->
                configuredDuration?.takeIf { it.totalMinutes == 0 }?.let { Duration.ZERO }
            TimerStatus.PAUSED, TimerStatus.FINISHED -> pausedRemaining
            TimerStatus.RUNNING -> {
                val elapsed = (clock.nowNanos() - runningSinceNanos).coerceAtLeast(0)
                (checkNotNull(pausedRemaining) - elapsed.nanoseconds).coerceAtLeast(Duration.ZERO)
            }
        }
        return TimerSnapshot(status, configuredDuration, remaining)
    }
}
