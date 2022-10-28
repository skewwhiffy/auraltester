package com.skewwhiffy.auraltester.controller

import org.scalatest.funsuite.AnyFunSuite

class InfoControllerTest extends AnyFunSuite {
  private val infoController = new InfoController()

  test("when scale requested then abc correct") {
    val result = infoController.get()

    assert(result != null)
  }
}