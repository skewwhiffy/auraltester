package com.skewwhiffy.auraltester.notation.factory

import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval
import com.skewwhiffy.auraltester.notation.model.scale.ScaleDirection
import com.skewwhiffy.auraltester.test.util.TestData
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class ScaleTypeFactoryTest {
    @MockK
    private lateinit var intervalFactory: IntervalFactory

    @InjectMockKs
    private lateinit var scaleTypeFactory: ScaleTypeFactory
    private lateinit var expectedIntervals: List<DirectedInterval>
    private lateinit var expectedDescendingIntervals: List<DirectedInterval>

    @BeforeEach
    fun setUp() {
        val getIntervals: () -> List<DirectedInterval> = { (1..9).map { TestData.random.directedInterval } }
        expectedIntervals = getIntervals()
        expectedDescendingIntervals = getIntervals()
    }

    @Test
    fun instantiatesMajorScale() {
        every { intervalFactory.getDirectedIntervals("1 2 3 4 5 6 7 8") } returns expectedIntervals
        val actual = scaleTypeFactory.major
        ScaleDirection.entries.forEach { assertThat(actual.getIntervals(it)).isEqualTo(expectedIntervals) }
    }

    @Test
    fun instantiatesMinorScale() {
        every { intervalFactory.getDirectedIntervals("1 2 3- 4 5 6- 7 8") } returns expectedIntervals
        val actual = scaleTypeFactory.minorHarmonic
        ScaleDirection.entries.forEach { assertThat(actual.getIntervals(it)).isEqualTo(expectedIntervals) }
    }

    @Test
    fun instantiatesMinorMelodicScale() {
        every { intervalFactory.getDirectedIntervals("1 2 3- 4 5 6 7 8") } returns expectedIntervals
        every { intervalFactory.getDirectedIntervals("1 2 3- 4 5 6- 7- 8") } returns expectedDescendingIntervals
        val actual = scaleTypeFactory.minorMelodic
        assertThat(actual.getIntervals(ScaleDirection.ASCENDING)).isEqualTo(expectedIntervals)
        assertThat(actual.getIntervals(ScaleDirection.DESCENDING)).isEqualTo(expectedDescendingIntervals)
    }
}
