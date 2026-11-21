package nl.entreco.elchrono

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import nl.entreco.elchrono.core.counter.MonotonicClock
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.counter.TimerEngine
import nl.entreco.elchrono.core.counter.TimerStatus
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.features.settings.TimerSettings

class AppViewModelTest {
    @Test
    fun `ticking requires running and not interrupted`() {
        val viewModel = AppViewModel(TimerEngine(TestMonotonicClock()))
        val initial = viewModel.uiState.value
        assertFalse(initial.isTicking)

        viewModel.saveSettings(settings(5))
        val running = viewModel.uiState.value
        assertTrue(running.isTicking)
        assertFalse(running.copy(interrupted = true).isTicking)

        viewModel.openSettings()
        val paused = viewModel.uiState.value
        assertFalse(paused.isTicking)
        assertFalse(paused.copy(interrupted = true).isTicking)
        assertFalse(initial.copy(interrupted = true).isTicking)

        viewModel.saveSettings(settings(5))
        viewModel.onAppStopped()
        assertFalse(viewModel.uiState.value.isTicking)
        viewModel.saveSettings(settings(5))
        assertTrue(viewModel.uiState.value.isTicking)
    }

    @Test
    fun `tick updates running timer and ignores a paused timer`() {
        val clock = TestMonotonicClock()
        val viewModel = AppViewModel(TimerEngine(clock))
        viewModel.tick()
        assertFalse(viewModel.uiState.value.isTicking)

        viewModel.saveSettings(settings(5))
        clock.advance(1.minutes)
        viewModel.tick()
        assertEquals(4.minutes, viewModel.uiState.value.timer.snapshot.remaining)

        viewModel.openSettings()
        val paused = viewModel.uiState.value
        clock.advance(1.minutes)
        viewModel.tick()
        assertEquals(paused, viewModel.uiState.value)
    }

    @Test
    fun `about navigation returns to settings and then timer`() {
        val viewModel = AppViewModel(TimerEngine(TestMonotonicClock()))

        viewModel.openSettings()
        viewModel.showAbout()
        assertEquals(AppScreen.ABOUT, viewModel.uiState.value.screen)

        viewModel.onBackPressed()
        assertEquals(AppScreen.SETTINGS, viewModel.uiState.value.screen)
        viewModel.onBackPressed()
        assertEquals(AppScreen.TIMER, viewModel.uiState.value.screen)
    }

    @Test
    fun `back closes settings and returns from about before closing settings`() {
        val viewModel = AppViewModel(TimerEngine(TestMonotonicClock()))

        viewModel.openSettings()
        viewModel.onBackPressed()
        assertEquals(AppScreen.TIMER, viewModel.uiState.value.screen)

        viewModel.openSettings()
        viewModel.showAbout()
        viewModel.onBackPressed()
        assertEquals(AppScreen.SETTINGS, viewModel.uiState.value.screen)

        viewModel.onBackPressed()
        assertEquals(AppScreen.TIMER, viewModel.uiState.value.screen)
    }

    @Test
    fun `navigation never removes the timer root or pushes duplicate destinations`() {
        val viewModel = AppViewModel(TimerEngine(TestMonotonicClock()))

        viewModel.onBackPressed()
        viewModel.showAbout()
        assertEquals(listOf(AppScreen.TIMER), viewModel.uiState.value.backStack)

        viewModel.openSettings()
        viewModel.openSettings()
        viewModel.showAbout()
        viewModel.showAbout()
        assertEquals(
            listOf(AppScreen.TIMER, AppScreen.SETTINGS, AppScreen.ABOUT),
            viewModel.uiState.value.backStack,
        )

        viewModel.saveSettings(settings(5))
        assertEquals(listOf(AppScreen.TIMER), viewModel.uiState.value.backStack)
    }

