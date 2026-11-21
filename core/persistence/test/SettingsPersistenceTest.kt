package nl.entreco.elchrono.core.persistence

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.design.TimerFont
import nl.entreco.elchrono.core.design.TimerTheme

class SettingsPersistenceTest {
    @Test
    fun `save and restore covers every detail`() {
        val storage = StubSettingsStorage()
        val defaults = settings(0)
        val repository = DefaultSettingsRepository(storage, defaults)

        for (detail in 0..12) {
            val settings = settings(detail)
            assertTrue(repository.save(settings) is StorageResult.Success)
            assertEquals(settings, repository.load().settings)
        }
    }

    @Test
    fun `invalid stale and unknown values restore defaults`() {
        val defaults = settings(6)
        val storage = StubSettingsStorage()
        val repository = DefaultSettingsRepository(storage, defaults)

        storage.serialized = "bad"
        assertEquals(defaults, repository.load().settings)
        storage.serialized = "1441|CLOCK|0|0|0|DEFAULT|6"
        assertEquals(defaults, repository.load().settings)
        storage.serialized = "0|UNKNOWN|0|0|0|DEFAULT|6"
        assertEquals(defaults, repository.load().settings)
    }

    @Test
    fun `storage failures are surfaced`() {
        val defaults = settings(6)
        val storage = StubSettingsStorage(loadFailure = "Storage unavailable")
        val restored = DefaultSettingsRepository(storage, defaults).load()
        assertEquals(defaults, restored.settings)
        assertEquals("Storage unavailable", restored.storageFailure)
    }

    @Test
    fun `save failure is returned rather than reported successful`() {
        val storage = StubSettingsStorage(saveFailure = "Save denied")
        val result = DefaultSettingsRepository(storage, settings(0)).save(settings(0))
        assertTrue(result is StorageResult.Failure)
    }

    private fun settings(detail: Int) = TimerSettings(
        duration = checkNotNull(TimerDuration.fromMinutes(1_440)),
        theme = TimerTheme.RING,
        backgroundColorArgb = 0xFFF7F7F2,
        foregroundColorArgb = 0xFF5B5BD6,
        textColorArgb = 0xFF17171C,
        font = TimerFont.MONOSPACE,
        detail = detail,
    )

    private class StubSettingsStorage(
        var serialized: String? = null,
        private val loadFailure: String? = null,
        private val saveFailure: String? = null,
    ) : SettingsStorage {
        override fun load(): StorageResult<String?> =
            loadFailure?.let { StorageResult.Failure(it) } ?: StorageResult.Success(serialized)

        override fun save(serialized: String): StorageResult<Unit> {
            if (saveFailure != null) return StorageResult.Failure(saveFailure)
            this.serialized = serialized
            return StorageResult.Success(Unit)
        }
    }
}
