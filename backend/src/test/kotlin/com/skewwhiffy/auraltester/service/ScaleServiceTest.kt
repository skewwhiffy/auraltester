package com.skewwhiffy.auraltester.service

import com.skewwhiffy.auraltester.test.util.TestData
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ScaleServiceTest {
    @InjectMockKs
    private lateinit var scaleService: ScaleService

    @Test
    fun instantiatesScale() {
        val clef = TestData.random.clef
        val note = TestData.random.note
        val scaleType = TestData.random.scaleType
        val direction = TestData.random.scaleDirection
        val expectedBottomNote = clef.getNoteNearBottom(note)
        val actual = scaleService.getScale(clef, note, scaleType, direction)
        assertThat(actual.lowestNote).isEqualTo(expectedBottomNote)
    }
}