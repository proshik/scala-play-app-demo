package model.entity

import org.joda.time.DateTime

/**
  * Created by proshik on 03.04.16.
  */
case class RawWordRequest(val id: Long, val word: String, val createdDate: DateTime, val userId: Long) {

  def this(word: String) {
    this(0, word, DateTime.now(), 0)
  }

  def this(word: String, userId: Long){
    this(0, word, DateTime.now(), userId)
  }

}