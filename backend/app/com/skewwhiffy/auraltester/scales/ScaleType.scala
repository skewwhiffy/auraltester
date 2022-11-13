package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.DirectedInterval

class ScaleType(
  val displayName: String,
  val abc: String,
  private val ascendingIntervals: List[DirectedInterval],
  private val descendingIntervals: List[DirectedInterval]
) {
  def this(
    displayName: String,
    abc: String,
    intervals: List[DirectedInterval]
  ) = this(displayName, abc, intervals, intervals)

  def intervals(direction: ScaleDirection): List[DirectedInterval] = {
    direction match {
      case ScaleDirection.ascending => ascendingIntervals
      case ScaleDirection.descending => descendingIntervals
    }
  }
}