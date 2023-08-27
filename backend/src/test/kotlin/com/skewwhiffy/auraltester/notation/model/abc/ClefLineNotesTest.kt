package com.skewwhiffy.auraltester.notation.model.abc

import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ClefLineNotesTest {
    @Test
    fun correctlyDefinesLineNotesFromClef() {
        val clef = TestData.noteFactories.clef.treble
        val expectedAbc = listOf("E", "G", "B", "d", "f")
        val expectedNames = expectedAbc.map { it.uppercase() }
        val actual = ClefLineNotes(clef)
        val actualAbc = actual.notes.map { it.getAbc(Key.cMajor) }
        val actualNames = actual.notes.map { it.lyric ?: throw RuntimeException() }
        assertThat(actualAbc).isEqualTo(expectedAbc)
        assertThat(actualNames).isEqualTo(expectedNames)
    }
}