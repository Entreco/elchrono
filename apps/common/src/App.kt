package nl.entreco.elchrono

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.skydoves.cloudy.CloudyState
import com.skydoves.cloudy.LiquidGlassDefaults
import com.skydoves.cloudy.LiquidGlassGlow
import com.skydoves.cloudy.Sky
import com.skydoves.cloudy.cloudy
import com.skydoves.cloudy.liquidGlass
import com.skydoves.cloudy.rememberSky
import com.skydoves.cloudy.sky
import kotlinx.coroutines.delay
import nl.entreco.elchrono.core.design.ElChronoMotion
import nl.entreco.elchrono.core.design.ElChronoTheme
import nl.entreco.elchrono.core.design.LocalDimens
import nl.entreco.elchrono.core.design.appColorScheme
import nl.entreco.elchrono.core.design.toFontFamily
import nl.entreco.elchrono.core.design.withFontFamily
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.app_title
import nl.entreco.elchrono.core.localization.resources.back
import nl.entreco.elchrono.core.localization.resources.glass_effect_unavailable
import nl.entreco.elchrono.core.localization.resources.hide_settings
import nl.entreco.elchrono.core.localization.resources.open_settings
import nl.entreco.elchrono.features.about.AboutFeature
import nl.entreco.elchrono.features.settings.SettingsPanel
import nl.entreco.elchrono.features.timer.TimerFeature
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.Duration.Companion.milliseconds

@Composable
@Preview
fun App(allowKeyboardInput: Boolean = false) {
    ElChronoTheme { AppContent(allowKeyboardInput) }
}

