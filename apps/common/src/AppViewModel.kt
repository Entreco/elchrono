package nl.entreco.elchrono

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.counter.TimerEngine
import nl.entreco.elchrono.core.counter.TimerStatus
import nl.entreco.elchrono.core.counter.TimerSnapshot
import nl.entreco.elchrono.core.counter.TimerUpdate
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.persistence.SettingsRepository
import nl.entreco.elchrono.core.persistence.StorageResult
import nl.entreco.elchrono.core.persistence.TimerSettings

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
    val persistenceFailure: String? = null,
) {

    val screen: AppScreen get() = backStack.last()
    val isTicking: Boolean get() = timer.snapshot.status == TimerStatus.RUNNING && !interrupted && !isPreviewing
    val isPreviewing: Boolean get() = AppScreen.SETTINGS in backStack
    val presentationSnapshot: TimerSnapshot
        get() = if (isPreviewing) {
            TimerSnapshot(
                status = if (settings.duration.totalMinutes == 0) TimerStatus.NOT_CONFIGURED else TimerStatus.PAUSED,
                configuredDuration = settings.duration,
                remaining = settings.duration.asDuration,
            )
        } else {
            timer.snapshot
        }
}

class AppViewModel(
    private val timerEngine: TimerEngine,
    private val settingsRepository: SettingsRepository,
    initialSettings: TimerSettings,
    initialPersistenceFailure: String?,
) : ViewModel() {
    private var resumeAfterSettings = false

    val uiState: StateFlow<AppUiState>
        field = MutableStateFlow(
            AppUiState(
                timer = timerEngine.current().let { timer ->
                    if (timer.snapshot.configuredDuration == null) {
                        timerEngine.reset(initialSettings.duration)
                    } else {
                        timer
                    }
                },
                settings = initialSettings,
                persistenceFailure = initialPersistenceFailure,
            ),
        )

    fun openSettings() {
        val state = uiState.value
        if (state.screen != AppScreen.TIMER) return
        val timer = timerEngine.pause()
        resumeAfterSettings = state.timer.snapshot.status == TimerStatus.RUNNING &&
                timer.snapshot.status == TimerStatus.PAUSED && !state.interrupted
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
            val removed = state.backStack.dropLast(1)
            if (state.screen == AppScreen.SETTINGS) {
                closeSettings(state.settings)
            } else {
                uiState.value = state.copy(backStack = removed)
            }
        }
    }

    fun saveSettings(settings: TimerSettings) {
        updateSettings(settings)
        closeSettings(settings)
    }

    fun updateSettings(settings: TimerSettings) {
        val state = uiState.value
        val timer = if (settings.duration != state.settings.duration) {
            timerEngine.reset(settings.duration)
        } else {
            state.timer
        }
        val saved = settingsRepository.save(settings)
        uiState.value = state.copy(
            timer = timer,
            settings = settings,
            persistenceFailure = (saved as? StorageResult.Failure)?.message,
        )
    }

    fun closeSettings(settings: TimerSettings = uiState.value.settings) {
        if (settings != uiState.value.settings) updateSettings(settings)
        val state = uiState.value
        val timer = if (resumeAfterSettings && !state.interrupted) {
            timerEngine.resume()
        } else {
            timerEngine.current()
        }
        resumeAfterSettings = false
        uiState.value = state.copy(
            timer = timer,
            settings = settings,
            backStack = TimerBackStack,
        )
    }

    fun onTimerAction() {
        val state = uiState.value
        if (state.screen != AppScreen.TIMER || state.settings.duration.totalMinutes == 0) return
        val timer = when {
            state.interrupted -> timerEngine.restart()
            state.timer.snapshot.status == TimerStatus.FINISHED -> timerEngine.restart()
            state.timer.snapshot.status == TimerStatus.RUNNING -> timerEngine.pause()
            else -> timerEngine.resume()
        }
        uiState.value = state.copy(timer = timer, interrupted = false)
    }

    fun tick() {
        val state = uiState.value
        if (!state.isTicking) return
        uiState.value = state.copy(timer = timerEngine.current())
    }
}

internal fun defaultTimerSettings() = TimerSettings(
    duration = checkNotNull(TimerDuration.fromMinutes(DEFAULT_DURATION_MINUTES)),
    theme = TimerTheme.CLOCK,
)

private const val DEFAULT_DURATION_MINUTES = 120
