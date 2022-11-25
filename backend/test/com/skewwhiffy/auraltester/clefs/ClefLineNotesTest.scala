package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.scales.Key
import com.skewwhiffy.auraltester.testutils.TestData
import org.scalatest.flatspec.AnyFlatSpec

class ClefLineNotesTest extends AnyFlatSpec {
  it should "correctly define line note from clef" in {
    val clef = TestData.noteFactories.clef.treble
    val expectedAbc = List("E", "G", "B", "d", "f")
    val expectedNames = expectedAbc.map(it => it.toUpperCase)

    val actual = ClefLineNotes(clef)

    assert(actual.notes.map(it => it.abc(Key.cMajor)) == expectedAbc)
    assert(actual.notes.map(it => it.lyric) == expectedNames.map(it => Some(it)))
  }

}
