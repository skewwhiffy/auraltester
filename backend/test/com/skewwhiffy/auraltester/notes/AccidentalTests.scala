package com.skewwhiffy.auraltester.notes

import org.scalatest.flatspec.AnyFlatSpec

class AccidentalTests extends AnyFlatSpec {
  it should "display correctly when natural" in {
    val expected = ""
    val natural = Accidental.natural

    val actual = natural.displayString

    assert(actual == expected)
  }

  it should "be flat when flattening natural" in {
    val natural = Accidental.natural

    val actual = natural.flat

    assert(actual == Accidental.flat)
  }

  it should "be sharp when sharpening natural" in {
    val natural = Accidental.natural

    val actual = natural.sharp

    assert(actual == Accidental.sharp)
  }

  it should "display flat when flat" in {
    val expected = "b"
    val flat = Accidental.flat

    val actual = flat.displayString

    assert(actual == expected)
  }

  it should "display double flat when flattening flat" in {
    val expected = "bb"
    val flat = Accidental.flat

    val actual = flat.flat

    assert(actual.displayString == expected)
  }

  it should "be natural when sharpening flat" in {
    val flat = Accidental.flat

    val actual = flat.sharp

    assert(actual == Accidental.natural)
  }

  List(3, 7).foreach(flats => {
    it should s"display correctly when $flats flats" in {
      val expected = "b".repeat(flats)
      val accidental = List.range(0, flats).foldRight(Accidental.natural)((_, acc) => acc.flat)

      val actual = accidental.displayString

      assert(actual == expected)
    }
  })

  it should "display sharp when sharp" in {
    val expected = "#"
    val sharp = Accidental.sharp

    val actual = sharp.displayString

    assert(actual == expected)
  }

  it should "display double sharp when sharpening sharp" in {
    val expected = "x"
    val sharp = Accidental.sharp

    val actual = sharp.sharp

    assert(actual.displayString == expected)
  }

  it should "be natural when flattening sharp" in {
    val sharp = Accidental.sharp

    val actual = sharp.flat

    assert(actual == Accidental.natural)
  }

  List(6, 10).foreach(numberOfSharps => {
    it should s"display correctly when $numberOfSharps" in {
      val expected = "x".repeat(numberOfSharps / 2)
      val accidental = List.range(0, numberOfSharps).foldRight(Accidental.natural)((_, it) => it.sharp)

      val actual = accidental.displayString

      assert(actual == expected)
    }
  })

  List(7, 13).foreach(numberOfSharps => {
    it should s"display correctly when $numberOfSharps" in {
      val expected = "x".repeat(numberOfSharps / 2) + "#"
      val accidental = List.range(0, numberOfSharps).foldRight(Accidental.natural)((_, it) => it.sharp)

      val actual = accidental.displayString

      assert(actual == expected)
    }
  })

  it should "equate equivalent accidentals" in {
    def accidental = Accidental(5)

    val first = accidental
    val second = accidental

    assert(first == second)
  }
}