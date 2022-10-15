package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.abc.AbcFactory
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval}

object Scale {
  lazy val majorIntervals = AbcFactory.intervals("1 2 3 4 5 6 7 8")
  lazy val major: AbsoluteNote => Scale = start => Scale(start, majorIntervals)
}

class Scale(private val start: AbsoluteNote, private val intervals: List[Interval]) {
  lazy val notes: List[AbsoluteNote] = intervals.map(start.add)
}
