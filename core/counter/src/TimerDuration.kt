package nl.entreco.elchrono.core.counter

import kotlin.jvm.JvmInline
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

@JvmInline
value class TimerDuration private constructor(val totalMinutes: Int) {
    val hours: Int
        get() = asDuration.inWholeHours.toInt()

    val minutes: Int
        get() = asDuration.toComponents { _, minutes, _, _ -> minutes }

    val asDuration: Duration
        get() = totalMinutes.minutes

    companion object {
        private const val MAX_HOURS = 24
        private val MAX_DURATION = MAX_HOURS.hours

        val MINUTES_PER_HOUR = 1.hours.inWholeMinutes.toInt()
        val MAX_MINUTES = MAX_DURATION.inWholeMinutes.toInt()

        fun fromMinutes(totalMinutes: Int): TimerDuration? =
            totalMinutes.takeIf { it in 0..MAX_MINUTES }?.let(::TimerDuration)

        fun fromHoursAndMinutes(hours: Int, minutes: Int): TimerDuration? {
            if (hours !in 0..MAX_HOURS || minutes !in 0 until MINUTES_PER_HOUR) return null
            return fromDuration(hours.hours + minutes.minutes)
        }

        fun fromDuration(duration: Duration): TimerDuration? {
            if (duration !in Duration.ZERO..MAX_DURATION) return null
            val wholeMinutes = duration.inWholeMinutes.toInt()
            if (wholeMinutes.minutes != duration) return null
            return TimerDuration(wholeMinutes)
        }
    }
}
