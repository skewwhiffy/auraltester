package com.skewwhiffy.notation.model.scale

import com.skewwhiffy.notation.model.interval.DirectedInterval

data class ScaleType(
    val displayName: String,
    private val abc: String,
    private val ascendingIntervals: List<DirectedInterval>,
    private val descendingIntervals: List<DirectedInterval>,
) {
  constructor(displayName: String, abc: String, intervals: List<DirectedInterval>)
    : this(displayName, abc, intervals, intervals)
  /*
  def intervals(direction: ScaleDirection): List[DirectedInterval] = {
    direction match {
      case ScaleDirection.ascending => ascendingIntervals
      case ScaleDirection.descending => descendingIntervals
    }
  }
}
   */
}