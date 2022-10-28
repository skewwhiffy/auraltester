package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.AbsoluteNote
import com.skewwhiffy.auraltester.scales.{Scale, ScaleType}
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
    val scaleType = ScaleType.minorMelodicDescending
    val expectedAbc = TestData.random.string
    class MockScale extends Scale(AbsoluteNote.middleC, ScaleType.major) {
      override lazy val abc: String = expectedAbc
    }
    val scale = new MockScale()
    (scaleService.getScale _).expects(clef, note, scaleType).returns(scale)

    val result = scaleController.get(clef.abc, note.noteName, "minor-melodic-descending")

    assert(result.abc.contains(expectedAbc))
  }

  test("when scale type not recognized then throws") {
    assertThrows[IllegalArgumentException] {
      scaleController.get("treble", "B", "demented")
    }
  }
}