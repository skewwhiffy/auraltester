package com.skewwhiffy.auraltester.services

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.Note
import com.skewwhiffy.auraltester.scales.ScaleType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(Array(classOf[MockitoExtension]))
class ScaleServiceTest {
  @InjectMocks
  private var scaleService: ScaleService = _

  @Test
  def when_majorScaleRequested_then_abcCorrect(): Unit = {
    //noinspection SpellCheckingInspection
    val expected = "DE^FGAB^cd"
    val result = scaleService.getScale(Clef.treble, Note.D, ScaleType.major)

    assertThat(result.abc).contains(expected)
  }

  @Test
  def when_minorMelodicDescendingRequested_then_abcCorrect(): Unit = {
    //noinspection SpellCheckingInspection
    val expected = "agfedcBA"

    val result = scaleService.getScale(Clef.treble, Note.A, ScaleType.minorMelodicDescending)

    assertThat(result.abc).contains(expected)
  }
}
