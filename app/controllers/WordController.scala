package controllers

import com.google.inject.Inject
import model.dto.RawWordRestIn
import play.api.Logger
import play.api.libs.json.JsError
import play.api.mvc._
import service.{RawWordRequestService, WordService}

/**
  * Created by proshik on 18.03.16.
  */
class WordController @Inject()(requestService: RawWordRequestService, wordService: WordService) extends Controller {

  def add = Action(BodyParsers.parse.json) { implicit request =>
    Logger.debug("{ADD} save request")
    requestService.save(request.body)

    Logger.debug("{ADD} validate and parse body from request")
    request.body.validate[RawWordRestIn].fold(
      errors =>
        BadRequest(JsError.toJson(errors)),
      word => {
        wordService.add(word.word)
        Created
      }
    )
  }
}
