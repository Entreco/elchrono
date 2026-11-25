package nl.entreco.elchrono.di

import nl.entreco.elchrono.AppViewModel
import nl.entreco.elchrono.core.counter.TimerEngine
import nl.entreco.elchrono.core.persistence.SettingsRepository
import nl.entreco.elchrono.core.persistence.SettingsStorage
import nl.entreco.elchrono.core.persistence.DefaultSettingsRepository
import nl.entreco.elchrono.core.persistence.platformSettingsRepository
import nl.entreco.elchrono.defaultTimerSettings
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = createAppModule(null)

private fun createAppModule(storage: SettingsStorage?) = module {
    factory { TimerEngine() }

    single<SettingsRepository> {
        storage?.let { DefaultSettingsRepository(it, defaultTimerSettings()) }
            ?: platformSettingsRepository(defaultTimerSettings())
    }

    viewModel {
        val repository = get<SettingsRepository>()
        val restored = repository.load()
        AppViewModel(
            timerEngine = get(),
            settingsRepository = repository,
            initialSettings = restored.settings,
            initialPersistenceFailure = restored.storageFailure,
        )
    }
}

fun initApp(storage: SettingsStorage? = null, config: KoinAppDeclaration? = null) {
    val application = org.koin.core.context.startKoin {
        config?.invoke(this)
        modules(if (storage == null) appModule else createAppModule(storage))
    }
    startupRepository = application.koin.get()
}

private var startupRepository: SettingsRepository? = null

fun startupBackgroundArgb(): Long =
    checkNotNull(startupRepository) { "Application is not initialized." }.load().settings.backgroundColorArgb
