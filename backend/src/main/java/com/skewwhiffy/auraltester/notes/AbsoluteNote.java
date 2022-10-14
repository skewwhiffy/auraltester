package com.skewwhiffy.auraltester.notes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.val;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AbsoluteNote {
  static final AbsoluteNote MIDDLE_C = new AbsoluteNote(Note.C, Octave.DEFAULT);

  private final Note note;
  private final Octave octave;

  AbsoluteNote add(Interval interval) {
    return switch (interval.getDegree()) {
      case 1:
        yield this;
      case 2:
        yield addMajorSecond();
      case 3:
        yield addMajorSecond().addMajorSecond();
      case 4:
        yield add(Interval.major(3)).addMinorSecond();
      case 5:
        yield add(Interval.perfect(4)).addMajorSecond();
      case 6:
        yield add(Interval.perfect(5)).addMajorSecond();
      case 7:
        yield add(Interval.major(6)).addMajorSecond();
      case 8:
        yield new AbsoluteNote(note, octave.getUp());
      default:
        throw new IllegalArgumentException();
    };
  }

  String getAbc() {
    return octave.getAbc(note);
  }

  private AbsoluteNote addMinorSecond() {
    val majorSecondUp = addMajorSecond();
    return new AbsoluteNote(majorSecondUp.note.getFlat(), majorSecondUp.octave);
  }

  private AbsoluteNote addMajorSecond() {
    val newOctave = "B".equals(note.getNoteName()) ? octave.getUp() : octave;
    return new AbsoluteNote(note.addMajorSecond(), newOctave);
  }

}
