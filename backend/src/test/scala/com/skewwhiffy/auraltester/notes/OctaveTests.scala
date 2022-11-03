package com.skewwhiffy.auraltester.notes

import org.scalatest.flatspec.AnyFlatSpec

class OctaveTests extends AnyFlatSpec {
  it should "when up then higher octave" in {
    def start = new Octave(70)

    def actual = start.up

    assert(actual.offsetFromDefault == start.offsetFromDefault + 1)
  }

  it should "when down then lower octave" in {
    def start = new Octave(81)

    def actual = start.down

    assert(actual.offsetFromDefault == start.offsetFromDefault - 1)
  }

  it should "when same octaves then equal" in {
    def octave = new Octave(52)

    val first = octave
    val second = octave

    assert(first==second)
    assert(first >= second)
  }

  it should "when first higher than second then relative operator works" in {
    val first = new Octave(20)
    val second = new Octave(21)

    assert(!(first > second))
    assert(first < second)
    assert(!(first >= second))
    assert(first <= second)
  }
}