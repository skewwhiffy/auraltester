package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;

import java.util.List;

public record ClefUpperLedgerNotes(Clef clef) implements ClefNotes {
    @Override
    public List<AbsoluteNote> getNotes() {
        throw new RuntimeException();
    }
}
