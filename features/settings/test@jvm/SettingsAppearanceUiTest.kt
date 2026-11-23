package nl.entreco.elchrono.features.settings

import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.design.TimerFont
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.design.ColorSwatchPicker
import nl.entreco.elchrono.core.design.DesignColorSwatch
import nl.entreco.elchrono.core.design.toFontFamily
import nl.entreco.elchrono.core.persistence.TimerSettings
import nl.entreco.elchrono.core.persistence.DefaultSettingsRepository
import nl.entreco.elchrono.core.persistence.SettingsStorage
import nl.entreco.elchrono.core.persistence.StorageResult

@OptIn(ExperimentalTestApi::class)
class SettingsAppearanceUiTest {
    @Test
    fun `font choices resolve to distinct families`() = runDesktopComposeUiTest {
        var families = emptyList<FontFamily>()
        setContent {
            val resolved = TimerFont.entries.map { it.toFontFamily() }
            SideEffect { families = resolved }
        }
        runOnIdle {
            assertEquals(TimerFont.entries.size, families.distinct().size)
            assertEquals(FontFamily.Default, families[TimerFont.DEFAULT.ordinal])
            assertEquals(FontFamily.Serif, families[TimerFont.SERIF.ordinal])
        }
    }

    @Test
    fun `swatches use at most eight columns and keep accessible targets on narrow widths`() = runDesktopComposeUiTest {
        var width by mutableStateOf(384.dp)
        var targetPixels = 0f
        val swatches = settingsColorPresets.map { DesignColorSwatch(it.id, it.id, Color(it.argb)) }
        setContent {
            val density = LocalDensity.current
            SideEffect { targetPixels = with(density) { 48.dp.toPx() } }
            Box(Modifier.width(width)) {
                ColorSwatchPicker(swatches, "", {})
            }
        }
        listOf(384.dp to 8, 288.dp to 6, 600.dp to 8).forEach { (available, columns) ->
            runOnIdle { width = available }
            val bounds = swatches.map { onNodeWithContentDescription(it.id).fetchSemanticsNode().boundsInRoot }
            val firstRow = bounds.take(columns)
            assertTrue(firstRow.all { it.top == firstRow.first().top })
            assertTrue(bounds[columns].top > firstRow.first().top)
            val targetWidth = firstRow.first().width
            assertTrue(bounds.all { it.width >= targetPixels && it.height >= targetPixels })
            assertTrue(bounds.all { it.right <= bounds.first().left + targetWidth * columns + 1f })
        }
    }

    @Test
    fun `rounded and serif fonts render differently from default`() = runDesktopComposeUiTest {
        var font by mutableStateOf(TimerFont.DEFAULT)
        setContent {
            Text("00:12 Rounded timer", fontFamily = font.toFontFamily(), fontSize = 32.sp)
        }
        fun image() = onNodeWithText("00:12 Rounded timer").captureToImage().toPixelMap()
        val default = image()
        for (choice in listOf(TimerFont.ROUNDED, TimerFont.SERIF)) {
            runOnIdle { font = choice }
            val actual = image()
            val differs = actual.width != default.width || actual.height != default.height ||
                (0 until default.width).any { x ->
                    (0 until default.height).any { y -> actual[x, y] != default[x, y] }
                }
            assertTrue(differs, "$choice must render differently from Default")
        }
    }

    @Test
    fun `all named presets select independently in each color picker`() = runDesktopComposeUiTest {
        var settings by mutableStateOf(
            TimerSettings(checkNotNull(TimerDuration.fromMinutes(10)), TimerTheme.CLOCK),
        )
        setContent {
            SettingsPanel(settings, { settings = it }, {}, true, true)
        }
        val labels = listOf(
            "White", "Paper", "Gray", "Ink", "Black", "Yellow", "Orange", "Red",
            "Pink", "Purple", "Navy", "Blue", "Cyan", "Teal", "Green", "Lime",
        )
        labels.forEachIndexed { index, label ->
            repeat(3) { picker ->
                onAllNodesWithContentDescription(label)[picker].performScrollTo().performClick()
                runOnIdle {
                    val actual = when (picker) {
                        0 -> settings.backgroundColorArgb
                        1 -> settings.foregroundColorArgb
                        else -> settings.textColorArgb
                    }
                    assertEquals(settingsColorPresets[index].argb, actual)
                }
            }
        }
        onNodeWithText("Rounded").performScrollTo().performClick()
        runOnIdle { assertEquals(TimerFont.ROUNDED, settings.font) }
        onNodeWithText("Serif").performScrollTo().performClick()
        runOnIdle { assertEquals(TimerFont.SERIF, settings.font) }
    }

    @Test
    fun `every preset round trips for each independent color`() {
        var stored: String? = null
        val storage = object : SettingsStorage {
            override fun load() = StorageResult.Success(stored)
            override fun save(serialized: String): StorageResult<Unit> {
                stored = serialized
                return StorageResult.Success(Unit)
            }
        }
        val defaults = TimerSettings(checkNotNull(TimerDuration.fromMinutes(10)), TimerTheme.CLOCK)
        val repository = DefaultSettingsRepository(storage, defaults)
        settingsColorPresets.forEach { preset ->
            val colors = listOf(
                defaults.copy(backgroundColorArgb = preset.argb),
                defaults.copy(foregroundColorArgb = preset.argb),
                defaults.copy(textColorArgb = preset.argb),
            )
            colors.forEach { configured ->
                assertTrue(repository.save(configured) is StorageResult.Success)
                assertEquals(configured, repository.load().settings)
                assertEquals(null, repository.load().storageFailure)
            }
        }
    }

