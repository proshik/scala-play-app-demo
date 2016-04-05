package model.ywordmodel

import play.api.libs.json.Json

case class ExampleTransfer(val text: Option[String])

object ExampleTransfer {

  implicit val formatExampleTransfer = Json.format[ExampleTransfer]

}