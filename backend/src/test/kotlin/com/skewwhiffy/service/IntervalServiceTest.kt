package com.skewwhiffy.service

import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class IntervalServiceTest {
  @InjectMocks
  private lateinit var intervalService: IntervalService

  @Test
  fun `gets interval notes`() {
    val clef = TestData.random.clef
    val note = TestData.random.note
    val interval = TestData.random.interval
    val expectedBottomNote = clef.getNoteNearBottom(note)

    val actual = intervalService.getInterval(clef, note, interval)

    assertThat(actual.bottomNote).isEqualTo(expectedBottomNote)
  }
}