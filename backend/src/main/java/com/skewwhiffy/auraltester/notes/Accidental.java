package com.skewwhiffy.auraltester.notes;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Accidental {
  private final int offset;

  public String getDisplayString() {
    return "";
  }

  static final Accidental NATURAL = new Accidental(0);
}
