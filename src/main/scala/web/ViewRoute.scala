package web

import akka.actor.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import domain.Pet

import scala.io.StdIn

object ViewRoute {
  def apply()(implicit system: ActorSystem) = {

var pets :List[Pet] = List()
    concat(
      path("getpets") {
        get {
          complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, pets.toString()))
        }
      },
      path("addpets") {
        post {
          entity(as[String]){
            pet =>
              pets =pets :+ Pet(pet)
              complete("pets added")
          }

        }
      },
      path("deletepets") {
        post {
          entity(as[String]){
            pet =>
              pets =pets.filter(_.name != pet)
              complete("pets added")
          }
        }
      }
    )
  }
}
