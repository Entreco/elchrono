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
import nl.entreco.elchrono.core.persistence.TimerSettings
import nl.entreco.elchrono.core.persistence.SettingsRepository
import nl.entreco.elchrono.core.persistence.RestoredSettings
import nl.entreco.elchrono.core.persistence.StorageResult

class AppViewModelTest {
    @Test
    fun `initial configuration waits for explicit action`() {
        val viewModel = testViewModel()
        assertEquals(5.minutes, viewModel.uiState.value.timer.snapshot.remaining)
        assertFalse(viewModel.uiState.value.isTicking)
        viewModel.openSettings()
        viewModel.onBackPressed()
        assertFalse(viewModel.uiState.value.isTicking)
        viewModel.onTimerAction()
        assertTrue(viewModel.uiState.value.isTicking)
    }

    @Test
    fun `settings round trip resumes remaining time without a reset`() {
        val clock = TestMonotonicClock()
        val viewModel = testViewModel(clock)
        viewModel.onTimerAction()
        clock.advance(2.minutes)
        viewModel.openSettings()
        assertEquals(3.minutes, viewModel.uiState.value.timer.snapshot.remaining)
        assertFalse(viewModel.uiState.value.isTicking)
        clock.advance(100.minutes)
        viewModel.tick()
        viewModel.onBackPressed()
        assertEquals(3.minutes, viewModel.uiState.value.timer.snapshot.remaining)
        assertTrue(viewModel.uiState.value.isTicking)
    }

    @Test
    fun `actual duration edits reset immediately and close resumes only prior running timer`() {
        val clock = TestMonotonicClock()
        val viewModel = testViewModel(clock)
        viewModel.onTimerAction()
        clock.advance(2.minutes)
        viewModel.openSettings()
        listOf(0, 1, 9, 10, 60, 120, 1440).forEach { minutes ->
            viewModel.updateSettings(settings(minutes))
            val state = viewModel.uiState.value
            assertEquals(minutes.minutes, state.timer.snapshot.remaining)
            assertEquals(minutes.minutes, state.presentationSnapshot.remaining)
            assertFalse(state.isTicking)
            clock.advance(1.minutes)
            viewModel.tick()
            assertEquals(state, viewModel.uiState.value)
        }
        viewModel.closeSettings()
        assertEquals(1440.minutes, viewModel.uiState.value.timer.snapshot.remaining)
        assertTrue(viewModel.uiState.value.isTicking)
    }

    @Test
    fun `appearance and unchanged duration edits never reset engine`() {
        val clock = TestMonotonicClock()
        val viewModel = testViewModel(clock)
        viewModel.onTimerAction()
        clock.advance(2.minutes)
        viewModel.openSettings()
        val paused = viewModel.uiState.value.timer
        viewModel.updateSettings(settings(5).copy(theme = TimerTheme.BATTERY, detail = 0))
        assertEquals(paused, viewModel.uiState.value.timer)
        assertEquals(5.minutes, viewModel.uiState.value.presentationSnapshot.remaining)
        viewModel.closeSettings()
        assertEquals(3.minutes, viewModel.uiState.value.timer.snapshot.remaining)
    }

    @Test
    fun `manual pause survives settings and duration change`() {
        val clock = TestMonotonicClock()
        val viewModel = testViewModel(clock)
        viewModel.onTimerAction()
        clock.advance(2.minutes)
        viewModel.onTimerAction()
        viewModel.openSettings()
        viewModel.closeSettings()
        assertEquals(3.minutes, viewModel.uiState.value.timer.snapshot.remaining)
        assertFalse(viewModel.uiState.value.isTicking)
        viewModel.openSettings()
        viewModel.updateSettings(settings(2))
        viewModel.closeSettings()
        assertEquals(2.minutes, viewModel.uiState.value.timer.snapshot.remaining)
        assertFalse(viewModel.uiState.value.isTicking)
        viewModel.onTimerAction()
        assertTrue(viewModel.uiState.value.isTicking)
    }

    @Test
    fun `completion stops at zero and settings cannot restart it`() {
        val clock = TestMonotonicClock()
        val viewModel = testViewModel(clock)
        viewModel.onTimerAction()
        clock.advance(10.minutes)
        viewModel.tick()
        assertEquals(1, viewModel.uiState.value.timer.completedCycles)
        assertEquals(TimerStatus.FINISHED, viewModel.uiState.value.timer.snapshot.status)
        assertEquals(Duration.ZERO, viewModel.uiState.value.timer.snapshot.remaining)
        assertFalse(viewModel.uiState.value.isTicking)
        viewModel.openSettings()
        viewModel.closeSettings()
        assertEquals(TimerStatus.FINISHED, viewModel.uiState.value.timer.snapshot.status)
        viewModel.onTimerAction()
        assertEquals(5.minutes, viewModel.uiState.value.timer.snapshot.remaining)
        assertTrue(viewModel.uiState.value.isTicking)
    }

    @Test
    fun `duration edit after completion prepares paused full countdown`() {
        val clock = TestMonotonicClock()
        val viewModel = testViewModel(clock)
        viewModel.onTimerAction()
        clock.advance(5.minutes)
        viewModel.tick()
        viewModel.openSettings()
        viewModel.updateSettings(settings(2))
        viewModel.closeSettings()
        assertEquals(TimerStatus.PAUSED, viewModel.uiState.value.timer.snapshot.status)
        assertEquals(2.minutes, viewModel.uiState.value.timer.snapshot.remaining)
        assertFalse(viewModel.uiState.value.isTicking)
    }

