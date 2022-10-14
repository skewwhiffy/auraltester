package com.skewwhiffy.auraltester.notes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AbsoluteNote {
  static final AbsoluteNote MIDDLE_C = new AbsoluteNote(Note.C, Octave.DEFAULT);

  private final Note note;
  private final Octave octave;

  AbsoluteNote add(Interval interval) {
    return this;
  }

  String getAbc() {
    return octave.getAbc(note);
  }

}
