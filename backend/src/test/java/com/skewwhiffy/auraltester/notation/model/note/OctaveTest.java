/*
package com.skewwhiffy.notation.model.note

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OctaveTests {
  @Test
  fun `return higher octave when up called`() {
    val start = Octave(70)

    val actual = start.up

    assertThat(actual.offsetFromDefault).isEqualTo(start.offsetFromDefault + 1)
  }

  @Test
  fun `return lower octave when down called`() {
    val start = Octave(81)

    val actual = start.down

    assertThat(actual.offsetFromDefault).isEqualTo(start.offsetFromDefault - 1)
  }

  @Test
  fun `equate same octaves`() {
    fun getOctave() = Octave (52)

    val first = getOctave()
    val second = getOctave()

    assertThat(first).isEqualTo(second)
    assertThat(first >= second).isTrue
  }

  @Test
  fun `be able to compare octaves`() {
    val first = Octave(20)
    val second = Octave(21)

    assertThat(first > second).isFalse
    assertThat(first < second).isTrue
    assertThat(first >= second).isFalse
    assertThat(first <= second).isTrue
  }
}
*/
