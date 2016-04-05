package model.entity

import org.joda.time.DateTime
import play.api.libs.json.JsValue

/**
  * Created by proshik on 03.04.16.
  */
case class RawWord(val id: Long, val body: JsValue, val createdDate: DateTime, val userId: Long) {

  def this(body: JsValue) {
    this(0, body, DateTime.now(), 0)
  }

  def this(body: JsValue, userId: Long){
    this(0, body, DateTime.now(), userId)
  }

}