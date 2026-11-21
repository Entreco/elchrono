package nl.entreco.elchrono

import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import kotlin.test.Test
import kotlin.test.assertTrue
import nl.entreco.elchrono.core.design.ElChronoTheme

@OptIn(ExperimentalTestApi::class)
class AppBarIconTest {
    @Test
    fun `navigation icons render the current text color and keep their actions`() = runDesktopComposeUiTest {
        var screen by mutableStateOf(AppScreen.TIMER)
        var color by mutableStateOf(Color(0xFF3D7A57))
        var clicks = 0
        setContent {
            ElChronoTheme {
                Surface(color = Color.White) {
                    AppBar(screen, color) { clicks++ }
                }
            }
        }

        listOf(
            AppScreen.TIMER to "Open Settings",
            AppScreen.SETTINGS to "Hide Settings",
            AppScreen.ABOUT to "Back",
        ).forEach { (target, label) ->
            runOnIdle { screen = target }
            for (selectedColor in listOf(Color(0xFF3D7A57), Color(0xFF5B5BD6))) {
                runOnIdle { color = selectedColor }
                val pixels = onNodeWithContentDescription(label).captureToImage().toPixelMap()
                val matchingPixels = (0 until pixels.width).sumOf { x ->
                    (0 until pixels.height).count { y ->
                        val pixel = pixels[x, y]
                        kotlin.math.abs(pixel.red - selectedColor.red) < 0.1f &&
                            kotlin.math.abs(pixel.green - selectedColor.green) < 0.1f &&
                            kotlin.math.abs(pixel.blue - selectedColor.blue) < 0.1f
                    }
                }
                assertTrue(matchingPixels > 3, "$label did not render in $selectedColor")
            }
            onNodeWithContentDescription(label).performClick()
            mainClock.advanceTimeBy(1000)
        }
        runOnIdle { assertTrue(clicks == 3) }
    }
}
