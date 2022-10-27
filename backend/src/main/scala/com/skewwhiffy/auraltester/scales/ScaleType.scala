package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.DirectedInterval

object ScaleType:
  private lazy val majorIntervals: List[DirectedInterval] = InternalNotationFactory.directedIntervals("1 2 3 4 5 6 7 8")
  private lazy val minorHarmonicIntervals: List[DirectedInterval] = InternalNotationFactory.directedIntervals("1 2 3- 4 5 6- 7 8")
  private lazy val minorMelodicAscendingIntervals: List[DirectedInterval] = InternalNotationFactory.directedIntervals("1 2 3- 4 5 6 7 8")
  private lazy val minorMelodicDescendingIntervals: List[DirectedInterval] = InternalNotationFactory.directedIntervals("1 -2 -3 -4 -5 -6 -7- -8")
  lazy val major: ScaleType = ScaleType("major", majorIntervals)
  lazy val minorHarmonic: ScaleType = ScaleType("minor harmonic", minorHarmonicIntervals)
  lazy val minorMelodicAscending: ScaleType = ScaleType("minor melodic ascending", minorMelodicAscendingIntervals)
  lazy val minorMelodicDescending: ScaleType = ScaleType("minor melodic descending", minorMelodicDescendingIntervals)

class ScaleType(val displayName: String, val intervals: List[DirectedInterval])