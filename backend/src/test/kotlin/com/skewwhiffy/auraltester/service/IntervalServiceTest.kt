package com.skewwhiffy.auraltester.service

import com.skewwhiffy.auraltester.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class IntervalServiceTest {
    @Autowired
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