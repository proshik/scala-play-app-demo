package service

import javax.inject.Singleton

import com.google.inject.Inject
import model.entity.RawWord
import play.api.Logger
import play.api.libs.json.JsValue
import repository.RawWordRepository

/**
  * Created by proshik on 03.04.16.
  */
@Singleton
class RawWordRequestService @Inject()(wordRepository: RawWordRepository) {

  def save(body: JsValue): Unit = {
    Logger.debug("{RAW_WORD_SERVICE} save raw word from request")
    wordRepository.insert(new RawWord(body, 0))
    Logger.debug("{RAW_WORD_SERVICE} success save raw word from request")
  }
}
