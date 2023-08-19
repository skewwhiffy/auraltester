package com.skewwhiffy.auraltester.notation.model.clef

import com.skewwhiffy.auraltester.controller.ClefType
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote

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
            NoteSequence.of(AbsoluteNote.getMiddleC().withLyric("Middle~C")),
            getLineNotes(),
            getSpaceNotes(),
            getLowerLedgerNotes(),
            getUpperLedgerNotes()
        )
    
    /*

    private NoteSequence getLineNotes() {
        return new ClefLineNotes(this);
    }

    private NoteSequence getSpaceNotes() {
        return new ClefSpaceNotes(this);
    }

    private NoteSequence getLowerLedgerNotes() {
        return new ClefLowerLedgerNotes(this);
    }

    private NoteSequence getUpperLedgerNotes() {
        return new ClefUpperLedgerNotes(this);
    }

    public AbsoluteNote getNoteNearBottom(Note note) {
        val candidateStartingNote = new AbsoluteNote(note, lowLedgerNote.octave(), Optional.empty());
        return candidateStartingNote.compareTo(lowLedgerNote) < 0
        ? candidateStartingNote.plus(Interval.perfect(8))
        : candidateStartingNote;
    }

     */
}
