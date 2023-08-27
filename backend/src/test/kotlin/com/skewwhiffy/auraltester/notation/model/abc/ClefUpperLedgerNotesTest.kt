package com.skewwhiffy.auraltester.notation.model.abc

import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ClefUpperLedgerNotesTest {
    @Test
    fun correctlyDefineLowerLedgerNotesFromClef() {
        val clef = TestData.noteFactories.clef.treble
        val expectedAbc = listOf("g", "a", "b", "c'", "d'", "e'")
        val expectedNames = expectedAbc.map { it[0].uppercase() }

        val actual = ClefUpperLedgerNotes(clef)

        val actualAbc = actual.notes.map { it.getAbc(Key.cMajor) }
        val actualNotes = actual.notes.map { it.lyric ?: throw RuntimeException() }
        assertThat(actualAbc).isEqualTo(expectedAbc)
        assertThat(actualNotes).isEqualTo(expectedNames)
    }
}
