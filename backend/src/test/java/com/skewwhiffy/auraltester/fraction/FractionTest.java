package com.skewwhiffy.auraltester.fraction;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FractionTest {
    @Test
    void canInstantiateSimpleFraction() {
        val expected = "1/2";

        val actual = new Fraction(1, 2);

        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    void canInstantiateMixedFraction() {
        val expected = "1 2/3";

        val actual = new Fraction(1, 2, 3);

        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    void canAddFractions() {
        val expected = "11/12";

        val actual = new Fraction(3, 4).plus(new Fraction(1, 6));

        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    void canSubtractFractions() {
        val expected = "1/2";

        val actual = new Fraction(3, 4).minus(new Fraction(1, 4));

        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    void canMultiplyFractions() {
        val expected = "3/8";

        val actual = new Fraction(1, 4).times(new Fraction(3, 2));

        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    void equatesIntegerToFraction() {
        val source = new Fraction(4, 2);

        //noinspection AssertBetweenInconvertibleTypes
        assertThat(source).isEqualTo(2);
    }

    @Test
    void equatesEquivalentFractions() {
        val first = new Fraction(2, 4);
        val second = new Fraction(-3, -6);

        assertThat(first).isEqualTo(second);
    }

    @Test
    void canGetTopHeavyString() {
        val fraction = new Fraction(1, 1, 2);
        val expected = "3/2";

        val actual = fraction.getTopHeavyString();

        assertThat(actual).isEqualTo(expected);
    }
}
