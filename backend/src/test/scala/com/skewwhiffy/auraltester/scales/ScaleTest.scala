package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.AbsoluteNote
import org.scalatest.funsuite.AnyFunSuite

class ScaleTest extends AnyFunSuite {
  test("can instantiate major scale") {
    val expected = InternalNotationFactory.notes("C D E F G A B c")

    val actual = new Scale(AbsoluteNote.middleC, ScaleType.major, ScaleDirection.ascending)

    assert(actual.notes == expected)
  }

  test("can instantiate minor harmonic scale") {
    val expected = InternalNotationFactory.notes("D E F G A Bb c# d")

    val actual = new Scale(
      InternalNotationFactory.note("D"),
      ScaleType.minorHarmonic,
      ScaleDirection.ascending
    )

    assert(actual.notes == expected)
  }

  test("can instantiate minor melodic ascending scale") {
    val expected = InternalNotationFactory.notes("E F# G A B c# d# e")

    val actual = new Scale(
      InternalNotationFactory.note("E"),
      ScaleType.minorMelodic,
      ScaleDirection.ascending
    )

    assert(actual.notes == expected)
  }

  test("can instantiate minor melodic descending scale") {
    val expected = InternalNotationFactory.notes("a g f e d c B A")

    val actual = new Scale(
      InternalNotationFactory.note("a"),
      ScaleType.minorMelodic,
      ScaleDirection.descending
    )

    assert(actual.notes == expected)
  }
}