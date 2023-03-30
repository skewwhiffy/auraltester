package com.skewwhiffy.auraltester.notation.factory;

/*
import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval;
import com.skewwhiffy.auraltester.notation.model.scale.ScaleDirection;
import com.skewwhiffy.auraltester.test.util.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ScaleTypeFactoryTest {
  @Mock
  private IntervalFactory intervalFactory;

  @InjectMocks
  private ScaleTypeFactory scaleTypeFactory;
  private List<DirectedIntervals> expectedIntervals;
  private  List<DirectedInterval>expectedDescendingIntervals;

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

 */