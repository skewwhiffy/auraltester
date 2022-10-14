package com.skewwhiffy.auraltester.notes

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NoteTests {
  @Test
  def when_natural_then_displayStringCorrect(): Unit = {
    val note = Note.A

    val actual = note.getDisplayString

    assertThat(actual).isEqualTo("A")
  }

  @Test
  def when_sharp_then_displayStringCorrect(): Unit = {
    val note = Note.B.getSharp

    val actual = note.getDisplayString

    assertThat(actual).isEqualTo("B#")
  }

  @Test
  def when_flat_then_displayStringCorrect(): Unit = {
    val note = Note.C.getFlat

    val actual = note.getDisplayString

    assertThat(actual).isEqualTo("Cb")
  }
}
