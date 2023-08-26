package com.skewwhiffy.auraltester.notation.factory

import com.ninjasquad.springmockk.MockkBean
import com.skewwhiffy.auraltester.controller.ClefType
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.test.util.TestData
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ClefFactoryTest {
    companion object {
        @JvmStatic
        fun clefTypeTestCases(): List<Arguments> {
            return listOf(
                Arguments.of(ClefType.TREBLE, ClefFactory::treble),
                Arguments.of(ClefType.ALTO, ClefFactory::alto),
                Arguments.of(ClefType.TENOR, ClefFactory::tenor),
                Arguments.of(ClefType.BASS, ClefFactory::bass)
            )
        }
    }

    @MockkBean
    private lateinit var noteFactory: NoteFactory

    @Autowired
    private lateinit var clefFactory: ClefFactory
    private lateinit var highLedgerNote: AbsoluteNote
    private lateinit var lowLedgerNote: AbsoluteNote

    @BeforeEach
    fun setUp() {
        highLedgerNote = TestData.random.absoluteNote
        lowLedgerNote = TestData.random.absoluteNote
        every { noteFactory.getAbsoluteNote(any()) } returns mockk<AbsoluteNote>()
    }

    @ParameterizedTest
    @MethodSource("clefTypeTestCases")
    fun getFromClefTypeWorks(clefType: ClefType, getExpected: (ClefFactory) -> Clef) {
        val expected = getExpected(clefFactory)

        val actual = clefFactory.get(clefType)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun initializedTrebleClefCorrectly() {
        every { noteFactory.getAbsoluteNote("a") } returns (highLedgerNote)
        val actual = clefFactory.treble
        assertThat(actual.abc).isEqualTo("treble")
        assertThat(actual.lowLedgerNote).isEqualTo(AbsoluteNote.middleC)
        assertThat(actual.highLedgerNote).isEqualTo(highLedgerNote)
    }

    @Test
    fun initializesAltoClefCorrectly() {
        testGeneric("alto", "D,", "B") { clefFactory.alto }
    }

    @Test
    fun initializesTenorClefCorrectly() {
        testGeneric("tenor", "B,,", "G") { clefFactory.tenor }
    }

    @Test
    fun initializesBassClefCorrectly() {
        every { noteFactory.getAbsoluteNote("E,,") } returns lowLedgerNote
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
        every { noteFactory.getAbsoluteNote(expectedLowLedgerNote) } returns lowLedgerNote
        every { noteFactory.getAbsoluteNote(expectedHighLedgerNote) } returns highLedgerNote
        val actual = getClef()
        assertThat(actual.abc).isEqualTo(abc)
        assertThat(actual.lowLedgerNote).isEqualTo(lowLedgerNote)
        assertThat(actual.highLedgerNote).isEqualTo(highLedgerNote)
    }

}