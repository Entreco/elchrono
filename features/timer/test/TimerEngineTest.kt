package nl.entreco.elchrono.features.timer

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

class TimerEngineTest {
    @Test
    fun `saving settings starts at the full configured duration`() {
        val clock = TestMonotonicClock()
        val engine = TimerEngine(clock)

        val update = engine.saveSettings(duration(5))

        assertEquals(TimerStatus.RUNNING, update.snapshot.status)
        assertEquals(5.minutes, update.snapshot.remaining)
        assertEquals(0, update.completedCycles)
    }

    @Test
    fun `remaining time derives from monotonic elapsed time`() {
        val clock = TestMonotonicClock()
        val engine = TimerEngine(clock)
        engine.saveSettings(duration(2))

        clock.advance(30.seconds)

        assertEquals(90.seconds, engine.current().snapshot.remaining)
    }

    @Test
    fun `maximum duration reaches zero and resets at the exact boundary`() {
        val clock = TestMonotonicClock()
        val engine = TimerEngine(clock)
        engine.saveSettings(duration(TimerDuration.MAX_MINUTES))

        clock.advance(24.hours - 1.nanoseconds)
        assertEquals(1.nanoseconds, engine.current().snapshot.remaining)

        clock.advance(1.nanoseconds)
        val completion = engine.current()
        assertEquals(1, completion.completedCycles)
        assertEquals(24.hours, completion.snapshot.remaining)
    }

    @Test
    fun `opening settings pauses and saving starts a new full countdown`() {
        val clock = TestMonotonicClock()
        val engine = TimerEngine(clock)
        engine.saveSettings(duration(5))
        clock.advance(2.minutes)

        val paused = engine.openSettings()
        assertEquals(TimerStatus.PAUSED, paused.snapshot.status)
        assertEquals(3.minutes, paused.snapshot.remaining)

        clock.advance(1.hours)
        assertEquals(3.minutes, engine.current().snapshot.remaining)
        assertEquals(TimerStatus.PAUSED, engine.current().snapshot.status)

        val saved = engine.saveSettings(duration(2))
        assertEquals(TimerStatus.RUNNING, saved.snapshot.status)
        assertEquals(2.minutes, saved.snapshot.remaining)
    }

    @Test
    fun `completion immediately resets to full duration without a completed state`() {
        val clock = TestMonotonicClock()
        val engine = TimerEngine(clock)
        engine.saveSettings(duration(1))

        clock.advance(1.minutes)

        val update = engine.current()
        assertEquals(TimerStatus.RUNNING, update.snapshot.status)
        assertEquals(1.minutes, update.snapshot.remaining)
        assertEquals(1, update.completedCycles)
    }

    @Test
    fun `completion is reported once at the boundary and remaining time never becomes negative`() {
        val clock = TestMonotonicClock()
        val engine = TimerEngine(clock)
        engine.saveSettings(duration(1))
        clock.advance(1.minutes)

        assertEquals(1, engine.current().completedCycles)
        assertEquals(0, engine.current().completedCycles)

        clock.advance(1.nanoseconds)
        val afterBoundary = engine.current()
        assertEquals(0, afterBoundary.completedCycles)
        assertEquals(1.minutes - 1.nanoseconds, afterBoundary.snapshot.remaining)
        assertEquals(false, checkNotNull(afterBoundary.snapshot.remaining).isNegative())

        clock.advance(1.minutes - 1.nanoseconds)
        val nextCompletion = engine.current()
        assertEquals(1, nextCompletion.completedCycles)
        assertEquals(1.minutes, nextCompletion.snapshot.remaining)
        assertEquals(0, engine.current().completedCycles)
    }

    @Test
    fun `opening settings before configuration does not create a timer`() {
        val engine = TimerEngine(TestMonotonicClock())

        val update = engine.openSettings()

        assertEquals(TimerStatus.NOT_CONFIGURED, update.snapshot.status)
        assertNull(update.snapshot.configuredDuration)
        assertNull(update.snapshot.remaining)
    }

    private fun duration(minutes: Int) = checkNotNull(TimerDuration.fromMinutes(minutes))

    private class TestMonotonicClock : MonotonicClock {
        private var currentNanos = 0L

        override fun nowNanos(): Long = currentNanos

        fun advance(duration: kotlin.time.Duration) {
            currentNanos += duration.inWholeNanoseconds
        }
    }
}
