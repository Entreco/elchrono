package nl.entreco.elchrono.tv

import android.app.Application
import nl.entreco.elchrono.initAndroidApp
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initAndroidApp(this) { androidContext(this@MainApplication) }
    }
}
