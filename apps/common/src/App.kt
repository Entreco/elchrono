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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.skydoves.cloudy.LiquidGlassDefaults
import com.skydoves.cloudy.LiquidGlassGlow
import com.skydoves.cloudy.Sky
import com.skydoves.cloudy.cloudy
import com.skydoves.cloudy.liquidGlass
import com.skydoves.cloudy.rememberSky
import com.skydoves.cloudy.sky
import kotlinx.coroutines.delay
import nl.entreco.elchrono.core.counter.TimerStatus
import nl.entreco.elchrono.core.design.elChronoFocusOutline
import nl.entreco.elchrono.core.design.ElChronoMotion
import nl.entreco.elchrono.core.design.ElChronoTheme
import nl.entreco.elchrono.core.design.LocalDimens
import nl.entreco.elchrono.core.design.appColorScheme
import nl.entreco.elchrono.core.design.toFontFamily
import nl.entreco.elchrono.core.design.withFontFamily
import nl.entreco.elchrono.core.design.resources.material_arrow_back
import nl.entreco.elchrono.core.design.resources.material_close
import nl.entreco.elchrono.core.design.resources.material_settings
import nl.entreco.elchrono.core.design.resources.Res as DesignResources
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.app_title
import nl.entreco.elchrono.core.localization.resources.back
import nl.entreco.elchrono.core.localization.resources.hide_settings
import nl.entreco.elchrono.core.localization.resources.open_settings
import nl.entreco.elchrono.features.about.AboutFeature
import nl.entreco.elchrono.features.settings.SettingsPanel
import nl.entreco.elchrono.features.timer.TimerFeature
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.Duration.Companion.milliseconds

@Composable
@Preview
fun App(onBackgroundChanged: (Long) -> Unit = {}) {
    ElChronoTheme {
        AppContent(onBackgroundChanged = onBackgroundChanged)
    }
}

