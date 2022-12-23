package com.skewwhiffy.notation.model.scale

data class ScaleType(
  private val displayName: String,
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