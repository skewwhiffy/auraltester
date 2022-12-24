package com.skewwhiffy.fraction

data class Fraction(val top: Int, val bottom: Int) {
  companion object {
    fun lcm(first: Int, second: Int): Int {
      return when {
        first < 0 -> lcm(-first, second)
        second < 0 -> lcm(first, -second)
        first < second -> lcm(second, first)
        second == 0 -> throw ArithmeticException("Cannot calculate lcm for 0")
        else -> (1..second).find { (first * it) % second == 0 }.let {
          if (it == null) throw ArithmeticException("Rules of mathematics have changed")
          first * it
        }
      }
    }

    fun hcf(first: Int, second: Int): Int {
      return first * second / lcm(first, second)
    }
  }

  init {
    if (bottom == 0) throw ArithmeticException("/ by zero")
  }

  constructor(whole: Int, top: Int, bottom: Int) : this(whole * bottom + top, bottom) {
    if (bottom < 0) throw ArithmeticException("Cannot instantiate mixed fraction with negative denominator")
  }

  private val simplified: Fraction
    get() = when {
      bottom < 0 -> Fraction(-top, -bottom).simplified
      top == 0 -> Fraction(0, 1)
      else -> hcf(top, bottom).let {
        when (it) {
          1 -> this
          else -> Fraction(top / it, bottom / it)
        }
      }
    }

  private val whole = top / bottom
  private val topWithoutWhole = top - (whole * bottom)

  operator fun plus(other: Fraction): Fraction {
    val lcd = lcm(bottom, other.bottom)
    val newTop = top * (lcd / bottom) + other.top * (lcd / other.bottom)
    return Fraction(newTop, lcd).simplified
  }

  operator fun minus(other: Fraction): Fraction {
    return this + -other
  }

  operator fun unaryMinus(): Fraction {
    return Fraction(-top, bottom)
  }

  operator fun times(other: Fraction): Fraction {
    return Fraction(top * other.top, bottom * other.bottom).simplified
  }

  val topHeavyString: String = if (bottom == 1) "$top" else "$top/$bottom"

  override fun toString(): String {
    return (if (whole == 0) "" else "$whole ")
      .let { "$it$topWithoutWhole/$bottom" }
  }

  override fun equals(other: Any?): Boolean {
    return other?.let {
      when (it) {
        is Int -> this == Fraction(it, 1)
        is Fraction -> simplified.top == it.simplified.top && simplified.bottom == it.simplified.bottom
        else -> false
      }
    } ?: false
  }

  override fun hashCode(): Int {
    var result = top
    result = 31 * result + bottom
    return result
  }
}