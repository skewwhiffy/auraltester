package com.skewwhiffy.auraltester.notes;

public class AbsoluteNote {

  private readonly note: Note
  private readonly octave: Octave

  constructor(note: Note, octave: Octave) {
    this.note = note
    this.octave = octave
  }

  add(interval: Interval) {
    return this
  }

  get abc() {
    return this.octave.abc(this.note)
  }

  static MiddleC = new AbsoluteNote(Note.C, Octave.Default)
}
