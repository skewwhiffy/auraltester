package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.AbsoluteNote
import com.skewwhiffy.auraltester.notes.Interval.Interval

class IntervalNotes (
  val bottomNote: AbsoluteNote,
  val interval: Interval
) extends NoteSequence {
  def notes: List[AbsoluteNote] = List(bottomNote, bottomNote + interval)
}
