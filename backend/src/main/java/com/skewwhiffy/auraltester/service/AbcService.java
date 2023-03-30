package com.skewwhiffy.auraltester.service;

import com.skewwhiffy.auraltester.notation.model.abc.SingleLineAbc;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.notation.model.note.NoteLength;
import com.skewwhiffy.auraltester.notation.model.note.NoteSequence;
import lombok.val;
import org.springframework.stereotype.Service;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AbcService {
    /*
  fun getAbc(clef: Clef, scale: Scale, key: Key? = null): AbcProvider = listOfNotNull(
    scale.displayName,
    scale.direction.displayString,
    key?.let { "(with key signature)" }
  )
    .joinToString(" ")
    .titleCase
    .let { SingleLineAbc(it, clef, NoteLength.semibreve, key, listOf(scale.notes))}

*/
    public AbcProvider getAbc(Clef clef) {
        val displayName = Optional.of(clef)
                .map(Clef::getDisplayName)
                .map(it -> MessageFormat.format("{0} Clef Notes", it));
        return new SingleLineAbc(
                displayName,
                clef,
                NoteLength.getSemibreve(),
                clef.getNotes().stream().map(NoteSequence::getNotes).toList()
        );
    }
    /*

  fun getAbc(clef: Clef, intervalNotes: IntervalNotes, key: Key): AbcProvider = SingleLineAbc(
    intervalNotes.interval.displayString.titleCase,
    clef,
    NoteLength.semibreve,
    key,
    listOf(intervalNotes.notes)
  )
     */

    public AbcProvider getAbc(Clef clef, Key key) {
        String title = getTitleCase(
                MessageFormat.format(
                        "{0} / {1}",
                        key.getDisplayString(),
                        key.getRelative().getDisplayString()
                )
        );
        return new SingleLineAbc(
                Optional.of(title),
                clef,
                NoteLength.getSemibreve(),
                Optional.of(key),
                Collections.singletonList(Collections.emptyList())
        );
    }

    private String getTitleCase(String source) {
        return Arrays.stream(source
                .split(" "))
                .map(it -> it.substring(0, 1).toUpperCase(Locale.UK) + it.substring(1))
                .collect(Collectors.joining(" "));
    }

}
