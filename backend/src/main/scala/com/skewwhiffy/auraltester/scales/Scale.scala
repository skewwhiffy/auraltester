package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, DirectedInterval, Interval}
import com.skewwhiffy.auraltester.scales.ScaleType

class Scale(private val start: AbsoluteNote, private val scaleType: ScaleType):
  lazy val displayName: String = s"${start.note.displayString} ${scaleType.displayName}"
  lazy val notes: List[AbsoluteNote] = scaleType.intervals.map(start.apply)
