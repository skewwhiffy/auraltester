package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.scales.Key
import org.scalatest.flatspec.AnyFlatSpec

class AbsoluteNoteTests extends AnyFlatSpec {
  it should "display middle C" in {
    val expected = "C"

    val actual = AbsoluteNote.middleC

    assert(actual.abc(Key.cMajor) == expected)
  }

  it should "add major and perfect intervals" in {
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

  it should "add minor interval" in {
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

  it should "add diminished and augmented interval" in {
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

  it should "not add compound intervals yet" in {
    val start = AbsoluteNote.middleC

    assertThrows[IllegalArgumentException] {
      start.add(Interval.major(9))
    }
  }

  it should "apply up interval" in {
    val interval = Interval.minor(3).up
    val start = AbsoluteNote.middleC
    val expected = "_E"

    val actual = start.apply(interval)

    assert(actual.abc(Key.cMajor) == expected)
  }

  it should "apply down interval" in {
    val interval = Interval.minor(3)
    val directedInterval = interval.down
    val start = AbsoluteNote.middleC
    val expected = "A,"

    val actualWithApply = start.apply(directedInterval)
    val actualWithSubtract = start - interval

    assert(actualWithApply.abc(Key.cMajor) == expected)
    assert(actualWithSubtract.abc(Key.cMajor) == expected)
  }

  it should "not subtract compound intervals yet" in {
    val interval = Interval.major(9)

    assertThrows[IllegalArgumentException] {
      AbsoluteNote.middleC - interval
    }
  }

  it should "equivalent notes are equal" in {
    def note = AbsoluteNote(Note.d.sharp, Octave.default.up)

    val first = note
    val second = note

    assert(first == second)
    assert(first <= second)
    assert(first >= second)
  }

  it should "absolute note is not equal to non absolute note" in {
    def note = AbsoluteNote.middleC

    def someOtherObject = Note.c

    //noinspection ComparingUnrelatedTypes
    assert(!note.equals(someOtherObject))
  }

  it should "toString returns abc" in {
    def note = AbsoluteNote.middleC

    assert(note.toString == note.abc(Key.cMajor))
  }

  it should "non equivalent notes in same octave are comparable" in {
    val lower = AbsoluteNote(Note.d, Octave.default)
    val higher = AbsoluteNote(Note.b, Octave.default)

    assert(lower < higher)
    assert(!(lower > higher))
    assert(lower <= higher)
    assert(!(lower >= higher))
  }

  private def testGeneric(start: AbsoluteNote, intervals: List[Interval], expectedAbcs: List[String]): Unit = {
    if (intervals.size != expectedAbcs.size) {
      fail("Need expectation size = interval list size")
    }
    List.range(0, intervals.size).foreach(it => testGeneric(start, intervals(it), expectedAbcs(it)))
  }

  private def testGeneric(start: AbsoluteNote, interval: Interval, expectedAbc: String) = {
    val actual = start.add(interval).abc(Key.cMajor)

    assert(actual == expectedAbc)
  }
}