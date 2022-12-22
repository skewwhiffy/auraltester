package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Note, Octave}
import com.skewwhiffy.auraltester.testutils.TestData
import org.scalatest.flatspec.AnyFlatSpec

class KeyTest extends AnyFlatSpec {
  it should "when note has accidental in key then no accidental is given in abc" in {
    val note = AbsoluteNote(Note.f.sharp, Octave.default)
    val key = Key(Note.d)

    val actual = key.abc(note)

    assert(actual == "F")
  }

  it should "when note has accidental different to key then note accidental is given in abc" in {
    val note = AbsoluteNote(Note.e.flat, Octave.default)
    val key = Key(Note.g)

    val actual = key.abc(note)

    assert(actual == "_E")
  }

  it should "when note is natural but key has accidental then note accidental is natural in abc" in {
    val note = AbsoluteNote(Note.g, Octave.default)
    val key = Key(Note.e)

    val actual = key.abc(note)

    assert(actual == "=G")
  }

  private val renderableMajors = "C G D A E B F# C# F Bb Eb Ab Db Gb Cb"
    .split(' ')
    .map(it => TestData.noteFactories.note.getNote(it))

  private val renderableMinors = "A E B F# C# G# D# A# D G C F Bb Eb Ab"
    .split(' ')
    .map(it => TestData.noteFactories.note.getNote(it))

  private val allNotes = "A B C D E F G"
    .split(' ')
    .map(it => TestData.noteFactories.note.getNote(it))
    .flatMap(it => List(it, it.sharp, it.flat))

  renderableMajors.foreach { note =>
    it should s"can render ${note.displayString} major" in {
      val key = Key(note)

      assert(key.canRenderSignature)
    }
  }

  allNotes.filter(it => !renderableMajors.contains(it)).foreach { note =>
    it should s"cannot render ${note.displayString} major" in {
      val key = Key(note)

      assert(!key.canRenderSignature)
    }
  }

  allNotes.filter(it => !renderableMinors.contains(it)).foreach { note =>
    it should s"cannot render ${note.displayString} minor" in {
      val key = Key(note, isMinor = true)

      assert(!key.canRenderSignature)
    }
  }

  renderableMinors.foreach { note =>
    it should s"can render ${note.displayString} minor" in {
      val key = Key(note, isMinor = true)

      assert(key.canRenderSignature)
    }
  }

  it should "when relativeMinor then correct" in {
    val key = Key(Note.e.flat)

    val actual = key.relativeMinor

    assert(actual.note == Note.c)
    assert(key.relativeMajor == key)
  }

  it should "when relativeMajor then correct" in {
    val key = Key(Note.e.flat, isMinor = true)

    val actual = key.relativeMajor

    assert(actual.note == Note.g.flat)
    assert(key.relativeMinor == key)
  }

  it should "when major then notes correct" in {
    val key = Key(Note.f.sharp)
    val expected = "F# G# A# B C# D# E#"

    val actual = key.notes.collect(it => it.displayString).mkString(" ")

    assert(actual == expected)
  }

  it should "when minor then notes correct" in {
    val key = Key(Note.a, isMinor = true)
    val expected = "A B C D E F G"

    val actual = key.notes.collect(it => it.displayString).mkString(" ")

    assert(actual == expected)
  }

  it should "when equivalent then equal" in {
    def key = Key(Note.g.flat)

    val first = key
    val second = key

    assert(first == second)
  }

  it should "when not key then not equal" in {
    //noinspection ComparingUnrelatedTypes
    assert(Key.cMajor != "hello")
  }
}
