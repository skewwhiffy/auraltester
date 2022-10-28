package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.Note
import org.scalatest.funsuite.AnyFunSuite

class KeyTest extends AnyFunSuite {
  test("when key has accidental in key then no accidental is given in abc") {
    val note = Note.f.sharp
    val key = new Key(Note.d)

    val actual = key.accidentalAbc(note)

    assert(actual == "")
  }
}
