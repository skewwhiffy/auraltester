package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.model.KeySignatureResponse
import com.skewwhiffy.auraltester.scales.Key
import com.skewwhiffy.auraltester.services.AbcService
import play.api.libs.json.{Json, OFormat}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject._
import scala.util.chaining.scalaUtilChainingOps

@Singleton
class KeySignatureController @Inject()(
  val controllerComponents: ControllerComponents,
  private val internalNotationFactory: InternalNotationFactory,
  private val abcService: AbcService
) extends BaseController {
  private implicit val keySignatureJson: OFormat[KeySignatureResponse] = Json.format[KeySignatureResponse]

  def index(clef: String, keySignature: Option[String]): Action[AnyContent] = Action {
    val clefObject = internalNotationFactory.clef(clef)
    internalNotationFactory
      .getNote(keySignature.getOrElse("C"))
      .note
      .pipe { it => Key(it) }
      .pipe { it => abcService.getAbc(clefObject, it) }
      .pipe { it => KeySignatureResponse(it) }
      .pipe { it => Ok(Json.toJson(it)) }
  }
}
