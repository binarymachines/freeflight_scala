package models

import play.api._
import play.api.mvc._


class LatLong(_latitude: Float, _longitude: Float){

      def latitude: Float = { _latitude; }
      def longitude: Float = { _longitude }
}


class Region(northWestCorner: LatLong, southEastCorner: LatLong) = {

      def NW: LatLong = { northWestCorner; }
      def SE: LatLong = { southEastCorner; }
}