    @Test
    fun `opening settings at completion boundary does not resume finished countdown`() {
        val clock = TestMonotonicClock()
        val viewModel = testViewModel(clock)
        viewModel.onTimerAction()
        clock.advance(5.minutes)
        viewModel.openSettings()
        assertEquals(1, viewModel.uiState.value.timer.completedCycles)
        viewModel.closeSettings()
        assertEquals(TimerStatus.FINISHED, viewModel.uiState.value.timer.snapshot.status)
    }

    @Test
    fun `zero remains idle and manual action cannot start it`() {
        val viewModel = testViewModel()
        viewModel.onTimerAction()
        viewModel.openSettings()
        viewModel.updateSettings(settings(0))
        viewModel.closeSettings()
        viewModel.onTimerAction()
        assertEquals(TimerStatus.NOT_CONFIGURED, viewModel.uiState.value.timer.snapshot.status)
        assertEquals(Duration.ZERO, viewModel.uiState.value.timer.snapshot.remaining)
        assertFalse(viewModel.uiState.value.isTicking)
    }

    @Test
    fun `about navigation cannot resume or trigger timer controls behind settings`() {
        val viewModel = testViewModel()
        viewModel.onTimerAction()
        viewModel.openSettings()
        viewModel.openSettings()
        viewModel.showAbout()
        viewModel.showAbout()
        assertEquals(listOf(AppScreen.TIMER, AppScreen.SETTINGS, AppScreen.ABOUT), viewModel.uiState.value.backStack)
        viewModel.onTimerAction()
        assertFalse(viewModel.uiState.value.isTicking)
        viewModel.onBackPressed()
        assertEquals(AppScreen.SETTINGS, viewModel.uiState.value.screen)
        assertFalse(viewModel.uiState.value.isTicking)
        viewModel.onBackPressed()
        assertTrue(viewModel.uiState.value.isTicking)
        viewModel.onBackPressed()
        viewModel.showAbout()
        assertEquals(listOf(AppScreen.TIMER), viewModel.uiState.value.backStack)
    }

    @Test
    fun `interruption requires explicit restart and settings cannot clear warning`() {
        val clock = TestMonotonicClock()
        val viewModel = testViewModel(clock)
        viewModel.onTimerAction()
        clock.advance(2.minutes)
        viewModel.onAppStopped()
        clock.advance(20.minutes)
        viewModel.onAppStopped()
        viewModel.tick()
        assertEquals(3.minutes, viewModel.uiState.value.timer.snapshot.remaining)
        assertTrue(viewModel.uiState.value.interrupted)
        viewModel.openSettings()
        viewModel.updateSettings(settings(2))
        viewModel.closeSettings()
        assertTrue(viewModel.uiState.value.interrupted)
        assertFalse(viewModel.uiState.value.isTicking)
        viewModel.onTimerAction()
        assertFalse(viewModel.uiState.value.interrupted)
        assertEquals(2.minutes, viewModel.uiState.value.timer.snapshot.remaining)
        assertTrue(viewModel.uiState.value.isTicking)
    }

    @Test
    fun `stop while settings holds running intent prevents automatic resume`() {
        val viewModel = testViewModel()
        viewModel.onTimerAction()
        viewModel.openSettings()
        viewModel.onAppStopped()
        viewModel.closeSettings()
        assertTrue(viewModel.uiState.value.interrupted)
        assertFalse(viewModel.uiState.value.isTicking)
    }

    @Test
    fun `stopping manual paused or finished timers does not mark interruption`() {
        val clock = TestMonotonicClock()
        val viewModel = testViewModel(clock)
        viewModel.onAppStopped()
        assertFalse(viewModel.uiState.value.interrupted)
        viewModel.onTimerAction()
        clock.advance(5.minutes)
        viewModel.onAppStopped()
        assertEquals(TimerStatus.FINISHED, viewModel.uiState.value.timer.snapshot.status)
        assertFalse(viewModel.uiState.value.interrupted)
    }

    @Test
    fun `persistence failure remains exposed and initial failure is retained`() {
        val viewModel = AppViewModel(
            TimerEngine(TestMonotonicClock()),
            StubSettingsRepository(StorageResult.Failure("Save denied")),
            settings(5),
            "Restore failed",
        )
        assertEquals("Restore failed", viewModel.uiState.value.persistenceFailure)
        viewModel.openSettings()
        viewModel.saveSettings(settings(2))
        assertEquals("Save denied", viewModel.uiState.value.persistenceFailure)
        assertEquals(2.minutes, viewModel.uiState.value.timer.snapshot.remaining)
        assertFalse(viewModel.uiState.value.isTicking)
    }

    private fun settings(minutes: Int) = TimerSettings(
        duration = checkNotNull(TimerDuration.fromMinutes(minutes)),
        theme = TimerTheme.CLOCK,
    )

    private fun testViewModel(clock: TestMonotonicClock = TestMonotonicClock()) =
        AppViewModel(TimerEngine(clock), StubSettingsRepository(StorageResult.Success(Unit)), settings(5), null)

    private class StubSettingsRepository(private val saveResult: StorageResult<Unit>) : SettingsRepository {
        override fun load() = RestoredSettings(
            TimerSettings(checkNotNull(TimerDuration.fromMinutes(5)), TimerTheme.CLOCK), null,
        )
        override fun save(settings: TimerSettings): StorageResult<Unit> = saveResult
    }

    private class TestMonotonicClock : MonotonicClock {
        private var currentNanos = 0L
        override fun nowNanos(): Long = currentNanos
        fun advance(duration: Duration) {
            currentNanos += duration.inWholeNanoseconds
        }
    }
}
