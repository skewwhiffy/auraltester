package com.skewwhiffy.auraltester.notes

import org.scalatest.funsuite.AnyFunSuite

class AccidentalTests extends AnyFunSuite {
  test("when natural then displays correctly") {
    val expected = ""
    val natural = Accidental.natural

    val actual = natural.displayString

    assert(actual == expected)
  }

  test("when flattening natural then displays flat") {
    val expected = "b"
    val natural = Accidental.natural

    val actual = natural.flat

    assert(actual.displayString == expected)
  }

  test("when sharpening natural then displays sharp") {
    val expected = "#"
    val natural = Accidental.natural

    val actual = natural.sharp

    assert(actual.displayString == expected)
  }

  test("when flat then displays flat") {
    val expected = "b"
    val flat = Accidental.flat

    val actual = flat.displayString

    assert(actual == expected)
  }

  test("when flattening flat then displays double flat") {
    val expected = "bb"
    val flat = Accidental.flat

    val actual = flat.flat

    assert(actual.displayString == expected)
  }

  test("when sharpening flat then displays natural") {
    val expected = ""
    val flat = Accidental.flat

    val actual = flat.sharp

    assert(actual.displayString == expected)
  }

  List(3, 7).foreach(flats => {
    test(s"when $flats flats then displays correctly") {
      val expected = "b".repeat(flats)
      val accidental = List.range(0, flats).foldRight(Accidental.natural)((_, acc) => acc.flat)

      val actual = accidental.displayString

      assert(actual == expected)
    }
  })

  test("when sharp then displays sharp") {
    val expected = "#"
    val sharp = Accidental.sharp

    val actual = sharp.displayString

    assert(actual == expected)
  }

  test("when sharpening sharp then displays double sharp") {
    val expected = "x"
    val sharp = Accidental.sharp

    val actual = sharp.sharp

    assert(actual.displayString == expected)
  }

  test("when flattening sharp then displays natural") {
    val expected = ""
    val sharp = Accidental.sharp

    val actual = sharp.flat

    assert(actual.displayString == expected)
  }

  List(6, 10).foreach(numberOfSharps => {
    test(s"when $numberOfSharps then displays correctly") {
      val expected = "x".repeat(numberOfSharps / 2)
      val accidental = List.range(0, numberOfSharps).foldRight(Accidental.natural)((_, it) => it.sharp)

      val actual = accidental.displayString

      assert(actual == expected)
    }
  })
  List(7, 13).foreach(numberOfSharps => {
    test(s"when $numberOfSharps then displays correctly") {
      val expected = "x".repeat(numberOfSharps / 2) + "#"
      val accidental = List.range(0, numberOfSharps).foldRight(Accidental.natural)((_, it) => it.sharp)

      val actual = accidental.displayString

      assert(actual == expected)
    }
  })

  test("when accidentals equivalent then equal") {
    def accidental = new Accidental(5)

    val first = accidental
    val second = accidental

    assert(first == second)
  }
}