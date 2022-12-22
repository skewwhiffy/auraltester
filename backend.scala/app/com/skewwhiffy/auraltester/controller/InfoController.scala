package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.model.InfoResponse
import play.api.libs.json.{Json, OFormat}
import play.api.mvc._

import javax.inject._

@Singleton
class InfoController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  private implicit val infoJson: OFormat[InfoResponse] = Json.format[InfoResponse]

  def index(): Action[AnyContent] = Action {
    Ok(Json.toJson(InfoResponse("0.0.1")))
  }
}
