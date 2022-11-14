package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.notes.{DirectedInterval, Interval}

import scala.util.chaining.scalaUtilChainingOps

class IntervalFactory {
  def getDirectedIntervals(rawIntervals: String): List[DirectedInterval] = rawIntervals
    .split(' ')
    .map(getDirectedInterval)
    .toList

  def getDirectedInterval(rawInterval: String): DirectedInterval = {
    getRawDeviations(rawInterval)
      .toCharArray
      .foldRight(getBaseInterval(rawInterval))((it, current) => it match {
        case '-' => current.diminished
        case '+' => current.augmented
        case _ => throw new IllegalArgumentException(s"Not a valid deviation: '$it'")
      })
      .pipe(it => {
        getRawDirection(rawInterval) match {
          case "-" => it.down
          case "+" | "" => it.up
          case it => throw new IllegalArgumentException(s"Not a valid direction: '$it''")
        }
      })
  }

  private def getBaseInterval(rawInterval: String): Interval = getRawInt(rawInterval).toInt.pipe {
    case it@(1 | 4 | 5 | 8) => Interval.perfect(it)
    case it => Interval.major(it)
  }

  private def getRawDirection(rawInterval: String) = rawInterval match {
    case it if it.startsWith("+") => "+"
    case it if it.startsWith("-") => "-"
    case _ => ""
  }

  private def getRawInt(rawInterval: String) = {
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

    if (rawInterval.startsWith("+") || rawInterval.startsWith("-")) getRawInt("", rawInterval.substring(1))
    else getRawInt("", rawInterval)
  }

  private def getRawDeviations(rawInterval: String): String = rawInterval
    .substring(getRawInt(rawInterval).length + getRawDirection(rawInterval).length)
}
