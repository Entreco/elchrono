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
    fun `typing in either displayed field appends sequential digits`() {
        val initial = DurationDigitEntry.empty()
        val afterTwo = initial.applyTextEdit("00", "200")
        val afterZero = afterTwo.applyTextEdit("00", "000")
        val afterFive = afterZero.applyTextEdit("20", "520")

        assertEquals(2, afterFive.hours)
        assertEquals(5, afterFive.minutes)
    }

    @Test
    fun `keyboard replacement removes selected digits before appending`() {
        val entry = "205".fold(DurationDigitEntry.empty()) { current, digit ->
            current.append(digit.digitToInt())
        }

        val updated = entry.applyTextEdit("05", "09")

        assertEquals(2, updated.hours)
        assertEquals(9, updated.minutes)
    }

    @Test
    fun `nondigit replacement leaves the duration unchanged`() {
        val entry = "205".fold(DurationDigitEntry.empty()) { current, digit ->
            current.append(digit.digitToInt())
        }

        val updated = entry.applyTextEdit("05", "x")

        assertEquals(2, updated.hours)
        assertEquals(5, updated.minutes)
    }

    @Test
    fun `keyboard input supports maximum duration and rejects invalid digit`() {
        val initial = DurationDigitEntry.empty()
        val afterTwo = initial.applyTextEdit("00", "002")
        val afterFour = afterTwo.applyTextEdit("02", "024")
        val afterZero = afterFour.applyTextEdit("24", "240")
        val maximum = afterZero.applyTextEdit("40", "400")
        val rejected = maximum.applyTextEdit("00", "001")

        assertEquals(TimerDuration.MAX_MINUTES, maximum.duration?.totalMinutes)
        assertEquals(TimerDuration.MAX_MINUTES, rejected.duration?.totalMinutes)
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
    fun `invalid digit entry is rejected`() {
        val invalid = "2401".fold(DurationDigitEntry.empty()) { entry, digit ->
            entry.append(digit.digitToInt())
        }

        assertEquals(160, invalid.duration?.totalMinutes)
    }
}
