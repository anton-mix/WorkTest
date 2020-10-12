package controllers

import javax.inject._
import models._
import play.api.libs.json._
import play.api.mvc._


@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  val Store = ShortcutParser

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def add() = Action { request =>

    try {
      val json = request.body.asJson.get
      json.validate[Shortcut] match {
        case JsSuccess(value, _) =>
          Store.add(value)
          Ok(Json.obj("success" -> true))
        case JsError(e) => BadRequest(Json.obj("success" -> false))
      }
    } catch {
      case e: IllegalArgumentException =>
        Ok(Json.obj("success" -> false))
    }
  }

  def search(path: String) = Action { request =>

    Ok(Json.toJson(Store.search(path)))
  }
}
