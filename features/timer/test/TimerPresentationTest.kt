package nl.entreco.elchrono.features.timer

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.counter.TimerSnapshot
import nl.entreco.elchrono.core.counter.TimerStatus
import nl.entreco.elchrono.core.design.TimerTheme

class TimerPresentationTest {
    @Test
    fun `ring thickness keeps a fixed outer radius and shared track progress centerline`() {
        listOf(24f, 100f, 400f).forEach { radius ->
            (1..100).forEach { thickness ->
                val geometry = ringGeometry(radius, thickness)
                assertEquals(radius, geometry.outerRadius)
                assertEquals(radius, geometry.centerlineRadius + geometry.strokeWidth / 2f, 0.001f)
                assertEquals(geometry.innerRadius, geometry.centerlineRadius - geometry.strokeWidth / 2f, 0.001f)
                assertTrue(geometry.innerRadius >= 0f)
                if (thickness == 100) assertEquals(0f, geometry.innerRadius)
            }
        }
        assertFailsWith<IllegalArgumentException> { ringGeometry(10f, 0) }
        assertFailsWith<IllegalArgumentException> { ringGeometry(10f, 101) }
    }

    @Test
    fun `clock label envelope covers every detail without depending on visible labels`() {
        (0..1440).forEach { duration ->
            val values = clockLabelEnvelope(duration).map { it.value }.toSet()
            (0..12).forEach { detail ->
                assertTrue(clockDurationLabels(duration, detail).all { it.value in values })
            }
        }
    }

    @Test
    fun `battery stays shallow centered and includes its terminal within bounds`() {
        listOf(272f to 400f, 600f to 180f, 1000f to 700f).forEach { (width, height) ->
            val geometry = batteryGeometry(width, height)
            assertTrue(geometry.bodyWidth / geometry.bodyHeight >= 3.6f - 0.001f)
            assertTrue(geometry.bodyHeight <= 126f)
            assertTrue(geometry.left >= 0f && geometry.top >= 0f)
            assertTrue(geometry.left + geometry.bodyWidth + geometry.terminalWidth <= width)
            assertTrue(geometry.top + geometry.bodyHeight <= height)
            assertEquals(width / 2f, geometry.left + (geometry.bodyWidth + geometry.terminalWidth) / 2f)
            assertEquals(height / 2f, geometry.top + geometry.bodyHeight / 2f)
        }
        assertEquals(batteryGeometry(1000f, 400f).bodyHeight, batteryGeometry(1000f, 1000f).bodyHeight)
    }

    @Test
    fun `clock has no segment boundaries while other shapes expose equal divisions`() {
        (0..12).forEach { detail ->
            TimerTheme.entries.forEach { theme ->
                val boundaries = visualizationBoundaryFractions(theme, detail)
                assertEquals(
                    if (theme == TimerTheme.CLOCK || detail == 0) 0 else detail - 1,
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
    fun `clock duration labels follow detail count and circle positions`() {
        (1..12).forEach { detail ->
            val labels = clockDurationLabels(totalMinutes = 60, detail = detail)
            assertEquals(detail, labels.size, "detail $detail")
            assertEquals(-90f, labels.first().angleDegrees, "detail $detail starts at 12")
        }
        assertEquals(
            listOf(
                ClockDurationLabel("0", -90f),
                ClockDurationLabel("30", 90f),
            ),
            clockDurationLabels(totalMinutes = 60, detail = 2),
        )
        assertEquals(
            listOf(
                ClockDurationLabel("0", -90f),
                ClockDurationLabel("100", -30f),
                ClockDurationLabel("80", 30f),
                ClockDurationLabel("60", 90f),
                ClockDurationLabel("40", 150f),
                ClockDurationLabel("20", 210f),
            ),
            clockDurationLabels(totalMinutes = 120, detail = 6),
        )
        assertEquals(
            listOf(
                ClockDurationLabel("0", -90f),
                ClockDurationLabel("360", 30f),
                ClockDurationLabel("180", 150f),
            ),
            clockDurationLabels(totalMinutes = 9, detail = 3),
        )
        assertEquals(
            listOf(
                ClockDurationLabel("0", -90f),
                ClockDurationLabel("7.5", 0f),
                ClockDurationLabel("5", 90f),
                ClockDurationLabel("2.5", 180f),
            ),
            clockDurationLabels(totalMinutes = 10, detail = 4),
        )
    }

    @Test
    fun `clock labels and units are omitted when detail or duration is zero`() {
        assertEquals(emptyList(), clockDurationLabels(totalMinutes = 60, detail = 0))
        assertEquals(emptyList(), clockDurationLabels(totalMinutes = 0, detail = 2))
        assertEquals(ClockDurationUnit.SECONDS, clockDurationUnit(9))
        assertEquals(ClockDurationUnit.MINUTES, clockDurationUnit(10))
    }

    @Test
    fun `clock labels remain inside compact and expanded layouts`() {
        listOf(
            272f to 400f,
            600f to 240f,
            1_000f to 700f,
        ).forEach { (width, height) ->
            val labelWidth = 68f
            val labelHeight = 34f
            val geometry = clockLayoutGeometry(width, height, labelWidth, labelHeight)
            assertTrue(geometry.diskRadius >= 0f)
            assertTrue(geometry.labelOrbitRadius + labelWidth / 2f <= width / 2f - 8f)
            assertTrue(geometry.labelOrbitRadius + labelHeight / 2f <= height / 2f - 8f)
            assertTrue(geometry.diskRadius < geometry.labelOrbitRadius)
        }
    }

    @Test
    fun `fan semicircle is centered and fully inside compact and expanded layouts`() {
        listOf(
            272f to 400f,
            600f to 240f,
            1_000f to 700f,
        ).forEach { (width, height) ->
            val geometry = fanGeometry(width, height)
            val top = geometry.centerY - geometry.radius
            val bottom = geometry.centerY
            assertTrue(geometry.radius <= width / 2f)
            assertTrue(top >= 0f)
            assertTrue(bottom <= height)
            assertEquals(height / 2f, (top + bottom) / 2f)
        }
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
        assertFailsWith<IllegalArgumentException> { clockDurationLabels(60, -1) }
        assertFailsWith<IllegalArgumentException> { clockDurationLabels(1441, 2) }
        assertFailsWith<IllegalArgumentException> {
            visualizationBoundaryFractions(TimerTheme.CLOCK, 13)
        }
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
