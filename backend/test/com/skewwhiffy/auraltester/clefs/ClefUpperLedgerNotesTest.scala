package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.scales.Key
import com.skewwhiffy.auraltester.testutils.TestData
import org.scalatest.flatspec.AnyFlatSpec

class ClefUpperLedgerNotesTest extends AnyFlatSpec {
  it should "correctly define lower ledger notes from clef" in {
    val clef = TestData.noteFactories.clef.treble
    val expectedAbc = List("g", "a", "b", "c'", "d'", "e'")
    val expectedNames = expectedAbc.map(it => it.substring(0, 1).toUpperCase)

    val actual = ClefUpperLedgerNotes(clef)

    assert(actual.notes.map(it => it.abc(Key.cMajor)) == expectedAbc)
    assert(actual.notes.map(it => it.lyric) == expectedNames.map(it => Some(it)))
  }
}
