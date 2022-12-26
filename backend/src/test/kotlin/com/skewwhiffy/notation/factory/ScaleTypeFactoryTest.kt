package com.skewwhiffy.notation.factory

import com.skewwhiffy.notation.model.interval.DirectedInterval
import com.skewwhiffy.notation.model.scale.ScaleDirection
import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ScaleTypeFactoryTest {
  @Mock
  private lateinit var intervalFactory: IntervalFactory

  @InjectMocks
  private lateinit var scaleTypeFactory: ScaleTypeFactory
  private lateinit var expectedIntervals: List<DirectedInterval>
  private lateinit var expectedDescendingIntervals: List<DirectedInterval>

  @BeforeEach
  fun `set up`() {
    fun getIntervals() = (0..10).map { TestData.random.directedInterval}
    expectedIntervals = getIntervals()
    expectedDescendingIntervals = getIntervals()
  }

  @Test
  fun `instantiates major scale`() {
    `when`(intervalFactory.getDirectedIntervals("1 2 3 4 5 6 7 8"))
      .thenReturn(expectedIntervals)

    val actual = scaleTypeFactory.major

    ScaleDirection
      .values()
      .forEach { assertThat(actual.intervals(it)).isEqualTo(expectedIntervals) }
  }

  @Test
  fun `instantiates minor scale`() {
    `when`(intervalFactory.getDirectedIntervals("1 2 3- 4 5 6- 7 8"))
      .thenReturn(expectedIntervals)

    val actual = scaleTypeFactory.minorHarmonic

    ScaleDirection
      .values()
      .forEach { assertThat(actual.intervals(it)).isEqualTo(expectedIntervals) }
  }

  @Test
  fun `instantiates minor melodic scale`() {
    `when`(intervalFactory.getDirectedIntervals("1 2 3- 4 5 6 7 8"))
      .thenReturn(expectedIntervals)
    `when`(intervalFactory.getDirectedIntervals("1 2 3- 4 5 6- 7- 8"))
      .thenReturn(expectedDescendingIntervals)

    val actual = scaleTypeFactory.minorMelodic

    assertThat(actual.intervals(ScaleDirection.ASCENDING)).isEqualTo(expectedIntervals)
    assertThat(actual.intervals(ScaleDirection.DESCENDING)).isEqualTo(expectedDescendingIntervals)
  }
}