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

    concat(
      path("getpets") {
        get {
          complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, Persistence.get().toString))
        }
      },
      path("addpets") {
        post {
          entity(as[String]){
            pet =>
              Persistence.insert(Pet(pet))
              complete("pets added")
          }

        }
      },
      path("deletepets") {
        post {
          entity(as[String]){
            pet =>
              Persistence.delete(pet)
              complete("pets deleted")
          }
        }
      }
    )
  }
}
