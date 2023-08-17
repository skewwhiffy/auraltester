package com.skewwhiffy.auraltester.notation.factory;

import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval;
import com.skewwhiffy.auraltester.notation.model.scale.ScaleDirection;
import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScaleTypeFactoryTest {
    @Mock
    private IntervalFactory intervalFactory;

    @InjectMocks
    private ScaleTypeFactory scaleTypeFactory;
    private List<DirectedInterval> expectedIntervals;
    private List<DirectedInterval> expectedDescendingIntervals;

    @BeforeEach
    void setUp() {
        Supplier<List<DirectedInterval>> getIntervals = () -> IntStream
                .range(1, 10)
                .mapToObj(it -> TestData.random().directedInterval())
                .toList();
        expectedIntervals = getIntervals.get();
        expectedDescendingIntervals = getIntervals.get();
    }

    @Test
    void instantiatesMajorScale() {
        when(intervalFactory.getDirectedIntervals("1 2 3 4 5 6 7 8"))
                .thenReturn(expectedIntervals);

        val actual = scaleTypeFactory.getMajor();

        Arrays.stream(ScaleDirection.values())
                .forEach(it -> assertThat(actual.getIntervals(it)).isEqualTo(expectedIntervals));
    }

    @Test
    void instantiatesMinorScale() {
        when(intervalFactory.getDirectedIntervals("1 2 3- 4 5 6- 7 8"))
                .thenReturn(expectedIntervals);

        val actual = scaleTypeFactory.getMinorHarmonic();

        Arrays.stream(ScaleDirection.values())
                .forEach(it -> assertThat(actual.getIntervals(it)).isEqualTo(expectedIntervals));
    }

    @Test
    void instantiatesMinorMelodicScale() {
        when(intervalFactory.getDirectedIntervals("1 2 3- 4 5 6 7 8"))
                .thenReturn(expectedIntervals);
        when(intervalFactory.getDirectedIntervals("1 2 3- 4 5 6- 7- 8"))
                .thenReturn(expectedDescendingIntervals);

        val actual = scaleTypeFactory.getMinorMelodic();

        assertThat(actual.getIntervals(ScaleDirection.ASCENDING)).isEqualTo(expectedIntervals);
        assertThat(actual.getIntervals(ScaleDirection.DESCENDING)).isEqualTo(expectedDescendingIntervals);
    }
}