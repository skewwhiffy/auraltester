package com.skewwhiffy.notation.factory

import com.skewwhiffy.notation.model.clef.Clef
import com.skewwhiffy.notation.model.note.AbsoluteNote
import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ClefFactoryTest {
  @Mock
  private lateinit var noteFactory: NoteFactory
  @InjectMocks
  private lateinit var clefFactory: ClefFactory

  private lateinit var highLedgerNote: AbsoluteNote
  private lateinit var lowLedgerNote: AbsoluteNote

  @BeforeEach
  fun `set up`() {
    highLedgerNote = TestData.random.absoluteNote
    lowLedgerNote = TestData.random.absoluteNote
  }

  @Test
  fun`initialize treble clef correctly` () {
    `when`(noteFactory.getAbsoluteNote("a")).thenReturn(highLedgerNote)

    val actual = clefFactory.treble

    assertThat(actual.abc).isEqualTo("treble")
    assertThat(actual.lowLedgerNote).isEqualTo(AbsoluteNote.middleC)
    assertThat(actual.highLedgerNote).isEqualTo(highLedgerNote)
  }

  @Test
  fun`initialize alto clef correctly` () {
    testGeneric("alto", "D,", "B") {
      clefFactory.alto
    }
  }

  @Test
  fun`initialize tenor clef correctly` () {
    testGeneric("tenor", "B,,", "G") {
      clefFactory.tenor
    }
  }

  @Test
  fun`bass clef initializes correctly` () {
    `when`(noteFactory.getAbsoluteNote("E,,")).thenReturn(lowLedgerNote)

    val actual = clefFactory.bass

    assertThat(actual.abc).isEqualTo("bass")
    assertThat(actual.lowLedgerNote).isEqualTo(lowLedgerNote)
    assertThat(actual.highLedgerNote).isEqualTo(AbsoluteNote.middleC)
  }

  private fun testGeneric(
    abc: String,
    expectedLowLedgerNote: String,
    expectedHighLedgerNote: String,
    getClef: () -> Clef
  ) {
    `when`(noteFactory.getAbsoluteNote(expectedLowLedgerNote)).thenReturn(lowLedgerNote)
    `when`(noteFactory.getAbsoluteNote(expectedHighLedgerNote)).thenReturn(highLedgerNote)

    val actual = getClef()

    assertThat(actual.abc).isEqualTo(abc)
    assertThat(actual.lowLedgerNote).isEqualTo(lowLedgerNote)
    assertThat(actual.highLedgerNote).isEqualTo(highLedgerNote)
  }
}