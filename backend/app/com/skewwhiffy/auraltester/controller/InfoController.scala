package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.model.InformationResponse
import play.api.libs.json.{Json, OFormat}
import play.api.mvc._

import javax.inject._

@Singleton
class InfoController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  implicit val infoJson: OFormat[InformationResponse] = Json.format[InformationResponse]

  def index(): Action[AnyContent] = Action {
    Ok(Json.toJson(new InformationResponse("hello")))
  }
}
