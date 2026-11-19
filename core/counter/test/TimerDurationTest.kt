package nl.entreco.elchrono.core.counter

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

class TimerDurationTest {
    @Test
    fun `every whole minute in the supported range is representable`() {
        for (minutes in 1..TimerDuration.MAX_MINUTES) {
            val duration = assertNotNull(TimerDuration.fromMinutes(minutes))
            assertEquals(minutes, duration.totalMinutes)
            assertEquals(duration, TimerDuration.fromHoursAndMinutes(duration.hours, duration.minutes))
            assertEquals(duration, TimerDuration.fromDuration(minutes.minutes))
        }
    }

    @Test
    fun `durations expose whole hours and remaining minutes`() {
        assertEquals(0 to 1, TimerDuration.fromMinutes(1)?.let { it.hours to it.minutes })
        assertEquals(2 to 5, TimerDuration.fromMinutes(125)?.let { it.hours to it.minutes })
        assertEquals(24 to 0, TimerDuration.fromMinutes(1_440)?.let { it.hours to it.minutes })
    }

    @Test
    fun `zero and durations above the maximum are rejected`() {
        assertNull(TimerDuration.fromMinutes(0))
        assertNull(TimerDuration.fromMinutes(-1))
        assertNull(TimerDuration.fromMinutes(1_441))
        assertNull(TimerDuration.fromHoursAndMinutes(24, 1))
    }

    @Test
    fun `invalid hour and minute fields are rejected`() {
        assertNull(TimerDuration.fromHoursAndMinutes(-1, 0))
        assertNull(TimerDuration.fromHoursAndMinutes(25, 0))
        assertNull(TimerDuration.fromHoursAndMinutes(0, -1))
        assertNull(TimerDuration.fromHoursAndMinutes(0, 60))
        assertNull(TimerDuration.fromHoursAndMinutes(0, 0))
    }

    @Test
    fun `duration input must be finite within range and a whole number of minutes`() {
        assertEquals(2, TimerDuration.fromDuration(2.minutes)?.totalMinutes)
        assertNull(TimerDuration.fromDuration(59.seconds))
        assertNull(TimerDuration.fromDuration(2.minutes + 1.seconds))
        assertNull(TimerDuration.fromDuration(2.minutes + 1.nanoseconds))
        assertNull(TimerDuration.fromDuration(25.hours))
        assertNull(TimerDuration.fromDuration(Duration.INFINITE))
    }
}
