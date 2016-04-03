package repository

import javax.inject.Inject

import com.github.tototoshi.slick.PostgresJodaSupport._
import com.google.inject.Singleton
import model.entity.RawWordRequest
import org.joda.time.DateTime
import play.api.Logger
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import slick.driver.PostgresDriver.api._

/**
  * Created by proshik on 18.03.16.
  */
@Singleton
class RawWordRequestRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  private val words = TableQuery[RawWordRequestTable]

  def insert(word: RawWordRequest): Unit = {
    db.run((words returning words.map(_.id)) += word)
    Logger.debug("{RAW_WORD_REQUEST_REPOSITORY} success insert")
  }

  private class RawWordRequestTable(tag: Tag) extends Table[RawWordRequest](tag, "raw_word_request") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def word = column[String]("word")

    def createdDate = column[DateTime]("created_date")

    def userId = column[Long]("user_id")

    override def * = (id, word, createdDate, userId) <>(RawWordRequest.tupled, RawWordRequest.unapply)
  }

}

