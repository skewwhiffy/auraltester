package com.skewwhiffy.auraltester.notation.factory;

/*
import com.skewwhiffy.auraltester.notation.model.interval.Interval;
import org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IntervalFactoryTest {
  @InjectMocks
  private IntervalFactory intervalFactory;

  @ParameterizedTest
  @ValueSource(ints = [2, 3, 6, 7])
  fun `instantiates major interval`(intervalDegree: Int) {
    val expected = Interval.major(intervalDegree).up

    val actual = intervalFactory.getDirectedInterval(intervalDegree.toString())

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `throws when invalid deviation`() {
    assertThrows<IllegalArgumentException> { intervalFactory.getDirectedIntervals("5*") }
  }

  @ParameterizedTest
  @ValueSource(ints = [1, 4, 5, 8])
  fun `instantiates perfect interval`(degree: Int) {
    val expected = Interval.perfect(degree).up

    val actual = intervalFactory.getDirectedInterval(degree.toString())

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `instantiates minor thirg`() {
    val expected = Interval.minor(3).up

    val actual = intervalFactory.getDirectedInterval("3-")

    assertThat(actual).isEqualTo(expected)
  }
}

 */