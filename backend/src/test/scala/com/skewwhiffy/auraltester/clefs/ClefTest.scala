package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.scales.Key
import org.scalatest.Outcome
import org.scalatest.funsuite.AnyFunSuite

class ClefTest extends AnyFunSuite {
  private var clefFactory: ClefFactory = _

  override protected def withFixture(test: NoArgTest): Outcome = {
    clefFactory = new ClefFactory()
    test()
  }

  test("treble clef") {
    val actual = clefFactory.treble

    assert(actual.abc == "treble")
    assert(actual.lowLedgerNote.abc(Key.cMajor) == "C")
    assert(actual.highLedgerNote.abc(Key.cMajor) == "a")
  }

  test("alto clef") {
    val actual = clefFactory.alto

    assert(actual.abc == "alto")
    assert(actual.lowLedgerNote.abc(Key.cMajor) == "D,")
    assert(actual.highLedgerNote.abc(Key.cMajor) == "B")
  }

  test("tenor clef") {
    val actual = clefFactory.tenor

    assert(actual.abc == "tenor")
    assert(actual.lowLedgerNote.abc(Key.cMajor) == "B,,")
    assert(actual.highLedgerNote.abc(Key.cMajor) == "G")
  }

  test("bass clef") {
    val actual = clefFactory.bass

    assert(actual.abc == "bass")
    assert(actual.lowLedgerNote.abc(Key.cMajor) == "E,,")
    assert(actual.highLedgerNote.abc(Key.cMajor) == "C")
  }
}
