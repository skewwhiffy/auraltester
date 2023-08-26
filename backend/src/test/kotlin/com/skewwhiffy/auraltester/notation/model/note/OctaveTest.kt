package com.skewwhiffy.auraltester.notation.model.note
/*

import lombok.`val`
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.util.function.Supplier

internal class OctaveTests {
    @Test
    fun returnHigherOctaveWhenUpCalled() {
        val start: `val` = Octave(70)
        val actual: `val` = start.up()
        assertThat(actual.offsetFromDefault()).isEqualTo(start.offsetFromDefault() + 1)
    }

    @Test
    fun returnsLowerOctaveWhenDownCalled() {
        val start: `val` = Octave(81)
        val actual: `val` = start.down()
        assertThat(actual.offsetFromDefault()).isEqualTo(start.offsetFromDefault() - 1)
    }

    @Test
    fun equatesSameOctaves() {
        val getOctave = Supplier { Octave(52) }
        val first: `val` = getOctave.get()
        val second: `val` = getOctave.get()
        Assertions.assertThat<`val`>(first).isEqualTo(second)
        Assertions.assertThat<`val`>(first).isGreaterThanOrEqualTo(second)
    }

    @get:Test
    val isAbleToCompareOctaves: Unit
        get() {
            val first: `val` = Octave(20)
            val second: `val` = Octave(21)
            Assertions.assertThat<`val`>(first).isLessThan(second)
            Assertions.assertThat<`val`>(first).isLessThanOrEqualTo(second)
        }
}

 */