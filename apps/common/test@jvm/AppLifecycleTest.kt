package nl.entreco.elchrono

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class AppLifecycleTest {
    @Test
    fun `configuration stop is ignored but a later real stop is delivered`() = runUiTest {
        val owner = TestLifecycleOwner()
        var configurationChange = false
        var interruptions = 0
        setContent {
            CompositionLocalProvider(LocalLifecycleOwner provides owner) {
                AppBackgroundEffect(
                    onBackgrounded = { interruptions++ },
                    isChangingConfigurations = { configurationChange },
                )
            }
        }
        runOnIdle {
            configurationChange = true
            owner.registry.currentState = Lifecycle.State.CREATED
            assertEquals(0, interruptions)
            owner.registry.currentState = Lifecycle.State.RESUMED
            configurationChange = false
            owner.registry.currentState = Lifecycle.State.CREATED
            assertEquals(1, interruptions)
        }
    }

    @Test
    fun `disposing composition unregisters the background observer`() = runUiTest {
        val owner = TestLifecycleOwner()
        var visible by mutableStateOf(true)
        var interruptions = 0
        setContent {
            if (visible) {
                CompositionLocalProvider(LocalLifecycleOwner provides owner) {
                    AppBackgroundEffect({ interruptions++ }, { false })
                }
            }
        }
        runOnIdle { visible = false }
        runOnIdle {
            owner.registry.currentState = Lifecycle.State.CREATED
            assertEquals(0, interruptions)
        }
    }

    private class TestLifecycleOwner : LifecycleOwner {
        val registry = LifecycleRegistry.createUnsafe(this).apply {
            currentState = Lifecycle.State.RESUMED
        }
        override val lifecycle: Lifecycle get() = registry
    }
}
