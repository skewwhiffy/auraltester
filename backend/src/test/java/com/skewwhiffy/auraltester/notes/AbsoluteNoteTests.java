package com.skewwhiffy.auraltester.notes;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

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
    val middleC = AbsoluteNote.MIDDLE_C;
    val expected = Arrays.asList("C", "D", "E", "F", "G", "A", "B", "c");

    val actual = intervals
      .stream()
      .map(middleC::add)
      .map(AbsoluteNote::getAbc)
      .collect(Collectors.toList());

    assertThat(actual).isEqualTo(expected);
  }
}
