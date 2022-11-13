package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.internalnotation.IntervalFactory
import com.skewwhiffy.auraltester.notes.DirectedInterval

import javax.inject.{Inject, Singleton}

@Singleton
class ScaleTypeFactory @Inject()(intervalFactory: IntervalFactory) {
  private lazy val majorIntervals: String = "1 2 3 4 5 6 7 8"
  private lazy val minorHarmonicIntervals: String = "1 2 3- 4 5 6- 7 8"
  private lazy val minorMelodicAscendingIntervals: String = "1 2 3- 4 5 6 7 8"
  private lazy val minorMelodicDescendingIntervals: String = "1 2 3- 4 5 6- 7- 8"

  def major: ScaleType = new ScaleType("major", "", majorIntervals.toIntervals)
  def minorHarmonic: ScaleType = new ScaleType("minor harmonic", "min", minorHarmonicIntervals.toIntervals)
  def minorMelodic: ScaleType = new ScaleType(
    "minor melodic",
    "min",
    minorMelodicAscendingIntervals.toIntervals,
    minorMelodicDescendingIntervals.toIntervals
  )

  implicit class IntervalConverter(val rawIntervals: String) {
    def toIntervals: List[DirectedInterval] = intervalFactory.getDirectedIntervals(rawIntervals)
  }
}