package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.abc.AbcFactory
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, DirectedInterval, Interval}

object Scale {
  private lazy val majorIntervals: List[DirectedInterval] = AbcFactory.directedIntervals("1 2 3 4 5 6 7 8")
  lazy val major: AbsoluteNote => Scale = start => Scale(start, majorIntervals)

  object minor {
    private lazy val harmonicIntervals: List[DirectedInterval] = AbcFactory.directedIntervals("1 2 3- 4 5 6- 7 8")
    lazy val harmonic: AbsoluteNote => Scale = start => Scale(start, harmonicIntervals)

    object melodic {
      private lazy val ascendingIntervals: List[DirectedInterval] = AbcFactory.directedIntervals("1 2 3- 4 5 6 7 8")
      private lazy val descendingIntervals: List[DirectedInterval] = AbcFactory.directedIntervals("1 -2 -3 -4 -5 -6 -7- -8")
      lazy val ascending: AbsoluteNote => Scale = start => Scale(start, ascendingIntervals)
      lazy val descending: AbsoluteNote => Scale = start => Scale(start, descendingIntervals)
    }
  }
}

class Scale(private val start: AbsoluteNote, private val intervals: List[DirectedInterval]) {
  lazy val notes: List[AbsoluteNote] = intervals.map(start.apply)
}
