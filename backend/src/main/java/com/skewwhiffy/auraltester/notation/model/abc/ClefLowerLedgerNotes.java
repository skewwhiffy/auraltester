package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.helper.NoParallelStream;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record ClefLowerLedgerNotes(Clef clef) implements ClefNotes {
    @Override
    public List<AbsoluteNote> getNotes() {
        return IntStream
                .range(1, 5)
                .boxed()
                .reduce(
                        Collections.singletonList(clef.lowLedgerNote().upOne()),
                        (soFar, i) -> {
                            val next = soFar.get(soFar.size() - 1).downOne();
                            return Stream.concat(soFar.stream(), Stream.of(next)).toList();
                        },
                        NoParallelStream.get()
                )
                .stream()
                .map(AbsoluteNote::withNoteName)
                .toList();
    }
    /*

data class ClefLowerLedgerNotes(private val clef: Clef) : ClefNotes {
  override val notes: List<AbsoluteNote> = (1..5)
    .fold(listOf(clef.lowLedgerNote.upOne)) { soFar, _ -> soFar + soFar.last().downOne }
    .map { it.withNoteName }
}
     */
}
