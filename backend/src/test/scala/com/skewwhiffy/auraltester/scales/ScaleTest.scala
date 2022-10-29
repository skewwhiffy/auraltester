package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.clefs.ClefFactory
import com.skewwhiffy.auraltester.internalnotation.{InternalNotationFactory, IntervalFactory, NoteFactory}
import com.skewwhiffy.auraltester.notes.AbsoluteNote
import org.scalatest.funsuite.AnyFunSuite

class ScaleTest extends AnyFunSuite {
  private val noteFactory = new NoteFactory()
  private val clefFactory = new ClefFactory(noteFactory)
  private val intervalFactory = new IntervalFactory()
  private val scaleTypeFactory = new ScaleTypeFactory(intervalFactory)
  private val internalNotationFactory: InternalNotationFactory = new InternalNotationFactory(
    clefFactory,
    noteFactory,
    intervalFactory
  )

  test("can instantiate major scale") {
    val expected = internalNotationFactory.getNotes("C D E F G A B c")

    val actual = new Scale(AbsoluteNote.middleC, scaleTypeFactory.major, ScaleDirection.ascending)

    assert(actual.notes == expected)
  }

  test("can instantiate minor harmonic scale") {
    val expected = internalNotationFactory.getNotes("D E F G A Bb c# d")

    val actual = new Scale(
      internalNotationFactory.getNote("D"),
      scaleTypeFactory.minorHarmonic,
      ScaleDirection.ascending
    )

    assert(actual.notes == expected)
  }

  test("can instantiate minor melodic ascending scale") {
    val expected = internalNotationFactory.getNotes("E F# G A B c# d# e")

    val actual = new Scale(
      internalNotationFactory.getNote("E"),
      scaleTypeFactory.minorMelodic,
      ScaleDirection.ascending
    )

    assert(actual.notes == expected)
  }

  test("can instantiate minor melodic descending scale") {
    val expected = internalNotationFactory.getNotes("a g f e d c B A")

    val actual = new Scale(
      internalNotationFactory.getNote("A"),
      scaleTypeFactory.minorMelodic,
      ScaleDirection.descending
    )

    assert(actual.notes == expected)
  }
}