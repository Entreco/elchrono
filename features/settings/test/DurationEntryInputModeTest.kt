package nl.entreco.elchrono.features.settings

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DurationEntryInputModeTest {
    @Test
    fun `keypad-only target ignores keyboard availability`() {
        val mode = DurationEntryInputMode(keyboardInputEnabled = false, keyboardAvailable = true)

        assertTrue(mode.fieldsReadOnly)
        assertTrue(mode.showNumericKeypad)
    }

    @Test
    fun `keypad-only target remains available without a keyboard`() {
        val mode = DurationEntryInputMode(keyboardInputEnabled = false, keyboardAvailable = false)

        assertTrue(mode.fieldsReadOnly)
        assertTrue(mode.showNumericKeypad)
    }

    @Test
    fun `mobile without a detected keyboard uses keypad only`() {
        val mode = DurationEntryInputMode(keyboardInputEnabled = true, keyboardAvailable = false)

        assertTrue(mode.fieldsReadOnly)
        assertTrue(mode.showNumericKeypad)
    }

    @Test
    fun `enabled keyboard entry uses text entry without keypad`() {
        val mode = DurationEntryInputMode(keyboardInputEnabled = true, keyboardAvailable = true)

        assertFalse(mode.fieldsReadOnly)
        assertFalse(mode.showNumericKeypad)
    }
}
