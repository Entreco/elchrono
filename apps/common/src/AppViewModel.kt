package nl.entreco.elchrono

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.counter.TimerEngine
import nl.entreco.elchrono.core.counter.TimerStatus
import nl.entreco.elchrono.core.counter.TimerUpdate
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.features.settings.TimerSettings

enum class AppScreen {
    TIMER,
    SETTINGS,
    ABOUT,
}

private val TimerBackStack = listOf(AppScreen.TIMER)

data class AppUiState(
    val timer: TimerUpdate,
    val settings: TimerSettings,
    val backStack: List<AppScreen> = TimerBackStack,
    val interrupted: Boolean = false,
) {
    val screen: AppScreen get() = backStack.last()
    val isTicking: Boolean get() = timer.snapshot.status == TimerStatus.RUNNING && !interrupted
}

class AppViewModel(
    private val timerEngine: TimerEngine = TimerEngine(),
    initialSettings: TimerSettings = defaultTimerSettings(),
) : ViewModel() {
    val uiState: StateFlow<AppUiState>
        field = MutableStateFlow(
            AppUiState(
                timer = timerEngine.current(),
                settings = initialSettings,
            ),
        )

    fun openSettings() {
        val state = uiState.value
        if (state.screen != AppScreen.TIMER) return
        val timer = if (state.timer.snapshot.status == TimerStatus.RUNNING) {
            timerEngine.openSettings()
        } else {
            state.timer
        }
        uiState.value = state.copy(backStack = state.backStack + AppScreen.SETTINGS, timer = timer)
    }

    fun showAbout() {
        val state = uiState.value
        if (state.screen == AppScreen.SETTINGS) {
            uiState.value = state.copy(backStack = state.backStack + AppScreen.ABOUT)
        }
    }

    fun onBackPressed() {
        val state = uiState.value
        if (state.backStack.size > 1) {
            uiState.value = state.copy(backStack = state.backStack.dropLast(1))
        }
    }

    fun saveSettings(settings: TimerSettings) {
        uiState.value = uiState.value.copy(
            timer = timerEngine.saveSettings(settings.duration),
            settings = settings,
            backStack = TimerBackStack,
            interrupted = false,
        )
    }

    fun onAppStopped() {
        val state = uiState.value
        if (state.timer.snapshot.status != TimerStatus.RUNNING) return

        uiState.value = state.copy(
            timer = timerEngine.openSettings(),
            interrupted = true,
        )
    }

    fun tick() {
        val state = uiState.value
        if (!state.isTicking) return
        uiState.value = state.copy(timer = timerEngine.current())
    }
}

private fun defaultTimerSettings() = TimerSettings(
    duration = checkNotNull(TimerDuration.fromMinutes(DEFAULT_DURATION_MINUTES)),
    theme = TimerTheme.CLOCK,
)

private const val DEFAULT_DURATION_MINUTES = 120
