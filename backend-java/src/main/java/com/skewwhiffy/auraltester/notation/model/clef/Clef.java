package com.skewwhiffy.auraltester.notation.model.clef;

import com.skewwhiffy.auraltester.model.ClefType;
import com.skewwhiffy.auraltester.notation.model.abc.ClefLineNotes;
import com.skewwhiffy.auraltester.notation.model.abc.ClefLowerLedgerNotes;
import com.skewwhiffy.auraltester.notation.model.abc.ClefSpaceNotes;
import com.skewwhiffy.auraltester.notation.model.abc.ClefUpperLedgerNotes;
import com.skewwhiffy.auraltester.notation.model.interval.Interval;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.notation.model.note.Note;
import com.skewwhiffy.auraltester.notation.model.note.NoteSequence;
import lombok.val;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public record Clef(ClefType clefType, String abc, AbsoluteNote lowLedgerNote, AbsoluteNote highLedgerNote) {
    public String getDisplayName() {
        return abc;
    }

    public List<NoteSequence> getNotes() {
        return Arrays.asList(
                NoteSequence.of(AbsoluteNote.getMiddleC().withLyric("Middle~C")),
                getLineNotes(),
                getSpaceNotes(),
                getLowerLedgerNotes(),
                getUpperLedgerNotes()
        );
    }

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
}
