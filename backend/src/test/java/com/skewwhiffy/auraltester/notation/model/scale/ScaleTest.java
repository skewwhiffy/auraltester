package com.skewwhiffy.auraltester.notation.model.scale;

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScaleTest {
    @Test
    void instantiatesMajorScale() {
        val expected = TestData.noteFactories().internalNotation().getNotes("C D E F G A B c");

        val actual = new Scale(
                AbsoluteNote.getMiddleC(),
                TestData.noteFactories().scaleType().getMajor(),
                ScaleDirection.ASCENDING
        );

        assertThat(actual.getNotes()).isEqualTo(expected);
    }

    @Test
    void instantiateMinorHarmonicScale() {
        val expected = TestData.noteFactories().internalNotation().getNotes("D E F G A Bb c# d");

        val actual = new Scale(
                TestData.noteFactories().internalNotation().getNote("D"),
                TestData.noteFactories().scaleType().getMinorHarmonic(),
                ScaleDirection.ASCENDING
        );

        assertThat(actual.getNotes()).isEqualTo(expected);
    }

    @Test
    void instantiatesMinorMelodicAscendingScale() {
        val expected = TestData.noteFactories().internalNotation().getNotes("E F# G A B c# d# e");

        val actual = new Scale(
                TestData.noteFactories().internalNotation().getNote("E"),
                TestData.noteFactories().scaleType().getMinorMelodic(),
                ScaleDirection.ASCENDING
        );

        assertThat(actual.getNotes()).isEqualTo(expected);
    }

    @Test
    void instantiatesMinorMelodicDescendingScale() {
        val expected = TestData.noteFactories().internalNotation().getNotes("a g f e d c B A");

        val actual = new Scale(
                TestData.noteFactories().internalNotation().getNote("A"),
                TestData.noteFactories().scaleType().getMinorMelodic(),
                ScaleDirection.DESCENDING
        );

        assertThat(actual.getNotes()).isEqualTo(expected);
    }

    // TODO: test abc
}
