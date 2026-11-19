package nl.entreco.elchrono

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.js

@OptIn(ExperimentalWasmJsInterop::class)
private fun registerBrowserInterruptionListeners(): Unit = js(
    """{
        globalThis.__elchronoPageHidden = false;
        document.addEventListener('visibilitychange', () => {
            if (document.hidden) globalThis.__elchronoPageHidden = true;
        });
        window.addEventListener('pagehide', () => {
            globalThis.__elchronoPageHidden = true;
        });
    }""",
)

@OptIn(ExperimentalWasmJsInterop::class)
private fun consumeBrowserInterruption(): Boolean = js(
    """(() => {
        const interrupted = globalThis.__elchronoPageHidden === true;
        globalThis.__elchronoPageHidden = false;
        return interrupted;
    })()""",
)

actual fun initializeAppInterruptionTracking() {
    registerBrowserInterruptionListeners()
}

actual fun consumeAppInterruption(): Boolean = consumeBrowserInterruption()
