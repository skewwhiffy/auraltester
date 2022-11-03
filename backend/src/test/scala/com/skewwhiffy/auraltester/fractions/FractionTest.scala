package com.skewwhiffy.auraltester.fractions

import org.scalatest.flatspec.AnyFlatSpec

class FractionTest extends AnyFlatSpec {
  it should "instantiate simple fraction" in {
    val expected = "1/2"

    val actual = Fraction(1, 2)

    assert(actual.toString == expected)
  }

  it should "instantiate mixed fraction" in {
    val expected = "1 2/3"

    val actual = Fraction(1, 2, 3)

    assert(actual.toString == expected)
  }

  it should "add fractions" in {
    val expected = "11/12"

    val actual = Fraction(3, 4) + Fraction(1, 6)

    assert(actual.toString == expected)
  }

  it should "subtract fractions" in {
    val expected = "1/2"

    val actual = Fraction(3, 4) - Fraction(1, 4)

    assert(actual.toString == expected)
  }

  it should "multiply fractions" in {
    val expected = "3/8"

    val actual = Fraction(1, 4) * Fraction(3, 2)

    assert(actual.toString == expected)
  }

  it should "equate integer fractions to integers" in {
    val source = Fraction(4, 2)

    //noinspection ComparingUnrelatedTypes
    assert(source == 2)
  }

  it should "equate equivalent fractions" in {
    val first = Fraction(2, 4)
    val second = Fraction(-3, -6)

    assert(first == second)
  }
}