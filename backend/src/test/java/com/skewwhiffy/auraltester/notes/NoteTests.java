package com.skewwhiffy.auraltester.notes;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NoteTests {
  @Test
  void when_natural_then_displayStringCorrect() {
    val note = Note.getA();

    val actual = note.getDisplayString();

    assertThat(actual).isEqualTo("A");
  }

  @Test
  void when_sharp_then_displayStringCorrect() {
    val note = Note.getB().getSharp();

    val actual = note.getDisplayString();

    assertThat(actual).isEqualTo("B#");
  }

  @Test
  void when_flat_then_displayStringCorrect() {
    val note = Note.getC().getFlat();

    val actual = note.getDisplayString();

    assertThat(actual).isEqualTo("Cb");
  }
}
