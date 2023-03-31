/*
package com.skewwhiffy.notation.model.abc

import com.skewwhiffy.notation.model.key.Key
import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ClefLineNotesTest {
  @Test
  fun `correctly define line note from clef`() {
    val clef = TestData.noteFactories.clef.treble
    val expectedAbc = listOf("E", "G", "B", "d", "f")
    val expectedNames = expectedAbc.map { it.uppercase() }

    val actual = ClefLineNotes(clef)

    actual.notes.let { notes ->
      assertThat(notes.map { it.abc(Key.cMajor)}).isEqualTo(expectedAbc)
      assertThat(notes.map { it.lyric }).isEqualTo(expectedNames)
    }
  }

}
*/
