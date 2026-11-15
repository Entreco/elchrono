package nl.entreco.elchrono

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.reload.DevelopmentEntryPoint
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

import nl.entreco.elchrono.core.design.ElChronoTheme
import nl.entreco.elchrono.core.design.resources.Res as DesignRes
import nl.entreco.elchrono.core.design.resources.compose_multiplatform
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.compose_greeting
import nl.entreco.elchrono.core.localization.resources.greeting
import nl.entreco.elchrono.core.localization.resources.show_content

@Composable
@Preview
@DevelopmentEntryPoint
fun App() {
    ElChronoTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text(stringResource(Strings.string.show_content))
            }
            AnimatedVisibility(showContent) {
                val greeting = stringResource(Strings.string.greeting, getPlatform().name)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(DesignRes.drawable.compose_multiplatform), null)
                    Text(stringResource(Strings.string.compose_greeting, greeting))
                }
            }
        }
    }
}
