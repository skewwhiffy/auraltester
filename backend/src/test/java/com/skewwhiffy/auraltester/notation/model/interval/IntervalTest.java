/*
package com.skewwhiffy.notation.model.interval

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class IntervalTest {
  @Test
  fun `instantiates major second`() {
    val expected = "major second"

    val actual = Interval.major(2)

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  fun `returns minor third when diminishing major third`() {
    val expected = "minor third"
    val major = Interval.major(3)

    val actual = major.diminished

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  fun `instantiates minor sixth`() {
    val expected = "minor sixth"

    val actual = Interval.minor(6)

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  fun `returns diminished seventh when diminishing minor seventh`() {
    val expected = "diminished seventh"
    val minor = Interval.minor(7)

    val actual = minor.diminished

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  fun `returns doubly diminished second when diminishing diminished second`() {
    val expected = "doubly diminished second"
    val diminished = Interval.minor(2).diminished

    val actual = diminished.diminished

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  fun `returns 3x diminished third when diminishing doubly diminished third`() {
    val expected = "3x diminished third"
    val doublyDiminished = Interval.minor(3).diminished.diminished

    val actual = doublyDiminished.diminished

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  fun `returns augmented sixth when augmenting major sixth`() {
    val expected = "augmented sixth"
    val major = Interval.major(6)

    val actual = major.augmented

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @ParameterizedTest
  @ValueSource(ints = [1, 4, 5, 8])
  fun `does not instantiate major interval`(degree: Int) {
    assertThrows<IllegalArgumentException> { Interval.major(degree) }
  }

  @Test
  fun `instantiates perfect unison`() {
    val expected = "perfect unison"

    val actual = Interval.perfect(1)

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  fun `returns diminished fourth when diminishing perfect fourth`() {
    val expected = "diminished fourth"
    val perfect = Interval.perfect(4)

    val actual = perfect.diminished

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  fun `returns doubly diminished fifth when diminishing diminished fifth`() {
    val expected = "doubly diminished fifth"
    val diminished = Interval.perfect(5).diminished

    val actual = diminished.diminished

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  fun `returns 3x diminished octave when diminishing doubly diminished octave` () {
    val expected = "3x diminished octave"
    val doublyDiminished = Interval.perfect(8).diminished.diminished

    val actual = doublyDiminished.diminished

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  fun `returns augmented unison when augmenting perfect unison` () {
    val expected = "augmented unison"
    val perfect = Interval.perfect(1)

    val actual = perfect.augmented

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @ParameterizedTest
  @ValueSource(ints = [2, 3, 6, 7])
  fun `does not instantiate perfect interval`(degree: Int) {
    assertThrows<IllegalArgumentException>{ Interval.perfect(degree)}
  }

  @Test
  fun `returns doubly augmented second when augmenting augmented second` () {
    val expected = "doubly augmented second"
    val augmented = Interval.major(2).augmented

    val actual = augmented.augmented

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  fun `returns 3x augmented fourth when augmenting augmented fourth` () {
    val expected = "3x augmented fourth"
    val doublyAugmented = Interval.perfect(4).augmented.augmented

    val actual = doublyAugmented.augmented

    assertThat(actual.displayString).isEqualTo(expected)
  }

  @Test
  fun `returns equal when equivalent` () {
    fun getInterval() = Interval(6, 69)

    val first = getInterval()
    val second = getInterval()

    assertThat(first).isEqualTo(second)
  }
}*/
