package com.skewwhiffy.auraltester.clefs

import org.scalatest.funsuite.AnyFunSuite

class ClefTest extends AnyFunSuite {
  test("treble clef") {
    val actual = Clef.treble

    assert(actual.abc == "treble")
    assert(actual.lowLedgerNote.abc == "C")
    assert(actual.highLedgerNote.abc == "a")
  }

  test("alto clef") {
    val actual = Clef.alto

    assert(actual.abc == "alto")
    assert(actual.lowLedgerNote.abc == "D,")
    assert(actual.highLedgerNote.abc == "B")
  }

  test("tenor clef") {
    val actual = Clef.tenor

    assert(actual.abc == "tenor")
    assert(actual.lowLedgerNote.abc == "B,,")
    assert(actual.highLedgerNote.abc == "G")
  }

  test("bass clef") {
    val actual = Clef.bass

    assert(actual.abc == "bass")
    assert(actual.lowLedgerNote.abc == "E,,")
    assert(actual.highLedgerNote.abc == "C")
  }
}
