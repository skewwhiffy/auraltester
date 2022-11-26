package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.AbsoluteNote

object NoteSequence {
  def empty: NoteSequence = new NoteSequence {
    val notes: List[AbsoluteNote] = List()
  }

  def apply(values: AbsoluteNote*): NoteSequence = new NoteSequence {
    val notes: List[AbsoluteNote] = values.toList
  }
}

trait NoteSequence {
  def notes: List[AbsoluteNote]
}
