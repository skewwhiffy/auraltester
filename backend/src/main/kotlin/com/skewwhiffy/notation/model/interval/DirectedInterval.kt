package com.skewwhiffy.notation.model.interval

data class DirectedInterval(
    private val interval: Interval,
    private val direction: IntervalDirection
)
