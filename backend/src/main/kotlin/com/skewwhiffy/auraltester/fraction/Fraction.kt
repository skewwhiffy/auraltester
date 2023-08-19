package com.skewwhiffy.auraltester.fraction

data class Fraction(val top: Int, val bottom: Int) {
    init {
        if (bottom == 0) {
            throw ArithmeticException("Division by 0")
        }
    }

    constructor(int: Int) : this(int, 1)

    constructor(whole: Int, top: Int, bottom: Int) : this(whole * bottom + top, bottom)

    operator fun plus(that: Fraction): Any {
        val newTop = this.top * that.bottom + this.bottom * that.top
        val newBottom = this.bottom * that.bottom
        return Fraction(newTop, newBottom).simplified
    }

    operator fun minus(that: Fraction): Any {
        return this + (-that)
    }

    operator fun unaryMinus(): Fraction {
        return Fraction(-top, bottom).simplified
    }

    operator fun times(i: Int): Fraction {
        return Fraction(top * i, bottom).simplified
    }

    operator fun times(that: Fraction): Fraction {
        return this * that.top / that.bottom
    }

    operator fun div(i: Int): Fraction {
        return Fraction(top, bottom * i).simplified
    }

    val simplified: Fraction
        get() {
            if (bottom < 0) {
                return Fraction(-top, -bottom).simplified
            }
            if (top == 0) {
                return Fraction(0, 1)
            }
            val hcf = Factors.hcf(top, bottom)
            if (hcf == 1) {
                return this
            }
            return Fraction(top / hcf, bottom / hcf)
        }

    val topHeavyString: String
        get() {
            return "$top/$bottom"
        }

    override fun toString(): String {
        val wholePart = top / bottom
        return if (wholePart == 0) topHeavyString else "$wholePart ${top - wholePart * bottom}/$bottom"
    }

    override fun equals(other: Any?): Boolean {
        return when(other) {
            null -> false
            is Int -> this == Fraction(other, 1)
            is Fraction -> this.simplified.let { current ->
                other.simplified.let { current.top == it.top && current.bottom == it.bottom }
            }
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = top
        result = 31 * result + bottom
        return result
    }
}