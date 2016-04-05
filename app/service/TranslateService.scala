package service

import java.util.concurrent.TimeUnit
import javax.inject.{Inject, Singleton}

import com.google.inject.ImplementedBy
import model.WordRequestRestIn
import play.api.Configuration
import play.api.libs.ws.WSClient

import scala.concurrent.Await
import scala.concurrent.duration.FiniteDuration


/**
  * Created by proshik on 19.03.16.
  */
@ImplementedBy(classOf[TranslateServiceYandex])
trait TranslateService {

  def translate(rawWord: WordRequestRestIn): Unit

  //  YTranslateWord

}

@Singleton
class TranslateServiceYandex @Inject()(ws: WSClient, config: Configuration) extends TranslateService {

  implicit val context = play.api.libs.concurrent.Execution.Implicits.defaultContext

  override def translate(rawWord: WordRequestRestIn): Unit = {

    val result = Await.result(
      ws.url("https://dictionary.yandex.net/api/v1/dicservice.json/lookup")
        .withQueryString(
          "key" -> config.underlying.getString("y.dict.key "),
          "lang" -> "en-ru",
          "text" -> rawWord.word)
        .get(),
      FiniteDuration.apply(10, TimeUnit.SECONDS))

//    val result = ws.url("https://dictionary.yandex.net/api/v1/dicservice.json/lookup")
//      .withQueryString(
//        "key" -> "dict.1.1.20160315T215735Z.efa2f0f9aa89f225.a1845d9073947ee2350a879d35deabc4caf1f99d",
//        "lang" -> "en-ru",
//        "text" -> rawWord.word)
//      .get().result(FiniteDuration.apply(10, TimeUnit.SECONDS))
    //      .map {
    //        response =>
    //          (response.json \ "head").as[String]
    //      }
    println("success")
  }
}


//    val result = ws.url("http://httpbin.org/ip")
//      .get()
//      .map {
//        response =>
//          (response.json \ "origin").as[String]
//      }
//
//    println("success")
//  }


