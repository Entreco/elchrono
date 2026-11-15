package nl.entreco.elchrono.core.localization

import kotlinx.coroutines.test.runTest
import nl.entreco.elchrono.core.localization.resources.Res
import nl.entreco.elchrono.core.localization.resources.compose_greeting
import nl.entreco.elchrono.core.localization.resources.greeting
import nl.entreco.elchrono.core.localization.resources.show_content
import org.jetbrains.compose.resources.getString
import kotlin.test.Test
import kotlin.test.assertEquals

class LocalizationTest {
    @Test
    fun `starter button text is preserved`() = runTest {
        assertEquals("Click me!", getString(Res.string.show_content))
    }

    @Test
    fun `starter greeting formats the platform name`() = runTest {
        val greeting = getString(Res.string.greeting, "Test platform")
        assertEquals("Hello, Test platform!", greeting)
        assertEquals("Compose: Hello, Test platform!", getString(Res.string.compose_greeting, greeting))
    }
}
