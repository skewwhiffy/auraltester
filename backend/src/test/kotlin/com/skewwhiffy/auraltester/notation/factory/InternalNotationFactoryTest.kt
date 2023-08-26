package com.skewwhiffy.auraltester.notation.factory

import com.skewwhiffy.auraltester.exception.UnrecognizedClefException
import com.skewwhiffy.auraltester.test.util.TestData
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.reflect.InvocationTargetException

@ExtendWith(MockKExtension::class)
class InternalNotationFactoryTest {
    @MockK
    private lateinit var clefFactory: ClefFactory

    @MockK
    private lateinit var intervalFactory: IntervalFactory

    @MockK
    private lateinit var keyFactory: KeyFactory

    @MockK
    private lateinit var noteFactory: NoteFactory

    @InjectMockKs
    private lateinit var internalNotationFactory: InternalNotationFactory

    @ParameterizedTest
    @ValueSource(strings = ["treble", "alto", "tenor", "bass"])
    @Throws(InvocationTargetException::class, IllegalAccessException::class)
    fun when_clefIsRequested_then_proxiesToClefFactory(clef: String) {
        val expected = TestData.random.clef
        val method = ClefFactory::class.members.first { it.name == clef }
        every { method.call(clefFactory) } returns expected

        val actual = internalNotationFactory.clef(clef)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun when_clefNameInvalid_then_throws() {
        assertThatThrownBy { internalNotationFactory.clef("not a clef name") }
            .isInstanceOf(UnrecognizedClefException::class.java)
    }

    @Test
    fun when_noteIsRequested_then_proxiesToNoteFactory() {
        val rawNote = TestData.random.string
        val expected = TestData.random.absoluteNote
        every { noteFactory.getAbsoluteNote(rawNote) } returns expected
        val actual = internalNotationFactory.getNote(rawNote)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun when_notesAreRequested_then_proxiesToNoteFactory() {
        val rawNotes = (1..9).map { TestData.random.string }
        val expected = rawNotes.map { TestData.random.absoluteNote }
        rawNotes.zip(expected).forEach { every { noteFactory.getAbsoluteNote(it.first) } returns it.second }
        val notesRaw = rawNotes.joinToString(" ")
        val actual = internalNotationFactory.getNotes(notesRaw)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun when_directedIntervalIsRequested_then_proxiesToIntervalFactory() {
        val rawInterval = TestData.random.string
        val expected = TestData.random.directedInterval
        every { intervalFactory.getDirectedInterval(rawInterval) } returns expected
        val actual = internalNotationFactory.getDirectedInterval(rawInterval)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun when_directedIntervalsAreRequested_then_proxiesToIntervalFactory() {
        val rawInterval = TestData.random.string
        val expected = listOf(TestData.random.directedInterval)
        every { intervalFactory.getDirectedIntervals(rawInterval)} returns expected
        val actual = internalNotationFactory.getDirectedIntervals(rawInterval)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun when_keyIsRequested_then_proxiesToKeyFactory() {
        val rawKey = TestData.random.string
        val expected = TestData.random.key
        every {keyFactory.getKey(rawKey)} returns expected
        val actual = internalNotationFactory.getKey(rawKey)
        assertThat(actual).isEqualTo(expected)
    }
}