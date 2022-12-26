package com.skewwhiffy.notation.model.abc

import com.skewwhiffy.notation.model.key.Key
import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ClefSpaceNotesTest {
  @Test
  fun `correctly define line note from clef`() {
    val clef = TestData.noteFactories.clef.treble
    val expectedAbc = listOf("F", "A", "c", "e")
    val expectedNames = expectedAbc.map { it.uppercase() }

    val actual = ClefSpaceNotes(clef)

    assertThat(actual.notes.map { it.abc(Key.cMajor) }).isEqualTo(expectedAbc)
    assertThat(actual.notes.map { it.lyric }).isEqualTo(expectedNames)
  }
}