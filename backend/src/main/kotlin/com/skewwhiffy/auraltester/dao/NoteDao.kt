package com.skewwhiffy.auraltester.dao

import com.skewwhiffy.auraltester.notation.model.note.Note

data class NoteDao(
    val noteName: String,
    val accidental: AccidentalDao
) {
    val model
        get() = Note(noteName, accidental.model)
}
