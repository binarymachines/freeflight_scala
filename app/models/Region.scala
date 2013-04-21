package models

import play.api._, mvc._


case class LatLong(latitude: Float, longitude: Float)

class Region(val northWestCorner: LatLong, val southEastCorner: LatLong, val name: String = "anonymous")

