package com.skewwhiffy.auraltester.service

import com.skewwhiffy.auraltester.helper.StringHelper.getTitleCase
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider
import com.skewwhiffy.auraltester.notation.model.abc.SingleLineAbc
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.notation.model.note.IntervalNotes
import com.skewwhiffy.auraltester.notation.model.note.NoteLength
import com.skewwhiffy.auraltester.notation.model.scale.Scale
import org.springframework.stereotype.Service

@Service
class AbcService {
    fun getAbc(clef: Clef, scale: Scale) = SingleLineAbc(
        getTitleCase("${scale.displayName} ${scale.direction.displayString}"),
        clef,
        NoteLength.semibreve,
        null,
        listOf(scale.notes)
    )

    fun getAbc(clef: Clef, scale: Scale, key: Key) = SingleLineAbc(
        getTitleCase("${scale.displayName} ${scale.direction.displayString} (with key signature)"),
        clef,
        NoteLength.semibreve,
        key,
        listOf(scale.notes)
    )

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

    fun getAbc(clef: Clef, key: Key): AbcProvider =
        SingleLineAbc(
            getTitleCase("${key.displayString} / ${key.relative.displayString}"),
            clef,
            NoteLength.semibreve,
            key,
            listOf(listOf())
        )
}
