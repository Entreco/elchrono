package nl.entreco.elchrono.features.settings

import nl.entreco.elchrono.core.counter.TimerDuration
import nl.entreco.elchrono.core.design.TimerTheme

data class TimerSettings(
    val duration: TimerDuration,
    val theme: TimerTheme,
)
