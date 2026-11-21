package nl.entreco.elchrono

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import org.koin.compose.viewmodel.koinViewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.ui.NavDisplay
import kotlinx.coroutines.delay
import nl.entreco.elchrono.core.design.LocalDimens
import nl.entreco.elchrono.core.design.ElChronoMotion
import nl.entreco.elchrono.core.design.ElChronoTheme
import nl.entreco.elchrono.core.design.appColorScheme
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.app_title
import nl.entreco.elchrono.core.localization.resources.back
import nl.entreco.elchrono.core.localization.resources.hide_settings
import nl.entreco.elchrono.core.localization.resources.open_settings
import nl.entreco.elchrono.core.persistence.platformSettingsRepository
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
    viewModel: AppViewModel = koinViewModel(),
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val keyboardAvailable = allowKeyboardInput && isKeyboardAvailable()
    val directive = calculatePaneScaffoldDirective(currentWindowAdaptiveInfoV2())
    val useTwoPanes = directive.maxHorizontalPartitions >= 2
    val sceneStrategies = if (useTwoPanes) {
        val panes = rememberListDetailSceneStrategy<AppScreen>(
            directive = directive,
            backNavigationBehavior = BackNavigationBehavior.PopLatest,
        )
        remember(panes) {
            listOf(
                SceneStrategy<AppScreen> { entries ->
                    // Material can reserve two slots even with only the Timer entry.
                    if (entries.size > 1) with(panes) { calculateScene(entries) } else null
                },
            )
        }
    } else {
        emptyList()
    }

    val openTransition = if (useTwoPanes) ElChronoMotion.fade else ElChronoMotion.forward
    val backTransition = if (useTwoPanes) ElChronoMotion.fade else ElChronoMotion.backward
    // Material's list slot is on the left; Timer takes the remaining detail space.
    val settingsPane = ListDetailSceneStrategy.listPane() +
        ListDetailSceneStrategy.preferredPaneSize(width = 440.dp) +
        ListDetailSceneStrategy.paneAnimation(
            enterTransition = ElChronoMotion.enterFromLeft,
            exitTransition = ElChronoMotion.exitToLeft,
        )

    LifecycleEventEffect(Lifecycle.Event.ON_STOP) { viewModel.onAppStopped() }
    val ticking = state.isTicking
    LaunchedEffect(lifecycle, ticking) {
        if (ticking) lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            while (true) {
                delay(250.milliseconds)
                viewModel.tick()
            }
        }
    }

    MaterialTheme(
        colorScheme = appColorScheme(
            background = Color(state.settings.backgroundColorArgb),
            textColor = if (state.screen == AppScreen.SETTINGS) {
                Color(state.settings.textColorArgb)
            } else {
                null
            },
        ),
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize().background(Color(state.settings.backgroundColorArgb)),
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
            topBar = {
                AppBar(
                    screen = state.screen,
                    onNavigate = {
                        if (state.screen == AppScreen.TIMER) viewModel.openSettings()
                        else viewModel.onBackPressed()
                    },
                )
            },
        ) { contentPadding ->
            NavDisplay(
                backStack = state.backStack,
                onBack = viewModel::onBackPressed,
                modifier = Modifier.fillMaxSize().padding(contentPadding)
                    .padding(horizontal = LocalDimens.current.spaceXXXL, vertical = LocalDimens.current.spaceXL),
                sceneStrategies = sceneStrategies,
                transitionSpec = { openTransition },
                popTransitionSpec = { backTransition },
                predictivePopTransitionSpec = { backTransition },
                entryProvider = entryProvider {
                    entry(
                        AppScreen.TIMER,
                        metadata = ListDetailSceneStrategy.detailPane() + ListDetailSceneStrategy.paneAnimation(
                            enterTransition = ElChronoMotion.enterFromRight,
                            exitTransition = ElChronoMotion.exitToRight,
                        ),
                    ) {
                        TimerFeature(
                            snapshot = state.presentationSnapshot,
                            theme = state.settings.theme,
                            backgroundColorArgb = state.settings.backgroundColorArgb,
                            foregroundColorArgb = state.settings.foregroundColorArgb,
                            textColorArgb = state.settings.textColorArgb,
                            font = state.settings.font,
                            detail = state.settings.detail,
                            ringThicknessPercent = state.settings.ringThicknessPercent,
                            interrupted = state.interrupted && !state.isPreviewing,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                    entry(AppScreen.SETTINGS, metadata = settingsPane) {
                        SettingsPanel(
                            initialSettings = state.settings,
                            keyboardInputEnabled = allowKeyboardInput,
                            keyboardAvailable = keyboardAvailable,
                            onChange = viewModel::updateSettings,
                            persistenceFailure = state.persistenceFailure,
                            onShowAbout = viewModel::showAbout,
                        )
                    }
                    entry(AppScreen.ABOUT, metadata = settingsPane) {
                        AboutFeature(Modifier.verticalScroll(rememberScrollState()).padding(LocalDimens.current.spaceXL))
                    }
                },
            )
        }
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
        ),
        title = { Text(stringResource(Strings.string.app_title)) },
        navigationIcon = {
            IconButton(onClick = onNavigate) {
                Text(icon, Modifier.clearAndSetSemantics { contentDescription = description })
            }
        },
    )
}
