package com.skewwhiffy.fraction

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FractionTest {
  @Test
  fun `instantiates simple fraction`() {
    val expected = "1/2"

    val actual = Fraction(1, 2)

    assertThat(actual.toString()).isEqualTo(expected)
  }

  @Test
  fun `instantiates mixed fraction`() {
    val expected = "1 2/3"

    val actual = Fraction(1, 2, 3)

    assertThat(actual.toString()).isEqualTo(expected)
  }

  @Test
  fun `adds fractions`() {
    val expected = "11/12"

    val actual = Fraction(3, 4) + Fraction(1, 6)

    assertThat(actual.toString()).isEqualTo(expected)
  }

  @Test
  fun `subtracts fractions`() {
    val expected = "1/2"

    val actual = Fraction(3, 4) - Fraction(1, 4)

    assertThat(actual.toString()).isEqualTo(expected)
  }

  @Test
  fun `multiplies fractions`() {
    val expected = "3/8"

    val actual = Fraction(1, 4) * Fraction(3, 2)

    assertThat(actual.toString()).isEqualTo(expected)
  }

  @Test
  fun `equates integer fractions to integers`() {
    val source = Fraction(4, 2)

    @Suppress("AssertBetweenInconvertibleTypes")
    assertThat(source).isEqualTo(2)
  }

  @Test
  fun `equates equivalent fractions`() {
    val first = Fraction(2, 4)
    val second = Fraction(-3, -6)

    assertThat(first).isEqualTo(second)
  }
}