package controllers

import javax.inject.Inject

import play.api.mvc._

/**
  * A very small controller that renders a home page.
  */
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action { implicit request =>
    Ok(views.html.index())
  }

  def sayHello = Action(parse.json) { request =>
    (request.body \ "name").asOpt[String].map { name =>
      Ok(toJson(
        Map("status" -> "OK", "message" -> ("Hello " + name))
      ))
    }.getOrElse {
      BadRequest(toJson(
        Map("status" -> "KO", "message" -> "Missing parameter [name]")
      ))
    }
  }
}
