package service

import javax.inject.Singleton

import com.google.inject.Inject
import model.dto.RawWord
import model.entity.RawWordRequest
import play.api.Logger
import repository.RawWordRequestRepository

/**
  * Created by proshik on 03.04.16.
  */
@Singleton
class RawWordRequestService @Inject()(wordRepository: RawWordRequestRepository) {

  def save(params: RawWord): Unit = {
    Logger.debug("{RAW_WORD_REQUEST_SERVICE} save raw word from request")
    wordRepository.insert(new RawWordRequest(params.word, 0))
    Logger.debug("{RAW_WORD_REQUEST_SERVICE} success save raw word from request")
  }
}
