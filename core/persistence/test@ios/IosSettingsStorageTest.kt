package nl.entreco.elchrono.core.persistence

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.design.TimerTheme
import platform.Foundation.NSUserDefaults
import platform.Foundation.NSUUID

class IosSettingsStorageTest {
    @Test
    fun `settings survive a new storage and repository instance`() {
        val suite = "nl.entreco.elchrono.test.${NSUUID().UUIDString}"
        val preferences = NSUserDefaults(suiteName = suite)
        try {
            val defaults = TimerSettings(checkNotNull(TimerDuration.fromMinutes(120)), TimerTheme.CLOCK)
            val selected = defaults.copy(
                duration = checkNotNull(TimerDuration.fromMinutes(37)),
                theme = TimerTheme.RING,
                backgroundColorArgb = 0xFFF7F7F2,
                detail = 3,
                ringThicknessPercent = 75,
            )
            val first = DefaultSettingsRepository(IosSettingsStorage(preferences), defaults)
            assertTrue(first.save(selected) is StorageResult.Success)

            val reopened = DefaultSettingsRepository(
                IosSettingsStorage(NSUserDefaults(suiteName = suite)),
                defaults,
            ).load()
            assertEquals(selected, reopened.settings)
            assertEquals(null, reopened.storageFailure)
        } finally {
            preferences.removePersistentDomainForName(suite)
        }
    }

    @Test
    fun `non-string stored value is surfaced as a failure`() {
        val suite = "nl.entreco.elchrono.test.${NSUUID().UUIDString}"
        val preferences = NSUserDefaults(suiteName = suite)
        try {
            preferences.setBool(true, forKey = DefaultSettingsRepository.STORAGE_KEY)
            assertTrue(IosSettingsStorage(preferences).load() is StorageResult.Failure)
        } finally {
            preferences.removePersistentDomainForName(suite)
        }
    }
}
