package com.skewwhiffy.auraltester.notes

import org.scalatest.funsuite.AnyFunSuite

class OctaveTests extends AnyFunSuite {
  test("when getAbc in default octave then correct response") {
    val octave = Octave.default
    val note = Note.F
    val expected = "F"

    val actual = octave.getAbc(note)

    assert(actual == expected)
  }

  List(1, 5).foreach(octavesHigher => {
    test(s"when getAbc in octave $octavesHigher higher then correct response") {
      val octave = List.range(0, octavesHigher).foldRight(Octave.default)((_, it) => it.up)
      val note = Note.E
      val expected = "e" + "'".repeat(octavesHigher - 1)

      val actual = octave.getAbc(note)

      assert(actual == expected)
    }
  })

  List(1, 6).foreach(octavesLower => {
    test(s"when getAbc in octave $octavesLower lower then correct response") {
      val octave = List.range(0, octavesLower).foldRight(Octave.default)((_, it) => it.down)
      val note = Note.G
      val expected = "G" + ",".repeat(octavesLower)

      val actual = octave.getAbc(note)

      assert(actual == expected)
    }
  })

  test("when same octaves then equal") {
    def octave = new Octave(52)

    val first = octave
    val second = octave

    assert(first==second)
    assert(first >= second)
  }

  test("when first higher than second then relative operator works") {
    val first = new Octave(20)
    val second = new Octave(21)

    assert(!(first > second))
    assert(first < second)
    assert(!(first >= second))
    assert(first <= second)
  }
}