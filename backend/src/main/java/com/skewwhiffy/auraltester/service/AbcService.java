package com.skewwhiffy.auraltester.service;

import com.skewwhiffy.auraltester.notation.model.abc.SingleLineAbc;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.notation.model.note.IntervalNotes;
import com.skewwhiffy.auraltester.notation.model.note.NoteLength;
import com.skewwhiffy.auraltester.notation.model.note.NoteSequence;
import com.skewwhiffy.auraltester.notation.model.scale.Scale;
import lombok.val;
import org.springframework.stereotype.Service;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AbcService {
    public AbcProvider getAbc(Clef clef, Scale scale) {
        String title = Stream
                .of(scale.getDisplayName(), scale.direction().getDisplayString())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
        return new SingleLineAbc(
                Optional.of(getTitleCase(title)),
                clef,
                NoteLength.getSemibreve(),
                Optional.empty(),
                List.of(scale.getNotes())
        );
    }

    public AbcProvider getAbc(Clef clef, Scale scale, Key key) {
        String title = Stream
                .of(scale.getDisplayName(), scale.direction().getDisplayString(), "(with key signature)")
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
        return new SingleLineAbc(
                Optional.of(getTitleCase(title)),
                clef,
                NoteLength.getSemibreve(),
                Optional.of(key),
                List.of(scale.getNotes())
        );
    }

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
        val displayName = MessageFormat.format(
                "{0} Clef Notes",
                clef.getDisplayName()
        );
        return new SingleLineAbc(
                displayName,
                clef,
                NoteLength.getSemibreve(),
                clef.getNotes().stream().map(NoteSequence::getNotes).toList()
        );
    }

    public AbcProvider getAbc(Clef clef, IntervalNotes intervalNotes, Key key) {
        return new SingleLineAbc(
                getTitleCase(intervalNotes.interval().getDisplayString()),
                clef,
                NoteLength.getSemibreve(),
                key,
                List.of(intervalNotes.getNotes())
        );
    }


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
