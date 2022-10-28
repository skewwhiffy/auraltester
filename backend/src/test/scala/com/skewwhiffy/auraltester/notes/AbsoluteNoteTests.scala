package com.skewwhiffy.auraltester.notes

/*
import com.skewwhiffy.auraltester.notes.Interval.Interval
import org.assertj.core.api.Assertions.{assertThat, assertThatThrownBy, fail}
import org.junit.jupiter.api.Test

class AbsoluteNoteTests {
  @Test
  def canDisplayMiddleC(): Unit = {
    val expected = "C"

    val actual = AbsoluteNote.middleC

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
    val start = AbsoluteNote.middleC
    val expected = List("C", "D", "E", "F", "G", "A", "B", "c")

    testGeneric(start, intervals, expected)
  }

  @Test
  def canAddMinorInterval(): Unit = {
    val start = AbsoluteNote.middleC + Interval.major(6)
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
    val expected = List("A", "_B", "c", "d", "e", "f", "g", "a")

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
    val start = AbsoluteNote.middleC
    val expected = List("__E", "_F", "^G", "^A")

    testGeneric(start, intervals, expected)
  }

  @Test
  def cannotAddCompoundIntervalsYet(): Unit = {
    val start = AbsoluteNote.middleC

    assertThatThrownBy(() => start.add(Interval.major(9)))
      .isInstanceOf(classOf[IllegalArgumentException])
  }

  @Test
  def canApplyUpInterval() : Unit = {
    val interval = Interval.minor(3).up
    val start = AbsoluteNote.middleC
    val expected = "_E"

    val actual = start.apply(interval)

    assertThat(actual.abc).isEqualTo(expected)
  }

  @Test
  def canApplyDownInterval() : Unit = {
    val interval = Interval.minor(3)
    val directedInterval = interval.down
    val start = AbsoluteNote.middleC
    val expected = "A,"

    val actualWithApply = start.apply(directedInterval)
    val actualWithSubtract = start - interval

    assertThat(actualWithApply.abc).isEqualTo(expected)
    assertThat(actualWithSubtract.abc).isEqualTo(expected)
  }

  @Test
  def cannotSubtractCompoundIntervalsYet(): Unit = {
    val interval = Interval.major(9)

    assertThatThrownBy(() => AbsoluteNote.middleC - interval)
      .isInstanceOf(classOf[IllegalArgumentException])
  }

  @Test
  def equivalentNotesAreEqual() : Unit = {
    def note = new AbsoluteNote(Note.D.sharp, Octave.default.up)
    val first = note
    val second = note

    assertThat(first).isEqualTo(second)
    assertThat(first <= second).isTrue
    assertThat(first >= second).isTrue
  }

  @Test
  def absoluteNoteIsNotEqualToNonAbsoluteNote(): Unit = {
    def note = AbsoluteNote.middleC
    def someOtherObject = Note.C

    assertThat(note).isNotEqualTo(someOtherObject)
  }

  @Test
  def toStringReturnsAbc(): Unit = {
    def note = AbsoluteNote.middleC

    assertThat(note.toString).isEqualTo(note.abc)
  }

  @Test
  def nonEquivalentNotesInSameOctaveAreComparable(): Unit = {
    val lower = new AbsoluteNote(Note.D, Octave.default)
    val higher = new AbsoluteNote(Note.B, Octave.default)

    assertThat(lower < higher).isTrue
    assertThat(lower > higher).isFalse
    assertThat(lower <= higher).isTrue
    assertThat(lower >= higher).isFalse
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


 */