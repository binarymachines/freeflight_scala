package sim

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import play.api.libs.concurrent.Execution.Implicits._
import scala.collection.mutable.ListBuffer
import scala.concurrent.duration._
import play.api.libs.json._
import models._
import formats._

class Simulation extends Actor {
  import Simulation._
  import Event._
  
  val events = new ListBuffer[Event[_]]()
  val aircrafts = new ListBuffer()
  
  def receive = {
    case Tick => println("tick")
    case GetEvents(lastEventId) => sender ! events.toList //.takeWhile(_.id != lastEventId).toList
    case AddAircraft(aircraft) => {
      println("Added aircraft")
      
      // add aircraft
      logEvent(AircraftAddedEvent(0L, aircraft))
    }
  }
  
  def logEvent (ev: Event[_]) =  {
    //if (events.size > 100) events drop 1 
    events append ev
  }
}

object Simulation {
  class Command
  case object Tick extends Command
  case class GetEvents (lastEventId: Long) extends Command
  case class AddAircraft(aircraft: Aircraft)
}

sealed abstract class Event [T:Format](val eventType: String) {self: T =>
  def id: Long
  def toJson = Json.obj("eventType"->eventType, "eventId" -> id) ++ Json.toJson(this).asInstanceOf[JsObject]
}

object Event {
  case class AircraftAddedEvent(id: Long, target: Aircraft) extends Event[AircraftAddedEvent]("aircraft_added")
}

object Simulator {
  // TODO: make this safer
  var simulation: ActorRef = _
  
  def start (system: ActorSystem) {
    simulation = system.actorOf(Props(new sim.Simulation))
    system.scheduler.schedule(0 seconds, 1 second) {
      simulation ! Simulation.Tick
    }
    
    // add a couple of aircraft
    system.scheduler.scheduleOnce(5 seconds) {
      simulation ! Simulation.AddAircraft(Aircraft("a", 2))
      simulation ! Simulation.AddAircraft(Aircraft("b", 3))
    }
  }
}
