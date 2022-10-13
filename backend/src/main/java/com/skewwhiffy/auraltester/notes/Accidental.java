package com.skewwhiffy.auraltester.notes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Accidental {
  private final int offset;

  public String getDisplayString() {
    if (offset == 0) {
      return "";
    }
    if (offset < 0) {
      return "b".repeat(-offset);
    }
    if (offset % 2 == 0) {
      return "x".repeat(offset / 2);
    }
    return "x".repeat((offset - 1) / 2) + "#";
  }

  public Accidental getFlat() {
    return new Accidental(offset - 1);
  }

  public Accidental getSharp() {
    return new Accidental(offset + 1);
  }

  static final Accidental NATURAL = new Accidental(0);
  static final Accidental FLAT = new Accidental(-1);
  static final Accidental SHARP = new Accidental(1);
}
