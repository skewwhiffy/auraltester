package com.skewwhiffy.auraltester.notes

import org.assertj.core.api.Assert
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.fail

class AbsoluteNoteTests {
  @Test
  def canDisplayMiddleC(): Unit = {
    val expected = "C"

    val actual = AbsoluteNote.MIDDLE_C

    assertThat(actual.abc).isEqualTo(expected)
  }

  @Test
  def canAddMajorAndPerfectIntervals(): Unit = {
    val intervals = List(
      Interval.perfect(1),
      Interval.major(2),
      Interval.major(3),
      Interval.perfect(4),
      Interval.perfect(5),
      Interval.major(6),
      Interval.major(7),
      Interval.perfect(8)
    )
    val start = AbsoluteNote.MIDDLE_C
    val expected = List("C", "D", "E", "F", "G", "A", "B", "c")

    testGeneric(start, intervals, expected)
  }

  @Test
  def canAddMinorInterval(): Unit = {
    val start = AbsoluteNote.MIDDLE_C.add(Interval.major(6))
    val intervals = List(
      Interval.perfect(1),
      Interval.minor(2),
      Interval.minor(3),
      Interval.perfect(4),
      Interval.perfect(5),
      Interval.minor(6),
      Interval.minor(7),
      Interval.perfect(8)
    )
    val expected = List("A", "Bb", "c", "d", "e", "f", "g", "a")

    testGeneric(start, intervals, expected)
  }

  @Test
  def canAddDiminishedAndAugmentedInterval(): Unit = {
    val intervals = List(
      Interval.diminished(3),
      Interval.diminished(4),
      Interval.augmented(5),
      Interval.augmented(6)
    )
    val start = AbsoluteNote.MIDDLE_C
    val expected = List("Ebb", "Fb", "G#", "A#")

    testGeneric(start, intervals, expected)
  }

  private def testGeneric(start: AbsoluteNote, intervals: List[Interval], expectedAbcs: List[String]): Unit = {
    if (intervals.size != expectedAbcs.size) {
      fail("Need expectation size = interval list size")
    }
    List.range(0, intervals.size).foreach(it => testGeneric(start, intervals(it), expectedAbcs(it)))
  }

  private def testGeneric(start: AbsoluteNote, interval: Interval, expectedAbc: String) = {
    val actual = start.add(interval).abc

    assertThat(actual).isEqualTo(expectedAbc)
  }
}
