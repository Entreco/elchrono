package nl.entreco.elchrono.tv

import android.os.Bundle
import android.graphics.drawable.ColorDrawable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nl.entreco.elchrono.App
import nl.entreco.elchrono.di.startupBackgroundArgb

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(ColorDrawable(startupBackgroundArgb().toInt()))
        setContent { App() }
    }
}
