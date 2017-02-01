package com.spray.REST
import spray.routing._

import scala.concurrent.ExecutionContext
import scala.language.postfixOps

class RestInterface(implicit val executionContext: ExecutionContext) extends HttpServiceActor with Resources {
  def receive = runRoute(questionRoutes)
  val questionService = new QuestionService
}

trait Resources extends QuestionResource