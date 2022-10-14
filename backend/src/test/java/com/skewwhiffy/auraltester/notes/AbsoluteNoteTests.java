package com.skewwhiffy.auraltester.notes;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class AbsoluteNoteTests {
  @Test
  void canDisplayMiddleC() {
    val expected = "C";

    val actual = AbsoluteNote.MIDDLE_C;

    assertThat(actual.getAbc()).isEqualTo(expected);
  }

  @Test
  void canAddMajorAndPerfectIntervals() {
    val intervals = Arrays.asList(
      Interval.perfect(1),
      Interval.major(2),
      Interval.major(3),
      Interval.perfect(4),
      Interval.perfect(5),
      Interval.major(6),
      Interval.major(7),
      Interval.perfect(8)
    );
    val start = AbsoluteNote.MIDDLE_C;
    val expected = Arrays.asList("C", "D", "E", "F", "G", "A", "B", "c");

    testGeneric(start, intervals, expected);
  }

  @Test
  void canAddMinorInterval() {
    val start = AbsoluteNote.MIDDLE_C.add(Interval.major(6));
    val intervals = Arrays.asList(
      Interval.perfect(1),
      Interval.minor(2),
      Interval.minor(3),
      Interval.perfect(4),
      Interval.perfect(5),
      Interval.minor(6),
      Interval.minor(7),
      Interval.perfect(8)
    );
    val expected = Arrays.asList("A", "Bb", "c", "d", "e", "f", "g", "a");

    testGeneric(start, intervals, expected);
  }

  @Test
  void canAddDiminishedAndAugmentedInterval() {
    val intervals = Arrays .asList(
      Interval.diminished(3),
      Interval.diminished(4),
      Interval.augmented(5),
      Interval.augmented(6)
    );
    val start = AbsoluteNote.MIDDLE_C;
    val expected = Arrays.asList("Ebb", "Fb", "G#", "A#");

    testGeneric(start, intervals, expected);
  }

  private void testGeneric(AbsoluteNote start, List<Interval> intervals, List<String> expectedAbcs) {
    if (intervals.size() != expectedAbcs.size()) {
      fail("Need expectation size = interval list size");
    }
    IntStream
      .range(0, intervals.size())
      .parallel()
      .forEach(it -> testGeneric(start, intervals.get(it), expectedAbcs.get(it)));
  }

  private void testGeneric(AbsoluteNote start, Interval interval, String expectedAbc) {
    val actual = start.add(interval).getAbc();

    assertThat(actual).isEqualTo(expectedAbc);
  }

}
