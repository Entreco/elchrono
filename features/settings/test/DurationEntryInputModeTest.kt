package nl.entreco.elchrono.features.settings

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DurationEntryInputModeTest {
    @Test
    fun `non-mobile target always uses the numeric keypad`() {
        val mode = DurationEntryInputMode(keyboardInputEnabled = false, keyboardAvailable = true)

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
    fun `mobile with a detected keyboard uses text entry without keypad`() {
        val mode = DurationEntryInputMode(keyboardInputEnabled = true, keyboardAvailable = true)

        assertFalse(mode.fieldsReadOnly)
        assertFalse(mode.showNumericKeypad)
    }
}
