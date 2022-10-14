package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.notes.Interval.{displayStrings, perfectDegrees}

import java.util

object Interval {
  val displayStrings: List[String] = List(
    "unison",
    "second",
    "third",
    "fourth",
    "fifth",
    "sixth",
    "seventh",
    "octave"
  )

  val perfectDegrees: Set[Int] = Set(1, 4, 5, 8)

  def augmented(degree: Int): Interval = {
    val baseInterval = if perfectDegrees.contains(degree) then perfect(degree) else major(degree)
    baseInterval.augmented
  }

  def diminished(degree: Int): Interval = {
    val baseInterval = if perfectDegrees.contains(degree) then perfect(degree) else minor(degree)
    baseInterval.diminished
  }

  def minor(degree: Int): Interval = {
    major(degree).diminished
  }

  def major(degree: Int): Interval = {
    if (perfectDegrees.contains(degree)) {
      throw new IllegalArgumentException(s"Cannot instantiate major interval of degree '$degree'")
    }
    Interval(degree, 0)
  }

  def perfect(degree: Int): Interval = {
    if (!perfectDegrees.contains(degree)) {
      throw new IllegalArgumentException(s"Cannot instantiate perfect interval of degree '$degree'")
    }
    new Interval(degree, 0)
  }
}

private class Interval(val degree: Int, val deviation: Int) {
  lazy val diminished: Interval = Interval(degree, deviation - 1)

  lazy val augmented: Interval = Interval(degree, deviation + 1)

  lazy val displayString: String = s"$quality ${displayStrings(degree - 1)}"

  private def quality: String = {
    if (deviation == 0) {
      return if perfectDegrees.contains(degree) then "perfect" else "major"
    }
    if (deviation == -1 && !perfectDegrees.contains(degree)) {
      return "minor"
    }
    if (deviation < 0) {
      val diminishedDegree = if perfectDegrees.contains(degree) then -deviation else -deviation - 1
      if (diminishedDegree == 1) {
        return "diminished"
      }
      if (diminishedDegree == 2) {
        return "doubly diminished"
      }
      return s"${diminishedDegree}x diminished"
    }
    if (deviation == 1) {
      return "augmented"
    }
    if (deviation == 2) {
      return "doubly augmented"
    }
    s"${deviation}x augmented"
  }

}
