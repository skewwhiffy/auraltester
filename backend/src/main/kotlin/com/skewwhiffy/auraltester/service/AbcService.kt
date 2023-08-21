package com.skewwhiffy.auraltester.service

import com.skewwhiffy.auraltester.helper.StringHelper.getTitleCase
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider
import com.skewwhiffy.auraltester.notation.model.abc.SingleLineAbc
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.notation.model.note.IntervalNotes
import com.skewwhiffy.auraltester.notation.model.note.NoteLength
import org.springframework.stereotype.Service

@Service
class AbcService {
    /*
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
    */

    fun getAbc(clef: Clef): AbcProvider {
        val displayName = getTitleCase(
            clef.displayName,
            "Clef Notes"
        )
        return SingleLineAbc(
            displayName,
            clef,
            NoteLength.semibreve,
            clef.notes.map { it.notes }
        )
    }

    /*
    public AbcProvider getAbc(Clef clef, AbsoluteNote absoluteNote) {
        return getAbc(clef, Collections.singletonList(absoluteNote));
    }

    public AbcProvider getAbc(Clef clef, List<AbsoluteNote> absoluteNotes) {
        return new SingleLineAbc(
                clef,
        NoteLength.getSemibreve(),
        List.of(absoluteNotes)
        );
    }
    */

    fun getAbc(clef: Clef, intervalNotes: IntervalNotes, key: Key): AbcProvider = SingleLineAbc(
        getTitleCase(intervalNotes.interval.displayString),
        clef,
        NoteLength.semibreve,
        key,
        listOf(intervalNotes.notes)
    )

    fun getAbc(clef: Clef, key: Key): AbcProvider = getTitleCase("${key.displayString} / ${key.relative.displayString}")
        .let { SingleLineAbc(it, clef, NoteLength.semibreve, key, listOf(listOf())) }
}
