package nl.entreco.elchrono.features.settings

import nl.entreco.elchrono.core.counter.TimerDuration
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DurationFieldEntryTest {
    @Test
    fun `hours edits preserve minutes until combined duration exceeds maximum`() {
        val initial = DurationFieldEntry.fromDuration(duration(125))

        val updated = initial.editHours("3")

        assertEquals(3, updated?.entry?.hours)
        assertEquals(5, updated?.entry?.minutes)
        assertEquals(185, updated?.entry?.duration?.totalMinutes)
        assertEquals("3", updated?.text)
    }

    @Test
    fun `empty field immediately contributes zero`() {
        val initial = DurationFieldEntry.fromDuration(duration(125))

        val updated = initial.editMinutes("")

        assertEquals(2, updated?.entry?.hours)
        assertEquals(0, updated?.entry?.minutes)
        assertEquals(120, updated?.entry?.duration?.totalMinutes)
        assertEquals("", updated?.text)
    }

    @Test
    fun `sixty minutes carries one hour and resets minutes`() {
        val initial = DurationFieldEntry.fromDuration(duration(23 * 60 + 59))

        val updated = initial.editMinutes("60")

        assertEquals(24, updated?.entry?.hours)
        assertEquals(0, updated?.entry?.minutes)
        assertEquals(TimerDuration.MAX_MINUTES, updated?.entry?.duration?.totalMinutes)
        assertEquals("00", updated?.text)
    }

    @Test
    fun `hours clamp to the maximum and remove minutes beyond the limit`() {
        val initial = DurationFieldEntry.fromDuration(duration(23 * 60 + 30))

        val updated = initial.editHours("99")

        assertEquals(24, updated?.entry?.hours)
        assertEquals(0, updated?.entry?.minutes)
        assertEquals(TimerDuration.MAX_MINUTES, updated?.entry?.duration?.totalMinutes)
        assertEquals("24", updated?.text)
    }

    @Test
    fun `minutes clamp to sixty then carry`() {
        val initial = DurationFieldEntry.fromDuration(duration(4 * 60 + 30))

        val updated = initial.editMinutes("99")

        assertEquals(5, updated?.entry?.hours)
        assertEquals(0, updated?.entry?.minutes)
        assertEquals(300, updated?.entry?.duration?.totalMinutes)
        assertEquals("00", updated?.text)
    }

    @Test
    fun `combined duration never exceeds twenty four hours`() {
        val initial = DurationFieldEntry.fromDuration(duration(TimerDuration.MAX_MINUTES))

        val updated = initial.editMinutes("01")

        assertEquals(TimerDuration.MAX_MINUTES, updated?.entry?.duration?.totalMinutes)
        assertEquals(0, updated?.entry?.minutes)
    }

    @Test
    fun `non ASCII digits and overlength edits are rejected`() {
        val initial = DurationFieldEntry.fromDuration(duration(125))

        assertNull(initial.editHours("1x"))
        assertNull(initial.editHours("123"))
        assertNull(initial.editMinutes("１２"))
    }

    @Test
    fun `idle and allowed duration boundaries are representable`() {
        assertEquals(0, DurationFieldEntry.fromDuration(duration(0)).duration.totalMinutes)
        assertEquals(1, DurationFieldEntry.fromDuration(duration(1)).duration.totalMinutes)
        assertEquals(
            TimerDuration.MAX_MINUTES,
            DurationFieldEntry.fromDuration(duration(TimerDuration.MAX_MINUTES)).duration.totalMinutes,
        )
    }

    @Test
    fun `every allowed whole minute duration can be entered through the fields`() {
        (0..TimerDuration.MAX_MINUTES).forEach { totalMinutes ->
            val expected = duration(totalMinutes)
            var entry = DurationFieldEntry.fromDuration(duration(0))
            entry = checkNotNull(entry.editHours(expected.hours.toString())).entry
            entry = checkNotNull(entry.editMinutes(expected.minutes.toString())).entry

            assertEquals(totalMinutes, entry.duration.totalMinutes)
        }
    }

    private fun duration(totalMinutes: Int) = checkNotNull(TimerDuration.fromMinutes(totalMinutes))
}
