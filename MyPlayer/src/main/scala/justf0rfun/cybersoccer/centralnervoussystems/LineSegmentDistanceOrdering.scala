package justf0rfun.cybersoccer.centralnervoussystems
import justf0rfun.mathematics.geometry.linear.LineSegment
import scala.math._

class LineSegmentDistanceOrdering extends ExtendedOrdering[LineSegment] {

	override def compare(lineSegmentA: LineSegment, lineSegmentB: LineSegment) = (abs(lineSegmentA.distance) - abs(lineSegmentB.distance)).toInt

}