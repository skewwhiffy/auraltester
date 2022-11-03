package com.skewwhiffy.auraltester.notes

import org.scalatest.flatspec.AnyFlatSpec

class AccidentalTests extends AnyFlatSpec {
  it should "when natural then displays correctly" in {
    val expected = ""
    val natural = Accidental.natural

    val actual = natural.displayString

    assert(actual == expected)
  }

  it should "when flattening natural then displays flat" in {
    val expected = "b"
    val natural = Accidental.natural

    val actual = natural.flat

    assert(actual.displayString == expected)
  }

  it should "when sharpening natural then displays sharp" in {
    val expected = "#"
    val natural = Accidental.natural

    val actual = natural.sharp

    assert(actual.displayString == expected)
  }

  it should "when flat then displays flat" in {
    val expected = "b"
    val flat = Accidental.flat

    val actual = flat.displayString

    assert(actual == expected)
  }

  it should "when flattening flat then displays double flat" in {
    val expected = "bb"
    val flat = Accidental.flat

    val actual = flat.flat

    assert(actual.displayString == expected)
  }

  it should "when sharpening flat then displays natural" in {
    val expected = ""
    val flat = Accidental.flat

    val actual = flat.sharp

    assert(actual.displayString == expected)
  }

  List(3, 7).foreach(flats => {
    it should s"when $flats flats then displays correctly" in {
      val expected = "b".repeat(flats)
      val accidental = List.range(0, flats).foldRight(Accidental.natural)((_, acc) => acc.flat)

      val actual = accidental.displayString

      assert(actual == expected)
    }
  })

  it should "when sharp then displays sharp" in {
    val expected = "#"
    val sharp = Accidental.sharp

    val actual = sharp.displayString

    assert(actual == expected)
  }

  it should "when sharpening sharp then displays double sharp" in {
    val expected = "x"
    val sharp = Accidental.sharp

    val actual = sharp.sharp

    assert(actual.displayString == expected)
  }

  it should "when flattening sharp then displays natural" in {
    val expected = ""
    val sharp = Accidental.sharp

    val actual = sharp.flat

    assert(actual.displayString == expected)
  }

  List(6, 10).foreach(numberOfSharps => {
    it should s"when $numberOfSharps then displays correctly" in {
      val expected = "x".repeat(numberOfSharps / 2)
      val accidental = List.range(0, numberOfSharps).foldRight(Accidental.natural)((_, it) => it.sharp)

      val actual = accidental.displayString

      assert(actual == expected)
    }
  })

  List(7, 13).foreach(numberOfSharps => {
    it should s"when $numberOfSharps then displays correctly" in {
      val expected = "x".repeat(numberOfSharps / 2) + "#"
      val accidental = List.range(0, numberOfSharps).foldRight(Accidental.natural)((_, it) => it.sharp)

      val actual = accidental.displayString

      assert(actual == expected)
    }
  })

  it should "when accidentals equivalent then equal" in {
    def accidental = new Accidental(5)

    val first = accidental
    val second = accidental

    assert(first == second)
  }
}