package service

import javax.inject.{Inject, Singleton}

import com.google.inject.ImplementedBy
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.WSClient
import play.api.{Configuration, Logger}

import scala.concurrent.Future

/**
  * Created by proshik on 19.03.16.
  */
@ImplementedBy(classOf[TranslateServiceYandex])
trait TranslateService {

  def translate(rawWord: String): Future[JsValue]

}

@Singleton
class TranslateServiceYandex @Inject()(ws: WSClient,
                                       config: Configuration) extends TranslateService {

  val yandexDictURL = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup"

  val translateFromTo = "en-ru"

  val yandexDictKey = config.underlying.getString("y.dict.key")

  override def translate(rawWord: String): Future[JsValue] = {
    Logger.debug("{TRANSLATE} send request to yandex.dict")

    ws.url(yandexDictURL)
      .withQueryString("key" -> yandexDictKey, "lang" -> translateFromTo, "text" -> rawWord)
      .get()
      .map(value => Json.parse(value.body))
  }

}
