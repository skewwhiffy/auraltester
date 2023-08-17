package com.skewwhiffy.auraltester.notation.model.interval;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

class DirectedIntervalTest {
    @Test
    void instantiatesUpInterval() {
        val interval = Interval.major(6);

        val actual = interval.up();

        assertThat(actual.interval()).isEqualTo(interval);
        assertThat(actual.direction()).isEqualTo(IntervalDirection.UP);
    }

    @Test
    void instantiatesDownInterval() {
        val interval = Interval.major(7);

        val actual = interval.down();

        assertThat(actual.interval()).isEqualTo(interval);
        assertThat(actual.direction()).isEqualTo(IntervalDirection.DOWN);
    }

    @Test
    void equatesEquivalentDirectedIntervals() {
        Supplier<Interval> getInterval = () -> Interval.perfect(5);

        val first = getInterval.get();
        val second = getInterval.get();

        assertThat(first).isEqualTo(second);
    }
}
