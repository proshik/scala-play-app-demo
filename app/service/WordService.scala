package service

import com.google.inject.{ImplementedBy, Inject, Singleton}
import model.entity.Word
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits._
import repository.WordRepository

/**
  * Created by proshik on 18.03.16.
  */
@ImplementedBy(classOf[WordServiceEnglish])
trait WordService {

  def add(word: String): Unit

}

@Singleton
class WordServiceEnglish @Inject()(wordRepository: WordRepository,
                                   translateService: TranslateService) extends WordService {

  override def add(rawWord: String): Unit = {
    Logger.debug("{ADD} translate word and save translate")

    translateService
      .translate(rawWord)
      .onComplete(transWord => wordRepository.insert(new Word(rawWord, transWord.get)))
  }

}


