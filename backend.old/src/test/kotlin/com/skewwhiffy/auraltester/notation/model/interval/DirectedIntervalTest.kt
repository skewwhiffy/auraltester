package com.skewwhiffy.auraltester.notation.model.interval

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DirectedIntervalTest {
    @Test
    fun instantiatesUpInterval() {
        val interval = Interval.major(6)
        val actual = interval.up
        assertThat(actual.interval).isEqualTo(interval)
        assertThat(actual.direction).isEqualTo(IntervalDirection.UP)
    }

    @Test
    fun instantiatesDownInterval() {
        val interval = Interval.major(7)
        val actual = interval.down
        assertThat(actual.interval).isEqualTo(interval)
        assertThat(actual.direction).isEqualTo(IntervalDirection.DOWN)
    }

    @Test
    fun equatesEquivalentDirectedIntervals() {
        val getInterval = { Interval.perfect(5) }
        val first = getInterval()
        val second = getInterval()
        assertThat(first).isEqualTo(second)
    }
}
