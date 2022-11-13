package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval, Note}

class Clef(val abc: String, val lowLedgerNote: AbsoluteNote, val highLedgerNote: AbsoluteNote) {
  def getNoteNearBottom(note: Note): AbsoluteNote = {
    val candidateStartingNote = new AbsoluteNote(note, lowLedgerNote.octave)
    if (candidateStartingNote < lowLedgerNote) candidateStartingNote + Interval.perfect(8) else candidateStartingNote
  }
}
