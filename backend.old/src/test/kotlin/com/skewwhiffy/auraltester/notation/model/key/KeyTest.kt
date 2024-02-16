package com.skewwhiffy.auraltester.notation.model.key

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.notation.model.note.Note
import com.skewwhiffy.auraltester.notation.model.note.Octave
import com.skewwhiffy.auraltester.notation.model.note.displayString
import com.skewwhiffy.auraltester.notation.model.note.flatten
import com.skewwhiffy.auraltester.notation.model.note.sharpen
import com.skewwhiffy.auraltester.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class KeyTest {
    companion object {
        @JvmStatic
        fun canRenderMinorTestCases() = canRenderMinors.map { Arguments.of(it) }

        @JvmStatic
        fun cannotRenderMinorTestCases() = allNotes.filter { !canRenderMinors.contains(it) }.map { Arguments.of(it) }

        @JvmStatic
        fun renderMajorTestCases() = canRenderMajors.map { Arguments.of(it) }

        @JvmStatic
        fun cannotRenderMajorTestCases() = allNotes.filter { !canRenderMajors.contains(it) }.map { Arguments.of(it) }

        @JvmStatic
        private val canRenderMajors = "C G D A E B F# C# F Bb Eb Ab Db Gb Cb".split(" ".toRegex())
            .dropLastWhile { it.isEmpty() }
            .map { TestData.noteFactories.note.getNote(it) }

        @JvmStatic
        private val canRenderMinors = "A E B F# C# G# D# A# D G C F Bb Eb Ab".split(" ".toRegex())
            .dropLastWhile { it.isEmpty() }
            .map { TestData.noteFactories.note.getNote(it) }

        @JvmStatic
        private val allNotes = "A B C D E F G".split(" ".toRegex())
            .dropLastWhile { it.isEmpty() }
            .map { TestData.noteFactories.note.getNote(it) }
            .flatMap { listOf(it, it.sharpen, it.flatten) }
    }

    @Test
    fun when_noteHasAccidentalInKey_then_noAccidentalIsGivenInAbc() {
        val note = AbsoluteNote(Note.f.sharpen, Octave.default, null)
        val key = Key.major(Note.d)

        val actual = key.getAbc(note)

        assertThat(actual).isEqualTo("F")
    }

    @Test
    fun when_noteHasAccidentalDifferentToKey_then_noteAccidentalIsGiven() {
        val note = AbsoluteNote(Note.e.flatten, Octave.default, null)
        val key = Key.major(Note.g)

        val actual = key.getAbc(note)

        assertThat(actual).isEqualTo("_E")
    }

    @Test
    fun when_noteIsNatural_and_keyHasAccidental_then_noteAccidentalIsNatural() {
        val note = AbsoluteNote(Note.g, Octave.default, null)
        val key = Key.major(Note.e)
        val actual = key.getAbc(note)
        assertThat(actual).isEqualTo("=G")
    }

    @ParameterizedTest
    @MethodSource("renderMajorTestCases")
    fun canRenderMajorKey(note: Note?) {
        val key = Key.major(note!!)
        assertThat(key.canRenderSignature).isTrue()
    }

    @ParameterizedTest
    @MethodSource("cannotRenderMajorTestCases")
    fun cannotRenderMajorKey(note: Note?) {
        val key = Key.major(note!!)
        assertThat(key.canRenderSignature).isFalse()
    }

    @ParameterizedTest
    @MethodSource("cannotRenderMinorTestCases")
    fun cannotRenderMinorKey(note: Note?) {
        val key = Key.minor(note!!)
        assertThat(key.canRenderSignature).isFalse()
    }

    @ParameterizedTest
    @MethodSource("canRenderMinorTestCases")
    fun canRenderMinorKeys(note: Note?) {
        val key = Key.minor(note!!)
        assertThat(key.canRenderSignature).isTrue()
    }

    @Test
    fun when_relativeMinor_then_correct() {
        val key = Key.major(Note.e.flatten)
        val actual = key.relativeMinor
        assertThat(actual.note).isEqualTo(Note.c)
        assertThat(key.relativeMajor).isEqualTo(key)
    }

    @Test
    fun when_relativeMajor_then_correct() {
        val key = Key.minor(Note.e.flatten)
        val actual = key.relativeMajor
        assertThat(actual.note).isEqualTo(Note.g.flatten)
        assertThat(key.relativeMinor).isEqualTo(key)
    }

    @Test
    fun when_major_then_notesCorrect() {
        val key = Key.major(Note.f.sharpen)
        val expected = "F# G# A# B C# D# E#"
        val actual = key.notes.joinToString(" ") { it.displayString }
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun when_minor_then_notesCorrect() {
        val key = Key.minor(Note.a)
        val expected = "A B C D E F G"
        val actual = key.notes.joinToString(" ") { it.displayString }
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun when_equivalent_then_equal() {
        val getKey = { Key.major(Note.g.flatten) }
        val first = getKey()
        val second = getKey()
        assertThat(first).isEqualTo(second)
    }

    @Test
    fun when_notKey_then_notEqual() {
        assertThat(Key.cMajor).isNotEqualTo("hello")
    }

}