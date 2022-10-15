package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.abc.AbcFactory
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval}

object Scale {
  private lazy val majorIntervals: List[Interval] = AbcFactory.intervals("1 2 3 4 5 6 7 8")
  lazy val major: AbsoluteNote => Scale = start => Scale(start, majorIntervals)

  object minor {
    private lazy val harmonicIntervals: List[Interval] = AbcFactory.intervals("1 2 3- 4 5 6- 7 8")
    lazy val harmonic: AbsoluteNote => Scale = start => Scale(start, harmonicIntervals)

    object melodic {
      private lazy val ascendingIntervals: List[Interval] = AbcFactory.intervals("1 2 3- 4 5 6 7 8")
      private lazy val descendingIntervals: List[Interval] = AbcFactory.intervals("1 -2 -3 -4 -5 -6 -7- -8")
      lazy val ascending: AbsoluteNote => Scale = start => Scale(start, ascendingIntervals)
      lazy val descending: AbsoluteNote => Scale = start => Scale(start, descendingIntervals)
    }
  }
}

class Scale(private val start: AbsoluteNote, private val intervals: List[Interval]) {
  lazy val notes: List[AbsoluteNote] = intervals.map(start.add)
}
