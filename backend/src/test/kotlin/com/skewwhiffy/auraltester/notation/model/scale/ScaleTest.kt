package com.skewwhiffy.auraltester.notation.model.scale

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ScaleTest {
    @Test
    fun instantiatesMajorScale() {
        val expected = TestData.noteFactories.internalNotation.getNotes("C D E F G A B c")
        val actual = Scale(
            AbsoluteNote.middleC,
            TestData.noteFactories.scaleType.major,
            ScaleDirection.ASCENDING
        )
        assertThat(actual.notes).isEqualTo(expected)
    }

    @Test
    fun instantiateMinorHarmonicScale() {
        val expected = TestData.noteFactories.internalNotation
            .getNotes("D E F G A Bb c# d")
        val actual = Scale(
            TestData.noteFactories.internalNotation.getNote("D"),
            TestData.noteFactories.scaleType.minorHarmonic,
            ScaleDirection.ASCENDING
        )
        assertThat(actual.notes).isEqualTo(expected)
    }

    @Test
    fun instantiatesMinorMelodicAscendingScale() {
        val expected = TestData.noteFactories.internalNotation.getNotes("E F# G A B c# d# e")
        val actual = Scale(
             TestData . noteFactories .internalNotation.getNote("E"),
             TestData . noteFactories .scaleType.minorMelodic,
            ScaleDirection.ASCENDING
        )
        assertThat(actual.notes).isEqualTo(expected)
    }

    @Test
    fun instantiatesMinorMelodicDescendingScale() {
        val expected = TestData . noteFactories .internalNotation .getNotes("a g f e d c B A")
        val actual = Scale(
             TestData . noteFactories .internalNotation.getNote("A"),
             TestData . noteFactories .scaleType.minorMelodic,
            ScaleDirection.DESCENDING
        )
        assertThat(actual.notes).isEqualTo(expected)
    } // TODO: test abc
}
