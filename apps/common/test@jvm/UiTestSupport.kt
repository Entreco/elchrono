package nl.entreco.elchrono

import androidx.compose.ui.test.DesktopComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import kotlin.time.Duration.Companion.minutes

/**
 * Runs a desktop Compose UI test with a wall-clock timeout that tolerates slow,
 * contended CI runners; the 60 s library default caused spurious failures.
 */
@OptIn(ExperimentalTestApi::class)
internal fun runUiTest(block: suspend DesktopComposeUiTest.() -> Unit) =
    runDesktopComposeUiTest(testTimeout = UI_TEST_TIMEOUT, block = block)

private val UI_TEST_TIMEOUT = 5.minutes
