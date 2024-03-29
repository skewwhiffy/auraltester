package com.skewwhiffy.auraltester.service

import com.skewwhiffy.auraltester.helper.getTitleCase
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider
import com.skewwhiffy.auraltester.notation.model.abc.SingleLineAbc
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.notation.model.note.IntervalNotes
import com.skewwhiffy.auraltester.notation.model.note.NoteLength
import com.skewwhiffy.auraltester.notation.model.scale.Scale
import org.springframework.stereotype.Service

@Service
class AbcService {
    fun getAbc(clef: Clef, scale: Scale): AbcProvider = SingleLineAbc(
        getTitleCase("${scale.displayName} ${scale.direction.displayString}"),
        clef,
        NoteLength.semibreve,
        null,
        listOf(scale.notes)
    )

    fun getAbc(clef: Clef, scale: Scale, key: Key): AbcProvider = SingleLineAbc(
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

    fun getAbc(clef: Clef, absoluteNote: AbsoluteNote): AbcProvider = getAbc(clef, listOf(absoluteNote))

    fun getAbc(clef: Clef, absoluteNotes: List<AbsoluteNote>): AbcProvider = SingleLineAbc(
        clef,
        NoteLength.semibreve,
        listOf(absoluteNotes)
    )

    fun getAbc(clef: Clef, intervalNotes: IntervalNotes, key: Key, displayString: String? = null): AbcProvider =
        SingleLineAbc(
            displayString ?: getTitleCase(intervalNotes.interval.displayString),
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
