package controllers

import com.google.inject.Inject
import model.dto.RawWord
import play.api.libs.json.JsError
import play.api.mvc._
import service.{RawWordRequestService, WordService}

/**
  * Created by proshik on 18.03.16.
  */
class WordController @Inject()(requestService: RawWordRequestService, wordService: WordService) extends Controller {

  def add = Action(BodyParsers.parse.json) { implicit request =>
    request.body.validate[RawWord].fold(
      errors =>
        BadRequest(JsError.toJson(errors)),
      word => {
        requestService.save(word)
        wordService.add(word)
        Created
      }
    )
  }
}
