package controllers

import com.google.inject.Inject
import model.Word
import play.api.libs.json.JsError
import play.api.mvc.{Action, BodyParsers, Controller}
import service.WordService

/**
  * Created by proshik on 18.03.16.
  */
class WordController @Inject()(wordService: WordService) extends Controller {

  def add = Action.apply(BodyParsers.parse.json) { implicit request =>
    request.body.validate[Word].fold(
      errors => BadRequest(JsError.toJson(errors)),
      word => {
        wordService.add(word)
        Created
      }
    )
  }

}
