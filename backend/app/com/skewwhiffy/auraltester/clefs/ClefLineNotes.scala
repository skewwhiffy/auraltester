package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Accidental, Interval, Note}
import com.skewwhiffy.auraltester.scales.NoteSequence

import scala.util.chaining.scalaUtilChainingOps

case class ClefLineNotes(clef: Clef) extends NoteSequence {
  def notes: List[AbsoluteNote] = {
    val lowestNote = clef.lowLedgerNote.nextNoteUp
    Range(1, 5)
      .foldLeft(List(lowestNote))((soFar, _) => soFar :+ soFar.last.nextNoteUp)
      .map(it => it.withLyric(it.note.displayString))
  }

  implicit class BetterString(val source: AbsoluteNote) {
    def nextNoteUp: AbsoluteNote = (source + Interval.minor(3)).ignoreAccidental

    def ignoreAccidental: AbsoluteNote = {
      Note(source.note.noteName, Accidental.natural)
        .pipe(it => AbsoluteNote(it, source.octave, source.lyric))
    }
  }

}
