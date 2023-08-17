package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.test.util.TestData;
import static org.assertj.core.api.Assertions.assertThat;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

class ClefSpaceNotesTest {
  @Test
    void correctlyDefinesLineNotesFromClef() {
    val clef = TestData.noteFactories().clef().getTreble();
    val expectedAbc = List.of("F", "A", "c", "e");
    val expectedNames = expectedAbc.stream().map(it -> it.toUpperCase(Locale.UK)).toList();

    val actual = new ClefSpaceNotes(clef);
    val actualNotes = actual.getNotes().stream().map(it -> it.getAbc(Key.getCMajor())).toList();
    val actualNames = actual.getNotes().stream().map(it -> it.lyric().orElseThrow());

    assertThat(actualNotes).isEqualTo(expectedAbc);
    assertThat(actualNames).isEqualTo(expectedNames);
  }
}
