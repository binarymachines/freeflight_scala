import play.api.{Application, GlobalSettings}
import play.api.libs.concurrent.Akka
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.duration._
import akka.actor.Props
import sim.Simulation

object Global extends GlobalSettings {
  
  override def onStart (app: Application) = {
    
    val system = Akka.system(app)
    
    val simulation = system.actorOf(Props(new sim.Simulation))
    
    system.scheduler.schedule(0 seconds, 1 second) {
      simulation ! Simulation.Tick
    }
  }
  
}
