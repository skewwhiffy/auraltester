package com.skewwhiffy.auraltester.notation.factory;

import com.skewwhiffy.auraltester.helper.NoParallelStream;
import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval;
import com.skewwhiffy.auraltester.notation.model.interval.Interval;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class IntervalFactory {
    public List<DirectedInterval> getDirectedIntervals(String rawIntervals) {
        return Arrays.stream(rawIntervals.split(" "))
                .map(this::getDirectedInterval)
                .toList();
    }

    public DirectedInterval getDirectedInterval(String rawInterval) {
        Interval interval = getRawDeviations(rawInterval)
                .chars()
                .mapToObj(it -> (char) it)
                .reduce(
                        getBaseInterval(rawInterval),
                        (current, it) -> switch (it) {
                            case '-' -> current.getDiminished();
                            case '+' -> current.getAugmented();
                            default -> throw new IllegalArgumentException(
                                    MessageFormat.format("Not a valid deviation: '{0}'", it)
                            );
                        },
                        new NoParallelStream<>()
                );
        return switch (getRawDirection(rawInterval)) {
            case "-" -> interval.down();
            case "+", "" -> interval.up();
            default -> throw new IllegalArgumentException("Not a valid direction: '$it''");
        };
    }

    private Interval getBaseInterval(String rawInterval) {
        int rawInt = Integer.parseInt(getRawInt(rawInterval));
        return List.of(1, 4, 5, 8).contains(rawInt)
                ? Interval.perfect(rawInt)
                : Interval.major(rawInt);
    }

    private String getRawDirection(String rawInterval) {
        return Stream.of("+", "-")
                .filter(rawInterval::startsWith)
                .findFirst()
                .orElse("");
    }

    private String getRawInt(String rawInterval) {
        return (rawInterval.startsWith("+") || rawInterval.startsWith("-"))
                ? getRawInt("", rawInterval.substring(1))
                : getRawInt("", rawInterval);
    }

    private String getRawInt(String soFar, String remaining) {
        try {
            return getRawInt(soFar + Integer.parseInt(remaining.substring(0, 1)), remaining.substring(1));
        } catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
            return soFar;
        }
    }

    private String getRawDeviations(String rawInterval) {
        return rawInterval
                .substring(getRawInt(rawInterval).length() + getRawDirection(rawInterval).length());
    }
}
