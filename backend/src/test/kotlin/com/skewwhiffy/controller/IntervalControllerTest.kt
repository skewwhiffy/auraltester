package com.skewwhiffy.controller

import com.skewwhiffy.notation.factory.InternalNotationFactory
import com.skewwhiffy.notation.model.abc.AbcProvider
import com.skewwhiffy.notation.model.clef.Clef
import com.skewwhiffy.notation.model.interval.Interval
import com.skewwhiffy.notation.model.key.Key
import com.skewwhiffy.notation.model.note.AbsoluteNote
import com.skewwhiffy.notation.model.note.IntervalNotes
import com.skewwhiffy.notation.model.note.Note
import com.skewwhiffy.notation.model.note.Octave
import com.skewwhiffy.service.AbcService
import com.skewwhiffy.service.IntervalService
import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.lang.IllegalArgumentException
import java.util.stream.Stream

@ExtendWith(MockitoExtension::class)
class IntervalControllerTest {
  @Mock
  private lateinit var abcService: AbcService

  @Mock
  private lateinit var internalNotationFactory: InternalNotationFactory

  @Mock
  private lateinit var intervalService: IntervalService

  @InjectMocks
  private lateinit var intervalController: IntervalController

  @Mock
  private lateinit var result: AbcProvider

  private lateinit var clefString: String
  private lateinit var clef: Clef
  private lateinit var bottomNoteString: String
  private lateinit var bottomNote: Note
  private lateinit var keySignature: String
  private lateinit var keyNote: Note
  private lateinit var intervalNotes: IntervalNotes
  private lateinit var interval: Interval
  private lateinit var abc: String

  @BeforeEach
  fun `set up`() {
    clefString = TestData.random.string
    clef = TestData.random.clef
    bottomNoteString = TestData.random.string
    bottomNote = TestData.random.note
    keySignature = TestData.random.string
    keyNote = TestData.random.note
    intervalNotes = TestData.random.intervalNotes
    interval = TestData.random.interval
    abc = TestData.random.string

  }

  @ParameterizedTest
  @MethodSource("intervalQualityTestCases")
  fun `responds correctly`(
    intervalQuality: String,
    intervalSize: Int,
    expectedDirectedIntervalString: String,
  ) {
    `when`(internalNotationFactory.clef(clefString)).thenReturn(clef)
    `when`(internalNotationFactory.getNote(bottomNoteString)) .thenReturn(AbsoluteNote(bottomNote, Octave.default))
    `when`(internalNotationFactory.getNote(keySignature)) .thenReturn(AbsoluteNote(keyNote, Octave.default))
    `when`(intervalService.getInterval(clef, bottomNote, interval)).thenReturn(intervalNotes)
    `when`(result.abc).thenReturn(abc)
    `when`(internalNotationFactory.getDirectedInterval(expectedDirectedIntervalString)) .thenReturn(interval.up)
    `when`(abcService.getAbc(clef, intervalNotes, Key(keyNote)))
      .thenReturn(result)

    val actual = intervalController
      .get(clefString, bottomNoteString, intervalQuality, intervalSize, keySignature)

    assertThat(actual.abc).isEqualTo(abc)
  }

  @Test
  fun `when interval quality not valid then throws exception`() {
    `when`(internalNotationFactory.clef(clefString)).thenReturn(clef)
    `when`(internalNotationFactory.getNote(bottomNoteString)) .thenReturn(AbsoluteNote(bottomNote, Octave.default))

    assertThrows<IllegalArgumentException> {
      intervalController.get(clefString, bottomNoteString, "demented", 5, keySignature)
    }
  }

  companion object {
    @JvmStatic
    fun intervalQualityTestCases(): Stream<Arguments> {
      return listOf(
        Arguments.of("perfect", 5, "+5"),
        Arguments.of("major", 7, "+7"),
        Arguments.of("minor", 3, "+3-"),
        Arguments.of("diminished", 4, "+4-"),
        Arguments.of("diminished", 2, "+2--"),
        Arguments.of("augmented", 6, "+6+")
      ).stream()
    }
  }

}