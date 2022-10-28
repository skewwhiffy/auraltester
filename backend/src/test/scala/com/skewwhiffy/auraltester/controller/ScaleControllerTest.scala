package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.scales.{Key, Scale, ScaleDirection, ScaleType}
import com.skewwhiffy.auraltester.services.ScaleService
import com.skewwhiffy.auraltester.testutils.TestData
import org.scalamock.scalatest.MockFactory
import org.scalatest.Outcome
import org.scalatest.funsuite.AnyFunSuite

class ScaleControllerTest extends AnyFunSuite with MockFactory {
  private var scaleService: ScaleService = _
  private var scaleController: ScaleController = _

  override def withFixture(test: NoArgTest): Outcome = {
    scaleService = mock[ScaleService]
    scaleController = new ScaleController(scaleService)
    test()
  }

  test("abc correct") {
    val clef = InternalNotationFactory.clef("treble")
    val note = InternalNotationFactory.note("A").note
    val scaleType = ScaleType.minorMelodic
    val expectedAbc = TestData.random.string
    val direction = ScaleDirection.descending
    val key = Key.cMajor
    val scale = mock[Scale]
    (scale.abc _).expects(key).returns(expectedAbc)
    (scaleService.getScale _).expects(clef, note, scaleType, direction).returns(scale)

    val result = scaleController.get(
      clef.abc,
      note.noteName,
      "minor-melodic",
      "descending",
      withKeySignature = false
    )

    assert(result.abc.contains(expectedAbc))
  }

  test("abc correct with key signature") {
    ???
  }

  test("when scale type not recognized then throws") {
    assertThrows[IllegalArgumentException] {
      scaleController.get("treble", "B", "demented", "ascending", withKeySignature = false)
    }
  }
}