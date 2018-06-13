
import akka.http.scaladsl._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import scala.concurrent.duration._
import scala.concurrent.Await

import java.io.File

object Main {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem("Web")
    import system.dispatcher
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    val routes = 
      get(
        pathPrefix("accessible-scala-web")(
          concat(
            pathSingleSlash(getFromFile(new File("index.html"))),
            path(Remaining)(path => getFromFile(new File("accessible-scala-web/" + path)))
          )
        )
      )

    Await.result(Http().bindAndHandle(routes, "0.0.0.0", 8080), 1.seconds)
    Await.result(system.whenTerminated, Duration.Inf)
  }
}