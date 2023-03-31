package com.skewwhiffy.auraltester.notation.factory;

import com.skewwhiffy.auraltester.notation.model.interval.Interval;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
class IntervalFactoryTest {
    @InjectMocks
    private IntervalFactory intervalFactory;

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 6, 7})
    void instantiatesMajorInterval(int intervalDegree) {
        val expected = Interval.major(intervalDegree).up();

        val actual = intervalFactory.getDirectedInterval(Integer.toString(intervalDegree));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void when_deviationInvalid_then_throws() {
        assertThatThrownBy(() -> intervalFactory.getDirectedInterval("5*"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 5, 8})
    void instantiatesPerfectInterval(int degree) {
        val expected = Interval.perfect(degree).up();

        val actual = intervalFactory.getDirectedInterval(Integer.toString(degree));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void instantiatesMinorThird() {
        val expected = Interval.minor(3).up();

        val actual = intervalFactory.getDirectedInterval("3-");

        assertThat(actual).isEqualTo(expected);
    }
}