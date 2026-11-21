package nl.entreco.elchrono

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.counter.TimerEngine
import nl.entreco.elchrono.core.design.ElChronoTheme
import nl.entreco.elchrono.core.design.TimerFont
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.persistence.RestoredSettings
import nl.entreco.elchrono.core.persistence.SettingsRepository
import nl.entreco.elchrono.core.persistence.StorageResult
import nl.entreco.elchrono.core.persistence.TimerSettings

@OptIn(ExperimentalTestApi::class)
class RemainingTimeUiTest {
    @Test
    fun `remaining time is centered and unclipped across themes and sizes`() = runDesktopComposeUiTest {
        val initial = settings(1)
        val viewModel = AppViewModel(TimerEngine(), TestRepository(initial), initial, null)
        var width by mutableStateOf(320.dp)
        var height by mutableStateOf(640.dp)
        var fontScale by mutableStateOf(1f)
        setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                ElChronoTheme {
                    Box(Modifier.size(width, height)) { AppContent(false, viewModel) }
                }
            }
        }

        listOf(320.dp to 640.dp, 1200.dp to 720.dp).forEach { (w, h) ->
            listOf(1f, 2f).forEach { scale ->
                TimerTheme.entries.forEachIndexed { index, theme ->
                    listOf(1, 1440).forEach { minutes ->
                        val selected = settings(minutes).copy(
                            theme = theme,
                            font = TimerFont.entries[index % TimerFont.entries.size],
                        )
                        runOnIdle {
                            width = w
                            height = h
                            fontScale = scale
                            viewModel.saveSettings(selected)
                        }
                        val expected = if (minutes == 1) "0 hr 1 min" else "24 hr 0 min"
                        val panel = onNodeWithTag("remaining-time-panel").fetchSemanticsNode().boundsInRoot
                        val timer = onNodeWithTag("timer-layer").fetchSemanticsNode().boundsInRoot
                        val text = onNodeWithText(expected)
                        text.assertIsDisplayed()
                        val textNode = text.fetchSemanticsNode()
                        val bounds = textNode.boundsInRoot
                        assertEquals(timer.center.x, panel.center.x, 1f, "$theme $w $scale")
                        assertEquals(timer.center.y, panel.center.y, 1f, "$theme $w $scale")
                        assertEquals(panel.center.x, bounds.center.x, 1f)
                        assertEquals(panel.center.y, bounds.center.y, 1f)
                        assertTrue(panel.left >= timer.left && panel.right <= timer.right)
                        assertTrue(panel.top >= timer.top && panel.bottom <= timer.bottom)
                        val settingsButton = onNodeWithContentDescription("Open Settings")
                            .fetchSemanticsNode().boundsInRoot
                        assertTrue(!panel.overlaps(settingsButton), "Remaining time overlaps Settings")
                        val layouts = mutableListOf<TextLayoutResult>()
                        val getLayout = assertNotNull(textNode.config.getOrNull(SemanticsActions.GetTextLayoutResult))
                        assertTrue(assertNotNull(getLayout.action).invoke(layouts))
                        val layout = layouts.single()
                        repeat(layout.lineCount) { line ->
                            assertTrue(
                                layout.getLineLeft(line) >= -1f,
                                "$theme $expected at $w and $scale: left=${layout.getLineLeft(line)}",
                            )
                            assertTrue(
                                layout.getLineRight(line) <= layout.size.width + 1f,
                                "$theme $expected at $w and $scale: right=${layout.getLineRight(line)}, width=${layout.size.width}",
                            )
                        }
                        assertFalse(layout.didOverflowHeight, "$theme $expected at $w and font scale $scale")
                    }
                }
            }
        }
    }

    @Test
    fun `glass is absent for detail zero and idle`() = runDesktopComposeUiTest {
        val initial = settings(1)
        val viewModel = AppViewModel(TimerEngine(), TestRepository(initial), initial, null)
        setContent { ElChronoTheme { AppContent(false, viewModel) } }
        onNodeWithTag("remaining-time-panel").assertIsDisplayed()
        runOnIdle { viewModel.saveSettings(initial.copy(detail = 0)) }
        onNodeWithTag("remaining-time-panel").assertDoesNotExist()
        onNodeWithText("0 hr 1 min").assertDoesNotExist()
        (1..12).forEach { detail ->
            runOnIdle { viewModel.saveSettings(initial.copy(detail = detail)) }
            onNodeWithTag("remaining-time-panel").assertIsDisplayed()
            onNodeWithText("0 hr 1 min").assertIsDisplayed()
        }
        runOnIdle { viewModel.saveSettings(settings(0)) }
        onNodeWithTag("remaining-time-panel").assertDoesNotExist()
    }

    @Test
    fun `glass backdrop follows changes to the visualization`() = runDesktopComposeUiTest {
        val initial = settings(60).copy(
            theme = TimerTheme.BATTERY,
            backgroundColorArgb = 0xFF000000,
            foregroundColorArgb = 0xFFFF0000,
            textColorArgb = 0xFFFFFFFF,
        )
        val viewModel = AppViewModel(TimerEngine(), TestRepository(initial), initial, null)
        setContent { ElChronoTheme { Box(Modifier.size(600.dp, 400.dp)) { AppContent(false, viewModel) } } }
        runOnIdle { viewModel.saveSettings(initial) }
        val red = onNodeWithTag("remaining-time-panel").captureToImage().toPixelMap()
        runOnIdle { viewModel.updateSettings(initial.copy(foregroundColorArgb = 0xFF00FF00)) }
        val green = onNodeWithTag("remaining-time-panel").captureToImage().toPixelMap()
        val x = red.width / 2
        val y = red.height - 8
        assertTrue(red[x, y].red > red[x, y].green + 0.05f, "Red visualization not visible through glass")
        assertTrue(green[x, y].green > green[x, y].red + 0.05f, "Glass backdrop did not update")
    }

    private fun settings(minutes: Int) = TimerSettings(
        duration = checkNotNull(TimerDuration.fromMinutes(minutes)),
        theme = TimerTheme.CLOCK,
    )

    private class TestRepository(private val settings: TimerSettings) : SettingsRepository {
        override fun load() = RestoredSettings(settings, null)
        override fun save(settings: TimerSettings): StorageResult<Unit> = StorageResult.Success(Unit)
    }
}
