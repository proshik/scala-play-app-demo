package model.dto

import play.api.libs.json.Json

/**
  * Created by proshik on 18.03.16.
  */
case class RawWordRestIn(val word: String)

object RawWordRestIn {

  implicit val rawWordFormat = Json.format[RawWordRestIn]

}
