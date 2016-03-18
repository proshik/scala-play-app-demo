package service

import com.google.inject.{ImplementedBy, Inject, Singleton}
import model.WordRequestRestIn
import play.api.Logger
import repository.{WordRepository, WordRequest}

import scala.concurrent.Future

/**
  * Created by proshik on 18.03.16.
  */
@ImplementedBy(classOf[WordServiceEnglish])
trait WordService {

  def add(word: WordRequestRestIn): Future[Long]

}

@Singleton
class WordServiceEnglish @Inject()(wordRepository: WordRepository) extends WordService {

  override def add(params: WordRequestRestIn): Future[Long] = {
    val value = wordRepository.insert(new WordRequest(params.word))
    Logger.debug("success after add in service")
    value
  }

}


