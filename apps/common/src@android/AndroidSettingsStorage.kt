package nl.entreco.elchrono

import android.content.Context
import nl.entreco.elchrono.core.persistence.DefaultSettingsRepository
import nl.entreco.elchrono.core.persistence.SettingsStorage
import nl.entreco.elchrono.core.persistence.StorageResult
import nl.entreco.elchrono.di.initApp
import org.koin.dsl.KoinAppDeclaration

class AndroidSettingsStorage(context: Context) : SettingsStorage {
    private val application = context.applicationContext
    private fun preferences() = application.getSharedPreferences("elchrono", Context.MODE_PRIVATE)

    override fun load(): StorageResult<String?> = try {
        StorageResult.Success(preferences().getString(DefaultSettingsRepository.STORAGE_KEY, null))
    } catch (_: SecurityException) {
        StorageResult.Failure("Settings storage could not be read.")
    } catch (_: ClassCastException) {
        StorageResult.Failure("Settings storage could not be read.")
    }

    override fun save(serialized: String): StorageResult<Unit> = try {
        if (preferences().edit().putString(DefaultSettingsRepository.STORAGE_KEY, serialized).commit()) {
            StorageResult.Success(Unit)
        } else {
            StorageResult.Failure("Settings storage could not save settings.")
        }
    } catch (_: SecurityException) {
        StorageResult.Failure("Settings storage could not save settings.")
    }
}

fun initAndroidApp(context: Context, config: KoinAppDeclaration? = null) {
    initApp(AndroidSettingsStorage(context), config)
}
