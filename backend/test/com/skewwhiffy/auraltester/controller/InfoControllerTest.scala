package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.model.InfoResponse
import com.skewwhiffy.auraltester.testutils.MockInstantiation
import org.mockito.InjectMocks
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

import scala.util.chaining.scalaUtilChainingOps

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
