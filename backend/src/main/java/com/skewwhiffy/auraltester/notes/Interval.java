package com.skewwhiffy.auraltester.notes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Interval {
  private static final List<String> displayStrings = Arrays.asList(
    "unison",
    "second",
    "third",
    "fourth",
    "fifth",
    "sixth",
    "seventh",
    "octave"
  );

  private static final Collection<Integer> perfectDegrees = Arrays.asList(1, 4, 5, 8);

  private final int degree;
  private final int deviation;

  Interval getDiminished() {
    return new Interval(degree, deviation - 1);
  }

  Interval getAugmented() {
    return new Interval(degree, deviation + 1);
  }

  String getDisplayString() {
    return String.format("%s %s", getQuality(), displayStrings.get(degree - 1));
  }

  private String getQuality() {
    if (deviation == 0) {
      return perfectDegrees.contains(degree) ? "perfect" : "major";
    }
    if (deviation == - 1 && !perfectDegrees.contains(degree)) {
      return "minor";
    }
    if (deviation < 0) {
      val diminishedDegree = perfectDegrees.contains(degree) ? -deviation : -deviation - 1;
      if (diminishedDegree == 1) {
        return "diminished";
      }
      if (diminishedDegree == 2) {
        return "doubly diminished";
      }
      return diminishedDegree + "x diminished";
    }
    if (deviation == 1) {
      return "augmented";
    }
    if (deviation == 2) {
      return "doubly augmented";
    }
    return deviation + "x augmented";
  }

  static Interval major(int degree) {
    if (perfectDegrees.contains(degree)) {
      throw new IllegalArgumentException("Cannot instantiate major interval of degree " + degree);
    }
    return new Interval(degree, 0);
  }

  static Interval minor(int degree) {
    return major(degree).getDiminished();
  }

  static Interval perfect(int degree) {
    if (!perfectDegrees.contains(degree)) {
      throw new IllegalArgumentException("Cannot instantiate perfect interval of degree " + degree);
    }
    return new Interval(degree, 0);
  }
}