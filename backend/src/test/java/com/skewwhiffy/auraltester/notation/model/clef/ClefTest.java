package com.skewwhiffy.notation.model.clef

import com.skewwhiffy.notation.model.clef.Clef
import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ClefTest {
  private val abc = TestData.random.string
  private val lowLedgerNote = TestData.random.absoluteNote
  private val highLedgerNote = TestData.random.absoluteNote

  @Test
  fun `retain abc, lowLedgerNote and highLedgerNote`() {
    val actual = Clef(abc, lowLedgerNote, highLedgerNote)

    assertThat(actual.abc).isEqualTo(abc)
    assertThat(actual.lowLedgerNote).isEqualTo(lowLedgerNote)
    assertThat(actual.highLedgerNote).isEqualTo(highLedgerNote)
  }

  @Test
  fun `equate same clefs`() {
    val first = Clef(abc, lowLedgerNote, highLedgerNote)
    val second = Clef(abc, lowLedgerNote, highLedgerNote)

    assertThat(first).isEqualTo(second)
  }
}