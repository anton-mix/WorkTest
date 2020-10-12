package models

import play.api.libs.functional.syntax.unlift
import play.api.libs.json.{JsPath, Reads, Writes}
import play.api.libs.functional.syntax._

class Shortcut(val binding: SimpleBinding, val description: String, val action: SimpleAction) {


}

object Shortcut{

  implicit def ShortcutWriter:Writes[Shortcut] = (
    (JsPath \ "binding").write[String] and
      (JsPath \ "description").write[String] and
      (JsPath \ "action").write[String])(unlift(Shortcut.unapply))

  implicit def ShortcutReads: Reads[Shortcut] = (
    (JsPath \ "binding").read[String] and
      (JsPath \ "description").read[String] and
      (JsPath \ "action").read[String]
    )((binding,description,action) => Shortcut.apply(SimpleBinding(binding),description,SimpleAction(action)))

  def unapply(arg: Shortcut): Option[(String, String, String)] = {
    Option(arg.binding.toString,arg.description,arg.action.toString)
  }

  def apply(binding: SimpleBinding, description: String, action: SimpleAction): Shortcut = new Shortcut(binding, description, action)
}
