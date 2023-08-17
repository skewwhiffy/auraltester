package com.skewwhiffy.auraltester.notation.model.key;

import com.skewwhiffy.auraltester.helper.NoParallelStream;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.notation.model.note.Accidental;
import com.skewwhiffy.auraltester.notation.model.note.Note;
import lombok.val;
import org.apache.logging.log4j.util.Strings;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Stream;

public record Key(Note note, boolean isMinor) {
    public String getAbc() {
        return note.getDisplayString() + (isMinor ? "m" : "");
    }

    public String getDisplayString() {
        return MessageFormat.format(
                "{0} {1}",
                note.getDisplayString(),
                isMinor ? "minor" : "major"
        );
    }


    public String getAbc(AbsoluteNote note) {
        return getAccidentalAbc(note.note()) + getNoteAbc(note);
    }

    public boolean canRenderSignature() {
        return isMinor
                ? getRelativeMajor().canRenderSignature()
                : "C G D A E B F# C# F Bb Eb Ab Db Gb Cb".contains(note.getDisplayString());
    }

    private String getNoteAbc(AbsoluteNote note) {
        val offset = note.octave().offsetFromDefault();
        if (offset > 0) {
            return note.note().noteName().toLowerCase(Locale.UK) + Strings.repeat("'", offset - 1);
        }
        if (offset < 0) {
            return note.note().noteName() + Strings.repeat(",", -offset);
        }
        return note.note().noteName();
    }

    private String getAccidentalAbc(Note note) {
        val noteWithNoteName = getNotes()
                .stream()
                .filter(it -> it.noteName().equals(note.noteName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        MessageFormat.format("No note {0} in scale", note.noteName())
                ));
        if (note.accidental().equals(noteWithNoteName.accidental())) {
            return "";
        }
        if (note.accidental().equals(Accidental.getNatural())) {
            return "=";
        }
        return note.accidental().getAbc();
    }

    public Key getRelativeMinor() {
        return isMinor ? this : new Key(note.downMajorSecond().downMajorSecond().sharpen(), true);
    }

    public Key getRelativeMajor() {
        return isMinor ? new Key(note.upMajorSecond().upMajorSecond().flatten(), false) : this;
    }

    public Key getRelative() {
        return isMinor ? getRelativeMajor() : getRelativeMinor();
    }

    public List<Note> getNotes() {
        val semitones = isMinor ? Stream.of(2, 1, 2, 2, 1, 2) : Stream.of(2, 2, 1, 2, 2, 2);
        return semitones
                .reduce(
                        Collections.singletonList(note),
                        (soFar, nextInterval) -> {
                            val lastNote = soFar.get(soFar.size() - 1);
                            val nextNote = nextInterval == 2
                                    ? lastNote.upMajorSecond()
                                    : lastNote.upMajorSecond().flatten();
                            return Stream.concat(soFar.stream(), Stream.of(nextNote)).toList();
                        },
                        NoParallelStream.get()
                );
    }

    public static Key getCMajor() {
        return major(Note.getC());
    }

    public static Key major(Note note) {
        return new Key(note, false);
    }

    public static Key minor(Note note) {
        return new Key(note, true);
    }

}
