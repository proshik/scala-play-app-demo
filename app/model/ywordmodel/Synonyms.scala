package model.ywordmodel

import play.api.libs.json.Json

case class Synonyms(val text: Option[String],
                    val pos: Option[String],
                    val gen: Option[String])

object Synonyms {

  implicit val formatSynonyms = Json.format[Synonyms]

}