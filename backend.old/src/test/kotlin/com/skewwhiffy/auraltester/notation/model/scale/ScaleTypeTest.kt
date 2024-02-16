package com.skewwhiffy.auraltester.notation.model.scale

import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval
import com.skewwhiffy.auraltester.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ScaleTypeTest {
    private lateinit var ascendingIntervals: List<DirectedInterval>
    private lateinit var descendingIntervals: List<DirectedInterval>
    private lateinit var scaleType: ScaleType

    @BeforeEach
    fun setUp() {
        val displayName = TestData.random.string
        val abc = TestData.random.string
        ascendingIntervals = (1..6).map { TestData.random.directedInterval }
        descendingIntervals = (1..8).map { TestData.random.directedInterval }
        scaleType = ScaleType(displayName, abc, ascendingIntervals, descendingIntervals)
    }

    @Test
    fun when_scaleIsAscending_then_returnsAscendingIntervals() {
        val actual = scaleType.getIntervals(ScaleDirection.ASCENDING)
        assertThat(actual).isEqualTo(ascendingIntervals)
    }

    @Test
    fun when_scaleIsDescending_then_returnsDescendingIntervals() {
        val actual = scaleType.getIntervals(ScaleDirection.DESCENDING)
        assertThat(actual).isEqualTo(descendingIntervals)
    }
}
