package com.skewwhiffy.service

import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ScaleServiceTest {
  @InjectMocks
  private lateinit var scaleService: ScaleService

  @Test
  fun `instantiates scale`() {
    val clef = TestData.random.clef
    val note = TestData.random.note
    val scaleType = TestData.random.scaleType
    val direction = TestData.random.scaleDirection
    val expectedBottomNote = clef.getNoteNearBottom(note)

    val actual = scaleService.getScale(clef, note, scaleType, direction)

    assertThat(actual.lowestNote).isEqualTo(expectedBottomNote)
  }
}