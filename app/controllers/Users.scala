package controllers

/**
 * Created by Azamatov on 06.04.2015.
 */

import models._
import play.api.Logger
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.mvc._

import scala.slick.lifted.TableQuery

class Users extends Controller{

  val users = TableQuery[UserTable]

  def list = DBAction { implicit rs =>
    Logger.info(s"SHOW_ALL = ${users.list}")
    Ok(views.html.list(users.list))
  }

  def showAddForm = Action {
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

    println("Name: " + fname)
    println("Name: " + fname)
    val userId = (users returning users.map(_.id)) += User(None, fname, lname, uname, email, password)
    Redirect(routes.Users.list())
  }

  def remove(id: Int) = DBAction { implicit request =>
    users.filter(_.id === id).delete;
    Redirect(routes.Users.list())
  }




}
