package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.AbsoluteNote
import com.skewwhiffy.auraltester.testutils.TestData.noteFactories._
import org.scalatest.flatspec.AnyFlatSpec

class ScaleTest extends AnyFlatSpec {
  it should "can instantiate major scale" in {
    val expected = internalNotation.getNotes("C D E F G A B c")

    val actual = new Scale(AbsoluteNote.middleC, scaleTypeFactory.major, ScaleDirection.ascending)

    assert(actual.notes == expected)
  }

  it should "can instantiate minor harmonic scale" in {
    val expected = internalNotation.getNotes("D E F G A Bb c# d")

    val actual = new Scale(
      internalNotation.getNote("D"),
      scaleTypeFactory.minorHarmonic,
      ScaleDirection.ascending
    )

    assert(actual.notes == expected)
  }

  it should "can instantiate minor melodic ascending scale" in {
    val expected = internalNotation.getNotes("E F# G A B c# d# e")

    val actual = new Scale(
      internalNotation.getNote("E"),
      scaleTypeFactory.minorMelodic,
      ScaleDirection.ascending
    )

    assert(actual.notes == expected)
  }

  it should "can instantiate minor melodic descending scale" in {
    val expected = internalNotation.getNotes("a g f e d c B A")

    val actual = new Scale(
      internalNotation.getNote("A"),
      scaleTypeFactory.minorMelodic,
      ScaleDirection.descending
    )

    assert(actual.notes == expected)
  }
}