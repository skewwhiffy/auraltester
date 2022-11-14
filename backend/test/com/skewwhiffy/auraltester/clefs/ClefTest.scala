package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.testutils.TestData
import org.scalatest.flatspec.AnyFlatSpec

class ClefTest extends AnyFlatSpec {
  private val abc = TestData.random.string
  private val lowLedgerNote = TestData.random.absoluteNote
  private val highLedgerNote = TestData.random.absoluteNote

  it should "retain abc, lowLedgerNote and highLedgerNote" in {
    val actual = Clef(abc, lowLedgerNote, highLedgerNote)

    assert(actual.abc == abc)
    assert(actual.lowLedgerNote == lowLedgerNote)
    assert(actual.highLedgerNote == highLedgerNote)
  }

  it should "equate same clefs" in {
    val first = Clef(abc, lowLedgerNote, highLedgerNote)
    val second = Clef(abc, lowLedgerNote, highLedgerNote)

    assert(first == second)
  }
}
