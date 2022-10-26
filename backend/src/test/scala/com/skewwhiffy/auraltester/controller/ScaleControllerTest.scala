package com.skewwhiffy.auraltester.controller

import org.assertj.core.api.Assertions.{assertThat, assertThatThrownBy}
import org.junit.jupiter.api.Test

class ScaleControllerTest:
  private val scaleController = ScaleController()

  @Test
  def when_majorScaleRequested_then_abcCorrect(): Unit = {
    val result = scaleController.get("treble", "D", "major")

    //noinspection SpellCheckingInspection
    assertThat(result.abc).contains("DE^FGAB^cd")
    assertThat(result.abc).contains("T:D major")
    assertThat(result.abc).contains("K:clef=treble")
  }

  @Test
  def when_minorHarmonicScaleRequested_then_abcCorrect(): Unit = {
    val result = scaleController.get("bass", "C", "minor-harmonic")

    assertThat(result.abc).contains("C,D,_E,F,G,_A,B,C")
    assertThat(result.abc).contains("T:C minor harmonic")
    assertThat(result.abc).contains("K:clef=bass")
  }

  @Test
  def when_minorMelodicAscendingRequested_then_abcCorrect(): Unit = {
    val result = scaleController.get("alto", "E", "minor-melodic-ascending")

    assertThat(result.abc).contains("E,^F,G,A,B,^C^DE")
    assertThat(result.abc).contains("T:E minor melodic ascending")
    assertThat(result.abc).contains("K:clef=alto")
  }

  @Test
  def when_minorMelodicDescendingRequested_then_abcCorrect(): Unit = {
    val result = scaleController.get("treble", "A", "minor-melodic-descending")

    //noinspection SpellCheckingInspection
    assertThat(result.abc).contains("agfedcBA")
    assertThat(result.abc).contains("T:A minor melodic descending")
    assertThat(result.abc).contains("K:clef=treble")
  }

  @Test
  def when_scaleTypeNotRecognized_then_throws(): Unit = {
    assertThatThrownBy(() => scaleController.get("treble", "B", "demented"))
      .isInstanceOf(classOf[IllegalArgumentException])
  }
