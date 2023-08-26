package com.skewwhiffy.auraltester.notation.model.note

/*
import com.codepoetics.protonpack.StreamUtils
import com.skewwhiffy.auraltester.notation.model.interval.Interval
import com.skewwhiffy.auraltester.notation.model.key.Key
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.data.util.Pair
import java.util.Optional
import java.util.function.Supplier

internal class AbsoluteNoteTest {
    @Test
    fun instantiatesMiddleC() {
        val expected: `val` = "C"
        val actual: `val` = AbsoluteNote.getMiddleC()
        assertThat(actual.getAbc(Key.getCMajor())).isEqualTo(expected)
    }

    @Test
    fun addsMajorAndPerfectIntervals() {
        val intervals: `val` = java.util.List.of(
            Interval.perfect(1),
            Interval.major(2),
            Interval.major(3),
            Interval.perfect(4),
            Interval.perfect(5),
            Interval.major(6),
            Interval.major(7),
            Interval.perfect(8)
        )
        val start: `val` = AbsoluteNote.getMiddleC()
        val expected: `val` = listOf("C", "D", "E", "F", "G", "A", "B", "c")
        testGeneric(start, intervals, expected)
    }

    @Test
    fun addsMinorIntervals() {
        val start: `val` = AbsoluteNote.getMiddleC().plus(Interval.major(6))
        val intervals: `val` = java.util.List.of(
            Interval.perfect(1),
            Interval.minor(2),
            Interval.minor(3),
            Interval.perfect(4),
            Interval.perfect(5),
            Interval.minor(6),
            Interval.minor(7),
            Interval.perfect(8)
        )
        val expected: `val` = listOf("A", "_B", "c", "d", "e", "f", "g", "a")
        testGeneric(start, intervals, expected)
    }

    @Test
    fun addsDiminishedAndAugmentedIntervals() {
        val intervals: `val` = java.util.List.of(
            Interval.diminished(3), Interval.diminished(4), Interval.augmented(5), Interval.augmented(6)
        )
        val start: `val` = AbsoluteNote.getMiddleC()
        val expected: `val` = listOf("__E", "_F", "^G", "^A")
        testGeneric(start, intervals, expected)
    }

    @Test
    fun doesNotAddComponentIntervals__yet() {
        val start: `val` = AbsoluteNote.getMiddleC()
        Assertions.assertThatThrownBy(ThrowingCallable { start.plus(Interval.major(9)) })
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun appliesUpInterval() {
        val interval: `val` = Interval.minor(3).up()
        val start: `val` = AbsoluteNote.getMiddleC()
        val expected: `val` = "_E"
        val actual: `val` = start.apply(interval)
        assertThat(actual.getAbc(Key.getCMajor())).isEqualTo(expected)
    }

    @Test
    fun appliesDownInterval() {
        val interval: `val` = Interval.minor(3)
        val directedInterval: `val` = interval.down()
        val start: `val` = AbsoluteNote.getMiddleC()
        val expected: `val` = "A,"
        val actualWithApply: `val` = start.apply(directedInterval)
        val actualWithSubtract: `val` = start.minus(interval)
        assertThat(actualWithApply.getAbc(Key.getCMajor())).isEqualTo(expected)
        assertThat(actualWithSubtract.getAbc(Key.getCMajor())).isEqualTo(expected)
    }

    @Test
    fun notSubtractCompoundIntervals__yet() {
        val interval: `val` = Interval.major(9)
        Assertions.assertThatThrownBy(ThrowingCallable { AbsoluteNote.getMiddleC().minus(interval) })
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun equivalentNotesAreEqual() {
        val getNote = Supplier<AbsoluteNote> {
            AbsoluteNote(
                Note.getD().sharpen(),
                Octave.getDefault().up(),
                Optional.empty<Any>()
            )
        }
        val first: `val` = getNote.get()
        val second: `val` = getNote.get()
        Assertions.assertThat<`val`>(first).isEqualTo(second)
        Assertions.assertThat<`val`>(first).isGreaterThanOrEqualTo(second)
        Assertions.assertThat<`val`>(first).isLessThanOrEqualTo(second)
    }

    @Test
    fun absoluteNoteIsNotEqualToNonAbsoluteNote() {
        assertThat(AbsoluteNote.getMiddleC()).isNotEqualTo(Note.getC())
    }

    @Test
    fun toStringReturnsAbc() {
        val note: `val` = AbsoluteNote.getMiddleC()
        assertThat(note.toString()).isEqualTo(note.getAbc(Key.getCMajor()))
    }

    @Test
    fun nonEquivalentNotesInSameOctaveAreComparable() {
        val lower: `val` = AbsoluteNote(Note.getD(), Octave.getDefault(), Optional.empty<Any>())
        val higher: `val` = AbsoluteNote(Note.getB(), Octave.getDefault(), Optional.empty<Any>())
        Assertions.assertThat<`val`>(lower).isLessThan(higher)
        Assertions.assertThat<`val`>(lower).isLessThanOrEqualTo(higher)
    }

    @Test
    fun when_lowerBelowHigher_then_rangeWorks() {
        val lower: `val` = AbsoluteNote(Note.getD(), Octave.getDefault().down(), Optional.empty<Any>())
        val higher: `val` = AbsoluteNote(Note.getE(), Octave.getDefault().up(), Optional.empty<Any>())
        val expected: `val` = "D, E, F, G, A, B, C D E F G A B c d e"
        val actual: `val` = AbsoluteNote
            .range(lower, higher)
            .stream()
            .map { it -> it.getAbc(Key.getCMajor()) }
            .collect(Collectors.joining(" "))
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }

    @Test
    fun when_lowerAboveHigher_then_rangeWorks() {
        val lower: `val` = AbsoluteNote(Note.getD(), Octave.getDefault().down(), Optional.empty<Any>())
        val higher: `val` = AbsoluteNote(Note.getE(), Octave.getDefault().up(), Optional.empty<Any>())
        val expected: `val` = "D, E, F, G, A, B, C D E F G A B c d e"
        val actual: `val` = AbsoluteNote
            .range(higher, lower)
            .stream()
            .map { it -> it.getAbc(Key.getCMajor()) }
            .collect(Collectors.joining(" "))
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }

    @Test
    fun when_lowerNotNatural_then_throws() {
        val nonNatural: `val` = AbsoluteNote(Note.getC().sharpen(), Octave.getDefault(), Optional.empty<Any>())
        Assertions.assertThatThrownBy(ThrowingCallable { AbsoluteNote.range(nonNatural, AbsoluteNote.getMiddleC()) })
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun when_upperNotNatural_then_throws() {
        val nonNatural: `val` = AbsoluteNote(Note.getC().sharpen(), Octave.getDefault(), Optional.empty<Any>())
        Assertions.assertThatThrownBy(ThrowingCallable { AbsoluteNote.range(AbsoluteNote.getMiddleC(), nonNatural) })
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
        StreamUtils.zip(intervals.stream(), expectedAbcs.stream()) { first: S?, second: T? -> Pair.of(first, second) }
            .forEach { it -> testGeneric(start, it.getFirst(), it.getSecond()) }
    }

    private fun testGeneric(
        start: AbsoluteNote,
        interval: Interval,
        expectedAbc: String
    ) {
        val actual: `val` = start.plus(interval).getAbc(Key.getCMajor())
        Assertions.assertThat<`val`>(actual).isEqualTo(expectedAbc)
    }
}


 */