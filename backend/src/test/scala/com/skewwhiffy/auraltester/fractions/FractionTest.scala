package com.skewwhiffy.auraltester.fractions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FractionTest:
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
