package com.skewwhiffy.auraltester.services

import com.skewwhiffy.auraltester.clefs.{Clef, ClefFactory}
import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.Note
import com.skewwhiffy.auraltester.scales.{Key, ScaleDirection, ScaleType}
import org.scalamock.scalatest.MockFactory
import org.scalatest.Outcome
import org.scalatest.funsuite.AnyFunSuite


class ScaleServiceTest extends AnyFunSuite with MockFactory {
  private val clefFactory = new ClefFactory()
  private val internalNotationFactory = new InternalNotationFactory(clefFactory)
  private var scaleService: ScaleService = _

  override def withFixture(test: NoArgTest): Outcome = {
    scaleService = new ScaleService
    test()
  }

  test("when major scale requested then abc correct") {
    //noinspection SpellCheckingInspection
    val expected = "DE^FGAB^cd"
    val result = scaleService.getScale(clefFactory.treble, Note.d, ScaleType.major, ScaleDirection.ascending)

    assert(result.abc(Key.cMajor).contains(expected))
  }

  test("when minor melodic descending requested then abc correct") {
    //noinspection SpellCheckingInspection
    val expected = "agfedcBA"

    val result = scaleService.getScale(clefFactory.treble, Note.a, ScaleType.minorMelodic, ScaleDirection.descending)

    assert(result.abc(Key.cMajor).contains(expected))
  }
}