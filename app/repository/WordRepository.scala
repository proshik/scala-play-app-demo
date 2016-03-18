package repository

import javax.inject.Inject

import com.google.inject.Singleton
import model._
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import slick.driver.PostgresDriver.api._
import com.github.tototoshi.slick.PostgresJodaSupport._

import scala.concurrent.Future

//case class Word(val id: Long, val word: String)

@Singleton
class WordRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {

  private val words = TableQuery[WordsTable]

  def all(): Future[Seq[Word]] = db.run(words.result)

  def getById(id: Long): Future[Option[Word]] = {
    val query = words.filter(_.id === id)
    val action = query.result.headOption
    db.run(action)
  }

  def insert(word: Word): Future[Long] = {
    //For return as input object Future[WordRestIn]
    //    val action = (Words returning Words.map(_.id)).into((word, id) => word.copy()) += WordRestIn(Some(0), word.word)
    db.run((words returning words.map(_.id)) += word)
  }

  private class WordsTable(tag: Tag) extends Table[Word](tag, "word") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def word = column[String]("word")

    def createdDate = column[Option[DateTime]]("created_date")

    override def * = (id.?, word, createdDate) <>((Word.apply _).tupled, Word.unapply)
  }

}

