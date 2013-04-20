package controllers

import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.ask
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.concurrent.Execution.Implicits._

object Events extends Controller {
  implicit val ts =  Timeout(5 seconds)
  
  def getEvents (lastEventId: Long) = Action {
    Async {
      for (events <- sim.Simulator.simulation ? sim.Simulation.GetEvents(lastEventId)) yield {
        Ok {
          Json.arr()
        }
      }
    }
  }
  
}
