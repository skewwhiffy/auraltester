package com.skewwhiffy.notation.factory

import org.junit.jupiter.api.Test

class ClefFactoryTest {
  @Test
  fun dummy(): Unit = TODO()
  /*
class ClefFactoryTest extends AnyFlatSpec with MockInstantiation with BeforeAndAfter {
  @Mock
  private var noteFactory: NoteFactory = _
  @InjectMocks
  private var clefFactory: ClefFactory = _

  private var highLedgerNote: AbsoluteNote = _
  private var lowLedgerNote: AbsoluteNote = _

  before {
    highLedgerNote = TestData.random.absoluteNote
    lowLedgerNote = TestData.random.absoluteNote
  }

  it should "initialize treble clef correctly" in {
    when(noteFactory.getAbsoluteNote("a")).thenReturn(highLedgerNote)

    val actual = clefFactory.treble

    assert(actual.abc == "treble")
    assert(actual.lowLedgerNote == AbsoluteNote.middleC)
    assert(actual.highLedgerNote == highLedgerNote)
  }

  it should "initialize alto clef correctly" in {
    testGeneric("alto", "D,", "B") {
      clefFactory.alto
    }
  }

  it should "initialize tenor clef correctly" in {
    testGeneric("tenor", "B,,", "G") {
      clefFactory.tenor
    }
  }

  it should "bass clef initializes correctly" in {
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

   */
}