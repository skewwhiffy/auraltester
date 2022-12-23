package com.skewwhiffy.notation.model.interval

data class Interval(val degree: Int, val deviation: Int) {
  companion object {
    /*
    lazy val displayStrings: List[String] = List(
      "unison",
      "second",
      "third",
      "fourth",
      "fifth",
      "sixth",
      "seventh",
      "octave"
    )

  */
    private val perfectDegrees: Set<Int> = setOf(1, 4, 5, 8)

    /*
  lazy val augmented: Int => Interval = degree => {
    val baseInterval = if (perfectDegrees.contains(degree)) perfect(degree) else major(degree)
    baseInterval.augmented
  }

  lazy val diminished: Int => Interval = degree => {
    val baseInterval = if (perfectDegrees.contains(degree)) perfect(degree) else minor(degree)
    baseInterval.diminished
  }

*/
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

  /*
  lazy val displayString: String = s"$quality ${displayStrings(degree - 1)}"
  */

  val up: DirectedInterval = DirectedInterval(this, IntervalDirection.UP)

  val down: DirectedInterval = DirectedInterval(this, IntervalDirection.DOWN)

  /*
  private def quality: String = {
    lazy val defaultQuality = if (perfectDegrees.contains(degree)) "perfect" else "major"
    lazy val negativeQuality = {
      if (!perfectDegrees.contains(degree) && deviation == -1) "minor"
      else {
        (if (perfectDegrees.contains(degree)) -deviation else -deviation - 1) match {
          case 1 => "diminished"
          case 2 => "doubly diminished"
          case it => s"${it}x diminished"
        }
      }
    }
    lazy val positiveQuality = (deviation match {
      case 1 => ""
      case 2 => "doubly "
      case it => s"${it}x "
    }) + "augmented"

    deviation match {
      case 0 => defaultQuality
      case it if it < 0 => negativeQuality
      case it if it > 0 => positiveQuality
    }
  }

  override def equals(obj: Any): Boolean = obj match {
    case other: Interval => other.deviation == deviation && other.degree == degree
    case _ => false
  }

  override def toString: String = displayString
}
   */
}