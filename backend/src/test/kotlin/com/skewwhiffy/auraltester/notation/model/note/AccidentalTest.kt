package com.skewwhiffy.auraltester.notation.model.note

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class AccidentalTest {
    @Test
    fun hasCorrectAbcWhenNatural() {
        val expected = ""
        val natural = Accidental.natural

        val actual = natural.abc

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun hasCorrectAbcWhenFlat() {
        val expected = "_"
        val flat = Accidental.flat

        val actual = flat.abc

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun hasCorrectAbcWhenSharp() {
        val expected = "^"
        val sharp = Accidental.sharp

        val actual = sharp.abc

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun displaysCorrectlyWhenNatural() {
        val expected = ""
        val natural = Accidental.natural

        val actual = natural.displayString

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun when_flatteningNatural_then_isFlat() {
        val natural = Accidental.natural

        val actual = natural.flatten

        assertThat(actual).isEqualTo(Accidental.flat)
    }

    @Test
    fun when_sharpeningNatural_then_isSharp() {
        val natural = Accidental.natural

        val actual = natural.sharpen

        assertThat(actual).isEqualTo(Accidental.sharp)
    }

    @Test
    fun when_flat_then_displaysFlat() {
        val expected = "b"
        val flat = Accidental.flat

        val actual = flat.displayString

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun when_flatteningFlat_then_displaysDoubleFlat() {
        val expected = "bb"
        val flat = Accidental.flat

        val actual = flat.flatten

        assertThat(actual.displayString).isEqualTo(expected)
    }

    @Test
    fun when_sharpeningFlat_then_isNatural() {
        val flat = Accidental.flat

        val actual = flat.sharpen

        assertThat(actual).isEqualTo(Accidental.natural)
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 7])
    fun displaysCorrectlyWithFlats(flats: Int) {
        val expected = "b".repeat(flats)
        val accidental = (1..flats).fold(Accidental.natural) { it, _ -> it.flatten }

        val actual = accidental.displayString

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun when_sharp_then_displaysSharp() {
        val expected = "#"
        val sharp = Accidental.sharp

        val actual = sharp.displayString

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun when_sharpeningSharp_then_displaysDoubleSharp() {
        val expected = "x"
        val sharp = Accidental.sharp

        val actual = sharp.sharpen

        assertThat(actual.displayString).isEqualTo(expected)
    }

    @Test
    fun when_flatteningSharp_then_isNatural() {
        val sharp = Accidental.sharp

        val actual = sharp.flatten

        assertThat(actual).isEqualTo(Accidental.natural)
    }

    @ParameterizedTest
    @ValueSource(ints = [6, 10])
    fun displaysEvenNumberOfSharpsCorrectly(numberOfSharps: Int) {
        val expected = "x".repeat(numberOfSharps / 2)
        val accidental = (1..numberOfSharps).fold(Accidental.natural) { it, _ -> it.sharpen }

        val actual = accidental.displayString

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [7, 13])
    fun displaysOddNumberOfSharpsCorrectly(numberOfSharps: Int) {
        val expected = "x".repeat(numberOfSharps / 2) + "#"
        val accidental = (1..numberOfSharps).fold(Accidental.natural) { it, _ -> it.sharpen }

        val actual = accidental.displayString

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun equatesEquivalentAccidentals() {
        val getAccidental = { Accidental(5) }

        val first = getAccidental()
        val second = getAccidental()

        assertThat(first).isEqualTo(second)
    }
}
