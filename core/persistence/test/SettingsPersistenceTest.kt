package nl.entreco.elchrono.core.persistence

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.design.TimerFont
import nl.entreco.elchrono.core.design.TimerTheme

class SettingsPersistenceTest {
    @Test
    fun `ring thickness round trips at every supported value`() {
        val storage = StubSettingsStorage()
        val repository = DefaultSettingsRepository(storage, settings(6))
        (1..100).forEach { thickness ->
            val configured = settings(6).copy(ringThicknessPercent = thickness)
            assertTrue(repository.save(configured) is StorageResult.Success)
            assertEquals(configured, repository.load().settings)
        }
        assertFailsWith<IllegalArgumentException> { settings(6).copy(ringThicknessPercent = 0) }
        assertFailsWith<IllegalArgumentException> { settings(6).copy(ringThicknessPercent = 101) }
    }

    @Test
    fun `legacy settings preserve selections and receive default thickness`() {
        val storage = StubSettingsStorage(serialized = "60|BATTERY|4280163870|4286545799|4279703318|MONOSPACE|12")
        val restored = DefaultSettingsRepository(storage, settings(6)).load()
        assertEquals(
            TimerSettings(checkNotNull(TimerDuration.fromMinutes(60)), TimerTheme.BATTERY,
                4280163870, 4286545799, 4279703318, TimerFont.MONOSPACE, 12, 24),
            restored.settings,
        )
        assertEquals(null, restored.storageFailure)
    }

    @Test
    fun `invalid stored thickness is reported and uses safe defaults`() {
        listOf("0", "101", "-1", "NaN", "", "999999999999999").forEach { thickness ->
            val defaults = settings(6)
            val storage = StubSettingsStorage(serialized = "60|RING|0|0|0|DEFAULT|6|$thickness")
            val restored = DefaultSettingsRepository(storage, defaults).load()
            assertEquals(defaults, restored.settings)
            assertTrue(restored.storageFailure != null)
        }
    }

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
