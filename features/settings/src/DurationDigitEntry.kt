package nl.entreco.elchrono.features.settings

import nl.entreco.elchrono.core.counter.TimerDuration

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

    companion object {
        fun empty(): DurationDigitEntry = DurationDigitEntry("")

        fun fromDuration(duration: TimerDuration): DurationDigitEntry =
            DurationDigitEntry(
                duration.hours.toString().padStart(2, '0') +
                    duration.minutes.toString().padStart(2, '0'),
            )
    }
}
