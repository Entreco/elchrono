package nl.entreco.elchrono.core.persistence

import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.design.TimerFont
import nl.entreco.elchrono.core.design.TimerTheme

interface SettingsStorage {
    fun load(): StorageResult<String?>
    fun save(serialized: String): StorageResult<Unit>
}

sealed interface StorageResult<out T> {
    data class Success<T>(val value: T) : StorageResult<T>
    data class Failure(val message: String) : StorageResult<Nothing>
}

interface SettingsRepository {
    fun load(): RestoredSettings
    fun save(settings: TimerSettings): StorageResult<Unit>
}

data class TimerSettings(
    val duration: TimerDuration,
    val theme: TimerTheme,
    val backgroundColorArgb: Long = 0xFFF7F7F2u.toLong(),
    val foregroundColorArgb: Long = 0xFF5B5BD6u.toLong(),
    val textColorArgb: Long = 0xFF17171Cu.toLong(),
    val font: TimerFont = TimerFont.DEFAULT,
    val detail: Int = 6,
)

data class RestoredSettings(
    val settings: TimerSettings,
    val storageFailure: String?,
)

class DefaultSettingsRepository(
    private val storage: SettingsStorage,
    private val defaults: TimerSettings,
) : SettingsRepository {
    override fun load(): RestoredSettings =
        when (val loaded = storage.load()) {
            is StorageResult.Success -> RestoredSettings(
                loaded.value?.let(::deserialize) ?: defaults,
                null,
            )
            is StorageResult.Failure -> RestoredSettings(defaults, loaded.message)
        }

    override fun save(settings: TimerSettings): StorageResult<Unit> =
        storage.save(serialize(settings))

    private fun serialize(settings: TimerSettings): String =
        listOf(
            settings.duration.totalMinutes,
            settings.theme.name,
            settings.backgroundColorArgb,
            settings.foregroundColorArgb,
            settings.textColorArgb,
            settings.font.name,
            settings.detail,
        ).joinToString("|")

    private fun deserialize(serialized: String): TimerSettings? {
        val values = serialized.split("|")
        if (values.size != 7) return null
        return try {
            val duration = TimerDuration.fromMinutes(values[0].toInt()) ?: return null
            val theme = TimerTheme.entries.firstOrNull { it.name == values[1] } ?: return null
            val background = values[2].toLong().takeIf { it in 0..0xFFFFFFFF } ?: return null
            val foreground = values[3].toLong().takeIf { it in 0..0xFFFFFFFF } ?: return null
            val text = values[4].toLong().takeIf { it in 0..0xFFFFFFFF } ?: return null
            val font = TimerFont.entries.firstOrNull { it.name == values[5] } ?: return null
            val detail = values[6].toInt().takeIf { it in 0..12 } ?: return null
            TimerSettings(duration, theme, background, foreground, text, font, detail)
        } catch (_: IllegalArgumentException) {
            null
        }
    }

    companion object {
        const val STORAGE_KEY = "elchrono.settings.v1"
    }
}

expect fun platformSettingsRepository(defaults: TimerSettings): SettingsRepository
