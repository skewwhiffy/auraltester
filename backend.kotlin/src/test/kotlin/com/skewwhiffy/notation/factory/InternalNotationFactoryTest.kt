package com.skewwhiffy.notation.factory

import com.skewwhiffy.exception.UnrecognizedClefException
import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class InternalNotationFactoryTest {
  @Mock
  private lateinit var clefFactory: ClefFactory

  @Mock
  private lateinit var intervalFactory: IntervalFactory

  @Mock
  private lateinit var keyFactory: KeyFactory

  @Mock
  private lateinit var noteFactory: NoteFactory

  @InjectMocks
  private lateinit var internalNotationFactory: InternalNotationFactory

  @ParameterizedTest
  @ValueSource(strings = ["treble", "alto", "tenor", "bass"])
  fun `proxy to clefFactory when clef is requested`(clef: String) {
    val expected = TestData.random.clef
    val method = ClefFactory::class.members.find { it.name == clef }
    method?.let { `when`(it.call(clefFactory)).thenReturn(expected) }
      ?: fail("Could not find member '$clef' on ClefFactory")

    val actual = internalNotationFactory.clef(clef)

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `throw when clef name invalid`() {
    assertThrows<UnrecognizedClefException> { internalNotationFactory.clef("not a clef name") }
  }

  @Test
  fun `proxy to noteFactory when note is requested`() {
    val rawNote = TestData.random.string
    val expected = TestData.random.absoluteNote
    `when`(noteFactory.getAbsoluteNote(rawNote)).thenReturn(expected)

    val actual = internalNotationFactory.getNote(rawNote)

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `proxy to noteFactory when notes are requested`() {
    val rawNotes = (0..10).map { TestData.random.string }
    val expected = rawNotes.map { TestData.random.absoluteNote }
    rawNotes.zip(expected).forEach {
      `when`(noteFactory.getAbsoluteNote(it.first)).thenReturn(it.second)
    }

    val actual = internalNotationFactory.getNotes(rawNotes.joinToString(" "))

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `proxy to intervalFactory when directed interval is requested`() {
    val rawInterval = TestData.random.string
    val expected = TestData.random.directedInterval
    `when`(intervalFactory.getDirectedInterval(rawInterval)).thenReturn(expected)

    val actual = internalNotationFactory.getDirectedInterval(rawInterval)

    assertThat(actual).isEqualTo(expected)
  }


  @Test
  fun `proxy to intervalFactory when directed intervals are requested`() {
    val rawInterval = TestData.random.string
    val expected = listOf(TestData.random.directedInterval)
    `when`(intervalFactory.getDirectedIntervals(rawInterval)).thenReturn(expected)

    val actual = internalNotationFactory.getDirectedIntervals(rawInterval)

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `proxy to keyFactory when key is requested`() {
    val rawKey = TestData.random.string
    val expected = TestData.random.key
    `when`(keyFactory.getKey(rawKey)).thenReturn(expected)

    val actual = internalNotationFactory.getKey(rawKey)

    assertThat(actual).isEqualTo(expected)
  }
}