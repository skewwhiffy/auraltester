package com.skewwhiffy.auraltester.notation.model.abc

import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ClefLowerLedgerNotesTest {
    @Test
    fun correctlyDefinesLowerLedgerNotesFromClef() {
        val clef = TestData.noteFactories.clef.treble
        val expectedAbc = listOf("D", "C", "B,", "A,", "G,", "F,")
        val expectedNames = expectedAbc.map { it[0].uppercase() }

        val actual = ClefLowerLedgerNotes(clef)

        val actualNotes = actual.notes.map { it.getAbc(Key.cMajor) }
        val actualNames = actual.notes.map { it.lyric ?: throw RuntimeException() }
        assertThat(actualNotes).isEqualTo(expectedAbc)
        assertThat(actualNames).isEqualTo(expectedNames)
    }
}