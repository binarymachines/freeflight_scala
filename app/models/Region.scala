package models

import play.api._, mvc._


class LatLong(val latitude: Float, val longitude: Float)

class Region(val northWestCorner: LatLong, val southEastCorner: LatLong, val name: String = "anonymous")

