package com.skewwhiffy.auraltester.notation.model.clef;

import com.skewwhiffy.auraltester.notation.model.abc.ClefLineNotes;
import com.skewwhiffy.auraltester.notation.model.abc.ClefLowerLedgerNotes;
import com.skewwhiffy.auraltester.notation.model.abc.ClefSpaceNotes;
import com.skewwhiffy.auraltester.notation.model.abc.ClefUpperLedgerNotes;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.notation.model.note.NoteSequence;

import java.util.stream.Stream;

public record Clef(String abc, AbsoluteNote lowLedgerNote, AbsoluteNote highLedgerNote) {
    public String getDisplayName() {
        return abc;
    }

    public Stream<NoteSequence> getNotes() {
        return Stream.of(
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

    /*
  fun getNoteNearBottom(note: Note): AbsoluteNote {
    val candidateStartingNote = AbsoluteNote(note, lowLedgerNote.octave)
    return if (candidateStartingNote < lowLedgerNote) candidateStartingNote + Interval.perfect(8) else candidateStartingNote
  }
}
     */
    public String getAbc() {
        return abc;
    }
}
