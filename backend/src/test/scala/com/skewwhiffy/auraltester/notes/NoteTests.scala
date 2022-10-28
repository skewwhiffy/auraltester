package com.skewwhiffy.auraltester.notes

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NoteTests {
  @Test
  def when_natural_then_displayStringCorrect(): Unit = {
    val note = Note.A

    val actual = note.displayString

    assertThat(actual).isEqualTo("A")
  }

  @Test
  def when_sharp_then_displayStringCorrect(): Unit = {
    val note = Note.B.sharp

    val actual = note.displayString

    assertThat(actual).isEqualTo("B#")
  }

  @Test
  def when_flat_then_displayStringCorrect(): Unit = {
    val note = Note.D.flat

    val actual = note.displayString

    assertThat(actual).isEqualTo("Db")
  }

  @Test
  def when_notesEquivalent_then_equalsWorks(): Unit = {
    def note = new Note("A", Accidental.sharp)

    val first = note
    val second = note

    assertThat(first).isEqualTo(second)
    assertThat(first <= second).isTrue
    assertThat(first >= second).isTrue
  }
  
  @Test
  def when_notesNotEquivalent_then_canCompare(): Unit = {
    def lower = Note.C
    def higher = Note.A
    
    assertThat(lower < higher).isTrue
    assertThat(lower > higher).isFalse
    // TODO: > and < with #s and bs
  }
}
