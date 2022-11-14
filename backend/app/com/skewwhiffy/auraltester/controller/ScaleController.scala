package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.model.ScaleResponse
import com.skewwhiffy.auraltester.scales.{Key, ScaleDirection, ScaleTypeFactory}
import com.skewwhiffy.auraltester.services.{AbcService, ScaleService}
import play.api.libs.json.{Json, OFormat}
import play.api.mvc._

import javax.inject._
import scala.util.chaining.scalaUtilChainingOps

@Singleton
class ScaleController @Inject()(
  val controllerComponents: ControllerComponents,
  private val abcService: AbcService,
  private val internalNotationFactory: InternalNotationFactory,
  private val scaleService: ScaleService,
  private val scaleTypeFactory: ScaleTypeFactory
) extends BaseController {
  private implicit val scaleJson: OFormat[ScaleResponse] = Json.format[ScaleResponse]
  def index(
    clef: String,
    note: String,
    scaleType: String,
    direction: String
  ): Action[AnyContent] = Action {
    val clefObject = internalNotationFactory.clef(clef)
    val noteObject = internalNotationFactory.getNote(note).note
    val (scaleTypeObject, isMinor) = scaleType match {
      case "major" => (scaleTypeFactory.major, false)
      case "minor-harmonic" => (scaleTypeFactory.minorHarmonic, true)
      case "minor-melodic" => (scaleTypeFactory.minorMelodic, true)
      case _ => throw new IllegalArgumentException(s"Unrecognized scale type: '$scaleType'")
    }
    val directionObject = direction match {
      case "ascending" => ScaleDirection.ascending
      case "descending" => ScaleDirection.descending
      case _ => throw new IllegalArgumentException(s"Unrecognized direction: '$direction'")
    }
    val scale = scaleService.getScale(clefObject, noteObject, scaleTypeObject, directionObject)
    val key = scale.lowestNote.note.pipe(it => Key(it, isMinor))
    val response = ScaleResponse(abcService.getAbc(clefObject, scale), abcService.getAbc(clefObject, scale, key))
    Ok(Json.toJson(response))
  }
}
