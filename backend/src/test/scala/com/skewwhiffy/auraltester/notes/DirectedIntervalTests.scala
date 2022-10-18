package com.skewwhiffy.auraltester.notes

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DirectedIntervalTests {
  @Test
  def canInstantiateUpInterval(): Unit = {
    val interval = Interval.major(6)

    val actual = interval.up

    assertThat(actual.interval).isEqualTo(interval)
    assertThat(actual.direction).isEqualTo(IntervalDirection.Up)
  }
  
  @Test
  def canInstantiateDownInterval(): Unit = {
    val interval = Interval.major(7)
    
    val actual = interval.down
    
    assertThat(actual.interval).isEqualTo(interval)
    assertThat(actual.direction).isEqualTo(IntervalDirection.Down)
  }
  
}
