package com.skewwhiffy.auraltester.notation.model.interval

data class DirectedInterval(val interval: Interval, val direction: IntervalDirection) {
    operator fun unaryMinus(): DirectedInterval = when (direction) {
        IntervalDirection.DOWN -> DirectedInterval(interval, IntervalDirection.UP)
        IntervalDirection.UP -> DirectedInterval(interval, IntervalDirection.DOWN)
    }
}
