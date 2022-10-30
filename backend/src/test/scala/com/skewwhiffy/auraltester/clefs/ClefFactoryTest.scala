package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.internalnotation.NoteFactory
import com.skewwhiffy.auraltester.notes.AbsoluteNote
import com.skewwhiffy.auraltester.testutils.{MockInstantiation, TestData}
import org.mockito.{InjectMocks, Mock}
import org.scalatest.Outcome
import org.scalatest.funsuite.AnyFunSuite

class ClefFactoryTest extends AnyFunSuite with MockInstantiation {
  @Mock
  private var noteFactory: NoteFactory = _
  @InjectMocks
  private var clefFactory: ClefFactory = _
  private var highLedgerNote: AbsoluteNote = _
  private var lowLedgerNote: AbsoluteNote = _

  override def withFixture(test: NoArgTest): Outcome = {
    noteFactory = mock[NoteFactory]
    clefFactory = new ClefFactory(noteFactory)

    highLedgerNote = TestData.random.absoluteNote
    lowLedgerNote = TestData.random.absoluteNote
    test()
  }

  test("treble clef initializes correctly") {
    when(noteFactory.getAbsoluteNote("a")).thenReturn(highLedgerNote)

    val actual = clefFactory.treble

    assert(actual.abc == "treble")
    assert(actual.lowLedgerNote == AbsoluteNote.middleC)
    assert(actual.highLedgerNote == highLedgerNote)
  }

  test("alto clef initializes correctly") {
    testGeneric("alto", "D,", "B") {
      clefFactory.alto
    }
  }

  test("tenor clef initializes correctly") {
    testGeneric("tenor", "B,,", "G") {
      clefFactory.tenor
    }
  }

  test("bass clef initializes correctly") {
    when(noteFactory.getAbsoluteNote("E,,")).thenReturn(lowLedgerNote)

    val actual = clefFactory.bass

    assert(actual.abc == "bass")
    assert(actual.lowLedgerNote == lowLedgerNote)
    assert(actual.highLedgerNote == AbsoluteNote.middleC)
  }

  private def testGeneric(
    abc: String,
    expectedLowLedgerNote: String,
    expectedHighLedgerNote: String
  )(getClef: => Clef) = {
    when(noteFactory.getAbsoluteNote(expectedLowLedgerNote)).thenReturn(lowLedgerNote)
    when(noteFactory.getAbsoluteNote(expectedHighLedgerNote)).thenReturn(highLedgerNote)

    val actual = getClef

    assert(actual.abc == abc)
    assert(actual.lowLedgerNote == lowLedgerNote)
    assert(actual.highLedgerNote == highLedgerNote)
  }
}