    @Test
    fun `opening settings pauses and saving starts a full new countdown`() {
        val clock = TestMonotonicClock()
        val viewModel = AppViewModel(TimerEngine(clock))
        val settings = settings(5)
        viewModel.saveSettings(settings)
        clock.advance(2.minutes)

        viewModel.openSettings()

        assertEquals(AppScreen.SETTINGS, viewModel.uiState.value.screen)
        assertEquals(TimerStatus.PAUSED, viewModel.uiState.value.timer.snapshot.status)
        assertEquals(3.minutes, viewModel.uiState.value.timer.snapshot.remaining)

        clock.advance(1.minutes)
        viewModel.saveSettings(settings(2))

        assertEquals(AppScreen.TIMER, viewModel.uiState.value.screen)
        assertEquals(TimerStatus.RUNNING, viewModel.uiState.value.timer.snapshot.status)
        assertEquals(2.minutes, viewModel.uiState.value.timer.snapshot.remaining)
    }

    @Test
    fun `stopping while running pauses and marks interruption without automatic resume`() {
        val clock = TestMonotonicClock()
        val viewModel = AppViewModel(TimerEngine(clock))
        viewModel.saveSettings(settings(5))
        clock.advance(2.minutes)

        viewModel.onAppStopped()
        clock.advance(1.minutes)
        viewModel.tick()

        assertTrue(viewModel.uiState.value.interrupted)
        assertEquals(TimerStatus.PAUSED, viewModel.uiState.value.timer.snapshot.status)
        assertEquals(3.minutes, viewModel.uiState.value.timer.snapshot.remaining)
    }

    @Test
    fun `repeated stop events do not change paused remaining time`() {
        val clock = TestMonotonicClock()
        val viewModel = AppViewModel(TimerEngine(clock))
        viewModel.saveSettings(settings(5))
        clock.advance(2.minutes)
        viewModel.onAppStopped()
        clock.advance(1.minutes)

        viewModel.onAppStopped()

        assertEquals(3.minutes, viewModel.uiState.value.timer.snapshot.remaining)
        assertTrue(viewModel.uiState.value.interrupted)
    }

    @Test
    fun `stopping before timer starts does not mark an interruption`() {
        val viewModel = AppViewModel(TimerEngine(TestMonotonicClock()))

        viewModel.onAppStopped()

        assertFalse(viewModel.uiState.value.interrupted)
        assertEquals(TimerStatus.NOT_CONFIGURED, viewModel.uiState.value.timer.snapshot.status)
    }

    @Test
    fun `saving after interruption clears warning and restarts the full duration`() {
        val viewModel = AppViewModel(TimerEngine(TestMonotonicClock()))
        viewModel.saveSettings(settings(5))
        viewModel.onAppStopped()

        viewModel.saveSettings(settings(2))

        assertFalse(viewModel.uiState.value.interrupted)
        assertEquals(TimerStatus.RUNNING, viewModel.uiState.value.timer.snapshot.status)
        assertEquals(2.minutes, viewModel.uiState.value.timer.snapshot.remaining)
    }

    @Test
    fun `tick reports completion and timer remains running at full duration`() {
        val clock = TestMonotonicClock()
        val viewModel = AppViewModel(TimerEngine(clock))
        viewModel.saveSettings(settings(1))
        clock.advance(1.minutes)

        viewModel.tick()

        assertEquals(1, viewModel.uiState.value.timer.completedCycles)
        assertEquals(TimerStatus.RUNNING, viewModel.uiState.value.timer.snapshot.status)
        assertEquals(1.minutes, viewModel.uiState.value.timer.snapshot.remaining)
    }

    private fun settings(minutes: Int) = TimerSettings(
        duration = checkNotNull(TimerDuration.fromMinutes(minutes)),
        theme = TimerTheme.CLOCK,
    )

    private class TestMonotonicClock : MonotonicClock {
        private var currentNanos = 0L

        override fun nowNanos(): Long = currentNanos

        fun advance(duration: Duration) {
            currentNanos += duration.inWholeNanoseconds
        }
    }
}
