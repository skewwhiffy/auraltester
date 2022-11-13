package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval, Note}

case class Clef(
  abc: String,
  lowLedgerNote: AbsoluteNote,
  highLedgerNote: AbsoluteNote
) {
  def getNoteNearBottom(note: Note): AbsoluteNote = {
    val candidateStartingNote = AbsoluteNote(note, lowLedgerNote.octave)
    if (candidateStartingNote < lowLedgerNote) candidateStartingNote + Interval.perfect(8) else candidateStartingNote
  }
}
