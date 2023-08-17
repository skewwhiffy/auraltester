package com.skewwhiffy.auraltester.notation.model.scale;

import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval;

import java.util.List;

public record ScaleType(
        String displayName,
        String abc,
        List<DirectedInterval> ascendingIntervals,
        List<DirectedInterval> descendingIntervals
) {
    public ScaleType(String displayName, String abc, List<DirectedInterval> intervals) {
        this(displayName, abc, intervals, intervals);
    }

    public List<DirectedInterval> getIntervals(ScaleDirection direction) {
        return switch (direction) {
            case ASCENDING -> ascendingIntervals;
            case DESCENDING -> descendingIntervals;
        };
    }
}
