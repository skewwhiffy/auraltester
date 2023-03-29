package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.helper.StreamHelper;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record ClefLineNotes(Clef clef) implements ClefNotes {
    @Override
    public List<AbsoluteNote> getNotes() {
        return IntStream
                .range(0, 5)
                .boxed()
                .reduce(
                        Collections.singletonList(clef.lowLedgerNote().skipOne()),
                        (soFar, i) -> {
                            val next = soFar.get(soFar.size() - 1).skipOne();
                            return Stream.concat(soFar.stream(), Stream.of(next)).toList();
                        },
                        StreamHelper.getNoParallel()
                );
    }
}
