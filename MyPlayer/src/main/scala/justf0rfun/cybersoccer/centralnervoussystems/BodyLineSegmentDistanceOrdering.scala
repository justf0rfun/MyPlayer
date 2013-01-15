package justf0rfun.cybersoccer.centralnervoussystems
import justf0rfun.mathematics.geometry.linear.LineSegment
import justf0rfun.cybersoccer.model.Body

class BodyLineSegmentDistanceOrdering extends ExtendedOrdering[(Body, LineSegment)]{

	private val lineSegmentDistanceOrdering = new LineSegmentDistanceOrdering
	
	override def compare(tupleA: Tuple2[Body, LineSegment], tupleB: Tuple2[Body, LineSegment]) = lineSegmentDistanceOrdering.compare(tupleA._2, tupleB._2)
	
}