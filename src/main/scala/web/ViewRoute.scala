package web

import akka.actor.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import domain.Pet
import persist.Persistence

import scala.io.StdIn

object ViewRoute {
  def apply()(implicit system: ActorSystem) = {

    var persist = new Persistence
    concat(
      path("getpets") {
        get {
          complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, persist.get().toString))
        }
      },
      path("addpets") {
        post {
          entity(as[String]){
            pet =>
              persist.insert(Pet(pet))
              complete("pets added")
          }

        }
      },
      path("deletepets") {
        post {
          entity(as[String]){
            pet =>
              persist.delete(pet)
              complete("pets deleted")
          }
        }
      }
    )
  }
}
