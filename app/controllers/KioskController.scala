package controllers

import play.api.mvc._
import models.Kiosk
import play.api.libs.json.Json

object KioskController extends Controller {

  def find() = Action {
    Ok(views.html.kiosks())
  }

  def search = Action {
    Ok(Json.toJson(Kiosk.all()))
  }

}
