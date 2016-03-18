package repository

import javax.inject.Inject

import com.google.inject.Singleton
import org.joda.time.DateTime
import play.api.Logger
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import slick.driver.PostgresDriver.api._
import com.github.tototoshi.slick.PostgresJodaSupport._

import scala.concurrent.Future

/**
  * Created by proshik on 18.03.16.
  */
case class WordRequest(val id: Long, val word: String, val createdDate: DateTime, val userId: Long) {

  def this(word: String) {
    this(0, word, DateTime.now(), 0)
  }

}

@Singleton
class WordRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {

  private val words = TableQuery[WordsTable]

  def all(): Future[Seq[WordRequest]] = db.run(words.result)

  def getById(id: Long): Future[Option[WordRequest]] = {
    val query = words.filter(_.id === id)
    val action = query.result.headOption
    db.run(action)
  }

  def insert(word: WordRequest): Future[Long] = {
    //For return as input object Future[WordRestIn]
    //    val action = (Words returning Words.map(_.id)).into((word, id) => word.copy()) += WordRestIn(Some(0), word.word)
    val result = db.run((words returning words.map(_.id)) += word)
    Logger.debug("success after add in repository")
    result
  }

  private class WordsTable(tag: Tag) extends Table[WordRequest](tag, "word_request") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def word = column[String]("word")

    def createdDate = column[DateTime]("created_date")

    def userId = column[Long]("user_id")

    override def * = (id, word, createdDate, userId) <>(WordRequest.tupled, WordRequest.unapply)
  }

}

