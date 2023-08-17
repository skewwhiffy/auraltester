package com.skewwhiffy.auraltester.notation.model.note;

import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IntervalNotesTest {
  @Test
    void instantiatesCorrectly() {
    val bottomNote = TestData.random().absoluteNote();
    val interval = TestData.random().interval();
    val expectedNotes = List.of(bottomNote, bottomNote.plus(interval));

    val actual = new IntervalNotes(bottomNote, interval);

    assertThat(actual.getNotes()).isEqualTo(expectedNotes);
  }
}
