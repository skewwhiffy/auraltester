package com.skewwhiffy.auraltester.dao;

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;

import java.util.Optional;

public record AbsoluteNoteDao(
        NoteDao note,
        OctaveDao octave,
        String lyric
) {
    public AbsoluteNote toModel() {
        return new AbsoluteNote(note.toModel(), octave.toModel(), Optional.ofNullable(lyric));
    }
}
