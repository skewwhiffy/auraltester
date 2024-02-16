package com.skewwhiffy.auraltester.notation.factory

import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval
import com.skewwhiffy.auraltester.notation.model.interval.Interval
import com.skewwhiffy.auraltester.notation.model.interval.IntervalDirection
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class IntervalFactoryTest {
    @Autowired
    private lateinit var intervalFactory: IntervalFactory

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 6, 7])
    fun instantiatesMajorInterval(intervalDegree: Int) {
        val expected = Interval.major(intervalDegree).up
        val actual = intervalFactory.getDirectedInterval(intervalDegree.toString())
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun when_deviationInvalid_then_throws() {
        Assertions.assertThatThrownBy { intervalFactory.getDirectedInterval("5*") }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 4, 5, 8])
    fun instantiatesPerfectInterval(degree: Int) {
        val expected = Interval.perfect(degree).up
        val actual = intervalFactory.getDirectedInterval(degree.toString())
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun instantiatesMinorThird() {
        val expected = Interval.minor(3).up
        val actual = intervalFactory.getDirectedInterval("3-")
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun instantiatesAugmentedFourth() {
        val expected = Interval.augmented(4).up
        val actual = intervalFactory.getDirectedInterval("4+")
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun isConsistentWithIntervalToString() {
        (1..8)
            .flatMap { degree ->
                val deviations = if (listOf(1, 4, 5, 8).contains(degree)) -1..1 else -2..1
                deviations.map { Interval(degree, it) }
            }
            .forEach { interval ->
                val toStringResult = interval.toString()
                val reconstituted = intervalFactory.getDirectedInterval(toStringResult)
                assertThat(reconstituted).isEqualTo(DirectedInterval(interval, IntervalDirection.UP))
            }
    }

    @Test
    fun isConsistentWithDirectedIntervalToString() {
        (1..8)
            .flatMap { degree ->
                val deviations = if (listOf(1, 4, 5, 8).contains(degree)) -1..1 else -2..1
                deviations.map { Interval(degree, it) }
            }
            .flatMap { interval -> IntervalDirection.entries.map { DirectedInterval(interval, it)} }
            .forEach { directedInterval ->
                val toStringResult = directedInterval.toString()
                val reconstituted = intervalFactory.getDirectedInterval(toStringResult)
                assertThat(reconstituted).isEqualTo(directedInterval)
            }
    }
}
