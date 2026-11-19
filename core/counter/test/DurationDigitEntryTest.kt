package nl.entreco.elchrono.core.counter

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

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
    fun `keyboard text edits preserve sequential digit entry`() {
        val initial = DurationDigitEntry.empty()
        val afterTwo = initial.applyTextEdit("00", "002")
        val afterZero = afterTwo.applyTextEdit("02", "020")
        val afterFive = afterZero.applyTextEdit("20", "205")

        assertEquals(2, afterFive.hours)
        assertEquals(5, afterFive.minutes)
        assertEquals(125, afterFive.duration?.totalMinutes)
    }

    @Test
    fun `keyboard deletion removes the last sequential digit`() {
        val entry = "205".fold(DurationDigitEntry.empty()) { current, digit ->
            current.append(digit.digitToInt())
        }

        val updated = entry.applyTextEdit("05", "0")

        assertEquals(0, updated.hours)
        assertEquals(20, updated.minutes)
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
