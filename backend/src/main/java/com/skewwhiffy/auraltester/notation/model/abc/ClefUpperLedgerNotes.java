package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.helper.NoParallelStream;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record ClefUpperLedgerNotes(Clef clef) implements ClefNotes {
    @Override
    public List<AbsoluteNote> getNotes() {
        return IntStream
                .range(1, 6)
                .boxed()
                .reduce(
                        Collections.singletonList(clef.highLedgerNote().downOne()),
                        (soFar, i) -> {
                            val next = soFar.get(soFar.size() - 1).upOne();
                            return Stream.concat(soFar.stream(), Stream.of(next)).toList();
                        },
                        NoParallelStream.get()
                )
                .stream()
                .map(AbsoluteNote::withNoteName)
                .toList();
    }
}
