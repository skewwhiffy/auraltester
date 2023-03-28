package com.skewwhiffy.auraltester.notation.factory;

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.notation.model.note.Accidental;
import com.skewwhiffy.auraltester.notation.model.note.Note;
import com.skewwhiffy.auraltester.notation.model.note.Octave;
import lombok.val;
import org.springframework.stereotype.Service;

import java.lang.IllegalArgumentException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;

@Service
public class NoteFactory {
    public AbsoluteNote getAbsoluteNote(String rawNote) {
        return new AbsoluteNote(getNote(rawNote), getOctave(rawNote), Optional.empty());
    }

    public Note getNote(String rawNote) {
        return new Note(getNoteName(rawNote), getAccidental(rawNote));
    }

    private String getNoteName(String rawNote) {
        val noteLetter = getNoteLetter(rawNote).toUpperCase(Locale.UK);
        if (noteLetter.compareTo("A") < 0 || noteLetter.compareTo("G") > 0) {
            throw new IllegalArgumentException(
                    MessageFormat.format("'{0}' is not a valid note name", noteLetter)
            );
        }
        return noteLetter;
    }

    private Accidental getAccidental(String rawNote) {
        Function<Character, Integer> getOffset = it -> switch (it) {
            case 'x' -> 2;
            case '#' -> 1;
            case 'b' -> -1;
            default -> throw new IllegalArgumentException(
                    MessageFormat.format("Not valid accidental: '{0}'", it)
            );
        };
        val offset = getRawAccidental(rawNote)
                .chars()
                .mapToObj(it -> (char) it)
                .map(getOffset)
                .mapToInt(it -> it)
                .sum();
        return new Accidental(offset);
    }

    private Octave getOctave(String rawNote) {
        Function<Character, Integer> getOffset = it -> switch (it) {
            case '\'' -> 1;
            case ',' -> -1;
            default -> throw new IllegalArgumentException(
                    MessageFormat.format("Not valid octave indicator: '{0}'", it)
            );
        };
        val offsetBase = getRawOctave(rawNote)
                .chars()
                .mapToObj(it -> (char) it)
                .map(getOffset)
                .mapToInt(it -> it)
                .sum();
        val offset = offsetBase + (rawNote.toLowerCase(Locale.UK).equals(rawNote) ? 1 : 0);
        return new Octave(offset);
    }


    private String getNoteLetter(String rawNote) {
        return rawNote.substring(0, 1);
    }

    private String getRawAccidental(String rawNote) {
        Function<String, String> getRawAccidental = it -> it.endsWith("'") || it.endsWith(",")
                ? getRawAccidental(it.substring(0, it.length() - 1))
                : it;

        return getRawAccidental.apply(rawNote.substring(1));
    }

    private String getRawOctave(String rawNote) {
        return rawNote
                .substring(getNoteLetter(rawNote).length() + getRawAccidental(rawNote).length());
    }
}
