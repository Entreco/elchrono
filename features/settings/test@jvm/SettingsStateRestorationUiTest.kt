package nl.entreco.elchrono.features.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.LocalSaveableStateRegistry
import androidx.compose.runtime.saveable.SaveableStateRegistry
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.design.TimerTheme
import nl.entreco.elchrono.core.persistence.TimerSettings

@OptIn(ExperimentalTestApi::class)
class SettingsStateRestorationUiTest {
    @Test
    fun `settings restore scroll and keep controls reachable after landscape resize`() = runUiTest {
        var registry by mutableStateOf(SaveableStateRegistry(null) { true })
        var visible by mutableStateOf(true)
        var landscape by mutableStateOf(false)
        var settings by mutableStateOf(
            TimerSettings(checkNotNull(TimerDuration.fromMinutes(5)), TimerTheme.RING),
        )
        setContent {
            if (visible) {
                CompositionLocalProvider(LocalSaveableStateRegistry provides registry) {
                    Box(
                        Modifier.size(
                            if (landscape) 640.dp else 360.dp,
                            if (landscape) 360.dp else 640.dp,
                        ).testTag("settings-viewport"),
                    ) {
                        SettingsPanel(settings, { settings = it }, {})
                    }
                }
            }
        }
        onNodeWithText("About").performScrollTo().assertIsDisplayed()
        val scrollMatcher = SemanticsMatcher.keyIsDefined(SemanticsProperties.VerticalScrollAxisRange)
        val scroll = onNode(scrollMatcher).fetchSemanticsNode()
            .config[SemanticsProperties.VerticalScrollAxisRange].value()
        assertTrue(scroll > 0f)
        val saved = runOnIdle { registry.performSave().also { visible = false } }
        runOnIdle {
            registry = SaveableStateRegistry(saved) { true }
            visible = true
        }
        onNodeWithText("About").assertIsDisplayed()
        assertEquals(
            scroll,
            onNode(scrollMatcher).fetchSemanticsNode()
                .config[SemanticsProperties.VerticalScrollAxisRange].value(),
        )
        runOnIdle { landscape = true }
        listOf("Hours", "Ring thickness", "About").forEach { label ->
            onNodeWithText(label).performScrollTo().assertIsDisplayed()
            val bounds = onNodeWithText(label).fetchSemanticsNode().boundsInRoot
            val viewport = onNodeWithTag("settings-viewport").fetchSemanticsNode().boundsInRoot
            assertTrue(bounds.top >= viewport.top && bounds.bottom <= viewport.bottom, label)
            assertTrue(bounds.left >= viewport.left && bounds.right <= viewport.right, label)
        }
    }
}
