package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class ClefLineNotesTest {
    @Test
    void correctlyDefinesLineNotesFromClef() {
        val clef = TestData.noteFactories().clef().getTreble();
        val expectedAbc = List.of("E", "G", "B", "d", "f");
        val expectedNames = expectedAbc.stream().map(it -> it.toUpperCase(Locale.UK)).toList();

        val actual = new ClefLineNotes(clef);
        val actualAbc = actual.getNotes().stream().map(it -> it.getAbc(Key.getCMajor())).toList();
        val actualNames = actual.getNotes().stream().map(it -> it.lyric().orElseThrow()).toList();

        assertThat(actualAbc).isEqualTo(expectedAbc);
        assertThat(actualNames).isEqualTo(expectedNames);
    }
}