package com.skewwhiffy.auraltester.notation.model.interval;

import java.text.MessageFormat;
import java.util.Set;

public record Interval(int degree, int deviation) {
    private static final Set<Integer> perfectDegrees = Set.of(1, 4, 5, 8);

    public Interval getDiminished() {
        return new Interval(degree, deviation - 1);
    }

    public Interval getAugmented() {
        return new Interval(degree, deviation + 1);
    }
        /*

  val displayString: String get() = "$quality ${displayStrings[degree - 1]}"
  */
    public DirectedInterval up() {
        return new DirectedInterval(this, IntervalDirection.UP);
    }

    public DirectedInterval down() {
        return new DirectedInterval(this, IntervalDirection.DOWN);
    }

        /*
  private val quality: String
    get() {
      val defaultQuality = if (perfectDegrees.contains(degree)) "perfect" else "major"
      val negativeQuality =
        if (!perfectDegrees.contains(degree) && deviation == -1) "minor"
        else (if (perfectDegrees.contains(degree)) -deviation else -deviation - 1).let {
          when (it) {
            1 -> "diminished"
            2 -> "doubly diminished"
            else -> "${it}x diminished"
          }
        }

      val positiveQuality = when (deviation) {
        1 -> ""
        2 -> "doubly "
        else -> "${deviation}x "
      } + "augmented"

      return when {
        deviation < 0 -> negativeQuality
        deviation > 0 -> positiveQuality
        else -> defaultQuality
      }
    }

  override fun toString(): String = displayString
}

  companion object {
    private val displayStrings: List<String> = listOf(
      "unison",
      "second",
      "third",
      "fourth",
      "fifth",
      "sixth",
      "seventh",
      "octave"
    )
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
