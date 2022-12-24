package com.skewwhiffy.notation.model.interval

data class Interval(val degree: Int, val deviation: Int) {
  companion object {
    private val displayStrings: List<String> = listOf(
      "unison",
      "second",
      "third",
      "fourth",
      "fifth",
      "sixth",
      "seventh",
      "octave"
    )

    private val perfectDegrees: Set<Int> = setOf(1, 4, 5, 8)

    fun augmented(degree: Int): Interval {
      val baseInterval = if (perfectDegrees.contains(degree)) perfect(degree) else major(degree)
      return baseInterval.augmented
    }

    fun diminished(degree: Int): Interval {
      val baseInterval = if (perfectDegrees.contains(degree)) perfect(degree) else minor(degree)
      return baseInterval.diminished
    }

    fun minor(degree: Int): Interval = major(degree).diminished

    fun major(degree: Int): Interval {
      if (perfectDegrees.contains(degree)) throw IllegalArgumentException("Cannot instantiate major interval of degree '$degree'")
      return Interval(degree, 0)
    }

    fun perfect(degree: Int): Interval {
      if (!perfectDegrees.contains(degree)) throw IllegalArgumentException("Cannot instantiate perfect interval of degree '$degree'")
      return Interval(degree, 0)
    }
  }

  val diminished: Interval get() = Interval(degree, deviation - 1)

  val augmented: Interval get() = Interval(degree, deviation + 1)

  val displayString: String get() = "$quality ${displayStrings[degree - 1]}"

  val up: DirectedInterval = DirectedInterval(this, IntervalDirection.UP)

  val down: DirectedInterval = DirectedInterval(this, IntervalDirection.DOWN)

  private val quality: String
    get() {
      val defaultQuality = if (perfectDegrees.contains(degree)) "perfect" else "major"
      val negativeQuality =
        if (!perfectDegrees.contains(degree) && deviation == -1) "minor"
        else (if (perfectDegrees.contains(degree)) -deviation else -deviation - 1).let {
          when (it) {
            1 -> "diminished"
            2 -> "doubly diminished"
            else -> "${it}x diminished"
          }
        }

      val positiveQuality = when (deviation) {
        1 -> ""
        2 -> "doubly "
        else -> "${deviation}x "
      } + "augmented"

      return when {
        deviation < 0 -> negativeQuality
        deviation > 0 -> positiveQuality
        else -> defaultQuality
      }
    }

  override fun toString(): String = displayString
}
