import akka.actor.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import web.ViewRoute

object Main extends App {
  implicit val system = ActorSystem( "my-system")
  Http().newServerAt("localhost", 8080).bind(ViewRoute())
}
