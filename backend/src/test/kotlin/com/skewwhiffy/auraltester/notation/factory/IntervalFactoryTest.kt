package com.skewwhiffy.auraltester.notation.factory

import com.skewwhiffy.auraltester.notation.model.interval.Interval
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(MockKExtension::class)
internal class IntervalFactoryTest {
    @InjectMockKs
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
}
