package com.spray.REST

/**
  * Created by vivek on 10/30/16.
  */
case class Question(id: String, title: String, text: String)
case class QuestionUpdate(title: Option[String], text: Option[String])

