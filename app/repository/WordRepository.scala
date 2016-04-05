package repository

import javax.inject.Inject

import com.github.tototoshi.slick.PostgresJodaSupport._
import com.google.inject.Singleton
import model.entity.Word
import org.joda.time.DateTime
import play.api.Logger
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.json._
import repository.utils.MyPostgresDriver.api._
import slick.driver.JdbcProfile

import scala.concurrent.Future
/**
  * Created by proshik on 03.04.16.
  */
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
    Logger.debug("{INSERT} into db")

    db.run((words returning words.map(_.id)) += word)
  }

  private class WordTable(tag: Tag) extends Table[Word](tag, "word") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def rawWord = column[String]("raw_word")

    def translate = column[JsValue]("translate")

    def createdDate = column[DateTime]("created_date")

    override def * = (id, rawWord, translate, createdDate) <>(Word.tupled, Word.unapply)

  }

}
