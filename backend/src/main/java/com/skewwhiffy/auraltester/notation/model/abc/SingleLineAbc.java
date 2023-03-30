package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.notation.model.note.NoteLength;
import lombok.val;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record SingleLineAbc(
        Optional<String> displayName,
        Clef clef,
        NoteLength noteLength,
        Optional<Key> keySignature,
        List<List<AbsoluteNote>> notes
) implements AbcProvider {

    public SingleLineAbc(
            String displayName,
            Clef clef,
            NoteLength noteLength,
            List<List<AbsoluteNote>> notes
    ) {
        this(
                Optional.of(displayName),
                clef,
                noteLength,
                Optional.empty(),
                notes
        );
    }

    /*
  constructor(
    clef: Clef,
    noteLength: NoteLength,
    notes: List<List<AbsoluteNote>>,
  ) : this(null, clef, noteLength, null, notes)

  fun includeKeySignature(key: Key): SingleLineAbc = SingleLineAbc(
    displayName,
    clef,
    noteLength,
    key,
    notes
  )
  */

    @Override
    public String getAbc() {
        Function<List<AbsoluteNote>, String> barAbc = value -> value
                .stream()
                .map(it -> it.getAbc(keySignature.orElse(Key.getCMajor())))
                .collect(Collectors.joining());

        val notesAbc = notes
                .stream()
                .map(barAbc)
                .collect(Collectors.joining("|"))
                + "|";
        val words = notes
                .stream()
                .flatMap(Collection::stream)
                .map(AbsoluteNote::getWordAbc)
                .collect(Collectors.joining(" "));

        return Stream.of(
                "X:1",
                displayName.map(it -> "T:" + it).orElse(""),
                "K:clef=" + clef.abc(),
                keySignature.map(it -> "K:" + it.getAbc()).orElse(""),
                "L:" + noteLength.getAbc(),
                notesAbc,
                "w:" + words
        )
                .filter(it -> !it.isEmpty())
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
