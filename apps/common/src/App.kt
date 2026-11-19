package nl.entreco.elchrono

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.counter.TimerEngine
import nl.entreco.elchrono.core.counter.TimerStatus
import nl.entreco.elchrono.core.design.ElChronoTheme
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.app_title
import nl.entreco.elchrono.core.localization.resources.back
import nl.entreco.elchrono.core.localization.resources.hide_settings
import nl.entreco.elchrono.core.localization.resources.open_settings
import nl.entreco.elchrono.features.about.AboutFeature
import nl.entreco.elchrono.features.settings.SettingsFeature
import nl.entreco.elchrono.features.settings.TimerSettings
import nl.entreco.elchrono.features.timer.TimerFeature
import org.jetbrains.compose.resources.stringResource

private enum class Screen {
    TIMER,
    SETTINGS,
    ABOUT,
}

@Composable
@Preview
fun App() {
    ElChronoTheme {
        val engine = remember { TimerEngine() }
        var update by remember { mutableStateOf(engine.current()) }
        var interrupted by remember { mutableStateOf(false) }
        var screen by remember { mutableStateOf(Screen.TIMER) }
        var settings by remember {
            mutableStateOf(
                TimerSettings(
                    duration = checkNotNull(TimerDuration.fromMinutes(DEFAULT_DURATION_MINUTES)),
                    theme = TimerTheme.CLOCK,
                ),
            )
        }

        LaunchedEffect(engine, interrupted, update.snapshot.status) {
            if (!interrupted && update.snapshot.status == TimerStatus.RUNNING) {
                while (true) {
                    delay(250)
                    if (consumeAppInterruption()) {
                        update = engine.openSettings()
                        interrupted = true
                        break
                    }
                    update = engine.current()
                }
            }
        }
        LaunchedEffect(engine) {
            initializeAppInterruptionTracking()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = stringResource(Strings.string.app_title),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.headlineMedium,
                )
                val actionDescription = when (screen) {
                    Screen.TIMER -> stringResource(Strings.string.open_settings)
                    Screen.SETTINGS -> stringResource(Strings.string.hide_settings)
                    Screen.ABOUT -> stringResource(Strings.string.back)
                }
                IconButton(
                    onClick = {
                        when (screen) {
                            Screen.TIMER -> {
                                if (update.snapshot.status == TimerStatus.RUNNING) {
                                    update = engine.openSettings()
                                }
                                screen = Screen.SETTINGS
                            }

                            Screen.SETTINGS -> screen = Screen.TIMER
                            Screen.ABOUT -> screen = Screen.SETTINGS
                        }
                    },
                    modifier = Modifier.semantics {
                        contentDescription = actionDescription
                    },
                ) {
                    Text(
                        text = when (screen) {
                            Screen.TIMER -> SETTINGS_ICON
                            Screen.SETTINGS -> CLOSE_ICON
                            Screen.ABOUT -> BACK_ICON
                        },
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            when (screen) {
                Screen.TIMER -> TimerFeature(
                    snapshot = update.snapshot,
                    theme = settings.theme,
                    interrupted = interrupted,
                    modifier = Modifier.fillMaxWidth(),
                )

                Screen.SETTINGS,
                Screen.ABOUT,
                -> Unit
            }
            SettingsFeature(
                visible = screen == Screen.SETTINGS,
                initialSettings = settings,
                onSave = { updatedSettings ->
                    settings = updatedSettings
                    update = engine.saveSettings(updatedSettings.duration)
                    interrupted = false
                    screen = Screen.TIMER
                },
                onShowAbout = { screen = Screen.ABOUT },
            )
            if (screen == Screen.ABOUT) {
                AboutFeature()
            }
        }
    }
}

private const val DEFAULT_DURATION_MINUTES = 120
private const val SETTINGS_ICON = "\u2699"
private const val CLOSE_ICON = "\u00D7"
private const val BACK_ICON = "\u2190"
