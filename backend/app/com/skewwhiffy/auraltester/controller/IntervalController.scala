package com.skewwhiffy.auraltester.controller

//import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.model.{IntervalRequest, IntervalResponse}

import scala.runtime.Nothing$
//import com.skewwhiffy.auraltester.notes.Interval
//import com.skewwhiffy.auraltester.services.{AbcService, IntervalService}
import play.api.libs.json.{Json, OFormat}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject._

@Singleton
class IntervalController @Inject()(
  val controllerComponents: ControllerComponents,
  //private val abcService: AbcService,
  // private val internalNotationFactory: InternalNotationFactory,
  //private val intervalService: IntervalService
) extends BaseController {
  private implicit val intervalJson: OFormat[IntervalResponse] = Json.format[IntervalResponse]

  def index(
    clef: String,
    bottomNote: String,
    intervalQuality: String,
    intervalSize: Int,
    keySignature: Option[String]
  ): Action[AnyContent] = Action {
    val tempResponse = List(
      s"Clef: $clef",
      s"Bottom note: $bottomNote",
      s"Interval quality: $intervalQuality",
      s"Interval size: $intervalSize",
      s"Key signature: ${keySignature.getOrElse("NONE")}"
    ).mkString(", ")
    Ok(tempResponse)
    /*
val clefObject = internalNotationFactory.clef(clef)
val bottomNoteObject = internalNotationFactory.getNote(bottomNote).note
val intervalQualitySuffix = intervalQuality match {
  case "perfect" | "major" => ""
  case "minor" => "-"
  case "diminished" => if (Interval.perfectDegrees.contains(intervalSize)) "-" else "--"
  case "augmented" => "+"
}
val interval = internalNotationFactory
  .getDirectedInterval(s"+$intervalSize$intervalQualitySuffix")
  .interval
val keyNote = internalNotationFactory.getNote(keySignature).note
val keySignatureObject = new Key(keyNote)
val intervalNotes = intervalService.getInterval(clefObject, bottomNoteObject, interval)
val response = abcService.getAbc(clefObject, intervalNotes, keySignatureObject)
  .pipe(it => new IntervalResponse(it))
Ok(Json.toJson(response))

     */
  }
}

