
package test


import akka.actor.{ Actor, ActorRef, ActorSystem, Props, FSM }
import akka.event.Logging
import scala.concurrent.duration._

import models._

object FlightTest{

       def testStateChange = {
       	   val system = ActorSystem("MySystem")	   
	   val aircraft = system.actorOf(Props(new Aircraft("N2345", 230).addBehavior(AircraftState.Lit, new LitBehavior())), name = "bird")
	   //aircraft.addBehavior(AircraftState.Lit, new LitBehavior())
	   aircraft ! AircraftState.Lit
	   aircraft ! AircraftState.Taxiing       	   
       }

       def main(args: Array[String]) = {
       	   testStateChange
	   
       }
}