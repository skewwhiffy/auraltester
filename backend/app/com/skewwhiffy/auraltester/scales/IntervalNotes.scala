package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval}

case class IntervalNotes (
  bottomNote: AbsoluteNote,
  interval: Interval
) extends NoteSequence {
  def notes: List[AbsoluteNote] = List(bottomNote, bottomNote + interval)
}
