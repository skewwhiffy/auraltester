package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval, Note}
import com.skewwhiffy.auraltester.scales.NoteSequence

case class Clef(
  abc: String,
  lowLedgerNote: AbsoluteNote,
  highLedgerNote: AbsoluteNote
) {
  def displayName: String = abc

  def notes: List[NoteSequence] = List(
    NoteSequence(AbsoluteNote.middleC.withLyric("Middle~C")),
    lineNotes,
    spaceNotes,
    lowerLedgerNotes,
    upperLedgerNotes,
  )

  def lineNotes: NoteSequence = ClefLineNotes(this)

  def spaceNotes: NoteSequence = ClefSpaceNotes(this)

  def lowerLedgerNotes: NoteSequence = ClefLowerLedgerNotes(this)

  def upperLedgerNotes: NoteSequence = ClefUpperLedgerNotes(this)

  def getNoteNearBottom(note: Note): AbsoluteNote = {
    val candidateStartingNote = AbsoluteNote(note, lowLedgerNote.octave)
    if (candidateStartingNote < lowLedgerNote) candidateStartingNote + Interval.perfect(8) else candidateStartingNote
  }
}