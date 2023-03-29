package com.skewwhiffy.auraltester.notation.model.note;

import java.text.MessageFormat;

public record Note(String noteName, Accidental accidental) {
    /*
  val displayString: String = "$noteName${accidental.displayString}"

  val sharp: Note get() = Note(noteName, accidental.sharp)
  */

    public Note flatten() {
        return new Note(noteName, accidental.flatten());
    }

    public Note getUpMajorSecond() {
        if ("ACDFG".contains(noteName)) {
            return new Note(getNextNoteName(), accidental);
        }
        return new Note(getNextNoteName(), accidental.sharpen());
    }

    /*
  val downMajorSecond: Note
    get() {
      return when (noteName) {
        in listOf("B", "A", "G", "E", "D") -> Note(previousNoteName, accidental)
        else -> Note(previousNoteName, accidental.flat)
      }
    }

  operator fun compareTo(other: Note): Int {
    if (this == other) {
      return 0
    }
    return noteNames
      .indexOf(this.noteName)
      .compareTo(noteNames.indexOf(other.noteName))
  }
  */

    private String getNextNoteName() {
        return switch (noteName) {
            case "A" ->"B";
            case "B" ->"C";
            case "C" ->"D";
            case "D" ->"E";
            case "E" ->"F";
            case "F" ->"G";
            case "G" ->"A";
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

  private val previousNoteName: String = when (noteName) {
    "A" -> "G"
    "B" -> "A"
    "C" -> "B"
    "D" -> "C"
    "E" -> "D"
    "F" -> "E"
    "G" -> "F"
    else -> throw IllegalArgumentException("Not a valid note name: '$noteName'")
  }
}
     */


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

    private static String noteNames = "CDEFGAB";
}
