package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.interval.Interval
import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.notation.model.note.IntervalNotes
import com.skewwhiffy.auraltester.notation.model.note.Note
import com.skewwhiffy.auraltester.notation.model.note.Octave
import com.skewwhiffy.auraltester.service.AbcService
import com.skewwhiffy.auraltester.service.IntervalService
import com.skewwhiffy.auraltester.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class IntervalControllerTest {
    companion object {
        @JvmStatic
        fun intervalQualityTestCases() = listOf(
            Arguments.of("perfect", 5, "+5"),
            Arguments.of("major", 7, "+7"),
            Arguments.of("minor", 3, "+3-"),
            Arguments.of("diminished", 4, "+4-"),
            Arguments.of("diminished", 2, "+2--"),
            Arguments.of("augmented", 6, "+6+")
        )

        /*
        static Stream<Arguments> intervalQualityTestCases() {
        }
         */
    }

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
    fun setUp() {
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
    fun respondsCorrectly(
        intervalQuality: String,
        intervalSize: Int,
        expectedDirectedIntervalString: String
    ) {
        `when`(internalNotationFactory.clef(clefString)).thenReturn(clef)
        `when`(internalNotationFactory.getNote(bottomNoteString))
            .thenReturn(AbsoluteNote(bottomNote, Octave.default, null))
        `when`(internalNotationFactory.getNote(keySignature))
            .thenReturn(AbsoluteNote(keyNote, Octave.default, null))
        `when`(intervalService.getInterval(clef, bottomNote, interval)).thenReturn(intervalNotes)
        `when`(result.abc).thenReturn(abc)
        `when`(internalNotationFactory.getDirectedInterval(expectedDirectedIntervalString))
            .thenReturn(interval.up)
        `when`(abcService.getAbc(clef, intervalNotes, Key.major(keyNote)))
            .thenReturn(result)

        val actual = intervalController
            .get(clefString, bottomNoteString, intervalQuality, intervalSize, keySignature)

        assertThat(actual.abc).isEqualTo(abc)
    }

    @Test
    fun when_intervalQualityNotValid_then_throws() {
        `when`(internalNotationFactory.clef(clefString)).thenReturn(clef)
        `when`(internalNotationFactory.getNote(bottomNoteString))
            .thenReturn(AbsoluteNote(bottomNote, Octave.default, null))

        assertThatThrownBy { intervalController.get(clefString, bottomNoteString, "demented", 5, keySignature) }
            .isInstanceOf(IllegalArgumentException::class.java)

    }
}
