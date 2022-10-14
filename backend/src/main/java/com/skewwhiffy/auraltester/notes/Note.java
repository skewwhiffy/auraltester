package com.skewwhiffy.auraltester.notes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Note {
  static final Note A = new Note("A", Accidental.NATURAL);
  static final Note B = new Note("B", Accidental.NATURAL);
  static final Note C = new Note("C", Accidental.NATURAL);
  static final Note D = new Note("D", Accidental.NATURAL);
  static final Note E = new Note("E", Accidental.NATURAL);
  static final Note F = new Note("F", Accidental.NATURAL);
  static final Note G = new Note("G", Accidental.NATURAL);

  private final String noteName;
  public String getNoteName() {
    return noteName;
  }
  public Accidental getAccidental() {
    return accidental;
  }
  private final Accidental accidental;

  String getDisplayString() {
    return noteName + accidental.getDisplayString();
  }

  Note getSharp() {
    return new Note(noteName, accidental.getSharp());
  }

  Note getFlat() {
    return new Note(noteName, accidental.getFlat());
  }

  protected Note addMajorSecond() {
    return switch (noteName) {
      case "A", "C", "D", "F", "G" -> new Note(getNextNoteName(), accidental);
      default -> new Note(getNextNoteName(), accidental.getSharp());
    };
  }

  private String getNextNoteName() {
    return switch (noteName) {
      case "A":
        yield "B";
      case "B":
        yield "C";
      case "C":
        yield "D";
      case "D":
        yield "E";
      case "E":
        yield "F";
      case "F":
        yield "G";
      case "G":
        yield "A";
      default:
        throw new IllegalArgumentException(String.format("Not a valid note name: '%s'", noteName));
    };
  }

}
