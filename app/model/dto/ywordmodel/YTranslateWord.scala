package model.dto.ywordmodel

import play.api.libs.functional.syntax._
import play.api.libs.json._

case class YTranslateWord(val head: Option[Head], val definitions: Seq[Definition])

object YTranslateWord {

  implicit val formatYTranslateWord: Format[YTranslateWord] = (
    (__ \ 'head).formatNullable(implicitly[Format[Head]]) and
      (__ \ 'def).format(implicitly[Format[Seq[Definition]]])) (YTranslateWord.apply, unlift(YTranslateWord.unapply))

}