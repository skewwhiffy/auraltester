package com.skewwhiffy.auraltester.notation.model.scale;

import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval;
import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ScaleTypeTest {
    private List<DirectedInterval> ascendingIntervals;
    private List<DirectedInterval> descendingIntervals;
    private ScaleType scaleType;

    @BeforeEach
    void setUp() {
        val displayName = TestData.random().string();
        val abc = TestData.random().string();
        ascendingIntervals = IntStream.range(0, 6).mapToObj(it -> TestData.random().directedInterval()).toList();
        descendingIntervals = IntStream.range(0, 8).mapToObj(it -> TestData.random().directedInterval()).toList();
        scaleType = new ScaleType(displayName, abc, ascendingIntervals, descendingIntervals);
    }

    @Test
    void when_scaleIsAscending_then_returnsAscendingIntervals() {
        val expected = ascendingIntervals;

        val actual = scaleType.getIntervals(ScaleDirection.ASCENDING);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void when_scaleIsDescending_then_returnsDescendingIntervals() {
        val expected = descendingIntervals;

        val actual = scaleType.getIntervals(ScaleDirection.DESCENDING);

        assertThat(actual).isEqualTo(expected);
    }
}
