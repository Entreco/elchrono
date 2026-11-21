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
    val backgroundColorArgb: Long = 0xFF151517u.toLong(),
    val foregroundColorArgb: Long = 0xFF5B5BD6u.toLong(),
    val textColorArgb: Long = 0xFFF1F1F1u.toLong(),
    val font: TimerFont = TimerFont.DEFAULT,
    val detail: Int = 6,
    val ringThicknessPercent: Int = 24,
) {
    init {
        require(ringThicknessPercent in 1..100)
    }
}

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
            is StorageResult.Success -> {
                val restored = loaded.value?.let(::deserialize)
                RestoredSettings(
                    restored ?: defaults,
                    if (loaded.value != null && restored == null) {
                        "Stored settings are invalid. Default settings have been restored."
                    } else {
                        null
                    },
                )
            }
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
            settings.ringThicknessPercent,
        ).joinToString("|")

    private fun deserialize(serialized: String): TimerSettings? {
        val values = serialized.split("|")
        if (values.size !in 7..8) return null
        val duration = values[0].toIntOrNull()?.let(TimerDuration::fromMinutes) ?: return null
        val theme = TimerTheme.entries.firstOrNull { it.name == values[1] } ?: return null
        val background = values[2].toLongOrNull()?.takeIf { it in 0..0xFFFFFFFF } ?: return null
        val foreground = values[3].toLongOrNull()?.takeIf { it in 0..0xFFFFFFFF } ?: return null
        val text = values[4].toLongOrNull()?.takeIf { it in 0..0xFFFFFFFF } ?: return null
        val font = TimerFont.entries.firstOrNull { it.name == values[5] } ?: return null
        val detail = values[6].toIntOrNull()?.takeIf { it in 0..12 } ?: return null
        val thickness = if (values.size == 7) defaults.ringThicknessPercent else {
            values[7].toIntOrNull()?.takeIf { it in 1..100 } ?: return null
        }
        return TimerSettings(duration, theme, background, foreground, text, font, detail, thickness)
    }

    companion object {
        const val STORAGE_KEY = "elchrono.settings.v1"
    }
}

expect fun platformSettingsRepository(defaults: TimerSettings): SettingsRepository
