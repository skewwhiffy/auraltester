package com.skewwhiffy.auraltester.notes

import org.scalatest.funsuite.AnyFunSuite

class NoteTests extends AnyFunSuite {
  test("when natural then display string correct") {
    val note = Note.a

    val actual = note.displayString

    assert(actual == "A")
  }

  test("when sharp then display string correct") {
    val note = Note.b.sharp

    val actual = note.displayString

    assert(actual == "B#")
  }

  test("when flat then display string correct") {
    val note = Note.d.flat

    val actual = note.displayString

    assert(actual == "Db")
  }

  test("when notes equivalent then equals works") {
    def note = new Note("A", Accidental.sharp)

    val first = note
    val second = note

    assert(first==second)
    assert(first <= second)
    assert(first >= second)
  }

  test("when notes not equivalent then can compare") {
    def lower = Note.c

    def higher = Note.a

    assert(lower < higher)
    assert(!(lower > higher))
    // TODO: > and < with #s and bs
  }
}