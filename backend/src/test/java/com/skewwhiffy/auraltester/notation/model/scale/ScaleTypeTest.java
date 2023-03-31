/*
package com.skewwhiffy.notation.model.scale

import com.skewwhiffy.notation.model.interval.DirectedInterval
import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ScaleTypeTest {
  private lateinit var displayName: String
  private lateinit var abc: String
  private lateinit var ascendingIntervals: List<DirectedInterval>
  private lateinit var descendingIntervals: List<DirectedInterval>
  private lateinit var scaleType: ScaleType

  @BeforeEach
  fun `set up`() {
    displayName = TestData.random.string
    abc = TestData.random.string
    ascendingIntervals = (0..5).map { TestData.random.directedInterval }
    descendingIntervals = (1..7).map { TestData.random.directedInterval }
    scaleType = ScaleType(displayName, abc, ascendingIntervals, descendingIntervals)
  }

  @Test
  fun `when scale is ascending then returns ascending intervals`() {
    val expected = ascendingIntervals

    val actual = scaleType.intervals(ScaleDirection.ASCENDING)

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `when scale is descending then returns descending intervals`() {
    val expected = descendingIntervals

    val actual = scaleType.intervals(ScaleDirection.DESCENDING)

    assertThat(actual).isEqualTo(expected)
  }
}*/
