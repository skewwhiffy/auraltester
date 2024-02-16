package com.skewwhiffy.auraltester.notation.model.clef

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ClefTest {
    private lateinit var abc: String
    private lateinit var lowLedgerNote: AbsoluteNote
    private lateinit var highLedgerNote: AbsoluteNote

    @BeforeEach
    fun setUp() {
        abc = TestData.random.string
        lowLedgerNote = TestData.random.absoluteNote
        highLedgerNote = TestData.random.absoluteNote
    }

    @Test
    fun retainAbc_lowLedgerNote_and_highLedgerNote() {
        val actual = Clef(TestData.random.clefType, abc, lowLedgerNote, highLedgerNote)
        assertThat(actual.abc).isEqualTo(abc)
        assertThat(actual.lowLedgerNote).isEqualTo(lowLedgerNote)
        assertThat(actual.highLedgerNote).isEqualTo(highLedgerNote)
    }

    @Test
    fun equatesSameClefs() {
        val first = Clef(TestData.random.clefType, abc, lowLedgerNote, highLedgerNote)
        val second = Clef(first.clefType, abc, lowLedgerNote, highLedgerNote)
        assertThat(first).isEqualTo(second)
    }
}