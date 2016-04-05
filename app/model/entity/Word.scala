package model.entity

import org.joda.time.DateTime
import play.api.libs.json.JsValue

/**
  * Created by proshik on 03.04.16.
  */
case class Word(val id: Long, val rawWord: String, val translate: JsValue, val createdDate: DateTime){

  def this(rawWord: String, translate: JsValue) {
    this(0, rawWord, translate, DateTime.now())
  }

}
