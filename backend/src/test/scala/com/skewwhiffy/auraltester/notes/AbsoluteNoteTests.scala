package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.notes.Interval.Interval
import org.scalatest.funsuite.AnyFunSuite

class AbsoluteNoteTests extends AnyFunSuite {
  test("can display middle C") {
    val expected = "C"

    val actual = AbsoluteNote.middleC

    assert(actual.abc == expected)
  }

  test("can add major and perfect intervals") {
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

  test("can add minor interval") {
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

  test("can add diminished and augmented interval") {
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

  test("cannot add compound intervals yet") {
    val start = AbsoluteNote.middleC

    assertThrows[IllegalArgumentException] {
      start.add(Interval.major(9))
    }
  }

  test("can apply up interval") {
    val interval = Interval.minor(3).up
    val start = AbsoluteNote.middleC
    val expected = "_E"

    val actual = start.apply(interval)

    assert(actual.abc == expected)
  }

  test("can apply down interval") {
    val interval = Interval.minor(3)
    val directedInterval = interval.down
    val start = AbsoluteNote.middleC
    val expected = "A,"

    val actualWithApply = start.apply(directedInterval)
    val actualWithSubtract = start - interval

    assert(actualWithApply.abc == expected)
    assert(actualWithSubtract.abc == expected)
  }

  test("cannot subtract compound intervals yet") {
    val interval = Interval.major(9)

    assertThrows[IllegalArgumentException] {
      AbsoluteNote.middleC - interval
    }
  }

  test("equivalent notes are equal") {
    def note = new AbsoluteNote(Note.D.sharp, Octave.default.up)

    val first = note
    val second = note

    assert(first == second)
    assert(first <= second)
    assert(first >= second)
  }

  test("absolute note is not equal to non absolute note") {
    def note = AbsoluteNote.middleC

    def someOtherObject = Note.C

    //noinspection ComparingUnrelatedTypes
    assert(!note.equals(someOtherObject))
  }

  test("toString returns abc") {
    def note = AbsoluteNote.middleC

    assert(note.toString == note.abc)
  }

  test("non equivalent notes in same octave are comparable") {
    val lower = new AbsoluteNote(Note.D, Octave.default)
    val higher = new AbsoluteNote(Note.B, Octave.default)

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
    val actual = start.add(interval).abc

    assert(actual == expectedAbc)
  }
}