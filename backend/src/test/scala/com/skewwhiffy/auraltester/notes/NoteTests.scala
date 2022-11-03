package com.skewwhiffy.auraltester.notes

import org.scalatest.flatspec.AnyFlatSpec

class NoteTests extends AnyFlatSpec {
  it should "when natural then display string correct" in {
    val note = Note.a

    val actual = note.displayString

    assert(actual == "A")
  }

  it should "when sharp then display string correct" in {
    val note = Note.b.sharp

    val actual = note.displayString

    assert(actual == "B#")
  }

  it should "when flat then display string correct" in {
    val note = Note.d.flat

    val actual = note.displayString

    assert(actual == "Db")
  }

  it should "when notes equivalent then equals works" in {
    def note = new Note("A", Accidental.sharp)

    val first = note
    val second = note

    assert(first==second)
    assert(first <= second)
    assert(first >= second)
  }

  it should "when notes not equivalent then can compare" in {
    def lower = Note.c

    def higher = Note.a

    assert(lower < higher)
    assert(!(lower > higher))
    // TODO: > and < with #s and bs
  }
}