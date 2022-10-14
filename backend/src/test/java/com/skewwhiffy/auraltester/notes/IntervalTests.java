package com.skewwhiffy.auraltester.notes;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IntervalTests {
  @Test
  void canInstantiateMajorSecond() {
    val expected = "major second";

    val actual = Interval.major(2);

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void when_diminishingMajorThird_then_minorThird() {
    val expected = "minor third";
    val major = Interval.major(3);

    val actual = major.getDiminished();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void canInstantiateMinorSixth() {
    val expected = "minor sixth";

    val actual = Interval.minor(6);

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void when_diminishingMinorSeventh_then_diminishedSeventh() {
    val expected = "diminished seventh";
    val minor = Interval.minor(7);

    val actual = minor.getDiminished();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void when_diminishingDiminishedSecond_then_doublyDiminishedSecond() {
    val expected = "doubly diminished second";
    val diminished = Interval.minor(2).getDiminished();

    val actual = diminished.getDiminished();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void when_diminishingDoublyDiminishedThird_then_3xDiminishedThird() {

    val expected = "3x diminished third";
    val doublyDiminished = Interval.minor(3).getDiminished().getDiminished();

    val actual = doublyDiminished.getDiminished();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void when_augmentingMajorSixth_then_augmentedSixth() {
    val expected = "augmented sixth";
    val major = Interval.major(6);

    val actual = major.getAugmented();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 4, 5, 8})
  void cannotInstantiateMajorIntervalWhenShouldBePerfect(int degree) {
    assertThatThrownBy(() -> Interval.major(degree)).isInstanceOf(IllegalArgumentException.class);

  }

  @Test
  void canInstantiatePerfectUnison() {
    val expected = "perfect unison";

    val actual = Interval.perfect(1);

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void when_diminishingPerfectFourth_then_diminishedFourth() {
    val expected = "diminished fourth";
    val perfect = Interval.perfect(4);

    val actual = perfect.getDiminished();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void when_diminishingDiminishedFifth_then_doublyDiminishedFifth() {
    val expected = "doubly diminished fifth";
    val diminished = Interval.perfect(5).getDiminished();

    val actual = diminished.getDiminished();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void when_diminishingDoublyDiminishedOctave_then_3xDiminishedOctave() {
    val expected = "3x diminished octave";
    val doublyDiminished = Interval.perfect(8).getDiminished().getDiminished();

    val actual = doublyDiminished.getDiminished();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void when_augmentingPerfectUnison_then_augmentedUnison() {
    val expected = "augmented unison";
    val perfect = Interval.perfect(1);

    val actual = perfect.getAugmented();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @ParameterizedTest
  @ValueSource(ints = {2, 3, 6, 7})
  void cannotInstantiatePerfectIntervalWhenShouldBeMajor(int degree) {
    assertThatThrownBy(() -> Interval.perfect(degree)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void when_augmentingAugmentedSecond_then_doublyAugmentedSecond() {
    val expected = "doubly augmented second";
    val augmented = Interval.major(2).getAugmented();

    val actual = augmented.getAugmented();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }

  @Test
  void when_augmentingAugmentedFourth_then_3xAugmentedFourth() {
    val expected = "3x augmented fourth";
    val doublyAugmented = Interval.perfect(4).getAugmented().getAugmented();

    val actual = doublyAugmented.getAugmented();

    assertThat(actual.getDisplayString()).isEqualTo(expected);
  }
}
