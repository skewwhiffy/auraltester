package com.skewwhiffy.auraltester.notes

import org.scalatest.flatspec.AnyFlatSpec

class DirectedIntervalTests extends AnyFlatSpec {
  it should "instantiate up interval" in {
    val interval = Interval.major(6)

    val actual = interval.up

    assert(actual.interval == interval)
    assert(actual.direction == IntervalDirection.up)
  }

  it should "instantiate down interval" in {
    val interval = Interval.major(7)

    val actual = interval.down

    assert(actual.interval == interval)
    assert(actual.direction == IntervalDirection.down)
  }

  it should "equate equivalent directed intervals" in {
    def interval = Interval.perfect(5).up

    val first = interval
    val second = interval

    assert(first == second)
  }
}