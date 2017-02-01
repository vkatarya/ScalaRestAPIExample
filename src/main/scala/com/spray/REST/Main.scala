package com.spray.REST

import akka.actor._
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import spray.can.Http

import scala.concurrent.duration._
import scala.util.Try

object Main extends App {
  val config = ConfigFactory.load()
  val host = Try(config.getString("http.host")).getOrElse("localhost")
  val port = Try(config.getInt("http.port")).getOrElse(5000)

  implicit val system = ActorSystem("Spray-REST-APIs")
  implicit val executionContext = system.dispatcher
  implicit val timeout = Timeout(10 seconds)

  ScalaDBConnection.setupDB
  val api = system.actorOf(Props(new RestInterface))

  IO(Http).ask(Http.Bind(listener = api, interface = host, port = port))
    .mapTo[Http.Event]
    .map {
      case Http.Bound(address) =>
        println(s"REST interface bound to $address")
      case Http.CommandFailed(cmd) =>
        println("REST interface could not bind to " +
          s"$host:$port, ${cmd.failureMessage}")
        system.shutdown()
    }
}