package com.skewwhiffy.auraltester.service

import com.skewwhiffy.auraltester.test.util.TestData
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class IntervalServiceTest {
    @InjectMockKs
    private lateinit var intervalService: IntervalService

    @Test
    fun getsIntervalNotes() {
        val clef = TestData.random.clef
        val note = TestData.random.note
        val interval = TestData.random.interval
        val expectedBottomNote = clef.getNoteNearBottom(note)
        val actual = intervalService.getInterval(clef, note, interval)
        assertThat(actual.bottomNote).isEqualTo(expectedBottomNote)
    }
}