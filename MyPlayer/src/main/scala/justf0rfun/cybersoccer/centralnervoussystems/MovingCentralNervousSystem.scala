package justf0rfun.cybersoccer.centralnervoussystems
import justf0rfun.mathematics.geometry.linear.LineSegment
import justf0rfun.mathematics.geometry.linear.Vector
import justf0rfun.cybersoccer.model.Move
import justf0rfun.mathematics.geometry.Point
import justf0rfun.mathematics.geometry.Angle
import scala.util.Random
import scala.math._
import justf0rfun.cybersoccer.model.Body

trait MovingCentralNervousSystem extends SensoringCentralNervousSystem {

	protected def moveTo(targetPoint: Point) = move(new LineSegment(location, targetPoint).vector)

	protected def move(movementVector: Vector) = {
		peripheralNervousSystem.move(new Move(movementVector))
	}

	protected def moveInRandomDirection = {
		move(Vector.createPolarVector(Angle.createByDegree(Random.nextDouble() * 360), body.velocityMaximum))
	}
	
	protected def moveToBall = moveTo(ball.location)

	protected def moveTo(someBody: Body): Unit = moveTo(someBody.location)

	//	protected def interceptBall = {
	//		//		interceptionPoint(ball.location, ball.move) match {
	//		//			case Some(point) => moveTo(point)
	//		//			case None => throw new RuntimeException("No interception point from location %s with ball %s.".format(location, ball))
	//		//		}
	//		moveTo(prognoseBallPosition(ballInterceptionTimeInterval(body)))
	//	}

//	protected def interceptBody(targetBody: Body) = {
//		interceptionPoint(targetBody.location, targetBody.move) match {
//			case Some(point) => moveTo(point)
//			case None => throw new RuntimeException("No interception point from location %s with body %s.".format(location, targetBody))
//		}
//	}

	protected def randomPointInOwnFieldHalf: Point = {
		if (targetGoal == field.rightGoal) {
			randomPointInLeftHalf
		} else {
			randomPointInRightHalf
		}
	}

	protected def randomPointInLeftHalf: Point = randomPoint(field.upperLeft.x, field.kickOffPoint.location.x, field.upperLeft.y, field.bottomLeft.y)

	protected def randomPointInRightHalf: Point = randomPoint(field.kickOffPoint.location.x, field.upperRight.x, field.upperLeft.y, field.bottomLeft.y)

	protected def randomPointInField: Point = randomPoint(field.upperLeft.x, field.upperRight.x, field.upperLeft.y, field.bottomLeft.y)

	protected def randomPoint(minX: Double, maxX: Double, minY: Double, maxY: Double): Point = new Point(Random.nextDouble() * (maxX - minX) + minX, Random.nextDouble() * (maxY - minY) + minY)

	protected def holdDistanceTo(point: Point, distance: Double) = {
		val vector = new LineSegment(location, point).vector
		if (distance < vector.distance) move(vector) else move(vector.inverse)
	}

	protected def formationAroundBall(distance: Double) = formationAroundPoint(distance, ball.location)

	protected def formationAroundPoint(distance: Double, referencePoint: Point) = if (isNearestTeamMemberToBall) moveToBall else moveTo( Vector.createPolarVector(Angle.createByDegree((360 / numberOfTeamMembers) * body.index), distance).point(referencePoint))

	protected def formationAroundTeamMemberNearestToBall(distance: Double) = formationAroundPoint(distance, nearestTeamMemberToBall match { case Some(teamMember) => teamMember.location })
	
	protected def teamMember(index: Int) = teamMembers.find(_.index == index)
	
	protected def nextTeamMember = teamMember((body.index + 1) % numberOfTeamMembers)

	protected def offerForPass = {
		if (1 < numberOfTeamMembers) {
			val ordered = teamMembersOrderedByBallDistance
			val firstNearest = ordered(0)
			val secondNearest = ordered(1)
			//There are two possible possition with angle of 90′ to the two team members. Random decides which one to target.
			val offeringPoint = if (Random.nextBoolean()) {
				new Point(max(firstNearest.location.x, secondNearest.location.x), min(firstNearest.location.y, secondNearest.location.y))
			} else {
				new Point(min(firstNearest.location.x, secondNearest.location.x), max(firstNearest.location.y, secondNearest.location.y))
			}
			moveTo(offeringPoint)
		}
	}

}