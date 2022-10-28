package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.fractions.Fraction
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NoteLengthTest:
  @Test
  def correctlyAddsDot(): Unit = {
    val original = NoteLength.crotchet
    val expected = NoteLength(Fraction(3, 8))

    val actual = original.dotted

    assertThat(actual).isEqualTo(expected)
  }
