package nl.entreco.elchrono.core.persistence

actual fun platformSettingsRepository(defaults: TimerSettings): SettingsRepository =
    DefaultSettingsRepository(InMemorySettingsStorage, defaults)

object InMemorySettingsStorage : SettingsStorage {
    private var value: String? = null
    override fun load(): StorageResult<String?> = StorageResult.Success(value)
    override fun save(serialized: String): StorageResult<Unit> {
        value = serialized
        return StorageResult.Success(Unit)
    }
}
