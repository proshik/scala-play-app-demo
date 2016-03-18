package service

import com.google.inject.{Inject, Singleton, ImplementedBy}
import model.Word
import repository.WordRepository

import scala.concurrent.Future

/**
  * Created by proshik on 18.03.16.
  */
@ImplementedBy(classOf[WordServiceEnglish])
trait WordService {

  def add(word: Word): Future[Long]

}

@Singleton
class WordServiceEnglish @Inject()(wordRepository: WordRepository) extends WordService {

  override def add(word: Word): Future[Long] = {
    wordRepository.insert(word)
  }

}


