package com.skewwhiffy.auraltester.notation.factory

import com.skewwhiffy.auraltester.notation.model.scale.ScaleType
import org.springframework.stereotype.Service

@Service
class ScaleTypeFactory(private val intervalFactory: IntervalFactory) {
    val major
        get() = ScaleType("major", "", intervalFactory.getDirectedIntervals("1 2 3 4 5 6 7 8"))

    val minorHarmonic
        get() = ScaleType("minor harmonic", "min", intervalFactory.getDirectedIntervals("1 2 3- 4 5 6- 7 8"))

    val minorMelodic
        get() = ScaleType(
            "minor melodic",
            "min",
            intervalFactory.getDirectedIntervals("1 2 3- 4 5 6 7 8"),
            intervalFactory.getDirectedIntervals("1 2 3- 4 5 6- 7- 8")
        )
}
