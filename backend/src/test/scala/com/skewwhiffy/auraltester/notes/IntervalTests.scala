package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.notes.Interval.Interval
import org.scalatest.funsuite.AnyFunSuite

class IntervalTests extends AnyFunSuite {
  test("can instantiate major second") {
    val expected = "major second"

    val actual = Interval.major(2)

    assert(actual.displayString == expected)
  }

  test("when diminishing major third then minor third") {
    val expected = "minor third"
    val major = Interval.major(3)

    val actual = major.diminished

    assert(actual.displayString == expected)
  }

  test("can instantiate minor sixth") {
    val expected = "minor sixth"

    val actual = Interval.minor(6)

    assert(actual.displayString == expected)
  }

  test("when diminishing minor seventh then diminished seventh") {
    val expected = "diminished seventh"
    val minor = Interval.minor(7)

    val actual = minor.diminished

    assert(actual.displayString == expected)
  }

  test("when diminishing diminished second then doubly diminished second") {
    val expected = "doubly diminished second"
    val diminished = Interval.minor(2).diminished

    val actual = diminished.diminished

    assert(actual.displayString == expected)
  }

  test("when diminishing doubly diminished third then 3x diminished third") {

    val expected = "3x diminished third"
    val doublyDiminished = Interval.minor(3).diminished.diminished

    val actual = doublyDiminished.diminished

    assert(actual.displayString == expected)
  }

  test("when augmenting major sixth then augmented sixth") {
    val expected = "augmented sixth"
    val major = Interval.major(6)

    val actual = major.augmented

    assert(actual.displayString == expected)
  }

  List(1, 4, 5, 8).foreach(degree => {
    test(s"cannot instantiate major $degree") {
      assertThrows[IllegalArgumentException] {
        Interval.major(degree)
      }
    }
  })

  test("can instantiate perfect unison") {
    val expected = "perfect unison"

    val actual = Interval.perfect(1)

    assert(actual.displayString == expected)
  }

  test("when diminishing perfect fourth then diminished fourth") {
    val expected = "diminished fourth"
    val perfect = Interval.perfect(4)

    val actual = perfect.diminished

    assert(actual.displayString == expected)
  }

  test("when diminishing diminished fifth then doubly diminished fifth") {
    val expected = "doubly diminished fifth"
    val diminished = Interval.perfect(5).diminished

    val actual = diminished.diminished

    assert(actual.displayString == expected)
  }

  test("when diminishing doubly diminished octave then 3x diminished octave") {
    val expected = "3x diminished octave"
    val doublyDiminished = Interval.perfect(8).diminished.diminished

    val actual = doublyDiminished.diminished

    assert(actual.displayString == expected)
  }

  test("when augmenting perfect unison then augmented unison") {
    val expected = "augmented unison"
    val perfect = Interval.perfect(1)

    val actual = perfect.augmented

    assert(actual.displayString == expected)
  }

  List(2, 3, 6, 7).foreach(degree => {
    test(s"cannot instantiate perfect $degree") {
      assertThrows[IllegalArgumentException] {
        Interval.perfect(degree)
      }
    }
  })

  test("when augmenting augmented second then doubly augmented second") {
    val expected = "doubly augmented second"
    val augmented = Interval.major(2).augmented

    val actual = augmented.augmented

    assert(actual.displayString == expected)
  }

  test("when augmenting augmented fourth then 3x augmented fourth") {
    val expected = "3x augmented fourth"
    val doublyAugmented = Interval.perfect(4).augmented.augmented

    val actual = doublyAugmented.augmented

    assert(actual.displayString == expected)
  }

  test("when equivalent then equal") {
    def interval = new Interval(6, 69)

    val first = interval
    val second = interval

    assert(first == second)
  }
}