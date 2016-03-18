package controllers

import com.google.inject.Inject
import model.WordRequestRestIn
import play.api.Logger
import play.api.libs.json.JsError
import play.api.mvc.{Action, BodyParsers, Controller}
import service.WordService

/**
  * Created by proshik on 18.03.16.
  */
class WordController @Inject()(wordService: WordService) extends Controller {

  def add = Action.apply(BodyParsers.parse.json) { implicit request =>
    request.body.validate[WordRequestRestIn].fold(
      errors =>
        BadRequest(JsError.toJson(errors)),
      word => {
        wordService.add(word)
        Logger.debug("success after add in controller")
        Created
      }
    )
  }

}
