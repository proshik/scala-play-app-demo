package model.ywordmodel

import play.api.libs.json.Json


case class Head(val value: Option[String])


object Head {
  implicit val formantHead = Json.format[Head]
}