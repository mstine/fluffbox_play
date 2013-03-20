package controllers

import play.api.mvc._
import models.Speaker

object SpeakerController extends Controller {

  def find(offset: Int) = Action {
    Ok(views.html.speakers(Speaker.all(offset), offset))
  }

  def get(id: Long) = TODO

  def search = TODO

}
