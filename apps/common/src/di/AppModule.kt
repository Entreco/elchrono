package nl.entreco.elchrono.di

import nl.entreco.elchrono.AppViewModel
import nl.entreco.elchrono.core.counter.TimerEngine
import nl.entreco.elchrono.core.persistence.SettingsRepository
import nl.entreco.elchrono.core.persistence.platformSettingsRepository
import nl.entreco.elchrono.defaultTimerSettings
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single { TimerEngine() }

    single<SettingsRepository> {
        platformSettingsRepository(defaultTimerSettings())
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

fun initApp(config: KoinAppDeclaration? = null) {
    org.koin.core.context.startKoin {
        config?.invoke(this)
        modules(appModule)
    }
}
