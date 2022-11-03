package com.skewwhiffy.auraltester.fractions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class FractionTest extends AnyFlatSpec with should.Matchers {
  it should "can instantiate simple fraction" in {
    val expected = "1/2"

    val actual = Fraction(1, 2)

    assert(actual.toString == expected)
  }

  it should "can instantiate mixed fraction" in {
    val expected = "1 2/3"

    val actual = Fraction(1, 2, 3)

    assert(actual.toString == expected)
  }

  it should "can add fractions" in {
    val expected = "11/12"

    val actual = Fraction(3, 4) + Fraction(1, 6)

    assert(actual.toString == expected)
  }

  it should "can subtract fractions" in {
    val expected = "1/2"

    val actual = Fraction(3, 4) - Fraction(1, 4)

    assert(actual.toString == expected)
  }

  it should "can multiply fractions" in {
    val expected = "3/8"

    val actual = Fraction(1, 4) * Fraction(3, 2)

    assert(actual.toString == expected)
  }

  it should "integer fractions are equal to integer" in {
    val source = Fraction(4, 2)

    //noinspection ComparingUnrelatedTypes
    assert(source == 2)
  }

  it should "equivalent fractions are equal" in {
    val first = Fraction(2, 4)
    val second = Fraction(-3, -6)

    assert(first == second)
  }
}