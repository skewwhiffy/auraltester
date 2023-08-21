package com.skewwhiffy.auraltester.notation.model.interval

data class Interval(val degree: Int, val deviation: Int) {
    companion object {
        val perfectDegrees = setOf(1, 4, 5, 8)
        val displayStrings = listOf(
            "unison",
            "second",
            "third",
            "fourth",
            "fifth",
            "sixth",
            "seventh",
            "octave"
        )

        /*
        public static Interval augmented(int degree) {
            val baseInterval = perfectDegrees.contains(degree) ? perfect(degree) : major(degree);
            return baseInterval.getAugmented();
        }

        public static Interval diminished(int degree) {
            val baseInterval = perfectDegrees.contains(degree) ? perfect(degree) : minor(degree);
            return baseInterval.getDiminished();
        }
        */

        fun minor(degree: Int) = major(degree).diminished

        fun major(degree: Int) = if (perfectDegrees.contains(degree))
            throw IllegalArgumentException("Cannot instantiate major interval of degree '$degree'")
        else Interval(degree, 0)

        fun perfect(degree: Int) = if (!perfectDegrees.contains(degree))
            throw IllegalArgumentException("Cannot instantiate perfect interval of degree '$degree'")
        else Interval(degree, 0)
    }

    val diminished
        get() = Interval(degree, deviation - 1)

    val augmented
        get() = Interval(degree, deviation + 1)

    val displayString
        get() = "$quality ${displayStrings[degree - 1]}"

    val up
        get() = DirectedInterval(this, IntervalDirection.UP)

    val down
        get() = DirectedInterval(this, IntervalDirection.DOWN)

    private val quality: String
        get() {
            val defaultQuality = if (perfectDegrees.contains(degree)) "perfect" else "major"
            val diminishedQuantity = if (perfectDegrees.contains(degree)) -deviation else -deviation - 1
            val negativeQuality = if (!perfectDegrees.contains(degree) && deviation == -1)
                "minor" else when (diminishedQuantity) {
                1 -> "diminished"
                2 -> "doubly diminished"
                else -> "${diminishedQuantity}x diminished"
            }

            val positiveQuality = (when (deviation) {
                1 -> ""
                2 -> "doubly "
                else -> "${deviation}x "
            }) + "augmented"

            return if (deviation < 0) negativeQuality
            else if (deviation > 0) positiveQuality else defaultQuality
        }
}
