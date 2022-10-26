package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, DirectedInterval, Interval}

object Scale:
  private lazy val majorIntervals: List[DirectedInterval] = InternalNotationFactory.directedIntervals("1 2 3 4 5 6 7 8")
  lazy val major: AbsoluteNote => Scale = start => Scale(start, majorIntervals, "major")

  object minor:
    private lazy val harmonicIntervals: List[DirectedInterval] = InternalNotationFactory.directedIntervals("1 2 3- 4 5 6- 7 8")
    lazy val harmonic: AbsoluteNote => Scale = start => Scale(start, harmonicIntervals, "minor harmonic")

    object melodic:
      private lazy val ascendingIntervals: List[DirectedInterval] = InternalNotationFactory.directedIntervals("1 2 3- 4 5 6 7 8")
      private lazy val descendingIntervals: List[DirectedInterval] = InternalNotationFactory.directedIntervals("1 -2 -3 -4 -5 -6 -7- -8")
      lazy val ascending: AbsoluteNote => Scale = start => Scale(start, ascendingIntervals, "minor melodic ascending")
      lazy val descending: AbsoluteNote => Scale = start => Scale(start, descendingIntervals, "minor melodic descending")

class Scale(private val start: AbsoluteNote, private val intervals: List[DirectedInterval], private val scaleType: String):
  lazy val displayName: String = s"${start.note.displayString} $scaleType"
  lazy val notes: List[AbsoluteNote] = intervals.map(start.apply)
