package nl.entreco.elchrono

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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

@Composable
@Preview
fun App() {
    ElChronoTheme { AppContent() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppContent(viewModel: AppViewModel = viewModel { AppViewModel() }) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val isExpanded = currentWindowAdaptiveInfoV2().windowSizeClass
        .isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)

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
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Box(Modifier.weight(1f).fillMaxHeight()) {
                if (state.screen == AppScreen.ABOUT) {
                    AboutFeature()
                } else {
                    TimerFeature(
                        snapshot = state.timer.snapshot,
                        theme = state.settings.theme,
                        interrupted = state.interrupted,
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                if (!isExpanded) {
                    CompactSettingsOverlay(
                        visible = state.screen == AppScreen.SETTINGS,
                        settings = state.settings,
                        onSave = viewModel::saveSettings,
                        onShowAbout = viewModel::showAbout,
                    )
                }
            }

            if (isExpanded) {
                AnimatedVisibility(
                    visible = state.screen == AppScreen.SETTINGS,
                    enter = expandedSettingsEnter(),
                    exit = expandedSettingsExit(),
                ) {
                    SettingsPanel(
                        initialSettings = state.settings,
                        modifier = Modifier
                            .widthIn(min = SETTINGS_PANE_MIN_WIDTH, max = SETTINGS_PANE_MAX_WIDTH)
                            .fillMaxHeight(),
                        onSave = viewModel::saveSettings,
                        onShowAbout = viewModel::showAbout,
                    )
                }
            }
        }
    }
}

@Composable
private fun CompactSettingsOverlay(
    visible: Boolean,
    settings: TimerSettings,
    onSave: (TimerSettings) -> Unit,
    onShowAbout: () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        modifier = Modifier.fillMaxSize(),
        enter = compactSettingsEnter(),
        exit = compactSettingsExit(),
    ) {
        SettingsPanel(
            initialSettings = settings,
            onSave = onSave,
            onShowAbout = onShowAbout,
        )
    }
}

@Composable
private fun SettingsPanel(
    initialSettings: TimerSettings,
    modifier: Modifier = Modifier,
    onSave: (TimerSettings) -> Unit,
    onShowAbout: () -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
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
                onSave = onSave,
                onShowAbout = onShowAbout,
            )
        }
    }
}

private fun compactSettingsEnter(): EnterTransition =
    fadeIn(tween(SETTINGS_ANIMATION_MILLIS)) +
        slideInVertically(tween(SETTINGS_ANIMATION_MILLIS)) { it / 6 }

private fun compactSettingsExit(): ExitTransition =
    fadeOut(tween(SETTINGS_ANIMATION_MILLIS)) +
        slideOutVertically(tween(SETTINGS_ANIMATION_MILLIS)) { it / 6 }

private fun expandedSettingsEnter(): EnterTransition =
    fadeIn(tween(SETTINGS_ANIMATION_MILLIS)) +
        expandHorizontally(tween(SETTINGS_ANIMATION_MILLIS), expandFrom = Alignment.End)

private fun expandedSettingsExit(): ExitTransition =
    fadeOut(tween(SETTINGS_ANIMATION_MILLIS)) +
        shrinkHorizontally(tween(SETTINGS_ANIMATION_MILLIS), shrinkTowards = Alignment.End)

private const val TIMER_REFRESH_MILLIS = 250L
private const val SETTINGS_ANIMATION_MILLIS = 220
private val SETTINGS_PANE_MIN_WIDTH = 320.dp
private val SETTINGS_PANE_MAX_WIDTH = 440.dp
private const val SETTINGS_ICON = "\u2699"
private const val CLOSE_ICON = "\u00D7"
private const val BACK_ICON = "\u2190"
