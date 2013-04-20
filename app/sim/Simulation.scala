package sim

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import play.api.libs.concurrent.Execution.Implicits._
import scala.collection.mutable.ListBuffer
import scala.concurrent.duration._
import models._

class Simulation extends Actor {
  import Simulation._
  
  val events = new ListBuffer[Event]()
  val aircrafts = new ListBuffer()
  
  def receive = {
    case Tick => println("tick")
    case GetEvents(lastEventId) => sender ! events.takeWhile(_.id != lastEventId)
  }
}

object Simulation {
  class Command
  case object Tick extends Command
  case class GetEvents (lastEventId: Long) extends Command
  //case class AddAircraft(aircraft: Aircraft)
}

sealed trait Event {
  def id: Long
}
object Event {
  
}

object Simulator {
  // TODO: make this safer
  var simulation: ActorRef = _
  
  def start (system: ActorSystem) {
    simulation = system.actorOf(Props(new sim.Simulation))
    system.scheduler.schedule(0 seconds, 1 second) {
      simulation ! Simulation.Tick
    }  
  }
}
