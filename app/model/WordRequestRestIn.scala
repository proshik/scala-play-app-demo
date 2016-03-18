package model

import play.api.libs.json.Json

/**
  * Created by proshik on 18.03.16.
  */
case class WordRequestRestIn(val word: String)

object WordRequestRestIn {

  implicit val wordFormat = Json.format[WordRequestRestIn]

}
