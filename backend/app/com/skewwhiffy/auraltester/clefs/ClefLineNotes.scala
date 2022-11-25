package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.notes.AbsoluteNote
import com.skewwhiffy.auraltester.scales.NoteSequence

class ClefLineNotes(clef: Clef) extends NoteSequence {
  def notes: List[AbsoluteNote] = List(AbsoluteNote.middleC)

}
