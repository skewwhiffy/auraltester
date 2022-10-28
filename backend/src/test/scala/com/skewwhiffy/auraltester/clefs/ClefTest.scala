package com.skewwhiffy.auraltester.clefs

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ClefTest {
  @Test
  def trebleClef(): Unit = {
    val actual = Clef.treble

    assertThat(actual.abc).isEqualTo("treble")
    assertThat(actual.lowLedgerNote.abc).isEqualTo("C")
    assertThat(actual.highLedgerNote.abc).isEqualTo("a")
  }

  @Test
  def altoClef(): Unit = {
    val actual = Clef.alto

    assertThat(actual.abc).isEqualTo("alto")
    assertThat(actual.lowLedgerNote.abc).isEqualTo("D,")
    assertThat(actual.highLedgerNote.abc).isEqualTo("B")
  }

  @Test
  def tenorClef(): Unit = {
    val actual = Clef.tenor

    assertThat(actual.abc).isEqualTo("tenor")
    assertThat(actual.lowLedgerNote.abc).isEqualTo("B,,")
    assertThat(actual.highLedgerNote.abc).isEqualTo("G")
  }

  @Test
  def bassClef(): Unit = {
    val actual = Clef.bass

    assertThat(actual.abc).isEqualTo("bass")
    assertThat(actual.lowLedgerNote.abc).isEqualTo("E,,")
    assertThat(actual.highLedgerNote.abc).isEqualTo("C")
  }
}