package service

import javax.inject.{Inject, Singleton}

import com.google.inject.ImplementedBy
import model.dto.RawWord
import model.dto.ywordmodel.YTranslateWord
import play.api.Configuration
import play.api.libs.json.Json
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.Future

import play.api.libs.concurrent.Execution.Implicits._

/**
  * Created by proshik on 19.03.16.
  */
@ImplementedBy(classOf[TranslateServiceYandex])
trait TranslateService {

  def translate(rawWord: RawWord): Future[YTranslateWord]

}

@Singleton
class TranslateServiceYandex @Inject()(ws: WSClient, config: Configuration) extends TranslateService {

  val yandexDictKey = config.underlying.getString("y.dict.key")

  val translateFromTo = "en-ru"

  override def translate(rawWord: RawWord): Future[YTranslateWord] = {

    val result: Future[WSResponse] = ws.url("https://dictionary.yandex.net/api/v1/dicservice.json/lookup")
      .withQueryString(
        "key" -> yandexDictKey,
        "lang" -> translateFromTo,
        "text" -> rawWord.word)
      .get()

    for {
      s1 <- result
    } yield Json.parse(s1.body).as[YTranslateWord]

  }

}
