package com.skewwhiffy.auraltester.controller

import org.junit.jupiter.api.Test

class ScaleControllerTest:
  private val scaleController = ScaleController()

  @Test
  def when_scaleRequested_then_abcCorrect(): Unit = {
    val result = scaleController.get("treble", "D", "major")

    println(result.abc)
  }
