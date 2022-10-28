package com.skewwhiffy.auraltester.services

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.Note
import com.skewwhiffy.auraltester.scales.ScaleType
import org.scalamock.scalatest.MockFactory
import org.scalatest.Outcome
import org.scalatest.funsuite.AnyFunSuite

class ScaleServiceTest extends AnyFunSuite with MockFactory {
  private var scaleService: ScaleService = _

  override def withFixture(test: NoArgTest): Outcome = {
    scaleService = new ScaleService
    test()
  }

  test("when major scale requested then abc correct") {
    //noinspection SpellCheckingInspection
    val expected = "DE^FGAB^cd"
    val result = scaleService.getScale(Clef.treble, Note.D, ScaleType.major)

    assert(result.abc.contains(expected))
  }

  test("when minor melodic descending requested then abc correct") {
    //noinspection SpellCheckingInspection
    val expected = "agfedcBA"

    val result = scaleService.getScale(Clef.treble, Note.A, ScaleType.minorMelodicDescending)

    assert(result.abc.contains(expected))
  }
}