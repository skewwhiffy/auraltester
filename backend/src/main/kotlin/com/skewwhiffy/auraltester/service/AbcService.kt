package com.skewwhiffy.auraltester.service

import com.skewwhiffy.auraltester.helper.StringHelper
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider
import com.skewwhiffy.auraltester.notation.model.abc.SingleLineAbc
import com.skewwhiffy.auraltester.notation.model.clef.Clef
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
        val displayName = StringHelper.getTitleCase(
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
     */
}
