package com.skewwhiffy.auraltester.notation.model.key;

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.notation.model.note.Note;
import com.skewwhiffy.auraltester.notation.model.note.Octave;
import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KeyTest {
    @Test
    void when_noteHasAccidentalInKey_then_noAccidentalIsGivenInAbc() {
        val note = new AbsoluteNote(Note.getF().sharpen(), Octave.getDefault(), Optional.empty());
        val key = Key.major(Note.getD());

        val actual = key.getAbc(note);

        assertThat(actual).isEqualTo("F");
    }

    @Test
    void when_noteHasAccidentalDifferentToKey_then_noteAccidentalIsGiven() {
        val note = new AbsoluteNote(Note.getE().flatten(), Octave.getDefault(), Optional.empty());
        val key = Key.major(Note.getG());

        val actual = key.getAbc(note);

        assertThat(actual).isEqualTo("_E");
    }

    @Test
    void when_noteIsNatural_and_keyHasAccidental_then_noteAccidentalIsNatural() {
        val note = new AbsoluteNote(Note.getG(), Octave.getDefault(), Optional.empty());
        val key = Key.major(Note.getE());

        val actual = key.getAbc(note);

        assertThat(actual).isEqualTo("=G");
    }

    @ParameterizedTest
    @MethodSource("renderMajorTestCases")
    void canRenderMajorKey(Note note) {
        val key = Key.major(note);

        assertThat(key.canRenderSignature()).isTrue();
    }


    @ParameterizedTest
    @MethodSource("cannotRenderMajorTestCases")
    void cannotRenderMajorKey(Note note) {
        val key = Key.major(note);

        assertThat(key.canRenderSignature()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("cannotRenderMinorTestCases")
    void cannotRenderMinorKey(Note note) {
        val key = Key.minor(note);

        assertThat(key.canRenderSignature()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("canRenderMinorTestCases")
    void canRenderMinorKeys(Note note) {
        val key = Key.minor(note);

        assertThat(key.canRenderSignature()).isTrue();
    }

    @Test
    void when_relativeMinor_then_correct() {
        val key = Key.major(Note.getE().flatten());

        val actual = key.getRelativeMinor();

        assertThat(actual.note()).isEqualTo(Note.getC());
        assertThat(key.getRelativeMajor()).isEqualTo(key);
    }

    @Test
    void when_relativeMajor_then_correct() {
        val key = Key.minor(Note.getE().flatten());

        val actual = key.getRelativeMajor();

        assertThat(actual.note()).isEqualTo(Note.getG().flatten());
        assertThat(key.getRelativeMinor()).isEqualTo(key);
    }

    @Test
    void when_major_then_notesCorrect() {
        val key = Key.major(Note.getF().sharpen());
        val expected = "F# G# A# B C# D# E#";

        val actual = key.getNotes().stream().map(Note::getDisplayString).collect(Collectors.joining(" "));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void when_minor_then_notesCorrect() {
        val key = Key.minor(Note.getA());
        val expected = "A B C D E F G";

        val actual = key.getNotes().stream().map(Note::getDisplayString).collect(Collectors.joining(" "));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void when_equivalent_then_equal() {
        Supplier<Key> getKey = () -> Key.major(Note.getG().flatten());

        val first = getKey.get();
        val second = getKey.get();

        assertThat(first).isEqualTo(second);
    }

    @Test
    void when_notKey_then_notEqual() {
        //noinspection AssertBetweenInconvertibleTypes
        assertThat(Key.getCMajor()).isNotEqualTo("hello");
    }

    static Stream<Arguments> canRenderMinorTestCases() {
        return canRenderMinors
                .stream()
                .map(Arguments::of);
    }

    static Stream<Arguments> cannotRenderMinorTestCases() {
        return allNotes
                .stream()
                .filter(it -> !canRenderMinors.contains(it))
                .map(Arguments::of);
    }


    static Stream<Arguments> renderMajorTestCases() {
        return canRenderMajors
                .stream()
                .map(Arguments::of);
    }

    static Stream<Arguments> cannotRenderMajorTestCases() {
        return allNotes
                .stream()
                .filter(it -> !canRenderMajors.contains(it))
                .map(Arguments::of);
    }

    private static final List<Note> canRenderMajors = Arrays.stream(
                    "C G D A E B F# C# F Bb Eb Ab Db Gb Cb".split(" ")
            )
            .map(it -> TestData.noteFactories().note().getNote(it))
            .toList();

    private static final List<Note> canRenderMinors = Arrays.stream(
                    "A E B F# C# G# D# A# D G C F Bb Eb Ab".split(" ")
            )
            .map(it -> TestData.noteFactories().note().getNote(it))
            .toList();

    private static final List<Note> allNotes = Arrays.stream(
                    "A B C D E F G".split(" ")
            )
            .map(it -> TestData.noteFactories().note().getNote(it))
            .flatMap(it -> Stream.of(it, it.sharpen(), it.flatten()))
            .toList();
}
