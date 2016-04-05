package repository.utils

import com.github.tminglei.slickpg.utils.SimpleArrayUtils
import com.github.tminglei.slickpg.{PgPlayJsonSupport, array}
import play.api.libs.json.{JsValue, Json}

/**
  * Created by proshik on 06.04.16.
  */
object MyPostgresDriver extends slick.driver.PostgresDriver
  with PgPlayJsonSupport
  with array.PgArrayJdbcTypes {

  override val pgjson = "jsonb"

  object pgAPI extends API with JsonImplicits {
    implicit val strListTypeMapper: DriverJdbcType[List[String]] = new SimpleArrayJdbcType[String]("text").to(_.toList)
    implicit val json4sJsonArrayTypeMapper: DriverJdbcType[List[JsValue]] =
      new AdvancedArrayJdbcType[JsValue](pgjson,
        (s) => SimpleArrayUtils.fromString[JsValue](Json.parse)(s).orNull,
        (v) => SimpleArrayUtils.mkString[JsValue](_.toString())(v)
      ).to(_.toList)
  }

  override val api = pgAPI

  val plainAPI = new API with PlayJsonPlainImplicits
}
