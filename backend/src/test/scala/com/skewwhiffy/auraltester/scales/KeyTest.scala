package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Note, Octave}
import org.scalatest.funsuite.AnyFunSuite

class KeyTest extends AnyFunSuite {
  test("when note has accidental in key then no accidental is given in abc") {
    val note = new AbsoluteNote(Note.f.sharp, Octave.default)
    val key = new Key(Note.d)

    val actual = key.abc(note)

    assert(actual == "F")
  }

  test("when note has accidental different to key then note accidental is given in abc") {
    val note = new AbsoluteNote(Note.e.flat, Octave.default)
    val key = new Key(Note.g)

    val actual = key.abc(note)

    assert(actual == "_E")
  }

  test("when note is natural but key has accidental then note accidental is natual in abc") {
    val note = new AbsoluteNote(Note.g, Octave.default)
    val key = new Key(Note.e)

    val actual = key.abc(note)

    assert(actual == "=G")
  }

  test("when relativeMinor then correct") {
    val key = new Key(Note.e.flat)

    val actual = key.relativeMinor

    assert(actual.note == Note.c)
    assert(key.relativeMajor == key)
  }

  test("when relativeMajor then correct") {
    val key = new Key(Note.e.flat, true)

    val actual = key.relativeMajor

    assert(actual.note == Note.g.flat)
    assert(key.relativeMinor == key)
  }

  test("when major then notes correct") {
    val key = new Key(Note.f.sharp, false)
    val expected = "F# G# A# B C# D# E#"

    val actual = key.notes.collect(it => it.displayString).mkString(" ")

    assert(actual == expected)
  }

  test("when minor then notes correct") {
    val key = new Key(Note.a, true)
    val expected = "A B C D E F G"

    val actual = key.notes.collect(it => it.displayString).mkString(" ")

    assert(actual == expected)
  }

  test("when equivalent then equal") {
    def key = new Key(Note.g.flat, false)

    val first = key
    val second = key

    assert(first == second)
  }

  test("when not key then not equal") {
    //noinspection ComparingUnrelatedTypes
    assert(Key.cMajor != "hello")
  }
}
