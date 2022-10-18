package com.skewwhiffy.auraltester.notes

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OctaveTests {
  @Test
  def when_getAbcInDefaultOctave_then_correctResponse(): Unit = {
    val octave = Octave.default
    val note = Note.F
    val expected = "F"

    val actual = octave.getAbc(note)

    assertThat(actual).isEqualTo(expected)
  }

  @ParameterizedTest
  @ValueSource(ints = Array(1, 5))
  def when_getAbcInHigherOctaves_then_correctResponse(octavesHigher: Int): Unit = {
    val octave = List.range(0, octavesHigher).foldRight(Octave.default)((_, it) => it.up)
    val note = Note.E
    val expected = "e" + "'".repeat(octavesHigher - 1)

    val actual = octave.getAbc(note)

    assertThat(actual).isEqualTo(expected)
  }

  @ParameterizedTest
  @ValueSource(ints = Array(1, 6))
  def when_getAbcInLowerOctaves_then_correctResponse(octavesLower: Int): Unit = {
    val octave = List.range(0, octavesLower).foldRight(Octave.default)((_, it) => it.down)
    val note = Note.G
    val expected = "G" + ",".repeat(octavesLower)

    val actual = octave.getAbc(note)

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  def when_sameOctaves_then_equal(): Unit = {
    def octave = Octave(52)

    val first = octave
    val second = octave

    assertThat(first).isEqualTo(second)
  }
}
