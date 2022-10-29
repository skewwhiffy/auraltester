package com.skewwhiffy.auraltester.services

import com.skewwhiffy.auraltester.clefs.ClefFactory
import com.skewwhiffy.auraltester.internalnotation.{IntervalFactory, NoteFactory}
import com.skewwhiffy.auraltester.notes.Note
import com.skewwhiffy.auraltester.scales.{Key, ScaleDirection, ScaleTypeFactory}
import org.scalamock.scalatest.MockFactory
import org.scalatest.Outcome
import org.scalatest.funsuite.AnyFunSuite


class ScaleServiceTest extends AnyFunSuite with MockFactory {
  private val noteFactory = new NoteFactory()
  private val clefFactory = new ClefFactory(noteFactory)
  private val intervalFactory = new IntervalFactory()
  private val scaleTypeFactory = new ScaleTypeFactory(intervalFactory)
  private var scaleService: ScaleService = _

  override def withFixture(test: NoArgTest): Outcome = {
    scaleService = new ScaleService
    test()
  }

  test("when major scale requested then abc correct") {
    //noinspection SpellCheckingInspection
    val expected = "DE^FGAB^cd"
    val result = scaleService.getScale(clefFactory.treble, Note.d, scaleTypeFactory.major, ScaleDirection.ascending)

    assert(result.abc(Key.cMajor).contains(expected))
  }

  test("when minor melodic descending requested then abc correct") {
    //noinspection SpellCheckingInspection
    val expected = "agfedcBA"

    val result = scaleService.getScale(clefFactory.treble, Note.a, scaleTypeFactory.minorMelodic, ScaleDirection.descending)

    assert(result.abc(Key.cMajor).contains(expected))
  }
}