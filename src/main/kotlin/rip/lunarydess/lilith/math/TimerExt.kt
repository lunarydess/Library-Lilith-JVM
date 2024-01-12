package rip.lunarydess.lilith.math

import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toTimeUnit

object TimerExt {
    fun Timer.getTimeIn(durationUnit: DurationUnit): Long {
        return this.getTimeIn(durationUnit.toTimeUnit())
    }

    fun Timer.setTimeIn(durationUnit: DurationUnit, duration: Duration) {
        return this.setTimeIn(durationUnit.toTimeUnit(), duration.inWholeNanoseconds)
    }

    fun Timer.setTimeIn(durationUnit: DurationUnit, nanos: Long) {
        return this.setTimeIn(durationUnit.toTimeUnit(), nanos)
    }

    fun Timer.hasElapsedIn(durationUnit: DurationUnit, duration: Duration): Boolean {
        return this.hasElapsedIn(durationUnit.toTimeUnit(), duration.inWholeNanoseconds)
    }

    fun Timer.hasElapsedIn(durationUnit: DurationUnit, nanos: Long): Boolean {
        return this.hasElapsedIn(durationUnit.toTimeUnit(), nanos)
    }

    fun Timer.elapsedTimeIn(durationUnit: DurationUnit): Long {
        return this.elapsedTimeIn(durationUnit.toTimeUnit())
    }
}