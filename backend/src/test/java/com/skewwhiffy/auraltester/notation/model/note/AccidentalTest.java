/*
package com.skewwhiffy.notation.model.note

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

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
  fun `is sharp when sharpening natural`() {
    val natural = Accidental.natural

    val actual = natural.sharp

    assertThat(actual).isEqualTo(Accidental.sharp)
  }

  @Test
  fun `displays flat when flat`() {
    val expected = "b"
    val flat = Accidental.flat

    val actual = flat.displayString

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `displays double flat when flattening flat`() {
    val expected = "bb"
    val flat = Accidental.flat

    val actual = flat.flat

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  fun `is natural when sharpening flat`() {
    val flat = Accidental.flat

    val actual = flat.sharp

    assertThat(actual).isEqualTo(Accidental.natural)
  }

  @ParameterizedTest
  @ValueSource(ints = [3, 7])
  fun `displays correctly with flats`(flats: Int) {
    val expected = "b".repeat(flats)
    val accidental = (1..flats).fold(Accidental.natural) { acc, _ -> acc.flat }

    val actual = accidental.displayString

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `displays sharp when sharp`() {
    val expected = "#"
    val sharp = Accidental.sharp

    val actual = sharp.displayString

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `displays double sharp when sharpening sharp`() {
    val expected = "x"
    val sharp = Accidental.sharp

    val actual = sharp.sharp

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  fun `is natural when flattening sharp`() {
    val sharp = Accidental.sharp

    val actual = sharp.flat

    assertThat(actual).isEqualTo(Accidental.natural)
  }

  @ParameterizedTest
  @ValueSource(ints = [6, 10])
  fun `displays even number of sharps correctly`(numberOfSharps: Int) {
    val expected = "x".repeat(numberOfSharps / 2)
    val accidental = (1..numberOfSharps).fold(Accidental.natural) { it, _ -> it.sharp }

    val actual = accidental.displayString

    assertThat(actual).isEqualTo(expected)
  }

  @ParameterizedTest
  @ValueSource(ints = [7, 13])
  fun `displays odd number of sharps correctly`(numberOfSharps: Int) {
    val expected = "x".repeat(numberOfSharps / 2) + "#"
    val accidental = (1..numberOfSharps)
      .fold(Accidental.natural) { it, _ -> it.sharp }

    val actual = accidental.displayString

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `equates equivalent accidentals`() {
    fun getAccidental() = Accidental (5)

    val first = getAccidental()
    val second = getAccidental()

    assertThat(first).isEqualTo(second)
  }
}*/
