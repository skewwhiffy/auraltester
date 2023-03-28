package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;

import java.util.stream.Stream;

public record ClefUpperLedgerNotes(Clef clef) implements ClefNotes {
    @Override
    public Stream<AbsoluteNote> getNotes() {
        throw new RuntimeException();
    }
}
