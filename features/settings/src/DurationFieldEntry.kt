package nl.entreco.elchrono.features.settings

import nl.entreco.elchrono.core.counter.TimerDuration

internal data class DurationFieldEdit(
    val entry: DurationFieldEntry,
    val text: String,
)

internal class DurationFieldEntry private constructor(
    val hours: Int,
    val minutes: Int,
) {
    val duration: TimerDuration
        get() = checkNotNull(TimerDuration.fromHoursAndMinutes(hours, minutes))

    fun editHours(text: String): DurationFieldEdit? {
        val input = text.toDurationFieldNumber() ?: return null
        val updatedHours = input.coerceAtMost(24)
        val updatedMinutes = if (updatedHours == 24) 0 else minutes
        val updated = DurationFieldEntry(updatedHours, updatedMinutes)
        return DurationFieldEdit(
            updated,
            if (updatedHours == input) text else updatedHours.toString(),
        )
    }

    fun editMinutes(text: String): DurationFieldEdit? {
        val input = text.toDurationFieldNumber() ?: return null
        val clamped = input.coerceAtMost(60)
        val carriesHour = clamped == 60
        val updatedHours = (hours + if (carriesHour) 1 else 0).coerceAtMost(24)
        val updatedMinutes = if (carriesHour || updatedHours == 24) 0 else clamped
        val updated = DurationFieldEntry(updatedHours, updatedMinutes)
        return DurationFieldEdit(
            updated,
            if (carriesHour || clamped != input) updatedMinutes.toString().padStart(2, '0')
            else text,
        )
    }

    companion object {
        fun fromDuration(duration: TimerDuration): DurationFieldEntry =
            DurationFieldEntry(duration.hours, duration.minutes)

        fun fromTotalMinutes(totalMinutes: Int): DurationFieldEntry =
            fromDuration(checkNotNull(TimerDuration.fromMinutes(totalMinutes)))
    }
}

private fun String.toDurationFieldNumber(): Int? {
    if (length > 2 || any { it !in '0'..'9' }) return null
    return toIntOrNull() ?: 0
}
