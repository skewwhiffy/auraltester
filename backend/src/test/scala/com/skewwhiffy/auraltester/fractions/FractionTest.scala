package com.skewwhiffy.auraltester.fractions

import org.scalatest.funsuite.AnyFunSuite

class FractionTest extends AnyFunSuite {
  test("can instantiate simple fraction") {
    val expected = "1/2"

    val actual = Fraction(1, 2)

    assert(actual.toString == expected)
  }

  test("can instantiate mixed fraction") {
    val expected = "1 2/3"

    val actual = Fraction(1, 2, 3)

    assert(actual.toString == expected)
  }

  test("can add fractions") {
    val expected = "11/12"

    val actual = Fraction(3, 4) + Fraction(1, 6)

    assert(actual.toString == expected)
  }

  test("can subtract fractions") {
    val expected = "1/2"

    val actual = Fraction(3, 4) - Fraction(1, 4)

    assert(actual.toString == expected)
  }

  test("can multiply fractions") {
    val expected = "3/8"

    val actual = Fraction(1, 4) * Fraction(3, 2)

    assert(actual.toString == expected)
  }

  test("integer fractions are equal to integer") {
    val source = Fraction(4, 2)

    //noinspection ComparingUnrelatedTypes
    assert(source == 2)
  }

  test("equivalent fractions are equal") {
    val first = Fraction(2, 4)
    val second = Fraction(-3, -6)

    assert(first == second)
  }
}