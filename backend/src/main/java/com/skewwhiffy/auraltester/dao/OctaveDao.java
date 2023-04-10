package com.skewwhiffy.auraltester.dao;

import com.skewwhiffy.auraltester.notation.model.note.Octave;

public record OctaveDao(int offsetFromDefault) {
    public Octave toModel() {
        return new Octave(offsetFromDefault);
    }
}
