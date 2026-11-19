package nl.entreco.elchrono

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import kotlinx.coroutines.delay
import nl.entreco.elchrono.core.counter.TimerStatus
import nl.entreco.elchrono.core.design.ElChronoTheme
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.app_title
import nl.entreco.elchrono.core.localization.resources.back
import nl.entreco.elchrono.core.localization.resources.hide_settings
import nl.entreco.elchrono.core.localization.resources.open_settings
import nl.entreco.elchrono.features.about.AboutFeature
import nl.entreco.elchrono.features.settings.SettingsPanel
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
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val keyboardAvailable = allowKeyboardInput && isKeyboardAvailable()
    val panes = rememberListDetailSceneStrategy<AppScreen>(
        shouldHandleSinglePaneLayout = true,
        backNavigationBehavior = BackNavigationBehavior.PopLatest,
    )
    // Material's list slot is on the left; Timer takes the remaining detail space.
    val settingsPane = ListDetailSceneStrategy.listPane() +
        ListDetailSceneStrategy.preferredPaneSize(width = 440.dp) +
        ListDetailSceneStrategy.paneAnimation(
            enterTransition = slideInHorizontally { -it },
            exitTransition = slideOutHorizontally { -it },
        )

    LifecycleEventEffect(Lifecycle.Event.ON_STOP) { viewModel.onAppStopped() }
    val ticking = state.timer.snapshot.status == TimerStatus.RUNNING && !state.interrupted
    LaunchedEffect(lifecycle, ticking) {
        if (ticking) lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            while (true) {
                delay(250.milliseconds)
                viewModel.tick()
            }
        }
    }

    Scaffold(topBar = {
        AppBar(
            screen = state.screen,
            onNavigate = {
                if (state.screen == AppScreen.TIMER) viewModel.openSettings()
                else viewModel.onBackPressed()
            },
        )
    }) { contentPadding ->
        NavDisplay(
            backStack = state.backStack,
            onBack = viewModel::onBackPressed,
            modifier = Modifier.fillMaxSize().padding(contentPadding).padding(horizontal = 24.dp, vertical = 16.dp),
            sceneStrategies = listOf(panes),
            entryProvider = entryProvider {
                entry(
                    AppScreen.TIMER,
                    metadata = ListDetailSceneStrategy.detailPane() + ListDetailSceneStrategy.paneAnimation(
                        enterTransition = slideInHorizontally { it },
                        exitTransition = slideOutHorizontally { it },
                    ),
                ) {
                    TimerFeature(
                        snapshot = state.timer.snapshot,
                        theme = state.settings.theme,
                        interrupted = state.interrupted,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
                entry(AppScreen.SETTINGS, metadata = settingsPane) {
                    SettingsPanel(
                        initialSettings = state.settings,
                        keyboardInputEnabled = allowKeyboardInput,
                        keyboardAvailable = keyboardAvailable,
                        onSave = viewModel::saveSettings,
                        onShowAbout = viewModel::showAbout,
                    )
                }
                entry(AppScreen.ABOUT, metadata = settingsPane) {
                    AboutFeature(Modifier.verticalScroll(rememberScrollState()).padding(16.dp))
                }
            },
        )
    }
}

@Composable
private fun AppBar(screen: AppScreen, onNavigate: () -> Unit) {
    val (icon, label) = when (screen) {
        AppScreen.TIMER -> "\u2699" to Strings.string.open_settings
        AppScreen.SETTINGS -> "\u00D7" to Strings.string.hide_settings
        AppScreen.ABOUT -> "\u2190" to Strings.string.back
    }
    val description = stringResource(label)
    TopAppBar(
        title = { Text(stringResource(Strings.string.app_title)) },
        navigationIcon = {
            IconButton(onClick = onNavigate) {
                Text(icon, Modifier.clearAndSetSemantics { contentDescription = description })
            }
        },
    )
}
