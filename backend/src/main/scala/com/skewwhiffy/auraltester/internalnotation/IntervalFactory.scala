package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.notes.Interval.Interval
import com.skewwhiffy.auraltester.notes.{DirectedInterval, Interval}

import scala.util.chaining.scalaUtilChainingOps

class IntervalFactory(private val abc: String) {
  lazy val directedInterval: DirectedInterval = rawDeviations
    .toCharArray
    .foldRight(baseInterval)((it, current) => it match {
      case '-' => current.diminished
      case '+' => current.augmented
      case _ => throw new IllegalArgumentException(s"Not a valid deviation: '$it'")
    })
    .pipe(it => {
      rawDirection match {
        case "-" => it.down
        case "+" | "" => it.up
        case _ => throw new IllegalArgumentException(s"Not a valid direction: '$rawDirection''")
      }
    })

  private lazy val baseInterval: Interval = rawInt.toInt.pipe {
    case it@(1 | 4 | 5 | 8) => Interval.perfect(it)
    case it => Interval.major(it)
  }

  private lazy val rawDirection: String = abc match {
    case it if it.startsWith("+") => "+"
    case it if it.startsWith("-") => "-"
    case _ => ""
  }

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

    if (abc.startsWith("+") || abc.startsWith("-")) getRawInt("", abc.substring(1))
    else getRawInt("", abc)
  }

  private lazy val rawDeviations: String = abc.substring(rawInt.length + rawDirection.length)
}
