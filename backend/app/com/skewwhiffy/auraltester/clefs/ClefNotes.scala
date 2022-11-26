package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Accidental, Interval, Note}
import com.skewwhiffy.auraltester.scales.NoteSequence

import scala.util.chaining.scalaUtilChainingOps

trait ClefNotes extends NoteSequence {
  implicit class AbsoluteNoteExtensions(val source: AbsoluteNote) {
    def downOne: AbsoluteNote = (source - Interval.minor(2)).ignoreAccidental

    def upOne: AbsoluteNote = (source + Interval.minor(2)).ignoreAccidental

    def skipOne: AbsoluteNote = (source + Interval.minor(3)).ignoreAccidental

    def ignoreAccidental: AbsoluteNote = Note(source.note.noteName, Accidental.natural)
        .pipe(it => AbsoluteNote(it, source.octave, source.lyric))

    def withNoteName: AbsoluteNote = source.withLyric(source.note.displayString)
  }
}
