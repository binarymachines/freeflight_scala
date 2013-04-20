package sim

import akka.actor.Actor

class Simulation extends Actor {
  import Simulation._
  
  def receive = {
    case Tick => println("tick")
  }
}

object Simulation {
  object Tick
}
