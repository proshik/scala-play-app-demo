package model.dto

import play.api.libs.json.Json

/**
  * Created by proshik on 18.03.16.
  */
case class RawWord(val word: String)

object RawWord {

  implicit val rawWordFormat = Json.format[RawWord]

}
