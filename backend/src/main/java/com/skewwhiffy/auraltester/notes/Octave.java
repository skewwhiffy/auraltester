package com.skewwhiffy.auraltester.notes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.val;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Octave {
  static Octave DEFAULT = new Octave(0);
  private final int offsetFromDefault;

  String getAbc(Note note) {
    if (offsetFromDefault == 0) {
      return note.getDisplayString();
    }
    if (offsetFromDefault > 0) {
      val apostropheSuffix = "'".repeat(offsetFromDefault - 1);
      return note.getDisplayString().toLowerCase() + apostropheSuffix;
    }
    val commaSuffix = ",".repeat(-offsetFromDefault);
    return note.getDisplayString() + commaSuffix;
  }

  Octave getUp() {
    return new Octave(offsetFromDefault + 1);
  }

  Octave getDown() {
    return new Octave(offsetFromDefault - 1);
  }
}
