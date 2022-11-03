package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.testutils.MockInstantiation
import org.mockito.InjectMocks
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec

class InfoControllerTest extends AnyFlatSpec with MockInstantiation with GivenWhenThen {
  @InjectMocks
  private var infoController: InfoController = _

  it should "return information" in {
    When("info is requested")
    val result = infoController.get()

    Then("info is returned")
    assert(result != null)
  }
}