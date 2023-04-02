package com.skewwhiffy.auraltester.notation.model.interval;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IntervalTest {
    @Test
    void instantiatesMajorSecond() {
        val expected = "major second";

        val actual = Interval.major(2);

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @Test
    void returnsMinorThirdWhenDiminishingMajorThird() {
        val expected = "minor third";
        val major = Interval.major(3);

        val actual = major.getDiminished();

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @Test
    void instantiatesMinorSixth() {
        val expected = "minor sixth";

        val actual = Interval.minor(6);

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @Test
    void returnsDiminishedSeventhWhenDiminishingMinorSeventh() {
        val expected = "diminished seventh";
        val minor = Interval.minor(7);

        val actual = minor.getDiminished();

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @Test
    void returnsDoublyDiminishedSecondWhenDiminishingDiminishedSecond() {
        val expected = "doubly diminished second";
        val diminished = Interval.minor(2).getDiminished();

        val actual = diminished.getDiminished();

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @Test
    void returns3xDiminishedThirdWhenDiminishingDoublyDiminishedThird() {
        val expected = "3x diminished third";
        val doublyDiminished = Interval.minor(3).getDiminished().getDiminished();

        val actual = doublyDiminished.getDiminished();

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @Test
    void returnsAugmentedSixthWhenAugmentingMajorSixth() {
        val expected = "augmented sixth";
        val major = Interval.major(6);

        val actual = major.getAugmented();

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 5, 8})
    void doesNotInstantiateMajorInterval(int degree) {
        assertThatThrownBy(() -> Interval.major(degree)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void instantiatesPerfectUnison() {
        val expected = "perfect unison";

        val actual = Interval.perfect(1);

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @Test
    void returnsDiminishedFourthWhenDiminishingPerfectFourth() {
        val expected = "diminished fourth";
        val perfect = Interval.perfect(4);

        val actual = perfect.getDiminished();

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @Test
    void returnsDoublyDiminishedFifthWhenDiminishingDiminishedFifth() {
        val expected = "doubly diminished fifth";
        val diminished = Interval.perfect(5).getDiminished();

        val actual = diminished.getDiminished();

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @Test
    void returns3xDiminishedOctaveWhenDiminishingDoublyDiminishedOctave() {
        val expected = "3x diminished octave";
        val doublyDiminished = Interval.perfect(8).getDiminished().getDiminished();

        val actual = doublyDiminished.getDiminished();

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @Test
    void returnsAugmentedUnisonWhenAugmentingPerfectUnison() {
        val expected = "augmented unison";
        val perfect = Interval.perfect(1);

        val actual = perfect.getAugmented();

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 6, 7})
    void doesNotInstantiatePerfectInterval(int degree) {
        assertThatThrownBy(() -> Interval.perfect(degree)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void returnsDoublyAugmentedSecondWhenAugmentingAugmentedSecond() {
        val expected = "doubly augmented second";
        val augmented = Interval.major(2).getAugmented();

        val actual = augmented.getAugmented();

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @Test
    void returns3xAugmentedFourthWhenAugmentingAugmentedFourth() {
        val expected = "3x augmented fourth";
        val doublyAugmented = Interval.perfect(4).getAugmented().getAugmented();

        val actual = doublyAugmented.getAugmented();

        assertThat(actual.getDisplayString()).isEqualTo(expected);
    }

    @Test
    void returnsEqualWhenEquivalent() {
        Supplier<Interval> getInterval = () -> new Interval(6, 69);

        val first = getInterval.get();
        val second = getInterval.get();

        assertThat(first).isEqualTo(second);
    }
}
