package nl.entreco.elchrono.features.timer

import kotlin.jvm.JvmInline
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

@JvmInline
value class TimerDuration private constructor(val totalMinutes: Int) {
    val hours: Int
        get() = totalMinutes / MINUTES_PER_HOUR

    val minutes: Int
        get() = totalMinutes % MINUTES_PER_HOUR

    val asDuration: Duration
        get() = totalMinutes.minutes

    companion object {
        const val MINUTES_PER_HOUR = 60
        const val MAX_MINUTES = 24 * MINUTES_PER_HOUR

        fun fromMinutes(totalMinutes: Int): TimerDuration? =
            totalMinutes.takeIf { it in 1..MAX_MINUTES }?.let(::TimerDuration)

        fun fromHoursAndMinutes(hours: Int, minutes: Int): TimerDuration? {
            if (hours !in 0..24 || minutes !in 0 until MINUTES_PER_HOUR) return null
            return fromMinutes(hours * MINUTES_PER_HOUR + minutes)
        }

        fun fromDuration(duration: Duration): TimerDuration? {
            if (!duration.isFinite() || duration < 1.minutes || duration > 24.hours) return null
            if (duration.inWholeNanoseconds % 1.minutes.inWholeNanoseconds != 0L) return null
            return fromMinutes(duration.inWholeMinutes.toInt())
        }
    }
}
