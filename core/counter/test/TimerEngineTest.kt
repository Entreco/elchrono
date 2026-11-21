package nl.entreco.elchrono.core.counter

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

class TimerEngineTest {
    @Test
    fun `zero duration stays idle and cannot resume or restart`() {
        val engine = TimerEngine(TestMonotonicClock())
        engine.reset(duration(0))
        listOf(engine.current(), engine.resume(), engine.restart()).forEach {
            assertEquals(TimerStatus.NOT_CONFIGURED, it.snapshot.status)
            assertEquals(Duration.ZERO, it.snapshot.remaining)
            assertEquals(0, it.completedCycles)
        }
    }

    @Test
    fun `unconfigured actions do not create a countdown`() {
        val engine = TimerEngine(TestMonotonicClock())
        listOf(engine.pause(), engine.resume(), engine.restart()).forEach {
            assertEquals(TimerStatus.NOT_CONFIGURED, it.snapshot.status)
            assertNull(it.snapshot.configuredDuration)
            assertNull(it.snapshot.remaining)
        }
    }

    @Test
    fun `reset prepares full duration without starting`() {
        val clock = TestMonotonicClock()
        val engine = TimerEngine(clock)
        assertEquals(TimerStatus.PAUSED, engine.reset(duration(5)).snapshot.status)
        clock.advance(1.hours)
        assertEquals(5.minutes, engine.current().snapshot.remaining)
        assertEquals(TimerStatus.RUNNING, engine.resume().snapshot.status)
        clock.advance(30.seconds)
        assertEquals(270.seconds, engine.current().snapshot.remaining)
    }

    @Test
    fun `pause and resume retain remaining time and are idempotent`() {
        val clock = TestMonotonicClock()
        val engine = TimerEngine(clock)
        engine.reset(duration(5))
        engine.resume()
        clock.advance(2.minutes)
        assertEquals(3.minutes, engine.pause().snapshot.remaining)
        clock.advance(24.hours)
        assertEquals(3.minutes, engine.pause().snapshot.remaining)
        engine.resume()
        clock.advance(1.minutes)
        assertEquals(2.minutes, engine.resume().snapshot.remaining)
        clock.advance(1.minutes)
        assertEquals(1.minutes, engine.current().snapshot.remaining)
    }

    @Test
    fun `every allowed duration finishes exactly once at zero and waits for restart`() {
        (1..TimerDuration.MAX_MINUTES).forEach { minutes ->
            val clock = TestMonotonicClock()
            val engine = TimerEngine(clock)
            engine.reset(duration(minutes))
            engine.resume()
            clock.advance(minutes.minutes - 1.nanoseconds)
            assertEquals(1.nanoseconds, engine.current().snapshot.remaining)
            clock.advance(1.nanoseconds)
            val completion = engine.current()
            assertEquals(TimerStatus.FINISHED, completion.snapshot.status)
            assertEquals(Duration.ZERO, completion.snapshot.remaining)
            assertEquals(1, completion.completedCycles)
            clock.advance(48.hours)
            listOf(engine.current(), engine.pause(), engine.resume()).forEach {
                assertEquals(TimerStatus.FINISHED, it.snapshot.status)
                assertEquals(Duration.ZERO, it.snapshot.remaining)
                assertEquals(0, it.completedCycles)
            }
            val restarted = engine.restart()
            assertEquals(TimerStatus.RUNNING, restarted.snapshot.status)
            assertEquals(minutes.minutes, restarted.snapshot.remaining)
            assertEquals(0, restarted.completedCycles)
        }
    }

    @Test
    fun `late update does not loop and pause at boundary reports completion`() {
        val clock = TestMonotonicClock()
        val engine = TimerEngine(clock)
        engine.reset(duration(1))
        engine.resume()
        clock.advance(24.hours)
        val update = engine.pause()
        assertEquals(TimerStatus.FINISHED, update.snapshot.status)
        assertEquals(Duration.ZERO, update.snapshot.remaining)
        assertEquals(1, update.completedCycles)
        assertEquals(0, engine.current().completedCycles)
    }

    @Test
    fun `changing duration clears completion and resets a running or finished timer paused`() {
        val clock = TestMonotonicClock()
        val engine = TimerEngine(clock)
        engine.reset(duration(1))
        engine.resume()
        clock.advance(1.minutes)
        engine.current()
        assertEquals(TimerStatus.PAUSED, engine.reset(duration(2)).snapshot.status)
        engine.resume()
        clock.advance(30.seconds)
        val reset = engine.reset(duration(10))
        assertEquals(TimerStatus.PAUSED, reset.snapshot.status)
        assertEquals(10.minutes, reset.snapshot.remaining)
        assertEquals(0, reset.completedCycles)
    }

    private fun duration(minutes: Int) = checkNotNull(TimerDuration.fromMinutes(minutes))

    private class TestMonotonicClock : MonotonicClock {
        private var currentNanos = 0L
        override fun nowNanos(): Long = currentNanos
        fun advance(duration: Duration) {
            currentNanos += duration.inWholeNanoseconds
        }
    }
}
