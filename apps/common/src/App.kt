package nl.entreco.elchrono

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldValue
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowSizeClass
import kotlinx.coroutines.delay
import nl.entreco.elchrono.core.counter.TimerStatus
import nl.entreco.elchrono.core.design.ElChronoTheme
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.app_title
import nl.entreco.elchrono.core.localization.resources.back
import nl.entreco.elchrono.core.localization.resources.hide_settings
import nl.entreco.elchrono.core.localization.resources.open_settings
import nl.entreco.elchrono.features.about.AboutFeature
import nl.entreco.elchrono.features.settings.SettingsScreen
import nl.entreco.elchrono.features.settings.TimerSettings
import nl.entreco.elchrono.features.timer.TimerFeature
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Duration.Companion.milliseconds

@Composable
@Preview
fun App(allowKeyboardInput: Boolean = false) {
    ElChronoTheme { AppContent(allowKeyboardInput) }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun AppContent(
    allowKeyboardInput: Boolean,
    viewModel: AppViewModel = viewModel { AppViewModel() },
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val windowAdaptiveInfo = currentWindowAdaptiveInfoV2()
    val isExpanded = windowAdaptiveInfo.windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)
    val keyboardAvailable = allowKeyboardInput && isKeyboardAvailable()

    LifecycleEventEffect(Lifecycle.Event.ON_STOP) {
        viewModel.onAppStopped()
    }
    LaunchedEffect(lifecycleOwner, state.timer.snapshot.status, state.interrupted) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            if (state.timer.snapshot.status == TimerStatus.RUNNING && !state.interrupted) {
                while (true) {
                    delay(TIMER_REFRESH_MILLIS)
                    viewModel.tick()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Strings.string.app_title)) },
                actions = {
                    val actionDescription = when (state.screen) {
                        AppScreen.TIMER -> stringResource(Strings.string.open_settings)
                        AppScreen.SETTINGS -> stringResource(Strings.string.hide_settings)
                        AppScreen.ABOUT -> stringResource(Strings.string.back)
                    }
                    IconButton(
                        onClick = {
                            when (state.screen) {
                                AppScreen.TIMER -> viewModel.openSettings()
                                AppScreen.SETTINGS -> viewModel.closeSettings()
                                AppScreen.ABOUT -> viewModel.backToSettings()
                            }
                        },
                        modifier = Modifier.semantics {
                            contentDescription = actionDescription
                        },
                    ) {
                        Text(
                            text = when (state.screen) {
                                AppScreen.TIMER -> SETTINGS_ICON
                                AppScreen.SETTINGS -> CLOSE_ICON
                                AppScreen.ABOUT -> BACK_ICON
                            },
                            textAlign = TextAlign.Center,
                        )
                    }
                },
            )
        },
    ) { contentPadding ->
        SupportingPaneScaffold(
            directive = calculatePaneScaffoldDirective(windowAdaptiveInfo),
            value = paneValue(isExpanded, state.screen),
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            mainPane = {
                AnimatedPane {
                    TimerFeature(
                        snapshot = state.timer.snapshot,
                        theme = state.settings.theme,
                        interrupted = state.interrupted,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            },
            supportingPane = {
                AnimatedPane(
                    modifier = if (isExpanded) {
                        Modifier.widthIn(
                            min = SETTINGS_PANE_MIN_WIDTH,
                            max = SETTINGS_PANE_MAX_WIDTH
                        )
                    } else {
                        Modifier.widthIn(max = SETTINGS_PANE_MAX_WIDTH)
                    },
                ) {
                    if (state.screen == AppScreen.ABOUT) {
                        AboutFeature()
                    } else {
                        SettingsPanel(
                            initialSettings = state.settings,
                            keyboardInputEnabled = allowKeyboardInput,
                            keyboardAvailable = keyboardAvailable,
                            onSave = viewModel::saveSettings,
                            onShowAbout = viewModel::showAbout,
                        )
                    }
                }
            },
        )
    }
}

@Composable
private fun SettingsPanel(
    initialSettings: TimerSettings,
    keyboardInputEnabled: Boolean,
    keyboardAvailable: Boolean,
    onSave: (TimerSettings) -> Unit,
    onShowAbout: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 2.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        ) {
            SettingsScreen(
                initialSettings = initialSettings,
                keyboardInputEnabled = keyboardInputEnabled,
                keyboardAvailable = keyboardAvailable,
                onSave = onSave,
                onShowAbout = onShowAbout,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun paneValue(isExpanded: Boolean, screen: AppScreen) =
    ThreePaneScaffoldValue(
        primary = if (screen != AppScreen.TIMER && !isExpanded) {
            PaneAdaptedValue.Hidden
        } else {
            PaneAdaptedValue.Expanded
        },
        secondary = if (screen == AppScreen.TIMER) {
            PaneAdaptedValue.Hidden
        } else {
            PaneAdaptedValue.Expanded
        },
        tertiary = PaneAdaptedValue.Hidden,
    )

private val TIMER_REFRESH_MILLIS = 250.milliseconds
private val SETTINGS_PANE_MIN_WIDTH = 320.dp
private val SETTINGS_PANE_MAX_WIDTH = 440.dp
private const val SETTINGS_ICON = "\u2699"
private const val CLOSE_ICON = "\u00D7"
private const val BACK_ICON = "\u2190"
