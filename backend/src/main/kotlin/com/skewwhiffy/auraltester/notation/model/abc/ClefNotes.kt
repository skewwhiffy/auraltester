package com.skewwhiffy.auraltester.notation.model.abc

import com.skewwhiffy.auraltester.notation.model.interval.Interval
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.notation.model.note.Accidental
import com.skewwhiffy.auraltester.notation.model.note.Note
import com.skewwhiffy.auraltester.notation.model.note.NoteSequence
import com.skewwhiffy.auraltester.notation.model.note.displayString

interface ClefNotes : NoteSequence

val AbsoluteNote.downOne: AbsoluteNote
    get() = (this - Interval.minor(2)).ignoreAccidental

val AbsoluteNote.upOne: AbsoluteNote
    get() = (this + Interval.minor(2)).ignoreAccidental

val AbsoluteNote.skipOne: AbsoluteNote
    get() = (this + Interval.minor(3)).ignoreAccidental

val AbsoluteNote.ignoreAccidental: AbsoluteNote
    get() = AbsoluteNote(Note(note.noteName, Accidental.natural), octave, lyric)

val AbsoluteNote.withNoteName: AbsoluteNote
    get() = withLyric(note.displayString)