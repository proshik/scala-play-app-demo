package repository

import javax.inject.Inject

import com.github.tototoshi.slick.PostgresJodaSupport._
import com.google.inject.Singleton
import model.entity.RawWord
import org.joda.time.DateTime
import play.api.Logger
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.json.JsValue
import repository.utils.MyPostgresDriver.api._
import slick.driver.JdbcProfile

/**
  * Created by proshik on 18.03.16.
  */
@Singleton
class RawWordRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  private val words = TableQuery[RawWordTable]

  def insert(word: RawWord): Unit = {
    Logger.debug("{INSERT} into db")

    db.run((words returning words.map(_.id)) += word)
  }

  private class RawWordTable(tag: Tag) extends Table[RawWord](tag, "raw_word") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def body = column[JsValue]("body")

    def createdDate = column[DateTime]("created_date")

    def userId = column[Long]("user_id")

    override def * = (id, body, createdDate, userId) <>(RawWord.tupled, RawWord.unapply)
  }

}

