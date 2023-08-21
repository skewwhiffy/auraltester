package com.skewwhiffy.auraltester.notation.factory

import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval
import com.skewwhiffy.auraltester.notation.model.interval.Interval
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class IntervalFactory {
    /*
    public List<DirectedInterval> getDirectedIntervals(String rawIntervals) {
        return Arrays.stream(rawIntervals.split(" "))
            .map(this::getDirectedInterval)
            .toList();
    }
    */

    fun getDirectedInterval(rawInterval: String): DirectedInterval {
        val interval: Interval = getRawDeviations(rawInterval)
            .fold(getBaseInterval(rawInterval)) { current, it ->
                when (it) {
                    '-' -> current.diminished
                    '+' -> current.augmented
                    else -> throw IllegalArgumentException("Not a valid deviation: '$it'")
                }
            }
        getRawDirection(rawInterval).let {
            return when (it) {
                "-" -> interval.down
                "+", "" -> interval.up
                else -> throw IllegalArgumentException("Not a valid direction: '$it''")
            }
        }
    }

    private fun getBaseInterval(rawInterval: String) = getRawInt(rawInterval)
        .let { Integer.parseInt(it) }
        .let { if (Interval.perfectDegrees.contains(it)) Interval.perfect(it) else Interval.major(it) }

    private fun getRawDirection(rawInterval: String) =
        listOf("+", "-").firstOrNull { rawInterval.startsWith(it) } ?: ""

    private fun getRawInt(rawInterval: String) =
        if (rawInterval.startsWith("+") || rawInterval.startsWith("-"))
            getRawInt("", rawInterval.substring(1))
        else getRawInt("", rawInterval)

    private fun getRawInt(soFar: String, remaining: String): String = try {
        getRawInt(soFar + Integer.parseInt(remaining.substring(0, 1)), remaining.substring(1))
    } catch (ex: Exception) {
        when (ex) {
            is NumberFormatException, is StringIndexOutOfBoundsException -> soFar
            else -> throw ex
        }
    }

    private fun getRawDeviations(rawInterval: String) =
        rawInterval[getRawInt(rawInterval).length + getRawDirection(rawInterval).length].toString()
}
