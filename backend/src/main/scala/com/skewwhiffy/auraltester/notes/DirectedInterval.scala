package com.skewwhiffy.auraltester.notes

class DirectedInterval(
  val interval: Interval,
  val direction: IntervalDirection
):
  override def equals(obj: Any): Boolean = obj match
    case other: DirectedInterval => other.interval == interval && other.direction == direction
    case _ => false

enum IntervalDirection:
  case Up, Down
