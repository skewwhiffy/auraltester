package com.skewwhiffy.auraltester.notes

import org.scalatest.flatspec.AnyFlatSpec

class IntervalTests extends AnyFlatSpec {
  it should "instantiate major second" in {
    val expected = "major second"

    val actual = Interval.major(2)

    assert(actual.displayString == expected)
  }

  it should "return minor third when diminishing major third" in {
    val expected = "minor third"
    val major = Interval.major(3)

    val actual = major.diminished

    assert(actual.displayString == expected)
  }

  it should "instantiate minor sixth" in {
    val expected = "minor sixth"

    val actual = Interval.minor(6)

    assert(actual.displayString == expected)
  }

  it should "return diminished seventh when diminishing minor seventh" in {
    val expected = "diminished seventh"
    val minor = Interval.minor(7)

    val actual = minor.diminished

    assert(actual.displayString == expected)
  }

  it should "return doubly diminished second when diminishing diminished second" in {
    val expected = "doubly diminished second"
    val diminished = Interval.minor(2).diminished

    val actual = diminished.diminished

    assert(actual.displayString == expected)
  }

  it should "return 3x diminished third when diminishing doubly diminished third" in {

    val expected = "3x diminished third"
    val doublyDiminished = Interval.minor(3).diminished.diminished

    val actual = doublyDiminished.diminished

    assert(actual.displayString == expected)
  }

  it should "return augmented sixth when augmenting major sixth" in {
    val expected = "augmented sixth"
    val major = Interval.major(6)

    val actual = major.augmented

    assert(actual.displayString == expected)
  }

  List(1, 4, 5, 8).foreach(degree => {
    it should s"not instantiate major $degree" in {
      assertThrows[IllegalArgumentException] {
        Interval.major(degree)
      }
    }
  })

  it should "instantiate perfect unison" in {
    val expected = "perfect unison"

    val actual = Interval.perfect(1)

    assert(actual.displayString == expected)
  }

  it should "return diminished fourth when diminishing perfect fourth" in {
    val expected = "diminished fourth"
    val perfect = Interval.perfect(4)

    val actual = perfect.diminished

    assert(actual.displayString == expected)
  }

  it should "return doubly diminished fifth when diminishing diminished fifth" in {
    val expected = "doubly diminished fifth"
    val diminished = Interval.perfect(5).diminished

    val actual = diminished.diminished

    assert(actual.displayString == expected)
  }

  it should "return 3x diminished octave when diminishing doubly diminished octave" in {
    val expected = "3x diminished octave"
    val doublyDiminished = Interval.perfect(8).diminished.diminished

    val actual = doublyDiminished.diminished

    assert(actual.displayString == expected)
  }

  it should "return augmented unison when augmenting perfect unison" in {
    val expected = "augmented unison"
    val perfect = Interval.perfect(1)

    val actual = perfect.augmented

    assert(actual.displayString == expected)
  }

  List(2, 3, 6, 7).foreach(degree => {
    it should s"not instantiate perfect $degree" in {
      assertThrows[IllegalArgumentException] {
        Interval.perfect(degree)
      }
    }
  })

  it should "return doubly augmented second when augmenting augmented second" in {
    val expected = "doubly augmented second"
    val augmented = Interval.major(2).augmented

    val actual = augmented.augmented

    assert(actual.displayString == expected)
  }

  it should "return 3x augmented fourth when augmenting augmented fourth" in {
    val expected = "3x augmented fourth"
    val doublyAugmented = Interval.perfect(4).augmented.augmented

    val actual = doublyAugmented.augmented

    assert(actual.displayString == expected)
  }

  it should "return equal when equivalent" in {
    def interval = Interval(6, 69)

    val first = interval
    val second = interval

    assert(first == second)
  }
}