package nl.entreco.elchrono.core.counter

import kotlin.time.TimeSource

fun interface MonotonicClock {
    fun nowNanos(): Long
}

internal class SystemMonotonicClock(
    timeSource: TimeSource = TimeSource.Monotonic,
) : MonotonicClock {
    private val origin = timeSource.markNow()

    override fun nowNanos(): Long = origin.elapsedNow().inWholeNanoseconds
}

internal val systemMonotonicClock: MonotonicClock = SystemMonotonicClock()
