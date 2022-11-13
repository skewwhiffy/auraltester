package controllers

import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import models.InformationResponse
import play.api.libs.json.{Json, OFormat}

import javax.inject._
import play.api.mvc._

@Singleton
class InfoController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  implicit val infoJson: OFormat[InformationResponse] = Json.format[InformationResponse]

  def index(): Action[AnyContent] = Action {
    Ok(Json.toJson(new InformationResponse("hello")))
  }
}
