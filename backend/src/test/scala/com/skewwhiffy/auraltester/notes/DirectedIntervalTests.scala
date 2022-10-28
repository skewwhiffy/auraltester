package com.skewwhiffy.auraltester.notes

import org.scalatest.funsuite.AnyFunSuite

class DirectedIntervalTests extends AnyFunSuite {
  test("can instantiate up interval") {
    val interval = Interval.major(6)

    val actual = interval.up

    assert(actual.interval == interval)
    assert(actual.direction == IntervalDirection.Up)
  }

  test("can instantiate down interval") {
    val interval = Interval.major(7)

    val actual = interval.down

    assert(actual.interval == interval)
    assert(actual.direction == IntervalDirection.Down)
  }

  test("equivalent directed intervals are equal") {
    def interval = Interval.perfect(5).up

    val first = interval
    val second = interval

    assert(first == second)
  }
}