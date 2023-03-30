package com.skewwhiffy.auraltester.notation.model.interval;

import lombok.val;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

public record Interval(int degree, int deviation) {
    public static final Set<Integer> perfectDegrees = Set.of(1, 4, 5, 8);
    private static final List<String> displayStrings = List.of(
            "unison",
            "second",
            "third",
            "fourth",
            "fifth",
            "sixth",
            "seventh",
            "octave"
    );

    public Interval getDiminished() {
        return new Interval(degree, deviation - 1);
    }

    public Interval getAugmented() {
        return new Interval(degree, deviation + 1);
    }

    public String getDisplayString() {
        return MessageFormat.format("{0} {1}", getQuality(), displayStrings.get(degree - 1));
    }

    public DirectedInterval up() {
        return new DirectedInterval(this, IntervalDirection.UP);
    }

    public DirectedInterval down() {
        return new DirectedInterval(this, IntervalDirection.DOWN);
    }

    private String getQuality() {
        val defaultQuality = perfectDegrees.contains(degree) ? "perfect" : "major";
        val negativeQuality = (!perfectDegrees.contains(degree) && deviation == -1)
                ? "minor"
                : switch (perfectDegrees.contains(degree) ? -deviation : -deviation - 1) {
            case 1 -> "diminished";
            case 2 -> "doubly diminished";
            default -> "${it}x diminished";
        };

        val positiveQuality = (switch (deviation) {
            case 1 -> "";
            case 2 -> "doubly ";
            default -> "${deviation}x ";
        }) + "augmented";

        return deviation < 0 ? negativeQuality : deviation > 0 ? positiveQuality : defaultQuality;
    }
    /*

  override fun toString(): String = displayString
}

     */


    /*
        fun augmented(degree: Int): Interval {
          val baseInterval = if (perfectDegrees.contains(degree)) perfect(degree) else major(degree)
          return baseInterval.augmented
        }

        fun diminished(degree: Int): Interval {
          val baseInterval = if (perfectDegrees.contains(degree)) perfect(degree) else minor(degree)
          return baseInterval.diminished
        }

    */
    public static Interval minor(int degree) {
        return major(degree).getDiminished();
    }

    public static Interval major(int degree) {
        if (perfectDegrees.contains(degree)) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Cannot instantiate major interval of degree '{0}'", degree)
            );
        }
        return new Interval(degree, 0);
    }

    public static Interval perfect(int degree) {
        if (!perfectDegrees.contains(degree)) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Cannot instantiate perfect interval of degree '{0}'", degree)
            );
        }
        return new Interval(degree, 0);
    }


}
