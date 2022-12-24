package com.skewwhiffy.notation.model.note

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AccidentalTest {
  @Test
  fun `has correct abc when natural`() {
    val expected = ""
    val natural = Accidental.natural

    val actual = natural.abc

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `has correct abc when flat`() {
    val expected = "_"
    val flat = Accidental.flat

    val actual = flat.abc

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `has correct abc when sharp`() {
    val expected = "^"
    val sharp = Accidental.sharp

    val actual = sharp.abc

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `displays correctly when natural`() {
    val expected = ""
    val natural = Accidental.natural

    val actual = natural.displayString

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `is flat when flattening natural`() {
    val natural = Accidental.natural

    val actual = natural.flat

    assertThat(actual).isEqualTo(Accidental.flat)
  }

  @Test
  fun`be sharp when sharpening natural` () {
    val natural = Accidental.natural

    val actual = natural.sharp

    assert(actual == Accidental.sharp)
  }

  fun`display flat when flat" () {
    val expected = "b"
    val flat = Accidental.flat

    val actual = flat.displayString

    assert(actual == expected)
  }

  fun`display double flat when flattening flat" () {
    val expected = "bb"
    val flat = Accidental.flat

    val actual = flat.flat

    assert(actual.displayString == expected)
  }

  fun`be natural when sharpening flat" () {
    val flat = Accidental.flat

    val actual = flat.sharp

    assert(actual == Accidental.natural)
  }

  List(3, 7).foreach(flats => {
    it should s"display correctly when $flats flats" () {
      val expected = "b".repeat(flats)
      val accidental = List.range(0, flats).foldRight(Accidental.natural)((_, acc) => acc.flat)

      val actual = accidental.displayString

      assert(actual == expected)
    }
  })

  fun`display sharp when sharp" () {
    val expected = "#"
    val sharp = Accidental.sharp

    val actual = sharp.displayString

    assert(actual == expected)
  }

  fun`display double sharp when sharpening sharp` () {
    val expected = "x"
    val sharp = Accidental.sharp

    val actual = sharp.sharp

    assert(actual.displayString == expected)
  }

  fun`be natural when flattening sharp` () {
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

  fun`equate equivalent accidentals` in {
    def accidental = Accidental(5)

    val first = accidental
    val second = accidental

    assert(first == second)
  }
}
   */
}