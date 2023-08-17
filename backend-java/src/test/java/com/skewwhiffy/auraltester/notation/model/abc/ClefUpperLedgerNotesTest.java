package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.test.util.TestData;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

class ClefUpperLedgerNotesTest {

    @Test
    void correctlyDefineLowerLedgerNotesFromClef() {
        val clef = TestData.noteFactories().clef().getTreble();
        val expectedAbc = List.of("g", "a", "b", "c'", "d'", "e'");
        val expectedNames = expectedAbc
                .stream()
                .map(it -> it.substring(0, 1).toUpperCase(Locale.UK))
                .toList();

        val actual = new ClefUpperLedgerNotes(clef);
        val actualAbc = actual
                .getNotes()
                .stream()
                .map(it -> it.getAbc(Key.getCMajor()))
                .toList();
        val actualNotes = actual
                .getNotes()
                .stream()
                .map(AbsoluteNote::lyric)
                .map(Optional::orElseThrow)
                .toList();

        assertThat(actualAbc).isEqualTo(expectedAbc);
        assertThat(actualNotes).isEqualTo(expectedNames);
    }
}
