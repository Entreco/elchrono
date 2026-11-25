package nl.entreco.elchrono.core.persistence

import java.util.UUID
import java.util.prefs.AbstractPreferences
import java.util.prefs.BackingStoreException
import java.util.prefs.Preferences
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.design.TimerTheme

class JvmSettingsStorageTest {
    @Test
    fun `settings survive a new storage and repository instance`() {
        val node = Preferences.userRoot().node("nl/entreco/elchrono-test-${UUID.randomUUID()}")
        try {
            val defaults = TimerSettings(checkNotNull(TimerDuration.fromMinutes(120)), TimerTheme.CLOCK)
            val selected = defaults.copy(
                duration = checkNotNull(TimerDuration.fromMinutes(37)),
                theme = TimerTheme.RING,
                backgroundColorArgb = 0xFFF7F7F2,
                detail = 3,
                ringThicknessPercent = 75,
            )
            val first = DefaultSettingsRepository(JvmSettingsStorage { node }, defaults)
            assertTrue(first.save(selected) is StorageResult.Success)

            val reopened = DefaultSettingsRepository(JvmSettingsStorage { node }, defaults).load()
            assertEquals(selected, reopened.settings)
            assertEquals(null, reopened.storageFailure)
        } finally {
            node.removeNode()
        }
    }

    @Test
    fun `access denial is returned as a storage failure`() {
        val storage = JvmSettingsStorage { throw SecurityException("denied") }
        assertTrue(storage.load() is StorageResult.Failure)
        assertTrue(storage.save("settings") is StorageResult.Failure)
    }

    @Test
    fun `backing store read and write failures are returned`() {
        val storage = JvmSettingsStorage { FailingPreferences() }
        assertTrue(storage.load() is StorageResult.Failure)
        assertTrue(storage.save("settings") is StorageResult.Failure)
    }

    private class FailingPreferences : AbstractPreferences(null, "") {
        override fun putSpi(key: String, value: String) = Unit
        override fun getSpi(key: String): String? = null
        override fun removeSpi(key: String) = Unit
        override fun removeNodeSpi() = Unit
        override fun keysSpi(): Array<String> = emptyArray()
        override fun childrenNamesSpi(): Array<String> = emptyArray()
        override fun childSpi(name: String): AbstractPreferences = error("Unexpected child")
        override fun syncSpi(): Unit = throw BackingStoreException("Read denied")
        override fun flushSpi(): Unit = throw BackingStoreException("Write denied")
    }
}
