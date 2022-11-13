package com.skewwhiffy.auraltester.notes

import org.scalatest.flatspec.AnyFlatSpec

class NoteTests extends AnyFlatSpec {
  it should "return display string when natural" in {
    val note = Note.a

    val actual = note.displayString

    assert(actual == "A")
  }

  it should "return display string when sharp" in {
    val note = Note.b.sharp

    val actual = note.displayString

    assert(actual == "B#")
  }

  it should "return display string when flat" in {
    val note = Note.d.flat

    val actual = note.displayString

    assert(actual == "Db")
  }

  it should "equate equivalent notes" in {
    def note = new Note("A", Accidental.sharp)

    val first = note
    val second = note

    assert(first==second)
    assert(first <= second)
    assert(first >= second)
  }

  it should "compare non-equivalent notes" in {
    def lower = Note.c

    def higher = Note.a

    assert(lower < higher)
    assert(!(lower > higher))
    // TODO: > and < with #s and bs
  }
}