package com.skewwhiffy.notation.model.note

import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class IntervalNotesTest {
  @Test
  fun `instantiates correctly`() {
    val bottomNote = TestData.random.absoluteNote
    val interval = TestData.random.interval
    val expectedNotes = listOf(bottomNote, bottomNote + interval)

    val actual = IntervalNotes(bottomNote, interval)

    assertThat(actual.notes).isEqualTo(expectedNotes)
  }
}