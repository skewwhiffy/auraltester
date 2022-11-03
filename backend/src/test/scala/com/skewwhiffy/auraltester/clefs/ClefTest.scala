package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.testutils.TestData
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class ClefTest extends AnyFlatSpec with should.Matchers {
  it should "Retains abc, lowLedgerNote and highLedgerNote" in {
    val abc = TestData.random.string
    val lowLedgerNote = TestData.random.absoluteNote
    val highLedgerNote = TestData.random.absoluteNote

    val actual = new Clef(abc, lowLedgerNote, highLedgerNote)

    assert(actual.abc == abc)
    assert(actual.lowLedgerNote == lowLedgerNote)
    assert(actual.highLedgerNote == highLedgerNote)
  }
}
