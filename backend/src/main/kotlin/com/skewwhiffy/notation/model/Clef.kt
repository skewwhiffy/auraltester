package com.skewwhiffy.notation.model

import com.skewwhiffy.notation.model.abc.ClefLineNotes
import com.skewwhiffy.notation.model.abc.ClefLowerLedgerNotes
import com.skewwhiffy.notation.model.abc.ClefSpaceNotes
import com.skewwhiffy.notation.model.abc.ClefUpperLedgerNotes

data class Clef(
  val abc: String,
  val lowLedgerNote: AbsoluteNote,
  val highLedgerNote: AbsoluteNote,
) {
  val displayName: String = abc

  private val lineNotes: NoteSequence = ClefLineNotes(this)

  private val spaceNotes: NoteSequence = ClefSpaceNotes(this)

  private val lowerLedgerNotes: NoteSequence = ClefLowerLedgerNotes(this)

  private val upperLedgerNotes: NoteSequence = ClefUpperLedgerNotes(this)


  val notes: List<NoteSequence> = listOf(
    NoteSequence.of(AbsoluteNote.middleC.withLyric("Middle~C")),
    lineNotes,
    spaceNotes,
    lowerLedgerNotes,
    upperLedgerNotes,
  )

  fun getNoteNearBottom(note: Note): AbsoluteNote {
    val candidateStartingNote = AbsoluteNote(note, lowLedgerNote.octave)
    return if (candidateStartingNote < lowLedgerNote) candidateStartingNote + Interval.perfect(8) else candidateStartingNote
  }
}