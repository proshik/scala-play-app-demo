package service

import com.google.inject.{ImplementedBy, Inject, Singleton}
import model.dto.RawWord
import play.api.Logger

/**
  * Created by proshik on 18.03.16.
  */
@ImplementedBy(classOf[WordServiceEnglish])
trait WordService {

  def add(word: RawWord): Unit

}

@Singleton
class WordServiceEnglish @Inject()(requestService: RawWordRequestService) extends WordService {

  override def add(params: RawWord): Unit = {
    Logger.debug("{WORD_SERVICE} Save rawWord to request to db")
    requestService.save(params)
    Logger.debug("{WORD_SERVICE} Success save rawWord to db")
    //todo add ws request to dict and save word
  }

}


