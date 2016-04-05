package service

import com.google.inject.{ImplementedBy, Inject, Singleton}
import model.dto.RawWord
import model.entity.Word
import org.joda.time.DateTime
import play.api.libs.json.Json
import repository.WordRepository

/**
  * Created by proshik on 18.03.16.
  */
@ImplementedBy(classOf[WordServiceEnglish])
trait WordService {

  def add(word: RawWord): Unit

}

@Singleton
class WordServiceEnglish @Inject()(wordRepository: WordRepository)
  extends WordService {

  override def add(params: RawWord): Unit = {
    val result = wordRepository.getById(1L)
    wordRepository.insert(Word(0, "future", Json.toJson(params), DateTime.now()))
  }

}


