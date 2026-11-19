package nl.entreco.elchrono

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

data class AppUiState(
    val timer: TimerUpdate,
    val settings: TimerSettings,
    val screen: AppScreen = AppScreen.TIMER,
    val interrupted: Boolean = false,
)

class AppViewModel(
    private val timerEngine: TimerEngine = TimerEngine(),
    initialSettings: TimerSettings = defaultTimerSettings(),
) : ViewModel() {
    private val mutableUiState = MutableStateFlow(
        AppUiState(
            timer = timerEngine.current(),
            settings = initialSettings,
        ),
    )

    val uiState = mutableUiState.asStateFlow()

    fun openSettings() {
        val state = mutableUiState.value
        val timer = if (state.timer.snapshot.status == TimerStatus.RUNNING) {
            timerEngine.openSettings()
        } else {
            state.timer
        }
        mutableUiState.value = state.copy(screen = AppScreen.SETTINGS, timer = timer)
    }

    fun closeSettings() {
        mutableUiState.value = mutableUiState.value.copy(screen = AppScreen.TIMER)
    }

    fun showAbout() {
        mutableUiState.value = mutableUiState.value.copy(screen = AppScreen.ABOUT)
    }

    fun backToSettings() {
        mutableUiState.value = mutableUiState.value.copy(screen = AppScreen.SETTINGS)
    }

    fun saveSettings(settings: TimerSettings) {
        mutableUiState.value = mutableUiState.value.copy(
            timer = timerEngine.saveSettings(settings.duration),
            settings = settings,
            screen = AppScreen.TIMER,
            interrupted = false,
        )
    }

    fun onAppStopped() {
        val state = mutableUiState.value
        if (state.timer.snapshot.status != TimerStatus.RUNNING) return

        mutableUiState.value = state.copy(
            timer = timerEngine.openSettings(),
            interrupted = true,
        )
    }

    fun tick() {
        val state = mutableUiState.value
        if (state.interrupted || state.timer.snapshot.status != TimerStatus.RUNNING) return
        mutableUiState.value = state.copy(timer = timerEngine.current())
    }
}

private fun defaultTimerSettings() = TimerSettings(
    duration = checkNotNull(TimerDuration.fromMinutes(DEFAULT_DURATION_MINUTES)),
    theme = TimerTheme.CLOCK,
)

private const val DEFAULT_DURATION_MINUTES = 120
