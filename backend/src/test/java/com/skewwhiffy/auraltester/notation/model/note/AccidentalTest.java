package com.skewwhiffy.auraltester.notation.model.note;

import com.skewwhiffy.auraltester.helper.NoParallelStream;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.Supplier;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class AccidentalTest {
    @Test
    void hasCorrectAbcWhenNatural() {
        val expected = "";
        val natural = Accidental.getNatural();

        val actual = natural.getAbc();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void hasCorrectAbcWhenFlat() {
        val expected = "_";
        val flat = Accidental.getFlat();

        val actual = flat.getAbc();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void hasCorrectAbcWhenSharp() {
        val expected = "^";
        val sharp = Accidental.getSharp();

        val actual = sharp.getAbc();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void displaysCorrectlyWhenNatural() {
        val expected = "";
        val natural = Accidental.getNatural();

        val actual = natural.getDisplayString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void when_flatteningNatural_then_isFlat() {
        val natural = Accidental.getNatural();

        val actual = natural.flatten();

        assertThat(actual).isEqualTo(Accidental.getFlat());
    }

    @Test
    void when_sharpeningNatural_then_isSharp() {
        val natural = Accidental.getNatural();

        val actual = natural.sharpen();

        assertThat(actual).isEqualTo(Accidental.getSharp());
    }

    @Test
    void when_flat_then_displaysFlat() {
        val expected = "b";
        val flat = Accidental.getFlat();

        val actual = flat.getDisplayString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void when_flatteningFlat_then_displaysDoubleFlat() {
        val expected = "bb";
        val flat = Accidental.getFlat();

        val actual = flat.flatten();

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @Test
    void when_sharpeningFlat_then_isNatural() {
        val flat = Accidental.getFlat();

        val actual = flat.sharpen();

        assertThat(actual).isEqualTo(Accidental.getNatural());
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 7})
    void displaysCorrectlyWithFlats(int flats) {
        val expected = "b".repeat(flats);
        val accidental = IntStream
                .range(0, flats)
                .boxed()
                .reduce(
                        Accidental.getNatural(),
                        (it, i) -> it.flatten(),
                        new NoParallelStream<>()
                );

        val actual = accidental.getDisplayString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void when_sharp_then_displaysSharp() {
        val expected = "#";
        val sharp = Accidental.getSharp();

        val actual = sharp.getDisplayString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void when_sharpeningSharp_then_displaysDoubleSharp() {
        val expected = "x";
        val sharp = Accidental.getSharp();

        val actual = sharp.sharpen();

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @Test
    void when_flatteningSharp_then_isNatural() {
        val sharp = Accidental.getSharp();

        val actual = sharp.flatten();

        assertThat(actual).isEqualTo(Accidental.getNatural());
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 10})
    void displaysEvenNumberOfSharpsCorrectly(int numberOfSharps) {
        val expected = "x".repeat(numberOfSharps / 2);
        val accidental = IntStream
                .range(0, numberOfSharps)
                .boxed()
                .reduce(
                        Accidental.getNatural(),
                        (it, i) -> it.sharpen(),
                        new NoParallelStream<>()
                );

        val actual = accidental.getDisplayString();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 13})
    void displaysOddNumberOfSharpsCorrectly(int numberOfSharps) {
        val expected = "x".repeat(numberOfSharps / 2) + "#";
        val accidental = IntStream
                .range(0, numberOfSharps)
                .boxed()
                .reduce(
                        Accidental.getNatural(),
                        (it, i) -> it.sharpen(),
                        new NoParallelStream<>()
                );

        val actual = accidental.getDisplayString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void equatesEquivalentAccidentals() {
        Supplier<Accidental> getAccidental = () -> new Accidental(5);

        val first = getAccidental.get();
        val second = getAccidental.get();

        assertThat(first).isEqualTo(second);
    }
}