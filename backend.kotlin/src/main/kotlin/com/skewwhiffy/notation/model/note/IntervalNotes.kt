package com.skewwhiffy.notation.model.note

import com.skewwhiffy.notation.model.interval.Interval

data class IntervalNotes (
  val bottomNote: AbsoluteNote,
  val interval: Interval
) : NoteSequence {
  override val notes: List<AbsoluteNote> = listOf(bottomNote, bottomNote + interval)
}
