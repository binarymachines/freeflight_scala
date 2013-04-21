package models

import play.api.libs.json.Json
import sim.Event._

case class Aircraft(name: String, test: Int)

object formats {
  implicit val aircraftFormat = Json.format[Aircraft]
  
  // events
  implicit val aircraftAddedFormat = Json.format[AircraftAddedEvent]
}
