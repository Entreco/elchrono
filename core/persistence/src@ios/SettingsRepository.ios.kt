package nl.entreco.elchrono.core.persistence

import platform.Foundation.NSUserDefaults

actual fun platformSettingsRepository(defaults: TimerSettings): SettingsRepository =
    DefaultSettingsRepository(IosSettingsStorage(NSUserDefaults.standardUserDefaults), defaults)

class IosSettingsStorage(private val preferences: NSUserDefaults) : SettingsStorage {
    override fun load(): StorageResult<String?> {
        val key = DefaultSettingsRepository.STORAGE_KEY
        val value = preferences.objectForKey(key) ?: return StorageResult.Success(null)
        return if (value is String) StorageResult.Success(value)
        else StorageResult.Failure("Stored settings are invalid.")
    }

    override fun save(serialized: String): StorageResult<Unit> {
        preferences.setObject(serialized, forKey = DefaultSettingsRepository.STORAGE_KEY)
        return if (preferences.synchronize()) StorageResult.Success(Unit)
        else StorageResult.Failure("Settings storage could not save settings.")
    }
}
