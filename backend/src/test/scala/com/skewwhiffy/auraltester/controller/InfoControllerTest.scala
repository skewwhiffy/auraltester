package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.testutils.MockInstantiation
import org.mockito.InjectMocks
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class InfoControllerTest extends AnyFlatSpec with MockInstantiation with should.Matchers {
  @InjectMocks
  private var infoController: InfoController = _

  it should "when scale requested then abc correct" in {
    val result = infoController.get()

    assert(result != null)
  }
}