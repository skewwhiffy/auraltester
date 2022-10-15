package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.notes.Interval.{displayStrings, perfectDegrees}

import java.util

object Interval {
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

  lazy val perfectDegrees: Set[Int] = Set(1, 4, 5, 8)

  lazy val augmented: Int => Interval = degree => {
    val baseInterval = if perfectDegrees.contains(degree) then perfect(degree) else major(degree)
    baseInterval.augmented
  }

  lazy val diminished: Int => Interval = degree => {
    val baseInterval = if perfectDegrees.contains(degree) then perfect(degree) else minor(degree)
    baseInterval.diminished
  }

  lazy val minor: Int => Interval = degree => major(degree).diminished

  lazy val major: Int => Interval = degree => {
    if (perfectDegrees.contains(degree)) {
      throw new IllegalArgumentException(s"Cannot instantiate major interval of degree '$degree'")
    }
    Interval(degree, 0)
  }

  lazy val perfect: Int => Interval = degree => {
    if (!perfectDegrees.contains(degree)) {
      throw new IllegalArgumentException(s"Cannot instantiate perfect interval of degree '$degree'")
    }
    new Interval(degree, 0)
  }
}

class Interval(val degree: Int, val deviation: Int) {
  lazy val diminished: Interval = Interval(degree, deviation - 1)

  lazy val augmented: Interval = Interval(degree, deviation + 1)

  lazy val displayString: String = s"$quality ${displayStrings(degree - 1)}"

  private def quality: String = {
    lazy val defaultQuality = if perfectDegrees.contains(degree) then "perfect" else "major"
    lazy val negativeQuality = {
      if (!perfectDegrees.contains(degree) && deviation == -1) "minor"
      else {
        (if perfectDegrees.contains(degree) then -deviation else -deviation - 1) match {
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
}
