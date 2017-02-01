package com.spray.REST

import slick.driver.H2Driver.api._
import slick.lifted.ProvenShape

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Created by vivek on 10/31/16.
  */
object ScalaDBConnection {

  class DBQuestion(tag: Tag)
    extends Table[(Int, String, String, String)](tag, "QUESTIONS") {
    def id = column[Int]("QUESTION_ID", O.PrimaryKey, O.AutoInc)
    def userId = column[String] ("USER_ID")
    def title = column[String] ("TITLE")
    def text = column[String] ("TEXT")
    def * : ProvenShape[(Int, String, String, String)] =
      (id, userId, title, text)
  }

  def setupDB = {
    val connectionURL = "jdbc:postgresql://localhost:5432/ScalaREST?user=vivek&password="

    val db = Database.forURL(connectionURL, driver = "org.postgresql.Driver")
    val dBQuestion = TableQuery[DBQuestion]

    try {
      Await.result(db.run(DBIO.seq(
        dBQuestion.schema.create
      )), Duration.Inf)
    }
    db.run(dBQuestion.schema.create)
  }
}
