package nl.entreco.elchrono.core.persistence

import kotlinx.browser.localStorage

actual fun platformSettingsRepository(defaults: TimerSettings): SettingsRepository =
    DefaultSettingsRepository(BrowserSettingsStorage, defaults)

object BrowserSettingsStorage : SettingsStorage {
    override fun load(): StorageResult<String?> = try {
        StorageResult.Success(localStorage.getItem(DefaultSettingsRepository.STORAGE_KEY))
    } catch (_: Throwable) {
        StorageResult.Failure("Browser storage could not be read.")
    }

    override fun save(serialized: String): StorageResult<Unit> = try {
        localStorage.setItem(DefaultSettingsRepository.STORAGE_KEY, serialized)
        StorageResult.Success(Unit)
    } catch (_: Throwable) {
        StorageResult.Failure("Browser storage could not save settings.")
    }
}