@Composable
internal fun AppContent(
    viewModel: AppViewModel = koinViewModel(),
    onBackgroundChanged: (Long) -> Unit = {},
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(state.settings.backgroundColorArgb) {
        onBackgroundChanged(state.settings.backgroundColorArgb)
    }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val settingsVisibility = remember { MutableTransitionState(state.isPreviewing) }
    settingsVisibility.targetState = state.isPreviewing
    val overlayVisible = settingsVisibility.currentState || settingsVisibility.targetState
    val sky = rememberSky()
    val aboutSky = rememberSky()

    val ticking = state.isTicking
    LaunchedEffect(lifecycle, ticking) {
        if (ticking) lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            while (true) {
                viewModel.tick()
                delay(250.milliseconds)
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
        withFrameNanos { }
        focusRequester.requestFocus()
    }

    MaterialTheme(
        colorScheme = appColorScheme(
            background = Color(state.settings.backgroundColorArgb),
            textColor = Color(state.settings.textColorArgb),
        ),
        typography = if (state.screen == AppScreen.SETTINGS) {
            MaterialTheme.typography.withFontFamily(state.settings.font.toFontFamily())
        } else {
            MaterialTheme.typography
        },
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .onKeyEvent { event ->

                    val direction = when (event.key) {
                        Key.DirectionUp -> FocusDirection.Up
                        Key.DirectionDown -> FocusDirection.Down
                        Key.DirectionLeft -> FocusDirection.Left
                        Key.DirectionRight -> FocusDirection.Right
                        else -> null
                    }
                    event.type == KeyEventType.KeyDown &&
                        direction != null && focusManager.moveFocus(direction)

                },
            containerColor = Color(state.settings.backgroundColorArgb),
            contentColor = MaterialTheme.colorScheme.onSurface,
            topBar = {
                AppBar(
                    screen = state.screen,
                    textColor = Color(state.settings.textColorArgb),
                    focusRequester = focusRequester,
                    modifier = if (
                        !overlayVisible &&
                        state.presentationSnapshot.status != TimerStatus.NOT_CONFIGURED
                    ) {
                        Modifier.onPreviewKeyEvent { event ->
                            event.type == KeyEventType.KeyDown && event.key == Key.DirectionDown &&
                                    (focusManager.moveFocus(FocusDirection.Down) ||
                                            focusManager.moveFocus(FocusDirection.Next))
                        }
                    } else Modifier,
                ) {
                    if (state.screen == AppScreen.TIMER) viewModel.openSettings()
                    else viewModel.onBackPressed()
                }
            },
        ) { contentPadding ->
            BoxWithConstraints(Modifier.fillMaxSize()) {
                val panelWidth = if (maxWidth < 600.dp) maxWidth else 440.dp
                val timerSemantics =
                    if (overlayVisible) Modifier.clearAndSetSemantics {} else Modifier
                Box(
                    Modifier
                        .fillMaxSize()
                ) {
                    TimerContent(
                        state,
                        contentPadding.calculateTopPadding(),
                        onTimerAction = viewModel::onTimerAction,
                        controlsEnabled = !overlayVisible,
                        actionModifier =
                            Modifier.onPreviewKeyEvent { event ->
                                event.type == KeyEventType.KeyDown && event.key == Key.DirectionUp &&
                                        (focusManager.moveFocus(FocusDirection.Up) ||
                                                focusManager.moveFocus(FocusDirection.Previous))
                            },
                        bottomInset = contentPadding.calculateBottomPadding(),
                        modifier = Modifier
                            .fillMaxSize()
                            .testTag("timer-layer")
                            .then(timerSemantics),
                    )
                    AnimatedVisibility(
                        visibleState = settingsVisibility,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .widthIn(max = panelWidth)
                            .fillMaxSize(),
                        enter = ElChronoMotion.enterFromLeft,
                        exit = ElChronoMotion.exitToLeft,
                    ) {
                        val semantics =
                            if (state.screen != AppScreen.SETTINGS) Modifier.clearAndSetSemantics {} else Modifier
                        GlassPanel(
                            sky,
                            Color(state.settings.backgroundColorArgb),
                            Modifier
                                .fillMaxSize()
                                .testTag("settings-layer")
                                .then(
                                    if (state.screen != AppScreen.SETTINGS) {
                                        Modifier.focusProperties { canFocus = false }
                                    } else Modifier,
                                )
                                .then(semantics),
                        ) {
                            SettingsPanel(
                                initialSettings = state.settings,
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
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .widthIn(max = panelWidth)
                        .fillMaxWidth(),
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
                                Modifier
                                    .fillMaxSize()
                                    .testTag("about-layer")
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
        Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .verticalScroll(rememberScrollState())
            .padding(LocalDimens.current.spaceXL),
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
    Box(modifier) {
        GlassBackdrop(
            sky = sky,
            background = background,
            modifier = Modifier.matchParentSize(),
            backdropRadius = backdropRadius,
            tintAlpha = tintAlpha,
        )
        Column(Modifier.fillMaxSize()) {
            Box(Modifier.weight(1f)) { content() }
        }
    }
}

@Composable
private fun GlassBackdrop(
    sky: Sky,
    background: Color,
    modifier: Modifier = Modifier,
    backdropRadius: Int = 0,
    tintAlpha: Float = 0.72f,
) {
    var size by remember { mutableStateOf(Size.Zero) }
    val radius = LocalDimens.current.spaceXL
    val radiusPx = with(LocalDensity.current) { radius.toPx() }
    val shape = RoundedCornerShape(radius)
    Box(
        modifier
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
            .cloudy(sky = sky, radius = backdropRadius, shape = shape)
            .background(background.copy(alpha = tintAlpha), shape),
    )
}

@Composable
private fun TimerContent(
    state: AppUiState,
    topInset: Dp,
    onTimerAction: () -> Unit,
    modifier: Modifier = Modifier,
    controlsEnabled: Boolean = true,
    actionModifier: Modifier = Modifier,
    bottomInset: Dp = 0.dp,
) {
    val sky = rememberSky()
    val snapshot = state.presentationSnapshot
    Box(modifier) {
        TimerFeature(
            snapshot = snapshot,
            theme = state.settings.theme,
            backgroundColorArgb = state.settings.backgroundColorArgb,
            foregroundColorArgb = state.settings.foregroundColorArgb,
            textColorArgb = state.settings.textColorArgb,
            font = state.settings.font,
            detail = state.settings.detail,
            ringThicknessPercent = state.settings.ringThicknessPercent,
            interrupted = state.interrupted && !state.isPreviewing,
            onTimerAction = onTimerAction,
            controlsEnabled = controlsEnabled,
            actionModifier = actionModifier,
            topContentInset = topInset,
            visualizationModifier = Modifier.sky(sky),
            remainingTimeDecoration = { content ->
                Box {
                    GlassBackdrop(
                        sky = sky,
                        background = Color(state.settings.backgroundColorArgb),
                        modifier = Modifier.matchParentSize(),
                    )
                    content()
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomInset),
        )
    }
}

@Composable
internal fun AppBar(
    screen: AppScreen,
    textColor: Color,
    focusRequester: FocusRequester? = null,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
) {
    val (icon, label) = when (screen) {
        AppScreen.TIMER -> DesignResources.drawable.material_settings to Strings.string.open_settings
        AppScreen.SETTINGS -> DesignResources.drawable.material_close to Strings.string.hide_settings
        AppScreen.ABOUT -> DesignResources.drawable.material_arrow_back to Strings.string.back
    }
    val description = stringResource(label)
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
            titleContentColor = textColor,
            navigationIconContentColor = textColor,
        ),
        title = { Text(stringResource(Strings.string.app_title)) },
        navigationIcon = {
            val buttonModifier = modifier
                .then(focusRequester?.let { Modifier.focusRequester(it) } ?: Modifier)
                .elChronoFocusOutline(CircleShape)
            IconButton(
                onClick = onNavigate,
                modifier = buttonModifier,
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = description,
                    tint = textColor,
                    modifier = if (screen == AppScreen.ABOUT &&
                        LocalLayoutDirection.current == LayoutDirection.Rtl
                    ) Modifier.scale(scaleX = -1f, scaleY = 1f) else Modifier,
                )
            }
        },
    )
}
