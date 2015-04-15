package controllers

import models._
import play.api.Logger
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.mvc._

import scala.slick.lifted.TableQuery

class Users extends Controller{

  val users = TableQuery[UserTable]

    def list = DBAction { implicit rs =>
//       Logger.info(s"SHOW_ALL = ${cities.list}")
    val userResult = (for {
      (user) <- users
    } yield (user)).list
      .map { case (user) => UserForDisplay(user)}

    Ok(views.html.list(userResult))
    }

  def showAddForm = DBAction { implicit rs =>
    Ok(views.html.signup())
  }

  def signup= Action {
    Ok(views.html.signup())
  }


  def login = Action {
    Ok(views.html.login())
  }


  def add = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val fname = formParams.get("fname")(0)
    val lname = formParams.get("lname")(0)
    val uname = formParams.get("uname")(0)
    val email = formParams.get("email")(0)
    val password = formParams.get("password")(0)

    val userId = (users returning users.map(_.id)) += User(None, fname, lname, uname, email, password)
     Logger.info(s"LastId = $userId")
    Redirect(routes.Users.list())
  }

  def remove(id: Int) = DBAction { implicit request =>
    users.filter(_.id === id).delete
    Redirect(routes.Users.list())
  }

  def update(id: Int) = DBAction { implicit rs =>
    val formParams = rs.body.asFormUrlEncoded
    val fname = formParams.get("fname")(0)
    val lname = formParams.get("lname")(0)
    val uname = formParams.get("uname")(0)
    val email = formParams.get("email")(0)
    val password = formParams.get("password")(0)

    val user = User(Some(id), fname, lname, uname, email, password)
    users.filter(_.id === id).update(user)

    Redirect(routes.Users.list())
  }
  def showEditForm(userId: Int) = DBAction { implicit request =>
    val byId = users.findBy(_.id)
    val user = byId(userId).list.head

    Ok(views.html.edit(user))
  }



}
