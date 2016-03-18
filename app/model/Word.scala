package model

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by proshik on 18.03.16.
  */
case class Word(val id: Option[Long], val word: String, val createdDate: Option[DateTime])

object Word {

  implicit val wordFormat = Json.format[Word]

}
