package models

import akka.actor.{ Actor, ActorRef, ActorSystem, Props, FSM }
import akka.event.Logging
import scala.concurrent.duration._

import scala.collection.mutable.HashMap


sealed trait AircraftState

object AircraftState{
      case object Parked extends AircraftState
      case object Lit extends AircraftState
      case object Taxiing extends AircraftState
      case object Takeoff extends AircraftState
      case object TakeoffCommit extends AircraftState
      case object InFlight extends AircraftState
}


abstract class AircraftBehavior{
      def trigger(target: Aircraft)
}



//class Aircraft(val transponderID, _altitude: Int) extends Actor with FSM[AircraftState, Data] {
class Aircraft(val transponderID: String, var _altitude: Int) extends Actor with akka.actor.ActorLogging{
      def altitude: Int = { _altitude }

      def provision() = {}
      def load() = {}
      def unload = {}

      var behaviorMap = new HashMap[AircraftState, AircraftBehavior]()

      def receive = {
      	  //if(behaviorMap.contains())
      	  //var behavior = behaviorMap.get
      	  case s: AircraftState => {
	       log.info(s"We received a state change message $s.")

	       if(behaviorMap.contains(s)){
		  var behavior = behaviorMap(s)
		  behavior.trigger(this)
	       }
	       else{
		  log.info(s"No behavior specified for aircraft state $s.")
	       }
	  }  
      }

      def climb(newAltitude: Int) = {
      	  // TODO: do this on a gradient
	  _altitude = newAltitude;
      }

      def descend(newAltitude: Int) = {
      	  _altitude = newAltitude;
      }

      def addBehavior(state: AircraftState, behavior: AircraftBehavior): Aircraft = {
      	  behaviorMap += (state -> behavior)
	  return this
      }
}



class LitBehavior extends AircraftBehavior{
      def trigger(target: Aircraft) = {
      	  println("Triggering the aircraft light-up behavior.")
      	  //log.info(s"Aircraft $transponderID is lit up.")   
      }
}


class TaxiBehavior extends AircraftBehavior{

      def trigger(target: Aircraft) = {
      	  println("Triggering the aircraft taxi behavior.")
      	  //log.info(s"Aircraft $transponderID is lit up.")   
      }
}

