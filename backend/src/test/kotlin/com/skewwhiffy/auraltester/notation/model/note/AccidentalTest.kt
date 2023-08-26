package com.skewwhiffy.auraltester.notation.model.note
/*

import com.skewwhiffy.auraltester.helper.NoParallelStream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.util.function.Supplier
import java.util.stream.IntStream

internal class AccidentalTest {
    @Test
    fun hasCorrectAbcWhenNatural() {
        val expected: `val` = ""
        val natural: `val` = Accidental.getNatural()
        val actual: `val` = natural.getAbc()
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }

    @Test
    fun hasCorrectAbcWhenFlat() {
        val expected: `val` = "_"
        val flat: `val` = Accidental.getFlat()
        val actual: `val` = flat.getAbc()
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }

    @Test
    fun hasCorrectAbcWhenSharp() {
        val expected: `val` = "^"
        val sharp: `val` = Accidental.getSharp()
        val actual: `val` = sharp.getAbc()
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }

    @Test
    fun displaysCorrectlyWhenNatural() {
        val expected: `val` = ""
        val natural: `val` = Accidental.getNatural()
        val actual: `val` = natural.getDisplayString()
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }

    @Test
    fun when_flatteningNatural_then_isFlat() {
        val natural: `val` = Accidental.getNatural()
        val actual: `val` = natural.flatten()
        Assertions.assertThat<`val`>(actual).isEqualTo(Accidental.getFlat())
    }

    @Test
    fun when_sharpeningNatural_then_isSharp() {
        val natural: `val` = Accidental.getNatural()
        val actual: `val` = natural.sharpen()
        Assertions.assertThat<`val`>(actual).isEqualTo(Accidental.getSharp())
    }

    @Test
    fun when_flat_then_displaysFlat() {
        val expected: `val` = "b"
        val flat: `val` = Accidental.getFlat()
        val actual: `val` = flat.getDisplayString()
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }

    @Test
    fun when_flatteningFlat_then_displaysDoubleFlat() {
        val expected: `val` = "bb"
        val flat: `val` = Accidental.getFlat()
        val actual: `val` = flat.flatten()
        assertThat(actual.getDisplayString()).isEqualTo(expected)
    }

    @Test
    fun when_sharpeningFlat_then_isNatural() {
        val flat: `val` = Accidental.getFlat()
        val actual: `val` = flat.sharpen()
        Assertions.assertThat<`val`>(actual).isEqualTo(Accidental.getNatural())
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 7])
    fun displaysCorrectlyWithFlats(flats: Int) {
        val expected: `val` = "b".repeat(flats)
        val accidental: `val` = IntStream
            .range(0, flats)
            .boxed()
            .reduce<`val`>(
                Accidental.getNatural(),
                BiFunction<`val`, Int, `val`> { it: `val`, i: Int? -> it.flatten() },
                NoParallelStream()
            )
        val actual: `val` = accidental.getDisplayString()
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }

    @Test
    fun when_sharp_then_displaysSharp() {
        val expected: `val` = "#"
        val sharp: `val` = Accidental.getSharp()
        val actual: `val` = sharp.getDisplayString()
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }

    @Test
    fun when_sharpeningSharp_then_displaysDoubleSharp() {
        val expected: `val` = "x"
        val sharp: `val` = Accidental.getSharp()
        val actual: `val` = sharp.sharpen()
        assertThat(actual.getDisplayString()).isEqualTo(expected)
    }

    @Test
    fun when_flatteningSharp_then_isNatural() {
        val sharp: `val` = Accidental.getSharp()
        val actual: `val` = sharp.flatten()
        Assertions.assertThat<`val`>(actual).isEqualTo(Accidental.getNatural())
    }

    @ParameterizedTest
    @ValueSource(ints = [6, 10])
    fun displaysEvenNumberOfSharpsCorrectly(numberOfSharps: Int) {
        val expected: `val` = "x".repeat(numberOfSharps / 2)
        val accidental: `val` = IntStream
            .range(0, numberOfSharps)
            .boxed()
            .reduce<`val`>(
                Accidental.getNatural(),
                BiFunction<`val`, Int, `val`> { it: `val`, i: Int? -> it.sharpen() },
                NoParallelStream()
            )
        val actual: `val` = accidental.getDisplayString()
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [7, 13])
    fun displaysOddNumberOfSharpsCorrectly(numberOfSharps: Int) {
        val expected: `val` = "x".repeat(numberOfSharps / 2) + "#"
        val accidental: `val` = IntStream
            .range(0, numberOfSharps)
            .boxed()
            .reduce<`val`>(
                Accidental.getNatural(),
                BiFunction<`val`, Int, `val`> { it: `val`, i: Int? -> it.sharpen() },
                NoParallelStream()
            )
        val actual: `val` = accidental.getDisplayString()
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }

    @Test
    fun equatesEquivalentAccidentals() {
        val getAccidental = Supplier { Accidental(5) }
        val first: `val` = getAccidental.get()
        val second: `val` = getAccidental.get()
        Assertions.assertThat<`val`>(first).isEqualTo(second)
    }
}

 */