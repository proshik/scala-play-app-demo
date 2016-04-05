package service

import com.google.inject.{ImplementedBy, Inject, Singleton}
import model.dto.RawWord
import model.entity.Word
import org.joda.time.DateTime
import play.api.libs.json.Json
import repository.WordRepository

import play.api.libs.concurrent.Execution.Implicits._

/**
  * Created by proshik on 18.03.16.
  */
@ImplementedBy(classOf[WordServiceEnglish])
trait WordService {

  def add(word: RawWord): Unit

}

@Singleton
class WordServiceEnglish @Inject()(wordRepository: WordRepository, translateService: TranslateService)
  extends WordService {

  override def add(params: RawWord): Unit = {
    translateService
      .translate(params)
      .onComplete(transWord => wordRepository.insert(Word(0, params.word, Json.toJson(transWord.get), DateTime.now())))
  }

}


