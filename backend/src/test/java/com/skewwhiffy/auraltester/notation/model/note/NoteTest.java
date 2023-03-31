/*
package com.skewwhiffy.notation.model.note

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NoteTest {
  @Test
  fun `return display string when natural`() {
    val note = Note.a

    val actual = note.displayString

    assertThat(actual).isEqualTo("A")
  }

  @Test
  fun `return display string when sharp`() {
    val note = Note.b.sharp

    val actual = note.displayString

    assertThat(actual).isEqualTo("B#")
  }

  @Test
  fun `return display string when flat`() {
    val note = Note.d.flat

    val actual = note.displayString

    assertThat(actual).isEqualTo("Db")
  }

  @Test
  fun `equate equivalent notes`() {
    fun getNote() = Note("A", Accidental.sharp)

    val first = getNote()
    val second = getNote()

    assertThat(first).isEqualTo(second)
    assertThat(first <= second).isTrue
    assertThat(first >= second).isTrue
  }

  @Test
  fun `compare non-equivalent notes`() {
    val lower = Note.c

    val higher = Note.a

    assertThat(lower < higher).isTrue
    assertThat(lower > higher).isFalse
    // TODO: > and < with #s and bs
  }
}*/
