package com.skewwhiffy.controller

import org.junit.jupiter.api.Test

class InfoControllerTest {
  @Test
  fun dummy(): Unit = TODO()
}
/*
class InfoControllerTest extends PlaySpec with MockInstantiation {
  @InjectMocks
  private val infoController: InfoController = null

  "/api/info GET" should {
    "be valid" in {
      val actual = infoController.index().apply(FakeRequest(GET, "/api/info"))

      status(actual) mustBe OK
      contentType(actual) mustBe Some("application/json")
      assert(contentAsJson(actual).pipe { it => Json.format[InfoResponse].reads(it) }.isSuccess)
    }
  }
}

 */