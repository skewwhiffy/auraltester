package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.model.InfoResponse
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

import scala.util.chaining.scalaUtilChainingOps

class InfoControllerTest extends PlaySpec {
  "/api/info GET" should {
    "be valid" in {
      val controller = new InfoController(stubControllerComponents())

      val actual = controller.index().apply(FakeRequest(GET, "/api/info"))

      status(actual) mustBe OK
      contentType(actual) mustBe Some("application/json")
      assert(contentAsJson(actual).pipe { it => Json.format[InfoResponse].reads(it) }.isSuccess)
    }
  }
}