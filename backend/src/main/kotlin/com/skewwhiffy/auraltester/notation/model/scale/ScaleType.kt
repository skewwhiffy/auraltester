package com.skewwhiffy.auraltester.notation.model.scale

import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval


data class ScaleType(
    val displayName: String,
    val abc: String,
    val ascendingIntervals: List<DirectedInterval>,
    val descendingIntervals: List<DirectedInterval>
) {
    constructor(displayName: String, abc: String, intervals: List<DirectedInterval>) : this(
        displayName,
        abc,
        intervals,
        intervals
    )

    fun getIntervals(direction: ScaleDirection) = when (direction) {
        ScaleDirection.ASCENDING -> ascendingIntervals
        ScaleDirection.DESCENDING -> descendingIntervals
    }
}