@Composable
internal fun AppContent(
    allowKeyboardInput: Boolean,
    viewModel: AppViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val keyboardAvailable = allowKeyboardInput && isKeyboardAvailable()
    val focusManager = LocalFocusManager.current
    val settingsVisibility = remember { MutableTransitionState(false) }
    settingsVisibility.targetState = state.isPreviewing
    val overlayVisible = settingsVisibility.currentState || settingsVisibility.targetState
    val sky = rememberSky()
    val aboutSky = rememberSky()

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
    LaunchedEffect(state.settings, overlayVisible) {
        if (overlayVisible) {
            sky.invalidate()
            aboutSky.invalidate()
        }
    }
    LaunchedEffect(state.screen) {
        focusManager.clearFocus()
    }

    MaterialTheme(
        colorScheme = appColorScheme(
            background = Color(state.settings.backgroundColorArgb),
            textColor = if (state.screen == AppScreen.SETTINGS) Color(state.settings.textColorArgb) else null,
        ),
        typography = if (state.screen == AppScreen.SETTINGS) {
            MaterialTheme.typography.withFontFamily(state.settings.font.toFontFamily())
        } else {
            MaterialTheme.typography
        },
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
            topBar = {
                AppBar(state.screen) {
                    if (state.screen == AppScreen.TIMER) viewModel.openSettings()
                    else viewModel.onBackPressed()
                }
            },
        ) { contentPadding ->
            BoxWithConstraints(Modifier.fillMaxSize()) {
                val panelWidth = if (maxWidth < 600.dp) maxWidth else 440.dp
                val timerSemantics = if (overlayVisible) Modifier.clearAndSetSemantics {} else Modifier
                Box(Modifier.fillMaxSize().sky(aboutSky)) {
                    TimerContent(
                        state,
                        contentPadding.calculateTopPadding(),
                        Modifier.fillMaxSize().testTag("timer-layer").then(timerSemantics)
                            .sky(sky).blur(if (overlayVisible) 20.dp else 0.dp),
                    )
                    AnimatedVisibility(
                        visibleState = settingsVisibility,
                        modifier = Modifier.align(Alignment.TopStart).widthIn(max = panelWidth).fillMaxWidth(),
                        enter = ElChronoMotion.enterFromLeft,
                        exit = ElChronoMotion.exitToLeft,
                    ) {
                        val semantics = if (state.screen != AppScreen.SETTINGS) Modifier.clearAndSetSemantics {} else Modifier
                        GlassPanel(
                            sky,
                            Color(state.settings.backgroundColorArgb),
                            Modifier.fillMaxSize().testTag("settings-layer")
                                .focusProperties { canFocus = state.screen == AppScreen.SETTINGS }
                                .then(semantics),
                        ) {
                            SettingsPanel(
                                initialSettings = state.settings,
                                keyboardInputEnabled = allowKeyboardInput,
                                keyboardAvailable = keyboardAvailable,
                                onChange = viewModel::updateSettings,
                                persistenceFailure = state.persistenceFailure,
                                onShowAbout = viewModel::showAbout,
                                modifier = Modifier.padding(contentPadding),
                            )
                        }
                    }
                }
                NavDisplay(
                    backStack = state.backStack,
                    onBack = viewModel::onBackPressed,
                    modifier = Modifier.align(Alignment.TopStart).widthIn(max = panelWidth).fillMaxWidth(),
                    transitionSpec = { ElChronoMotion.enterFromLeft togetherWith ExitTransition.None },
                    popTransitionSpec = { EnterTransition.None togetherWith ElChronoMotion.exitToLeft },
                    predictivePopTransitionSpec = { EnterTransition.None togetherWith ElChronoMotion.exitToLeft },
                    entryProvider = entryProvider {
                        entry(AppScreen.TIMER) { Box(Modifier.fillMaxSize()) }
                        entry(AppScreen.SETTINGS) { Box(Modifier.fillMaxSize()) }
                        entry(AppScreen.ABOUT) {
                            GlassPanel(
                                aboutSky,
                                Color(state.settings.backgroundColorArgb),
                                Modifier.fillMaxSize().testTag("about-layer")
                                    .focusProperties { canFocus = state.screen == AppScreen.ABOUT }
                                    .then(
                                        if (state.screen != AppScreen.ABOUT) Modifier.clearAndSetSemantics {}
                                        else Modifier,
                                    ),
                                backdropRadius = 24,
                                tintAlpha = 0.84f,
                            ) {
                                AboutContent(contentPadding)
                            }
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun AboutContent(contentPadding: PaddingValues) {
    AboutFeature(
        Modifier.fillMaxSize().padding(contentPadding)
            .verticalScroll(rememberScrollState()).padding(LocalDimens.current.spaceXL),
    )
}

@Composable
private fun TimerContent(state: AppUiState, topInset: Dp, modifier: Modifier = Modifier) {
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
        topContentInset = topInset,
        modifier = modifier,
    )
}

@Composable
private fun GlassPanel(
    sky: Sky,
    background: Color,
    modifier: Modifier = Modifier,
    backdropRadius: Int = 0,
    tintAlpha: Float = 0.72f,
    content: @Composable () -> Unit,
) {
    var size by remember { mutableStateOf(Size.Zero) }
    var blurError by remember { mutableStateOf(false) }
    val radius = LocalDimens.current.spaceXL
    val radiusPx = with(LocalDensity.current) { radius.toPx() }
    val shape = RoundedCornerShape(radius)
    Box(modifier) {
        Box(
            Modifier.matchParentSize()
                .onSizeChanged { size = Size(it.width.toFloat(), it.height.toFloat()) }
                .liquidGlass(
                    lensCenter = Offset(size.width / 2f, size.height / 2f),
                    lensSize = if (size == Size.Zero) LiquidGlassDefaults.LENS_SIZE else size,
                    cornerRadius = radiusPx,
                    refraction = 0.16f,
                    curve = 0.18f,
                    dispersion = 0f,
                    tint = background.copy(alpha = 0.16f),
                    glow = LiquidGlassGlow(intensity = 0.2f),
                )
                .cloudy(sky = sky, radius = backdropRadius, shape = shape, onStateChanged = {
                    when (it) {
                        is CloudyState.Error -> blurError = true
                        is CloudyState.Success -> blurError = false
                        else -> Unit
                    }
                })
                .background(background.copy(alpha = if (blurError) 0.95f else tintAlpha), shape),
        )
        Column(Modifier.fillMaxSize()) {
            Box(Modifier.weight(1f)) { content() }
            if (blurError) {
                Surface(color = MaterialTheme.colorScheme.errorContainer) {
                    Text(
                        stringResource(Strings.string.glass_effect_unavailable),
                        Modifier.fillMaxWidth().padding(LocalDimens.current.spaceM),
                        color = MaterialTheme.colorScheme.onErrorContainer,
                    )
                }
            }
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
