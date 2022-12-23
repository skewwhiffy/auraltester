package com.skewwhiffy.notation.model

data class DirectedInterval(
  private val interval: Interval,
  private val direction: IntervalDirection
)
