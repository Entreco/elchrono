package nl.entreco.elchrono.features.timer

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.counter.TimerSnapshot
import nl.entreco.elchrono.core.counter.TimerStatus
import nl.entreco.elchrono.core.design.TimerTheme

class TimerPresentationTest {
    @Test
    fun `all shapes expose the configured number of equal segments`() {
        (0..12).forEach { detail ->
            TimerTheme.entries.forEach { theme ->
                val boundaries = segmentBoundaryFractions(detail)
                assertEquals(
                    if (detail == 0) 0 else detail - 1,
                    boundaries.size,
                    "$theme at detail $detail",
                )
                boundaries.forEachIndexed { index, fraction ->
                    assertEquals((index + 1).toFloat() / detail, fraction)
                }
            }
        }
    }

    @Test
    fun `one detail segment has no internal boundary`() {
        assertEquals(emptyList(), segmentBoundaryFractions(1))
    }

    @Test
    fun `zero detail hides text and uses a visual state cue`() {
        (0..12).forEach { detail ->
            assertEquals(detail > 0, showsTimerText(detail))
            assertEquals(detail == 0, showsTextFreeStateCue(detail))
        }
    }

    @Test
    fun `details outside the supported range are rejected`() {
        assertFailsWith<IllegalArgumentException> { segmentBoundaryFractions(-1) }
        assertFailsWith<IllegalArgumentException> { segmentBoundaryFractions(13) }
    }

    @Test
    fun `clock remaining arc moves clockwise from twelve as time elapses`() {
        assertEquals(RemainingArc(-90f, 360f), remainingArc(1f))
        assertEquals(RemainingArc(0f, 270f), remainingArc(0.75f))
        assertEquals(RemainingArc(90f, 180f), remainingArc(0.5f))
        assertEquals(RemainingArc(270f, 0f), remainingArc(0f))
    }

    @Test
    fun `remaining arc clamps invalid fractions`() {
        assertEquals(remainingArc(0f), remainingArc(-1f))
        assertEquals(remainingArc(1f), remainingArc(2f))
    }

    @Test
    fun `timer states map to distinct presentation states`() {
        assertEquals(
            TimerVisualState.RUNNING,
            timerVisualState(snapshot(TimerStatus.RUNNING), interrupted = false),
        )
        assertEquals(
            TimerVisualState.PAUSED,
            timerVisualState(snapshot(TimerStatus.PAUSED), interrupted = false),
        )
        assertEquals(
            TimerVisualState.INTERRUPTED,
            timerVisualState(snapshot(TimerStatus.PAUSED), interrupted = true),
        )
        assertEquals(
            TimerVisualState.IDLE,
            timerVisualState(snapshot(TimerStatus.NOT_CONFIGURED), interrupted = false),
        )
    }

    private fun snapshot(status: TimerStatus) = TimerSnapshot(
        status = status,
        configuredDuration = checkNotNull(TimerDuration.fromMinutes(60)),
        remaining = null,
    )
}
