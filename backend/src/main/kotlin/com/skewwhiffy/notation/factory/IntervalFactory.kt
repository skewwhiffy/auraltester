package com.skewwhiffy.notation.factory

import com.skewwhiffy.notation.model.interval.DirectedInterval
import com.skewwhiffy.notation.model.interval.Interval
import org.springframework.stereotype.Service

@Service
class IntervalFactory {
  fun getDirectedIntervals(rawIntervals: String) = rawIntervals
    .split(' ')
    .map(::getDirectedInterval)
    .toList()

  fun getDirectedInterval(rawInterval: String): DirectedInterval =
    getRawDeviations(rawInterval)
      .toCharArray()
      .fold(getBaseInterval(rawInterval)) { current, it ->
        when (it) {
          '-' -> current.diminished
          '+' -> current.augmented
          else -> throw IllegalArgumentException("Not a valid deviation: '$it'")
        }
      }
      .let {
        when (getRawDirection(rawInterval)) {
          "-" -> it.down
          "+" -> it.up
          "" -> it.up
          else -> throw IllegalArgumentException("Not a valid direction: '$it''")
        }
      }

  private fun getBaseInterval(rawInterval: String): Interval = getRawInt(rawInterval)
    .toInt()
    .let { if (listOf(1, 4, 5, 8).contains(it)) Interval.perfect(it) else Interval.major(it) }

  private fun getRawDirection(rawInterval: String): String = "+-"
    .toCharArray()
    .find { rawInterval.startsWith(it) }
    ?.toString() ?: ""

  private fun getRawInt(rawInterval: String): String {
    fun getRawInt(soFar: String, remaining: String): String = try {
      remaining
        .substring(0, 1)
        .toInt()
        .let { "$soFar$it" }
        .let { getRawInt(it, remaining.substring(1)) }
    } catch (ex: NumberFormatException) {
      soFar
    } catch (ex: StringIndexOutOfBoundsException) {
      soFar
    }

    return if (rawInterval.startsWith("+") || rawInterval.startsWith("-")) getRawInt("", rawInterval.substring(1))
    else getRawInt("", rawInterval)
  }

  private fun getRawDeviations(rawInterval: String): String = rawInterval
    .substring(getRawInt(rawInterval).length + getRawDirection(rawInterval).length)
}