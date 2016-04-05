package model.ywordmodel

import play.api.libs.json._

case class Example(val text: Option[String],
                   val tr: Option[Seq[ExampleTransfer]])

object Example {

    implicit val formatExample = Json.format[Example]

}