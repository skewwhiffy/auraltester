package com.skewwhiffy.auraltester.dao

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote

data class AbsoluteNoteDao(
    val note: NoteDao,
    val octave: OctaveDao,
    val lyric: String?
)

val AbsoluteNoteDao.model
    get() = AbsoluteNote(note.model, octave.model, lyric)
