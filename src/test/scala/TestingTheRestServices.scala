//import com.spray.REST.QuestionResource
//import org.specs2.Specification
//import spray.testkit.Specs2RouteTest
//
///**
//  * Created by vivek on 10/30/16.
//  */
//class TestingTheRestServices extends Specification
//  with Specs2RouteTest with QuestionResource {
//
//  def actorRefFactory = system
//
//  "Spray Rest API should" {
//    "Return OK when invoked" in {
//      Get() ~> questionRoutes ~> check { responseAs[String] == "OK" }
//    }
//
//    "Return OK when invoked" in {
//      Get() ~> questionRoutes ~> check { responseAs[String] == "OK" }
//    }
//
//  }
//}
