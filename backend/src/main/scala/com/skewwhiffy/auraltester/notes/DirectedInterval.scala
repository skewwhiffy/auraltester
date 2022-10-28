package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.notes.Interval.Interval

class DirectedInterval(
  val interval: Interval,
  val direction: IntervalDirection
) {
  override def equals(obj: Any): Boolean = obj match {
    case other: DirectedInterval => other.interval == interval && other.direction == direction
    case _ => false
  }
}