package nl.entreco.elchrono.core.persistence

import java.util.prefs.BackingStoreException
import java.util.prefs.Preferences

actual fun platformSettingsRepository(defaults: TimerSettings): SettingsRepository =
    DefaultSettingsRepository(JvmSettingsStorage { Preferences.userRoot().node("nl/entreco/elchrono") }, defaults)

class JvmSettingsStorage(private val preferences: () -> Preferences) : SettingsStorage {
    override fun load(): StorageResult<String?> = try {
        val preferences = preferences()
        preferences.sync()
        StorageResult.Success(preferences.get(DefaultSettingsRepository.STORAGE_KEY, null))
    } catch (_: BackingStoreException) {
        StorageResult.Failure("Settings storage could not be read.")
    } catch (_: SecurityException) {
        StorageResult.Failure("Settings storage could not be read.")
    }

    override fun save(serialized: String): StorageResult<Unit> = try {
        val preferences = preferences()
        preferences.put(DefaultSettingsRepository.STORAGE_KEY, serialized)
        preferences.flush()
        StorageResult.Success(Unit)
    } catch (_: BackingStoreException) {
        StorageResult.Failure("Settings storage could not save settings.")
    } catch (_: SecurityException) {
        StorageResult.Failure("Settings storage could not save settings.")
    }
}
