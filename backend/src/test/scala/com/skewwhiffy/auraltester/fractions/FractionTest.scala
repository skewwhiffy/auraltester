package com.skewwhiffy.auraltester.fractions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FractionTest {
  @Test
  def canInstantiateSimpleFraction(): Unit = {
    val expected = "1/2"

    val actual = Fraction(1, 2)

    assertThat(actual.toString).isEqualTo(expected)
  }

  @Test
  def canInstantiateMixedFraction(): Unit = {
    val expected = "1 2/3"

    val actual = Fraction(1, 2, 3)

    assertThat(actual.toString).isEqualTo(expected)
  }

  @Test
  def canAddFractions(): Unit = {
    val expected = "11/12"

    val actual = Fraction(3, 4) + Fraction(1, 6)

    assertThat(actual.toString).isEqualTo(expected)
  }

  @Test
  def canSubtractFractions(): Unit = {
    val expected = "1/2"

    val actual = Fraction(3, 4) - Fraction(1, 4)

    assertThat(actual.toString).isEqualTo(expected)
  }

  @Test
  def canMultiplyFractions(): Unit = {
    val expected = "3/8"

    val actual = Fraction(1, 4) * Fraction(3, 2)

    assertThat(actual.toString).isEqualTo(expected)
  }

  @Test
  def integerFractionsAreEqualToInteger(): Unit = {
    val source = Fraction(4, 2)

    assertThat(source).isEqualTo(2)
  }

  @Test
  def equivalentFractionsAreEqual(): Unit = {
    val first = Fraction(2, 4)
    val second = Fraction(-3, -6)

    assertThat(first).isEqualTo(second)
  }
}