package com.skewwhiffy.auraltester.notation.model.note;

import com.codepoetics.protonpack.StreamUtils;
import com.skewwhiffy.auraltester.notation.model.interval.Interval;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class AbsoluteNoteTest {
    @Test
    void instantiatesMiddleC() {
        val expected = "C";

        val actual = AbsoluteNote.getMiddleC();

        assertThat(actual.getAbc(Key.getCMajor())).isEqualTo(expected);
    }

    @Test
    void addsMajorAndPerfectIntervals() {
        val intervals = List.of(
                Interval.perfect(1),
                Interval.major(2),
                Interval.major(3),
                Interval.perfect(4),
                Interval.perfect(5),
                Interval.major(6),
                Interval.major(7),
                Interval.perfect(8)
        );
        val start = AbsoluteNote.getMiddleC();
        val expected = List.of("C", "D", "E", "F", "G", "A", "B", "c");

        testGeneric(start, intervals, expected);
    }

    @Test
    void addsMinorIntervals() {
        val start = AbsoluteNote.getMiddleC().plus(Interval.major(6));
        val intervals = List.of(
                Interval.perfect(1),
                Interval.minor(2),
                Interval.minor(3),
                Interval.perfect(4),
                Interval.perfect(5),
                Interval.minor(6),
                Interval.minor(7),
                Interval.perfect(8)
        );
        val expected = List.of("A", "_B", "c", "d", "e", "f", "g", "a");

        testGeneric(start, intervals, expected);
    }

    @Test
    void addsDiminishedAndAugmentedIntervals() {
        val intervals = List.of(
                Interval.diminished(3), Interval.diminished(4), Interval.augmented(5), Interval.augmented(6)
        );
        val start = AbsoluteNote.getMiddleC();
        val expected = List.of("__E", "_F", "^G", "^A");

        testGeneric(start, intervals, expected);
    }

    @Test
    void doesNotAddComponentIntervals__yet() {
        val start = AbsoluteNote.getMiddleC();

        assertThatThrownBy(() -> start.plus(Interval.major(9)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void appliesUpInterval() {
        val interval = Interval.minor(3).up();
        val start = AbsoluteNote.getMiddleC();
        val expected = "_E";

        val actual = start.apply(interval);

        assertThat(actual.getAbc(Key.getCMajor())).isEqualTo(expected);
    }

    @Test
    void appliesDownInterval() {
        val interval = Interval.minor(3);
        val directedInterval = interval.down();
        val start = AbsoluteNote.getMiddleC();
        val expected = "A,";

        val actualWithApply = start.apply(directedInterval);
        val actualWithSubtract = start.minus(interval);

        assertThat(actualWithApply.getAbc(Key.getCMajor())).isEqualTo(expected);
        assertThat(actualWithSubtract.getAbc(Key.getCMajor())).isEqualTo(expected);
    }

    @Test
    void notSubtractCompoundIntervals__yet() {
        val interval = Interval.major(9);

        assertThatThrownBy(() -> AbsoluteNote.getMiddleC().minus(interval))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void equivalentNotesAreEqual() {
        Supplier<AbsoluteNote> getNote = () -> new AbsoluteNote(
                Note.getD().sharpen(),
                Octave.getDefault().up(),
                Optional.empty()
        );

        val first = getNote.get();
        val second = getNote.get();

        assertThat(first).isEqualTo(second);
        assertThat(first).isGreaterThanOrEqualTo(second);
        assertThat(first).isLessThanOrEqualTo(second);
    }

    @Test
    void absoluteNoteIsNotEqualToNonAbsoluteNote() {
        //noinspection AssertBetweenInconvertibleTypes
        assertThat(AbsoluteNote.getMiddleC()).isNotEqualTo(Note.getC());
    }

    @Test
    void toStringReturnsAbc() {
        val note = AbsoluteNote.getMiddleC();

        assertThat(note.toString()).isEqualTo(note.getAbc(Key.getCMajor()));
    }

    @Test
    void nonEquivalentNotesInSameOctaveAreComparable() {
        val lower = new AbsoluteNote(Note.getD(), Octave.getDefault(), Optional.empty());
        val higher = new AbsoluteNote(Note.getB(), Octave.getDefault(), Optional.empty());

        assertThat(lower).isLessThan(higher);
        assertThat(lower).isLessThanOrEqualTo(higher);
    }

    @Test
    void when_lowerBelowHigher_then_rangeWorks() {
        val lower = new AbsoluteNote(Note.getD(), Octave.getDefault().down(), Optional.empty());
        val higher = new AbsoluteNote(Note.getE(), Octave.getDefault().up(), Optional.empty());
        val expected = "D, E, F, G, A, B, C D E F G A B c d e";

        val actual = AbsoluteNote
                .range(lower, higher)
                .stream()
                .map(it -> it.getAbc(Key.getCMajor()))
                .collect(Collectors.joining(" "));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void when_lowerAboveHigher_then_rangeWorks() {
        val lower = new AbsoluteNote(Note.getD(), Octave.getDefault().down(), Optional.empty());
        val higher = new AbsoluteNote(Note.getE(), Octave.getDefault().up(), Optional.empty());
        val expected = "D, E, F, G, A, B, C D E F G A B c d e";

        val actual = AbsoluteNote
                .range(higher, lower)
                .stream()
                .map(it -> it.getAbc(Key.getCMajor()))
                .collect(Collectors.joining(" "));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void when_lowerNotNatural_then_throws() {
        val nonNatural = new AbsoluteNote(Note.getC().sharpen(), Octave.getDefault(), Optional.empty());
        assertThatThrownBy(() -> AbsoluteNote.range(nonNatural, AbsoluteNote.getMiddleC()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void when_upperNotNatural_then_throws() {
        val nonNatural = new AbsoluteNote(Note.getC().sharpen(), Octave.getDefault(), Optional.empty());
        assertThatThrownBy(() -> AbsoluteNote.range(AbsoluteNote.getMiddleC(), nonNatural))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void testGeneric(
            AbsoluteNote start,
            List<Interval> intervals,
            List<String> expectedAbcs
    ) {
        if (intervals.size() != expectedAbcs.size()) {
            fail("Need expectation size = interval list size");
        }
        StreamUtils.zip(intervals.stream(), expectedAbcs.stream(), Pair::of)
                .forEach(it -> testGeneric(start, it.getFirst(), it.getSecond()));
    }

    private void testGeneric(
            AbsoluteNote start,
            Interval interval,
            String expectedAbc
    ) {
        val actual = start.plus(interval).getAbc(Key.getCMajor());

        assertThat(actual).isEqualTo(expectedAbc);
    }
}
