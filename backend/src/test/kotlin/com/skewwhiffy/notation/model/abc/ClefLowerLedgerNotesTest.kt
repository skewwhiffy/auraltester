package com.skewwhiffy.notation.model.abc

import com.skewwhiffy.notation.model.key.Key
import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ClefLowerLedgerNotesTest {
  @Test
  fun `correctly define lower ledger notes from clef`() {
    val clef = TestData.noteFactories.clef.treble
    val expectedAbc = listOf("D", "C", "B,", "A,", "G,", "F,")
    val expectedNames = expectedAbc.map { it.substring(0, 1).uppercase() }

    val actual = ClefLowerLedgerNotes(clef)

    assertThat(actual.notes.map { it.abc(Key.cMajor) }).isEqualTo(expectedAbc)
    assertThat(actual.notes.map { it.lyric }).isEqualTo(expectedNames)
  }
}
