package com.skewwhiffy.auraltester.notation.model.note

import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval
import com.skewwhiffy.auraltester.notation.model.interval.Interval
import com.skewwhiffy.auraltester.notation.model.interval.IntervalDirection
import com.skewwhiffy.auraltester.notation.model.key.Key
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class AbsoluteNoteTest {
    @Test
    fun instantiatesMiddleC() {
        val expected = "C"
        val actual = AbsoluteNote.middleC
        assertThat(actual.getAbc(Key.cMajor)).isEqualTo(expected)
    }

    @Test
    fun addsMajorAndPerfectIntervals() {
        val intervals = listOf(
            Interval.perfect(1),
            Interval.major(2),
            Interval.major(3),
            Interval.perfect(4),
            Interval.perfect(5),
            Interval.major(6),
            Interval.major(7),
            Interval.perfect(8)
        )
        val start = AbsoluteNote.middleC
        val expected = listOf("C", "D", "E", "F", "G", "A", "B", "c")
        testGeneric(start, intervals, expected)
    }

    @Test
    fun addsMinorIntervals() {
        val start = AbsoluteNote.middleC.plus(Interval.major(6))
        val intervals = listOf(
            Interval.perfect(1),
            Interval.minor(2),
            Interval.minor(3),
            Interval.perfect(4),
            Interval.perfect(5),
            Interval.minor(6),
            Interval.minor(7),
            Interval.perfect(8)
        )
        val expected = listOf("A", "_B", "c", "d", "e", "f", "g", "a")
        testGeneric(start, intervals, expected)
    }

    @Test
    fun addsDiminishedAndAugmentedIntervals() {
        val intervals = listOf(
            Interval.diminished(3), Interval.diminished(4), Interval.augmented(5), Interval.augmented(6)
        )
        val start = AbsoluteNote.middleC
        val expected = listOf("__E", "_F", "^G", "^A")
        testGeneric(start, intervals, expected)
    }

    @Test
    fun doesNotAddComponentIntervals__yet() {
        val start = AbsoluteNote.middleC
        assertThatThrownBy { start.plus(Interval.major(9)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun appliesUpInterval() {
        val interval = Interval.minor(3).up
        val start = AbsoluteNote.middleC
        val expected = "_E"
        val actual = start.apply(interval)
        assertThat(actual.getAbc(Key.cMajor)).isEqualTo(expected)
    }

    @Test
    fun appliesDownInterval() {
        val interval = Interval.minor(3)
        val directedInterval = interval.down
        val start = AbsoluteNote.middleC
        val expected = "A,"
        val actualWithSubtract = start - interval

        val actualWithApply = start.apply(directedInterval)

        assertThat(actualWithApply.getAbc(Key.cMajor)).isEqualTo(expected)
        assertThat(actualWithSubtract.getAbc(Key.cMajor)).isEqualTo(expected)
    }

    @Test
    fun notSubtractCompoundIntervals__yet() {
        val interval = Interval.major(9)
        assertThatThrownBy { AbsoluteNote.middleC - interval }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun canGetIntervalBetweenNotes() {
        val expected = Interval.major(6)
        val lower = AbsoluteNote.middleC
        val upper = lower + expected

        val actualUp = upper - lower
        val actualDown = lower - upper

        assertThat(actualUp).isEqualTo(DirectedInterval(expected, IntervalDirection.UP))
        assertThat(actualDown).isEqualTo(DirectedInterval(expected, IntervalDirection.DOWN))
    }

    @Test
    fun equivalentNotesAreEqual() {
        val getNote = { AbsoluteNote(Note.d.sharpen, Octave.default.up, null) }
        val first = getNote()
        val second = getNote()
        assertThat(first).isEqualTo(second)
        assertThat(first).isGreaterThanOrEqualTo(second)
        assertThat(first).isLessThanOrEqualTo(second)
    }

    @Test
    fun absoluteNoteIsNotEqualToNonAbsoluteNote() {
        assertThat(AbsoluteNote.middleC).isNotEqualTo(Note.c)
    }

    @Test
    fun toStringReturnsAbc() {
        val note = AbsoluteNote.middleC
        assertThat(note.toString()).isEqualTo(note.getAbc(Key.cMajor))
    }

    @Test
    fun nonEquivalentNotesInSameOctaveAreComparable() {
        val lower = AbsoluteNote(Note.d, Octave.default, null)
        val higher = AbsoluteNote(Note.b, Octave.default, null)
        assertThat(lower).isLessThan(higher)
        assertThat(lower).isLessThanOrEqualTo(higher)
    }

    @Test
    fun when_lowerBelowHigher_then_rangeWorks() {
        val lower = AbsoluteNote(Note.d, Octave.default.down, null)
        val higher = AbsoluteNote(Note.e, Octave.default.up, null)
        val expected = "D, E, F, G, A, B, C D E F G A B c d e"
        val actual = AbsoluteNote.range(lower, higher).joinToString(" ") { it.getAbc(Key.cMajor) }
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun when_lowerAboveHigher_then_rangeWorks() {
        val lower = AbsoluteNote(Note.d, Octave.default.down, null)
        val higher = AbsoluteNote(Note.e, Octave.default.up, null)
        val expected = "D, E, F, G, A, B, C D E F G A B c d e"

        val actual = AbsoluteNote.range(higher, lower).joinToString(" ") { it.getAbc(Key.cMajor) }

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun when_lowerNotNatural_then_throws() {
        val nonNatural = AbsoluteNote(Note.c.sharpen, Octave.default, null)
        assertThatThrownBy { AbsoluteNote.range(nonNatural, AbsoluteNote.middleC) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun when_upperNotNatural_then_throws() {
        val nonNatural = AbsoluteNote(Note.c.sharpen, Octave.default, null)
        assertThatThrownBy { AbsoluteNote.range(AbsoluteNote.middleC, nonNatural) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    private fun testGeneric(
        start: AbsoluteNote,
        intervals: List<Interval>,
        expectedAbcs: List<String>
    ) {
        if (intervals.size != expectedAbcs.size) {
            Assertions.fail<Any>("Need expectation size = interval list size")
        }
        intervals.zip(expectedAbcs).forEach { testGeneric(start, it.first, it.second) }
    }

    private fun testGeneric(
        start: AbsoluteNote,
        interval: Interval,
        expectedAbc: String
    ) {
        val actual = start.plus(interval).getAbc(Key.cMajor)
        assertThat(actual).isEqualTo(expectedAbc)
    }
}
