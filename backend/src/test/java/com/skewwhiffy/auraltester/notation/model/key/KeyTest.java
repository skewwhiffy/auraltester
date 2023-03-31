/*
package com.skewwhiffy.notation.model.key

import com.skewwhiffy.notation.model.note.AbsoluteNote
import com.skewwhiffy.notation.model.note.Note
import com.skewwhiffy.notation.model.note.Octave
import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class KeyTest {
  @Test
  fun `when note has accidental in key then no accidental is given in abc`() {
    val note = AbsoluteNote(Note.f.sharp, Octave.default)
    val key = Key(Note.d)

    val actual = key.abc(note)

    assertThat(actual).isEqualTo("F")
  }

  @Test
  fun `when note has accidental different to key then note accidental is given () abc`() {
    val note = AbsoluteNote(Note.e.flat, Octave.default)
    val key = Key(Note.g)

    val actual = key.abc(note)

    assertThat(actual).isEqualTo("_E")
  }

  @Test
  fun `when note is natural but key has accidental then note accidental is natural () abc`() {
    val note = AbsoluteNote(Note.g, Octave.default)
    val key = Key(Note.e)

    val actual = key.abc(note)

    assertThat(actual).isEqualTo("=G")
  }

  @Suppress("SpellCheckingInspection")
  @ParameterizedTest
  @MethodSource("renderableMajorsTestCases")
  fun `can render major key`(note: Note) {
    val key = Key(note)

    assertThat(key.canRenderSignature).isTrue
  }

  @Suppress("SpellCheckingInspection")
  @ParameterizedTest
  @MethodSource("notRenderableMajorsTestCases")
  fun `cannot render major key`(note: Note) {
    val key = Key(note)

    assertThat(key.canRenderSignature).isFalse
  }

  @Suppress("SpellCheckingInspection")
  @ParameterizedTest
  @MethodSource("notRenderableMinorsTestCases")
  fun `cannot render minor key`(note: Note) {
    val key = Key(note, isMinor = true)

    assertThat(key.canRenderSignature).isFalse
  }

  @Suppress("SpellCheckingInspection")
  @ParameterizedTest
  @MethodSource("renderableMinorsTestCases")
  fun `can render minor key`(note: Note) {

    val key = Key(note, isMinor = true)

    assertThat(key.canRenderSignature).isTrue
  }

  @Test
  fun `when relativeMinor then correct`() {
    val key = Key(Note.e.flat)

    val actual = key.relativeMinor

    assertThat(actual.note).isEqualTo(Note.c)
    assertThat(key.relativeMajor).isEqualTo(key)
  }

  @Test
  fun `when relativeMajor then correct`() {
    val key = Key(Note.e.flat, isMinor = true)

    val actual = key.relativeMajor

    assertThat(actual.note).isEqualTo(Note.g.flat)
    assertThat(key.relativeMinor).isEqualTo(key)
  }

  @Test
  fun `when major then notes correct`() {
    val key = Key(Note.f.sharp)
    val expected = "F# G# A# B C# D# E#"

    val actual = key.notes.joinToString(" ") { it.displayString }

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `when minor then notes correct`() {
    val key = Key(Note.a, isMinor = true)
    val expected = "A B C D E F G"

    val actual = key.notes.joinToString(" ") { it.displayString }

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `when equivalent then equal`() {
    fun getKey() = Key(Note.g.flat)

    val first = getKey()
    val second = getKey()

    assertThat(first).isEqualTo(second)
  }

  @Test
  fun `when not key then not equal`() {
    @Suppress("AssertBetweenInconvertibleTypes")
    assertThat(Key.cMajor).isNotEqualTo("hello")
  }

  @Suppress("SpellCheckingInspection")
  companion object {
    @JvmStatic
    fun renderableMinorsTestCases() = renderableMinors.map { Arguments.of(it) }.stream()

    @JvmStatic
    fun notRenderableMinorsTestCases() = (allNotes - renderableMinors.toSet())
      .map { Arguments.of(it) }
      .stream()

    @JvmStatic
    fun renderableMajorsTestCases() = renderableMajors.map { Arguments.of(it) }.stream()

    @JvmStatic
    fun notRenderableMajorsTestCases() = (allNotes - renderableMajors.toSet())
      .map { Arguments.of(it) }
      .stream()

    private val renderableMajors = "C G D A E B F# C# F Bb Eb Ab Db Gb Cb"
      .split(' ')
      .map { TestData.noteFactories.note.getNote(it) }

    private val renderableMinors = "A E B F# C# G# D# A# D G C F Bb Eb Ab"
      .split(' ')
      .map { TestData.noteFactories.note.getNote(it) }

    private val allNotes = "A B C D E F G"
      .split(' ')
      .map { TestData.noteFactories.note.getNote(it) }
      .flatMap { listOf(it, it.sharp, it.flat) }
  }
}
*/
