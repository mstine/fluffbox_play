package controllers

import play.api.mvc._
import models.Speaker

object SpeakerController extends Controller {

  def find(offset: Int) = Action {
    Ok(views.html.speakers(Speaker.all(offset), offset))
  }

  def get(id: Long) = Action {
    Ok(views.html.speaker(Speaker.getBy(id)))
  }

  def search = TODO

}
