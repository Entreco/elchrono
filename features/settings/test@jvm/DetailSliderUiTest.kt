package nl.entreco.elchrono.features.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.persistence.TimerSettings

@OptIn(ExperimentalTestApi::class)
class DetailSliderUiTest {
    @Test
    fun `all detail levels remain selectable and announced across zero`() = runDesktopComposeUiTest {
        var settings by mutableStateOf(
            TimerSettings(checkNotNull(TimerDuration.fromMinutes(10)), TimerTheme.CLOCK),
        )
        setContent {
            SettingsScreen(
                initialSettings = settings,
                onChange = { settings = it },
                onShowAbout = {},
                keyboardInputEnabled = true,
                keyboardAvailable = true,
            )
        }

        (0..12).toList().plus((11 downTo 0).toList()).forEach { detail ->
            onNodeWithContentDescription("Detail").performSemanticsAction(SemanticsActions.SetProgress) {
                it(detail.toFloat())
            }
            onNodeWithContentDescription("Detail").assert(
                SemanticsMatcher.expectValue(
                    SemanticsProperties.ProgressBarRangeInfo,
                    ProgressBarRangeInfo(detail.toFloat(), 0f..12f, 11),
                ),
            )
            runOnIdle { assertEquals(detail, settings.detail) }
        }
    }

    @Test
    fun `zero track has no visible dots and nonzero track restores them`() = runDesktopComposeUiTest {
        var settings by mutableStateOf(
            TimerSettings(checkNotNull(TimerDuration.fromMinutes(10)), TimerTheme.CLOCK, detail = 0),
        )
        setContent {
            Column(Modifier.width(400.dp).verticalScroll(rememberScrollState())) {
                SettingsScreen(
                    initialSettings = settings,
                    onChange = { settings = it },
                    onShowAbout = {},
                    keyboardInputEnabled = true,
                    keyboardAvailable = true,
                )
            }
        }

        val slider = onNodeWithContentDescription("Detail").performScrollTo()
        val zero = slider.captureToImage().toPixelMap()
        slider.performSemanticsAction(SemanticsActions.SetProgress) { it(1f) }
        val one = slider.captureToImage().toPixelMap()
        val start = zero.width / 2
        val end = zero.width * 9 / 10
        val y = zero.height / 2
        val zeroColors = (start until end).map { zero[it, y] }.toSet()
        val oneColors = (start until end).map { one[it, y] }.toSet()
        assertEquals(1, zeroColors.size, "The inactive track must not contain tick dots at zero")
        assertTrue(oneColors.size > 1, "The inactive track must contain tick dots above zero")
    }
}
