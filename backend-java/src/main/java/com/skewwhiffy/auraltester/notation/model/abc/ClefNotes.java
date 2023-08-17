package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.notation.model.note.NoteSequence;

public interface ClefNotes extends NoteSequence {
    /*

interface ClefNotes : NoteSequence {
  val AbsoluteNote.downOne: AbsoluteNote
    get() = (this - Interval.minor(2)).ignoreAccidental

  val AbsoluteNote.upOne: AbsoluteNote
    get() = (this + Interval.minor(2)).ignoreAccidental

  val AbsoluteNote.skipOne: AbsoluteNote
    get() = (this + Interval.minor(3)).ignoreAccidental

  val AbsoluteNote.ignoreAccidental: AbsoluteNote
    get() = Note(note.noteName, Accidental.natural)
      .let { AbsoluteNote(it, octave, lyric) }

  val AbsoluteNote.withNoteName: AbsoluteNote
    get() = withLyric(note.displayString)
}
     */
}
