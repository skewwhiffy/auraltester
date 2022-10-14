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

}
