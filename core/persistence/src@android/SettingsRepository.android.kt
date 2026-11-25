package nl.entreco.elchrono.core.persistence

actual fun platformSettingsRepository(defaults: TimerSettings): SettingsRepository =
    error("Android settings storage must be supplied by the application.")
