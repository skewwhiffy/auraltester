package com.skewwhiffy.auraltester.notation.model.note;

import com.skewwhiffy.auraltester.dao.NoteDao;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

public record Note(String noteName, Accidental accidental) implements Comparable<Note> {
    @SuppressWarnings("SpellCheckingInspection")
    private final static String noteNames = "CDEFGAB";

    public NoteDao toDao() {
        return new NoteDao(noteName, accidental.toDao());
    }

    public String getDisplayString() {
        return noteName + accidental.getDisplayString();
    }

    public Note sharpen() {
        return new Note(noteName, accidental.sharpen());
    }

    public Note flatten() {
        return new Note(noteName, accidental.flatten());
    }

    public Note upMajorSecond() {
        if ("ACDFG".contains(noteName)) {
            return new Note(getNextNoteName(), accidental);
        }
        return new Note(getNextNoteName(), accidental.sharpen());
    }

    public Note downMajorSecond() {
        return "BAGED".contains(noteName)
                ? new Note(getPreviousNoteName(), accidental)
                : new Note(getPreviousNoteName(), accidental.flatten());
    }

    @Override
    public int compareTo(@NotNull Note other) {
        if (this == other) {
            return 0;
        }
        return noteNames.indexOf(noteName) - (noteNames.indexOf(other.noteName));
    }

    private String getNextNoteName() {
        return switch (noteName) {
            case "A" -> "B";
            case "B" -> "C";
            case "C" -> "D";
            case "D" -> "E";
            case "E" -> "F";
            case "F" -> "G";
            case "G" -> "A";
            default -> throw new IllegalArgumentException(
                    MessageFormat.format("Not a valid note name: '{0}'", noteName)
            );
        };
    }

    /*
  private val nextNoteName: String = when (noteName) {
    "A" -> "B"
    "B" -> "C"
    "C" -> "D"
    "D" -> "E"
    "E" -> "F"
    "F" -> "G"
    "G" -> "A"
    else -> throw IllegalArgumentException("Not a valid note name: '$noteName'")
  }
  */

    private String getPreviousNoteName() {
        return switch (noteName) {
            case "A" -> "G";
            case "B" -> "A";
            case "C" -> "B";
            case "D" -> "C";
            case "E" -> "D";
            case "F" -> "E";
            case "G" -> "F";
            default -> throw new IllegalArgumentException(
                    MessageFormat.format("Not a valid note name: '{0}'", noteName)
            );
        };
    }

    public static Note getA() {
        return new Note("A", Accidental.getNatural());
    }

    public static Note getB() {
        return new Note("B", Accidental.getNatural());
    }

    public static Note getC() {
        return new Note("C", Accidental.getNatural());
    }

    public static Note getD() {
        return new Note("D", Accidental.getNatural());
    }

    public static Note getE() {
        return new Note("E", Accidental.getNatural());
    }

    public static Note getF() {
        return new Note("F", Accidental.getNatural());
    }

    public static Note getG() {
        return new Note("G", Accidental.getNatural());
    }
}
