package com.skewwhiffy.auraltester.notes;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NoteTests {
  @Test
  void when_natural_then_displayStringCorrect() {
    val note = Note.A;

    val actual = note.getDisplayString();

    assertThat(actual).isEqualTo("A");
  }

  @Test
  void when_sharp_then_displayStringCorrect() {
    val note = Note.B.getSharp();

    val actual = note.getDisplayString();

    assertThat(actual).isEqualTo("B#");
  }

  @Test
  void when_flat_then_displayStringCorrect() {
    val note = Note.C.getFlat();

    val actual = note.getDisplayString();

    assertThat(actual).isEqualTo("Cb");
  }
}
