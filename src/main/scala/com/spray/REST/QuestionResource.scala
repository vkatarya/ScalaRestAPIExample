package com.spray.REST

import spray.routing._

trait QuestionResource extends MyHttpService {

  val questionService: QuestionService

  def questionRoutes: Route = pathPrefix("questions") {
    pathEnd {
      post {
        entity(as[Question]) { question =>
          completeWithLocationHeader(
            resourceId = questionService.createQuestion(question),
            ifDefinedStatus = 201, ifEmptyStatus = 409)
        }
      }
    } ~
      path(Segment) { id =>
        get {
          complete(questionService.getQuestion(id))
        } ~
          put {
            entity(as[QuestionUpdate]) { update =>
              complete(questionService.updateQuestion(id, update))
            }
          } ~
          delete {
            complete(204, questionService.deleteQuestion(id))
          }
      } ~
      path(Segment / Segment) {
        (id1, id2) => get {
          complete(questionService.getQuestion(id2))
        }
      } ~
    path("") {
      get{
        complete("OK")
      }
    }
  }
}