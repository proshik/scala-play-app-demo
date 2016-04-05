package model.ywordmodel

import play.api.libs.json.Json

case class Meaning(val text: Option[String])

object Meaning {

  implicit val formatMeaning = Json.format[Meaning]

}