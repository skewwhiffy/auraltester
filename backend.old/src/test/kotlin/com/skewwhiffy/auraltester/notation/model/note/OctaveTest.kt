package com.skewwhiffy.auraltester.notation.model.note

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OctaveTest {
    @Test
    fun returnHigherOctaveWhenUpCalled() {
        val start = Octave(70)

        val actual = start.up

        assertThat(actual.offsetFromDefault).isEqualTo(start.offsetFromDefault + 1)
    }

    @Test
    fun returnsLowerOctaveWhenDownCalled() {
        val start = Octave(81)

        val actual = start.down

        assertThat(actual.offsetFromDefault).isEqualTo(start.offsetFromDefault - 1)
    }

    @Test
    fun equatesSameOctaves() {
        val getOctave = { Octave(52) }

        val first = getOctave()
        val second = getOctave()

        assertThat(first).isEqualTo(second)
        assertThat(first).isGreaterThanOrEqualTo(second)
    }

    @Test
    fun isAbleToCompareOctaves() {
        val first = Octave(20)
        val second = Octave(21)

        assertThat(first).isLessThan(second)
        assertThat(first).isLessThanOrEqualTo(second)
    }
}