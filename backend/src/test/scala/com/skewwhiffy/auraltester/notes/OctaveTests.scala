package com.skewwhiffy.auraltester.notes

import org.scalatest.funsuite.AnyFunSuite

class OctaveTests extends AnyFunSuite {
  test("when up then higher octave") {
    def start = new Octave(70)

    def actual = start.up

    assert(actual.offsetFromDefault == start.offsetFromDefault + 1)
  }

  test("when down then lower octave") {
    def start = new Octave(81)

    def actual = start.down

    assert(actual.offsetFromDefault == start.offsetFromDefault - 1)
  }

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