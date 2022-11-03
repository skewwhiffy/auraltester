package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.notes.Interval.Interval
import org.scalatest.flatspec.AnyFlatSpec

class IntervalTests extends AnyFlatSpec {
  it should "can instantiate major second" in {
    val expected = "major second"

    val actual = Interval.major(2)

    assert(actual.displayString == expected)
  }

  it should "when diminishing major third then minor third" in {
    val expected = "minor third"
    val major = Interval.major(3)

    val actual = major.diminished

    assert(actual.displayString == expected)
  }

  it should "can instantiate minor sixth" in {
    val expected = "minor sixth"

    val actual = Interval.minor(6)

    assert(actual.displayString == expected)
  }

  it should "when diminishing minor seventh then diminished seventh" in {
    val expected = "diminished seventh"
    val minor = Interval.minor(7)

    val actual = minor.diminished

    assert(actual.displayString == expected)
  }

  it should "when diminishing diminished second then doubly diminished second" in {
    val expected = "doubly diminished second"
    val diminished = Interval.minor(2).diminished

    val actual = diminished.diminished

    assert(actual.displayString == expected)
  }

  it should "when diminishing doubly diminished third then 3x diminished third" in {

    val expected = "3x diminished third"
    val doublyDiminished = Interval.minor(3).diminished.diminished

    val actual = doublyDiminished.diminished

    assert(actual.displayString == expected)
  }

  it should "when augmenting major sixth then augmented sixth" in {
    val expected = "augmented sixth"
    val major = Interval.major(6)

    val actual = major.augmented

    assert(actual.displayString == expected)
  }

  List(1, 4, 5, 8).foreach(degree => {
    it should s"cannot instantiate major $degree" in {
      assertThrows[IllegalArgumentException] {
        Interval.major(degree)
      }
    }
  })

  it should "can instantiate perfect unison" in {
    val expected = "perfect unison"

    val actual = Interval.perfect(1)

    assert(actual.displayString == expected)
  }

  it should "when diminishing perfect fourth then diminished fourth" in {
    val expected = "diminished fourth"
    val perfect = Interval.perfect(4)

    val actual = perfect.diminished

    assert(actual.displayString == expected)
  }

  it should "when diminishing diminished fifth then doubly diminished fifth" in {
    val expected = "doubly diminished fifth"
    val diminished = Interval.perfect(5).diminished

    val actual = diminished.diminished

    assert(actual.displayString == expected)
  }

  it should "when diminishing doubly diminished octave then 3x diminished octave" in {
    val expected = "3x diminished octave"
    val doublyDiminished = Interval.perfect(8).diminished.diminished

    val actual = doublyDiminished.diminished

    assert(actual.displayString == expected)
  }

  it should "when augmenting perfect unison then augmented unison" in {
    val expected = "augmented unison"
    val perfect = Interval.perfect(1)

    val actual = perfect.augmented

    assert(actual.displayString == expected)
  }

  List(2, 3, 6, 7).foreach(degree => {
    it should s"cannot instantiate perfect $degree" in {
      assertThrows[IllegalArgumentException] {
        Interval.perfect(degree)
      }
    }
  })

  it should "when augmenting augmented second then doubly augmented second" in {
    val expected = "doubly augmented second"
    val augmented = Interval.major(2).augmented

    val actual = augmented.augmented

    assert(actual.displayString == expected)
  }

  it should "when augmenting augmented fourth then 3x augmented fourth" in {
    val expected = "3x augmented fourth"
    val doublyAugmented = Interval.perfect(4).augmented.augmented

    val actual = doublyAugmented.augmented

    assert(actual.displayString == expected)
  }

  it should "when equivalent then equal" in {
    def interval = new Interval(6, 69)

    val first = interval
    val second = interval

    assert(first == second)
  }
}