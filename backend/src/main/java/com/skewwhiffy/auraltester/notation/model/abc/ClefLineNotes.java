package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;

import java.util.stream.Stream;

public record ClefLineNotes(Clef clef) implements ClefNotes {
    @Override
    public Stream<AbsoluteNote> getNotes() {
        throw new RuntimeException();
    }
    /*
data class ClefLineNotes(private val clef: Clef) : ClefNotes {
  override val notes: List<AbsoluteNote> = (1..4)
    .fold(listOf(clef.lowLedgerNote.skipOne)) { soFar, _ -> soFar + soFar.last().skipOne }
    .map { it.withNoteName }
}
     */
}
