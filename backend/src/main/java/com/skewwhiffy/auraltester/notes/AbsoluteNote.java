package com.skewwhiffy.auraltester.notes;

import com.skewwhiffy.auraltester.helper.StreamHelper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.stream.IntStream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AbsoluteNote {
  static final AbsoluteNote MIDDLE_C = new AbsoluteNote(Note.C, Octave.DEFAULT);

  private final Note note;
  private final Octave octave;

  AbsoluteNote add(Interval interval) {
    val defaultNote = switch (interval.getDegree()) {
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
    if (interval.getDeviation() < 0) {
      return IntStream
        .range(0, -interval.getDeviation())
        .boxed()
        .reduce(defaultNote, (note, i) -> note.getFlat(), StreamHelper::noParallel);
    }
    if (interval.getDeviation() > 0) {
      return IntStream
        .range(0, interval.getDeviation())
        .boxed()
        .reduce(defaultNote, (note, i) -> note.getSharp(), StreamHelper::noParallel);
    }
    return defaultNote;
  }

  String getAbc() {
    return octave.getAbc(note);
  }

  private AbsoluteNote getSharp() {
    return new AbsoluteNote(note.getSharp(), octave);
  }

  private AbsoluteNote getFlat() {
    return new AbsoluteNote(note.getFlat(), octave);
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
