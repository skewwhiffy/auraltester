/*
package com.skewwhiffy.notation.model.interval

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DirectedIntervalTest {
  @Test
  fun `instantiates up interval` () {
    val interval = Interval.major(6)

    val actual = interval.up

    assertThat(actual.interval).isEqualTo(interval)
    assertThat(actual.direction).isEqualTo(IntervalDirection.UP)
  }

  @Test
  fun `instantiates down interval` () {
    val interval = Interval.major(7)

    val actual = interval.down

    assertThat(actual.interval).isEqualTo(interval)
    assertThat(actual.direction).isEqualTo(IntervalDirection.DOWN)
  }

  @Test
  fun `equates equivalent directed intervals` () {
    fun getInterval() = Interval.perfect(5).up

    val first = getInterval()
    val second = getInterval()

    assertThat(first).isEqualTo(second)
  }
}*/
