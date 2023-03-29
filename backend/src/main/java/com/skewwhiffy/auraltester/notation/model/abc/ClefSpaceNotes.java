package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;

import java.util.List;
import java.util.stream.Stream;

public record ClefSpaceNotes(Clef clef) implements ClefNotes {
    @Override
    public List<AbsoluteNote> getNotes() {
        throw new RuntimeException();
    }
    /*

data class ClefSpaceNotes(private val clef: Clef) : ClefNotes {
  override val notes: List<AbsoluteNote> = (1..3)
    .fold(listOf(clef.lowLedgerNote.upOne.skipOne)) { soFar, _ -> soFar + soFar.last().skipOne }
    .map { it.withNoteName }
}
     */
}
