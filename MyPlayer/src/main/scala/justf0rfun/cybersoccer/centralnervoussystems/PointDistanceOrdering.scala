package justf0rfun.cybersoccer.centralnervoussystems
import justf0rfun.mathematics.geometry.Point
import scala.math._

class PointDistanceOrdering(referencePoint: Point) extends ExtendedOrdering[Point] {

	override def compare(pointA: Point, pointB: Point) = (abs(referencePoint.distance(pointA)) - abs(referencePoint.distance(pointB))).toInt

}