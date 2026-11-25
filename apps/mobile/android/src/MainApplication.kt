package nl.entreco.elchrono

import android.app.Application
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initAndroidApp(this) { androidContext(this@MainApplication) }
    }
}
