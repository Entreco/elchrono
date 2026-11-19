package nl.entreco.elchrono.features.settings

data class DurationEntryInputMode(
    val keyboardInputEnabled: Boolean,
    val keyboardAvailable: Boolean,
) {
    val fieldsReadOnly: Boolean
        get() = !keyboardInputEnabled || !keyboardAvailable

    val showNumericKeypad: Boolean
        get() = !keyboardInputEnabled || !keyboardAvailable
}
