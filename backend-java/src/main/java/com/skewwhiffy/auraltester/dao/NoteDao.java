package com.skewwhiffy.auraltester.dao;

import com.skewwhiffy.auraltester.notation.model.note.Note;

public record NoteDao(String noteName, AccidentalDao accidental) {
    public Note toModel() {
        return new Note(noteName, accidental.toModel());
    }
}
