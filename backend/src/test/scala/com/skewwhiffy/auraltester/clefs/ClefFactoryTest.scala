package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.internalnotation.NoteFactory
import com.skewwhiffy.auraltester.notes.AbsoluteNote
import com.skewwhiffy.auraltester.testutils.TestData
import org.scalamock.scalatest.MockFactory
import org.scalatest.Outcome
import org.scalatest.funsuite.AnyFunSuite

class ClefFactoryTest extends AnyFunSuite with MockFactory {
  var noteFactory: NoteFactory = _
  var clefFactory: ClefFactory = _
  var highLedgerNote: AbsoluteNote = _
  var lowLedgerNote: AbsoluteNote = _

  override def withFixture(test: NoArgTest): Outcome = {
    noteFactory = mock[NoteFactory]
    clefFactory = new ClefFactory(noteFactory)

    highLedgerNote = TestData.random.absoluteNote
    lowLedgerNote = TestData.random.absoluteNote
    test()
  }

  test("treble clef initializes correctly") {
    (noteFactory.getAbsoluteNote _).expects("a").returns(highLedgerNote)

    val actual = clefFactory.treble

    assert(actual.abc == "treble")
    assert(actual.lowLedgerNote == AbsoluteNote.middleC)
    assert(actual.highLedgerNote == highLedgerNote)
  }
}
