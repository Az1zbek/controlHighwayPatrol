package models
/**
 * Created by Azamatov on 06.04.2015.
 */
import play.api.db.slick.Config.driver.simple._


case class  User(id: Option[Int],
                fname: String,
                lname: String,
                uname: String,
                email: String,
                password: String)
case class UserForDisplay(user: User)

class UserTable(tag: Tag) extends Table[User](tag, "REGISTRATION") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def fname = column[String]("FNAME", O.Default(""))
  def lname = column[String]("LNAME", O.Default(""))
  def uname = column[String]("UNAME", O.Default(""))
  def email = column[String]("EMAIL", O.Default(""))
  def password = column[String]("PASSWORD", O.Default(""))


  def * = (id.?, fname, lname, uname, email, password) <> (User.tupled, User.unapply _)

}