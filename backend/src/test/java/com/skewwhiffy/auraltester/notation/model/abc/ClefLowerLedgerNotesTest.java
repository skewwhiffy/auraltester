package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class ClefLowerLedgerNotesTest {
  @Test
    void correctlyDefinesLowerLedgerNotesFromClef() {
    val clef = TestData.noteFactories().clef().getTreble();
    val expectedAbc = List.of("D", "C", "B,", "A,", "G,", "F,");
    val expectedNames = expectedAbc.stream().map(it -> it.substring(0, 1).toUpperCase(Locale.UK)).toList();

    val actual = new ClefLowerLedgerNotes(clef);
    val actualNotes = actual.getNotes().stream().map(it -> it.getAbc(Key.getCMajor()));
    val actualNames = actual.getNotes().stream().map(it -> it.lyric().orElseThrow());

    assertThat(actualNotes).isEqualTo(expectedAbc);
    assertThat(actualNames).isEqualTo(expectedNames);
  }
}