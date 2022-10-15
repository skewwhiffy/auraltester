package com.skewwhiffy.auraltester.abc

import com.skewwhiffy.auraltester.notes.Interval

import scala.annotation.tailrec
import scala.util.chaining.*

class IntervalFactory(private val abc: String) {
  lazy val interval: Interval = rawDeviations
    .toCharArray
    .foldRight(baseInterval)((it, current) => it match
      case '-' => current.diminished
      case '+' => current.augmented
      case _ => throw IllegalArgumentException(s"Not a valid deviation: '$it'")
    )

  private lazy val baseInterval: Interval = rawInt.toInt.pipe(it => it match
    case 1 | 4 | 5 | 8 => Interval.perfect(it)
    case _ => Interval.major(it)
  )

  private lazy val rawInt: String = {
    def getRawInt(soFar: String, remaining: String): String = {
      try {
        remaining
          .substring(0, 1)
          .toInt
          .pipe(it => s"$soFar$it")
          .pipe(it => getRawInt(it, remaining.substring(1)))
      } catch {
        case _: NumberFormatException => soFar
        case _: StringIndexOutOfBoundsException => soFar
      }
    }

    getRawInt("", abc)
  }

  private lazy val rawDeviations: String = abc.substring(rawInt.length)
}
