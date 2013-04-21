package models

import play.api.libs.json.Json
import sim.Event._

// TODO: stub, replace with real one
case class Aircraft(name: String, test: Int, currentPosition: LatLong)

/**
 * Serialization formats for json
 */
object formats {
  implicit val latLongF = Json.format[LatLong]
  implicit val aircraftF = Json.format[Aircraft]
  
  // events
  implicit val aircraftAddedF = Json.format[AircraftAddedEvent]
  implicit val aircraftVelocityChangedF = Json.format[AircraftVelocityChangedEvent]
}
