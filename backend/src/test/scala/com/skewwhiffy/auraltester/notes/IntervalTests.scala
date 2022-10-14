package com.skewwhiffy.auraltester.notes

import org.junit.jupiter.params.provider.ValueSource
import org.assertj.core.api.Assertions.{assertThat, assertThatThrownBy}
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest

class IntervalTests {
  @Test
  def canInstantiateMajorSecond(): Unit = {
    val expected = "major second"

    val actual = Interval.major(2)

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def when_diminishingMajorThird_then_minorThird(): Unit = {
    val expected = "minor third"
    val major = Interval.major(3)

    val actual = major.diminished

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def canInstantiateMinorSixth(): Unit = {
    val expected = "minor sixth"

    val actual = Interval.minor(6)

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def when_diminishingMinorSeventh_then_diminishedSeventh(): Unit = {
    val expected = "diminished seventh"
    val minor = Interval.minor(7)

    val actual = minor.diminished

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def when_diminishingDiminishedSecond_then_doublyDiminishedSecond(): Unit = {
    val expected = "doubly diminished second"
    val diminished = Interval.minor(2).diminished

    val actual = diminished.diminished

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def when_diminishingDoublyDiminishedThird_then_3xDiminishedThird(): Unit = {

    val expected = "3x diminished third"
    val doublyDiminished = Interval.minor(3).diminished.diminished

    val actual = doublyDiminished.diminished

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def when_augmentingMajorSixth_then_augmentedSixth(): Unit = {
    val expected = "augmented sixth"
    val major = Interval.major(6)

    val actual = major.augmented

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @ParameterizedTest
  @ValueSource(ints = Array(1, 4, 5, 8))
  def cannotInstantiateMajorIntervalWhenShouldBePerfect(degree: Int): Unit = {
    assertThatThrownBy(() => Interval.major(degree)).isInstanceOf(classOf[IllegalArgumentException])

  }

  @Test
  def canInstantiatePerfectUnison(): Unit = {
    val expected = "perfect unison"

    val actual = Interval.perfect(1)

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def when_diminishingPerfectFourth_then_diminishedFourth(): Unit = {
    val expected = "diminished fourth"
    val perfect = Interval.perfect(4)

    val actual = perfect.diminished

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def when_diminishingDiminishedFifth_then_doublyDiminishedFifth(): Unit = {
    val expected = "doubly diminished fifth"
    val diminished = Interval.perfect(5).diminished

    val actual = diminished.diminished

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def when_diminishingDoublyDiminishedOctave_then_3xDiminishedOctave(): Unit = {
    val expected = "3x diminished octave"
    val doublyDiminished = Interval.perfect(8).diminished.diminished

    val actual = doublyDiminished.diminished

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def when_augmentingPerfectUnison_then_augmentedUnison(): Unit = {
    val expected = "augmented unison"
    val perfect = Interval.perfect(1)

    val actual = perfect.augmented

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @ParameterizedTest
  @ValueSource(ints = Array(2, 3, 6, 7))
  def cannotInstantiatePerfectIntervalWhenShouldBeMajor(degree: Int): Unit = {
    assertThatThrownBy(() => Interval.perfect(degree)).isInstanceOf(classOf[IllegalArgumentException])
  }

  @Test
  def when_augmentingAugmentedSecond_then_doublyAugmentedSecond(): Unit = {
    val expected = "doubly augmented second"
    val augmented = Interval.major(2).augmented

    val actual = augmented.augmented

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  def when_augmentingAugmentedFourth_then_3xAugmentedFourth(): Unit = {
    val expected = "3x augmented fourth"
    val doublyAugmented = Interval.perfect(4).augmented.augmented

    val actual = doublyAugmented.augmented

    assertThat(actual.displayString).isEqualTo(expected)
  }
}
