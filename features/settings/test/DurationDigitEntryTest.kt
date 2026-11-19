package nl.entreco.elchrono.features.settings

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import nl.entreco.elchrono.core.counter.TimerDuration

class DurationDigitEntryTest {
    @Test
    fun `duration digits fill minutes first then shift into hours`() {
        val duration = "205".fold(DurationDigitEntry.empty()) { entry, digit ->
            entry.append(digit.digitToInt())
        }

        assertEquals(2, duration.hours)
        assertEquals(5, duration.minutes)
        assertEquals(125, duration.duration?.totalMinutes)
    }

    @Test
    fun `duration digit entry represents configured boundaries`() {
        val minimum = DurationDigitEntry.empty().append(1)
        val maximum = "2400".fold(DurationDigitEntry.empty()) { entry, digit ->
            entry.append(digit.digitToInt())
        }

        assertEquals(1, minimum.duration?.totalMinutes)
        assertEquals(TimerDuration.MAX_MINUTES, maximum.duration?.totalMinutes)
    }

    @Test
    fun `invalid digit entry is not a valid duration`() {
        val invalid = "9999".fold(DurationDigitEntry.empty()) { entry, digit ->
            entry.append(digit.digitToInt())
        }

        assertNull(invalid.duration)
    }
}
