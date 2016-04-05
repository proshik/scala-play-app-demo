package model.ywordmodel

import play.api.libs.json._

case class Transfer(val text: Option[String],
                    val pos: Option[String],
                    val gen: Option[String],
                    val syn: Option[Seq[Synonyms]],
                    val mean: Option[Seq[Meaning]],
                    val ex: Option[Seq[Example]])

object Transfer {

    implicit val formatTransfer = Json.format[Transfer]

}

