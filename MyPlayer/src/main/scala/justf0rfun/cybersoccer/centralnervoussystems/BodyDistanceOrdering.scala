package justf0rfun.cybersoccer.centralnervoussystems
import justf0rfun.cybersoccer.model.Body
import justf0rfun.mathematics.geometry.Point
import justf0rfun.cybersoccer.model.Ball

class BodyDistanceOrdering(referencePoint: Point) extends ExtendedOrdering[Body] {
	
	private val pointDistanceOrdering = new PointDistanceOrdering(referencePoint)
	
	def this(referenceBody: Body) = {
		this(referenceBody.location)
	}
	
	def this(ball: Ball) = {
		this(ball.location)
	}
	
	def compare(bodyA: Body, bodyB: Body) = pointDistanceOrdering.compare(bodyA.location, bodyB.location)

}