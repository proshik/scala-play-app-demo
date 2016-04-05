package model.ywordmodel

import play.api.libs.json.Json

case class Definition(val text: Option[String],
                      val pos: Option[String],
                      val ts: Option[String],
                      val tr: Option[Seq[Transfer]])

object Definition {

  implicit val formatDefinition = Json.format[Definition]

}