    @Test
    fun `appearance theme recomposes with the selected colors and font`() = runDesktopComposeUiTest {
        var settings by mutableStateOf(
            TimerSettings(checkNotNull(TimerDuration.fromMinutes(10)), TimerTheme.CLOCK),
        )
        var background = Color.Unspecified
        var foreground = Color.Unspecified
        var text = Color.Unspecified
        var font: FontFamily? = null
        setContent {
            SettingsAppearanceTheme(settings) {
                val scheme = MaterialTheme.colorScheme
                val family = MaterialTheme.typography.bodyLarge.fontFamily
                SideEffect {
                    background = scheme.background
                    foreground = scheme.primary
                    text = scheme.onSurface
                    font = family
                }
            }

        }

        runOnIdle {
            settings = settings.copy(
                backgroundColorArgb = 0xFFE58A3A,
                foregroundColorArgb = 0xFF3D7A57,
                textColorArgb = 0xFF5B5BD6,
                font = TimerFont.MONOSPACE,
            )
        }
        runOnIdle {
            assertEquals(Color(0xFFE58A3A), background)
            assertEquals(Color(0xFF3D7A57), foreground)
            assertEquals(Color(0xFF5B5BD6), text)
            assertEquals(FontFamily.Monospace, font)
        }
    }

    @Test
    fun `appearance controls publish changes and preserve earlier selections`() = runDesktopComposeUiTest {
        var settings by mutableStateOf(
            TimerSettings(checkNotNull(TimerDuration.fromMinutes(10)), TimerTheme.CLOCK),
        )
        setContent {
            SettingsPanel(
                initialSettings = settings,
                onChange = { settings = it },
                onShowAbout = {},
                keyboardInputEnabled = true,
                keyboardAvailable = true,
            )
        }

        onAllNodesWithContentDescription("Orange")[0].performScrollTo().performClick()
        onAllNodesWithContentDescription("Green")[1].performScrollTo().performClick()
        onAllNodesWithContentDescription("Blue")[2].performScrollTo().performClick()
        onNodeWithText("Monospace").performScrollTo().performClick()
        onNodeWithText("Ring").performScrollTo().performClick()
        runOnIdle {
            assertEquals(0xFFE58A3A, settings.backgroundColorArgb)
            assertEquals(0xFF3D7A57, settings.foregroundColorArgb)
            assertEquals(0xFF5B5BD6, settings.textColorArgb)
            assertEquals(TimerFont.MONOSPACE, settings.font)
            assertEquals(TimerTheme.RING, settings.theme)
            assertEquals(10, settings.duration.totalMinutes)
        }
    }

    @Test
    fun `selected swatch check uses text color with contrasting backing`() = runDesktopComposeUiTest {
        val presets = settingsColorPresets.map { Color(it.argb) }
        var color by mutableStateOf(presets.first())
        var selected by mutableStateOf("matching")
        val settings = TimerSettings(checkNotNull(TimerDuration.fromMinutes(10)), TimerTheme.CLOCK)
        setContent {
            SettingsAppearanceTheme(settings.copy(textColorArgb = color.toArgb().toLong() and 0xffffffffL)) {
                ColorSwatchPicker(
                    swatches = listOf(
                        DesignColorSwatch("matching", "Matching", color),
                        DesignColorSwatch("other", "Other", Color.Gray),
                    ),
                    selectedId = selected,
                    onSelected = { selected = it },
                )
            }
        }

        for (preset in presets) {
            runOnIdle { color = preset }
            val backing = if (preset.luminance() > 0.179f) Color.Black else Color.White
            val light = maxOf(preset.luminance(), backing.luminance())
            val dark = minOf(preset.luminance(), backing.luminance())
            assertTrue((light + 0.05f) / (dark + 0.05f) >= 4.5f)
            onNodeWithContentDescription("Matching").assertIsOn()
            val pixels = onNodeWithContentDescription("Matching").captureToImage().toPixelMap()
            val backingPixels = (pixels.width / 3 until pixels.width * 2 / 3).sumOf { x ->
                (pixels.height / 3 until pixels.height * 2 / 3).count { y ->
                    val pixel = pixels[x, y]
                    kotlin.math.abs(pixel.red - backing.red) < 0.1f &&
                        kotlin.math.abs(pixel.green - backing.green) < 0.1f &&
                        kotlin.math.abs(pixel.blue - backing.blue) < 0.1f
                }
            }
            assertTrue(backingPixels > 3, "Missing contrasting check backing for $preset")
            onNodeWithContentDescription("Other").assertIsOff().performClick()
            mainClock.advanceTimeBy(1000)
            onNodeWithContentDescription("Other").assertIsOn()
            val otherPixels = onNodeWithContentDescription("Other").captureToImage().toPixelMap()
            val iconPixels = (otherPixels.width / 3 until otherPixels.width * 2 / 3).sumOf { x ->
                (otherPixels.height / 3 until otherPixels.height * 2 / 3).count { y ->
                    val pixel = otherPixels[x, y]
                    kotlin.math.abs(pixel.red - preset.red) < 0.3f &&
                        kotlin.math.abs(pixel.green - preset.green) < 0.3f &&
                        kotlin.math.abs(pixel.blue - preset.blue) < 0.3f
                }
            }
            assertTrue(iconPixels > 3, "Check did not render in $preset")
            onNodeWithContentDescription("Matching").assertIsOff().performClick()
            mainClock.advanceTimeBy(1000)
        }
        onNodeWithContentDescription("Matching").assertIsOn()
        onNodeWithContentDescription("Other").assertIsOff()
    }
}
