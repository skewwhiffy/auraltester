package com.skewwhiffy.auraltester.notation.model.note

import com.skewwhiffy.auraltester.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class IntervalNotesTest {
    @Test
    fun instantiatesCorrectly() {
        val bottomNote = TestData.random.absoluteNote
        val interval = TestData.random.interval
        val expectedNotes = listOf(bottomNote, bottomNote.plus(interval))
        val actual = IntervalNotes(bottomNote, interval)
        assertThat(actual.notes).isEqualTo(expectedNotes)
    }
}