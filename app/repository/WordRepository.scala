package repository

import javax.inject.Inject

import com.github.tminglei.slickpg._
import com.github.tototoshi.slick.PostgresJodaSupport._
import com.google.inject.Singleton
import model.entity.Word
import org.joda.time.DateTime
import play.api.Logger
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.json._
import slick.driver.JdbcProfile

import scala.concurrent.Future
/**
  * Created by proshik on 03.04.16.
  */


object MyPostgresDriver extends slick.driver.PostgresDriver
  with PgPlayJsonSupport
  with array.PgArrayJdbcTypes {

  override val pgjson = "jsonb"

  object pgAPI extends API with JsonImplicits {
    implicit val strListTypeMapper: DriverJdbcType[List[String]] = new SimpleArrayJdbcType[String]("text").to(_.toList)
    implicit val json4sJsonArrayTypeMapper: DriverJdbcType[List[JsValue]] =
      new AdvancedArrayJdbcType[JsValue](pgjson,
        (s) => utils.SimpleArrayUtils.fromString[JsValue](Json.parse)(s).orNull,
        (v) => utils.SimpleArrayUtils.mkString[JsValue](_.toString())(v)
      ).to(_.toList)
  }

  override val api = pgAPI

  val plainAPI = new API with PlayJsonPlainImplicits

}

import repository.MyPostgresDriver.api._

@Singleton
class WordRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  private val words = TableQuery[WordTable]

  def all(): Future[Seq[Word]] = db.run(words.result)

  def getByRawWord(rawWord: String): Future[Option[Word]] = {
    val query = words.filter(_.rawWord === rawWord)
    val action = query.result.headOption
    db.run(action)
  }

  def getById(id: Long): Future[Option[Word]] = {
    val query = words.filter(_.id === id)
    val action = query.result.headOption
    db.run(action)
  }

  def insert(word: Word): Unit = {
    db.run((words returning words.map(_.id)) += word)
    Logger.debug("{WORD_REPOSITORY} success insert")

  }

  private class WordTable(tag: Tag) extends Table[Word](tag, "word") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def rawWord = column[String]("raw_word")

    def translate = column[JsValue]("translate")

    def createdDate = column[DateTime]("created_date")

    override def * = (id, rawWord, translate, createdDate) <>(Word.tupled, Word.unapply)

  }

}
