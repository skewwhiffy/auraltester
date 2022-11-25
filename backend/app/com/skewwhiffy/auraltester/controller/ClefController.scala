package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.model.ClefResponse
import com.skewwhiffy.auraltester.scales.Key
import com.skewwhiffy.auraltester.services.AbcService
import play.api.libs.json.{Json, OFormat}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject.Inject
import scala.util.chaining.scalaUtilChainingOps

class ClefController @Inject()(
  val controllerComponents: ControllerComponents,
  private val internalNotationFactory: InternalNotationFactory,
  private val abcService: AbcService,
) extends BaseController {
  private implicit val clefJson: OFormat[ClefResponse] = Json.format[ClefResponse]

  def index(clef: String): Action[AnyContent] = Action {
    val clefObject = internalNotationFactory.clef(clef)
    internalNotationFactory
      .getNote("C")
      .note
      .pipe { it => Key(it) }
      .pipe { it => abcService.getAbc(clefObject, it) }
      .pipe { it => ClefResponse(it) }
      .pipe { it => Ok(Json.toJson(it)) }
  }
}
