package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.DirectedInterval

object ScaleType {
  private lazy val majorIntervals: String = "1 2 3 4 5 6 7 8"
  private lazy val minorHarmonicIntervals: String = "1 2 3- 4 5 6- 7 8"
  private lazy val minorMelodicAscendingIntervals: String = "1 2 3- 4 5 6 7 8"
  private lazy val minorMelodicDescendingIntervals: String = "1 2 3- 4 5 6- 7- 8"
  lazy val major: ScaleType = ScaleType("major", majorIntervals)
  lazy val minorHarmonic: ScaleType = ScaleType("minor harmonic", minorHarmonicIntervals)
  lazy val minorMelodic: ScaleType = ScaleType(
    "minor melodic ascending",
    minorMelodicAscendingIntervals,
    minorMelodicDescendingIntervals
  )

  private def apply(
    displayName: String,
    intervalsString: String
  ): ScaleType = ScaleType(
    displayName,
    InternalNotationFactory.directedIntervals(intervalsString)
  )

  //noinspection SameParameterValue
  private def apply(
    displayName: String,
    ascendingIntervalsString: String,
    descendingIntervalsString: String
  ): ScaleType = ScaleType(
    displayName,
    InternalNotationFactory.directedIntervals(ascendingIntervalsString),
    InternalNotationFactory.directedIntervals(descendingIntervalsString))

  private def apply(displayName: String, intervals: List[DirectedInterval]): ScaleType = ScaleType(displayName, intervals, intervals)

  private def apply(
    displayName: String,
    ascendingIntervals: List[DirectedInterval],
    descendingIntervals: List[DirectedInterval]
  ) = new ScaleType(displayName, ascendingIntervals, descendingIntervals)
}

class ScaleType private(
  val displayName: String,
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