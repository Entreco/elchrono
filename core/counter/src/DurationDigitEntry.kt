package nl.entreco.elchrono.core.counter

class DurationDigitEntry private constructor(
    private val digits: String,
) {
    private val fourDigits: String
        get() = digits.padStart(4, '0')

    val hours: Int
        get() = fourDigits.take(2).toInt()

    val minutes: Int
        get() = fourDigits.takeLast(2).toInt()

    val duration: TimerDuration?
        get() = TimerDuration.fromHoursAndMinutes(hours, minutes)

    fun append(digit: Int): DurationDigitEntry {
        require(digit in 0..9)
        return DurationDigitEntry((digits + digit).takeLast(4))
    }

    fun deleteLast(): DurationDigitEntry = DurationDigitEntry(digits.dropLast(1))

    fun clear(): DurationDigitEntry = empty()

    fun applyTextEdit(previousText: String, editedText: String): DurationDigitEntry {
        val prefixLength = previousText.commonPrefixWith(editedText).length
        var suffixLength = 0
        while (
            suffixLength < previousText.length - prefixLength &&
            suffixLength < editedText.length - prefixLength &&
            previousText[previousText.lastIndex - suffixLength] ==
            editedText[editedText.lastIndex - suffixLength]
        ) {
            suffixLength++
        }

        val removedDigits = previousText.length - prefixLength - suffixLength
        val insertedDigits = editedText.substring(prefixLength, editedText.length - suffixLength)
        var updated = this
        repeat(removedDigits) { updated = updated.deleteLast() }
        insertedDigits.filter { it in '0'..'9' }.forEach { updated = updated.append(it.digitToInt()) }
        return updated
    }

    companion object {
        fun empty(): DurationDigitEntry = DurationDigitEntry("")

        fun fromDuration(duration: TimerDuration): DurationDigitEntry =
            DurationDigitEntry(
                duration.hours.toString().padStart(2, '0') +
                    duration.minutes.toString().padStart(2, '0'),
            )
    }
}
