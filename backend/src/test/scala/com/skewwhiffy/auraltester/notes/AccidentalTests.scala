package com.skewwhiffy.auraltester.notes

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class AccidentalTests {
  @Test
  def when_natural_then_displaysCorrectly(): Unit = {
    val expected = ""
    val natural = Accidental.natural

    val actual = natural.displayString

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  def when_flattening_natural_then_displaysFlat(): Unit = {
    val expected = "b"
    val natural = Accidental.natural

    val actual = natural.flat

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def when_sharpening_natural_then_displaysSharp(): Unit = {
    val expected = "#"
    val natural = Accidental.natural

    val actual = natural.sharp

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def when_flat_then_displaysFlat(): Unit = {
    val expected = "b"
    val flat = Accidental.flat

    val actual = flat.displayString

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  def when_flatteningFlat_then_displaysDoubleFlat(): Unit = {
    val expected = "bb"
    val flat = Accidental.flat

    val actual = flat.flat

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def when_sharpeningFlat_then_displaysNatural(): Unit = {
    val expected = ""
    val flat = Accidental.flat

    val actual = flat.sharp

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @ParameterizedTest
  @ValueSource(ints = Array(3, 7))
  def when_multipleFlats_then_displaysCorrectly(flats: Int): Unit = {
    val expected = "b".repeat(flats)
    val accidental = List.range(0, flats).foldRight(Accidental.natural)((_, acc) => acc.flat)

    val actual = accidental.displayString

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  def when_sharp_then_displaysSharp(): Unit = {
    val expected = "#"
    val sharp = Accidental.sharp

    val actual = sharp.displayString

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  def when_sharpeningSharp_then_displaysDoubleSharp(): Unit = {
    val expected = "x"
    val sharp = Accidental.sharp

    val actual = sharp.sharp

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def when_flatteningSharp_then_displaysNatural(): Unit = {
    val expected = ""
    val sharp = Accidental.sharp

    val actual = sharp.flat

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @ParameterizedTest
  @ValueSource(ints = Array(6, 10))
  def when_multipleEvenSharps_then_displaysCorrectly(numberOfSharps: Int): Unit = {
    val expected = "x".repeat(numberOfSharps / 2)
    val accidental = List.range(0, numberOfSharps).foldRight(Accidental.natural)((_, it) => it.sharp)

    val actual = accidental.displayString

    assertThat(actual).isEqualTo(expected)
  }

  @ParameterizedTest
  @ValueSource(ints = Array(7, 13))
  def when_multipleOddSharps_then_displaysCorrectly(numberOfSharps: Int): Unit = {
    val expected = "x".repeat(numberOfSharps / 2) + "#"
    val accidental = List.range(0, numberOfSharps).foldRight(Accidental.natural)((_, it) => it.sharp)

    val actual = accidental.displayString

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  def when_accidentalsEquivalent_then_equal(): Unit = {
    def accidental = new Accidental(5)

    val first = accidental
    val second = accidental

    assertThat(first).isEqualTo(second)
  }
}
