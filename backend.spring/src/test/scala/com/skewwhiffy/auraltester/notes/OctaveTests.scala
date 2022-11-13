package com.skewwhiffy.auraltester.notes

import org.scalatest.flatspec.AnyFlatSpec

class OctaveTests extends AnyFlatSpec {
  it should "return higher octave when up called" in {
    def start = new Octave(70)

    def actual = start.up

    assert(actual.offsetFromDefault == start.offsetFromDefault + 1)
  }

  it should "return lower octave when down called" in {
    def start = new Octave(81)

    def actual = start.down

    assert(actual.offsetFromDefault == start.offsetFromDefault - 1)
  }

  it should "equate same octaves" in {
    def octave = new Octave(52)

    val first = octave
    val second = octave

    assert(first==second)
    assert(first >= second)
  }

  it should "be able to compare octaves" in {
    val first = new Octave(20)
    val second = new Octave(21)

    assert(!(first > second))
    assert(first < second)
    assert(!(first >= second))
    assert(first <= second)
  }
}