package com.skewwhiffy.auraltester.notes

class DirectedInterval(
  val interval: Interval,
  val direction: IntervalDirection
) {

}

enum IntervalDirection:
  case Up, Down
