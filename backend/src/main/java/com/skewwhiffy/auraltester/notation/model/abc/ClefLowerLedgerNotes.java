package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;

import java.util.stream.Stream;

public record ClefLowerLedgerNotes(Clef clef) implements ClefNotes {
    @Override
    public Stream<AbsoluteNote> getNotes() {
        throw new RuntimeException();
    }
    /*

data class ClefLowerLedgerNotes(private val clef: Clef) : ClefNotes {
  override val notes: List<AbsoluteNote> = (1..5)
    .fold(listOf(clef.lowLedgerNote.upOne)) { soFar, _ -> soFar + soFar.last().downOne }
    .map { it.withNoteName }
}
     */
}
