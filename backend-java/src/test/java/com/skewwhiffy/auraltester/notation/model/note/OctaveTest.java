package com.skewwhiffy.auraltester.notation.model.note;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

class OctaveTests {
    @Test
    void returnHigherOctaveWhenUpCalled() {
        val start = new Octave(70);

        val actual = start.up();

        assertThat(actual.offsetFromDefault()).isEqualTo(start.offsetFromDefault() + 1);
    }

    @Test
    void returnsLowerOctaveWhenDownCalled() {
        val start = new Octave(81);

        val actual = start.down();

        assertThat(actual.offsetFromDefault()).isEqualTo(start.offsetFromDefault() - 1);
    }

    @Test
    void equatesSameOctaves() {
        Supplier<Octave> getOctave = () -> new Octave(52);

        val first = getOctave.get();
        val second = getOctave.get();

        assertThat(first).isEqualTo(second);
        assertThat(first).isGreaterThanOrEqualTo(second);
    }

    @Test
    void isAbleToCompareOctaves() {
        val first = new Octave(20);
        val second = new Octave(21);

        assertThat(first).isLessThan(second);
        assertThat(first).isLessThanOrEqualTo(second);
    }
}