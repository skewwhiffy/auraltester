package com.skewwhiffy.auraltester.dao;

import com.skewwhiffy.auraltester.notation.model.note.Accidental;

public record AccidentalDao(int offset) {
    public Accidental toModel() {
        return new Accidental(offset);
    }
}
