package com.skewwhiffy.auraltester.notation.model.clef

import com.skewwhiffy.auraltester.controller.ClefType
import com.skewwhiffy.auraltester.notation.model.abc.ClefLineNotes
import com.skewwhiffy.auraltester.notation.model.abc.ClefLowerLedgerNotes
import com.skewwhiffy.auraltester.notation.model.abc.ClefSpaceNotes
import com.skewwhiffy.auraltester.notation.model.abc.ClefUpperLedgerNotes
import com.skewwhiffy.auraltester.notation.model.interval.Interval
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.notation.model.note.Note
import com.skewwhiffy.auraltester.notation.model.note.NoteSequence

data class Clef(
    val clefType: ClefType,
    val abc: String,
    val lowLedgerNote: AbsoluteNote,
    val highLedgerNote: AbsoluteNote
) {
    val displayName: String
        get() {
            return abc
        }

    val notes: List<NoteSequence>
        get() = listOf(
            NoteSequence.of(AbsoluteNote.middleC.withLyric("Middle~C")),
            lineNotes,
            spaceNotes,
            lowerLedgerNotes,
            upperLedgerNotes
        )

    private val lineNotes: NoteSequence
        get() = ClefLineNotes(this)

    private val spaceNotes: NoteSequence
        get() = ClefSpaceNotes(this)

    private val lowerLedgerNotes: NoteSequence
        get() = ClefLowerLedgerNotes(this)

    private val upperLedgerNotes: NoteSequence
        get() = ClefUpperLedgerNotes(this)

    fun getNoteNearBottom(note: Note): AbsoluteNote = AbsoluteNote(note, lowLedgerNote.octave, null)
        .let { if (it < lowLedgerNote) it + Interval.perfect(8) else it }
}
