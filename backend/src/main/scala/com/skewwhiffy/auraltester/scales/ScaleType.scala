package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.clefs.ClefFactory
import com.skewwhiffy.auraltester.internalnotation.{InternalNotationFactory, IntervalFactory}
import com.skewwhiffy.auraltester.notes.DirectedInterval

object ScaleType {
  private val clefFactory = new ClefFactory()
  private val intervalFactory = new IntervalFactory()
  private val internalNotationFactory = new InternalNotationFactory(clefFactory, intervalFactory)

  private lazy val majorIntervals: String = "1 2 3 4 5 6 7 8"
  private lazy val minorHarmonicIntervals: String = "1 2 3- 4 5 6- 7 8"
  private lazy val minorMelodicAscendingIntervals: String = "1 2 3- 4 5 6 7 8"
  private lazy val minorMelodicDescendingIntervals: String = "1 2 3- 4 5 6- 7- 8"
  lazy val major: ScaleType = ScaleType("major", "", majorIntervals)
  lazy val minorHarmonic: ScaleType = ScaleType("minor harmonic", "min", minorHarmonicIntervals)
  lazy val minorMelodic: ScaleType = ScaleType(
    "minor melodic",
    "min",
    minorMelodicAscendingIntervals,
    minorMelodicDescendingIntervals
  )

  private def apply(
    displayName: String,
    abc: String,
    intervalsString: String
  ): ScaleType = ScaleType(
    displayName,
    abc,
    internalNotationFactory.directedIntervals(intervalsString)
  )

  //noinspection SameParameterValue
  private def apply(
    displayName: String,
    abc: String,
    ascendingIntervalsString: String,
    descendingIntervalsString: String
  ): ScaleType = ScaleType(
    displayName,
    abc,
    internalNotationFactory.directedIntervals(ascendingIntervalsString),
    internalNotationFactory.directedIntervals(descendingIntervalsString))

  private def apply(
    displayName: String,
    abc: String,
    intervals: List[DirectedInterval]
  ): ScaleType = ScaleType(displayName, abc, intervals, intervals)

  private def apply(
    displayName: String,
    abc: String,
    ascendingIntervals: List[DirectedInterval],
    descendingIntervals: List[DirectedInterval]
  ) = new ScaleType(displayName, abc, ascendingIntervals, descendingIntervals)
}

class ScaleType private(
  val displayName: String,
  val abc: String,
  private val ascendingIntervals: List[DirectedInterval],
  private val descendingIntervals: List[DirectedInterval]
) {
  def intervals(direction: ScaleDirection): List[DirectedInterval] = {
    direction match {
      case ScaleDirection.ascending => ascendingIntervals
      case ScaleDirection.descending => descendingIntervals
      // TODO: Check what happens with invalid choice
    }
  }
}