package justf0rfun.cybersoccer.centralnervoussystems

import justf0rfun.cybersoccer.model.Body
import justf0rfun.mathematics.geometry.linear.LineSegment
import justf0rfun.mathematics.geometry.linear.Vector
import justf0rfun.mathematics.geometry.circular.Circle
import justf0rfun.cybersoccer.model.Move
import justf0rfun.mathematics.geometry.Point
import justf0rfun.cybersoccer.model.RefereeDecision
import scala.math._
import justf0rfun.mathematics.geometry.linear.Line

trait SensoringCentralNervousSystem extends CentralNervousSystem {

	protected var latestRefereeDecision: Option[RefereeDecision] = None

	protected def nextRefereeDecision: Option[RefereeDecision] = {
		latestRefereeDecision = peripheralNervousSystem.nextRefereeDecision
		latestRefereeDecision
	}

	protected def nextOrLastestRefereeDecision: Option[RefereeDecision] = {
		peripheralNervousSystem.nextRefereeDecision match {
			case None => latestRefereeDecision
			case some => {
				latestRefereeDecision = some
				some
			}
		}
	}

	protected def body = peripheralNervousSystem.body

	protected def matchState = peripheralNervousSystem.matchState

	protected def ball = matchState.ball

	protected def bodies = matchState.bodies

	protected def team = body.team

	protected def field = matchState.field

	protected def location = body.location

	protected def ownGoal = if (team == matchState.host) matchState.guestTargetGoal else matchState.hostTargetGoal

	protected def targetGoal = if (team == matchState.host) matchState.hostTargetGoal else matchState.guestTargetGoal

	protected def isBallInRange = body.isBallInRange(ball)

	protected def teamMembersAndMe = bodies.filter(candidate => candidate.team == team)

	protected def teamMembers = bodies.filter(candidate => candidate != body && candidate.team == team)

	protected def bodyDistanceOrdering = new BodyDistanceOrdering(body)

	protected def nearestTeamMember = if (teamMembers.isEmpty) None else Some(teamMembers.min(bodyDistanceOrdering))

	protected def ballDistanceOrdering = new BodyDistanceOrdering(ball)

	protected def nearestToBall = bodies.min(ballDistanceOrdering)

	protected def nearestTeamMemberToBall = if (teamMembers.isEmpty) None else Some(teamMembers.min(ballDistanceOrdering))

	//	protected def nearestTeamMemberOrMeToBall = if (teamMembers.isEmpty) body else Some((body :: teamMembers.toList).min(ballDistanceOrdering))
	protected def nearestTeamMemberOrMeToBall = if (teamMembers.isEmpty) body else {
		nearestToBall(teamMembersAndMe.min(ballDistanceOrdering))
	}

	protected def isNearestTeamMemberToBall = nearestTeamMemberOrMeToBall == body

	protected def isNearestToBall = body == nearestToBall

	protected def numberOfTeamMembersWithMe = matchState.bodies.size / 2

	protected def numberOfTeamMembers = numberOfTeamMembersWithMe - 1

	protected def hasTeamMembers = 0 < numberOfTeamMembers

	protected def isTeamMember(body: Body) = body.team == team

	protected def isTeamMemberOrMeNearestToBall = isTeamMember(nearestToBall)

	protected def nearestToBall(bodyA: Body, bodyB: Body) = if (ball.location.distance(bodyA.location) <= ball.location.distance(bodyB.location)) bodyA else bodyB

	protected def nearestToBall(someBody: Body) = if (ball.location.distance(location) <= ball.location.distance(someBody.location)) body else someBody

	protected def teamMembersInsideField = teamMembers.filter(teamMember => isInsideField(teamMember.location))

	//	protected def teamMembersInKickRange = teamMembersInsideField.filter(isInKickRange(_))

	protected def isTeamMemberNearestToBall = nearestToBall match {
		case bodyNearestToBall if (bodyNearestToBall != body && isTeamMember(bodyNearestToBall)) => true
		case _ => false
	}

//	protected def interceptionVector(someBody: Body, currentTargetLocation: Point, targetMovement: Move): Option[LineSegment] = {
//		val interception: Option[Point] = interceptionPoint(someBody, currentTargetLocation, targetMovement)
//		interception match {
//			case Some(point) => Some(new LineSegment(someBody.location, point))
//			case None => None
//		}
//	}

//	protected def interceptionPoint(someBody: Body, currentTargetLocation: Point, targetMovement: Move): Option[Point] = {
//		val someBodyMovementRangeCircle = new Circle(someBody.velocityMaximum, someBody.location)
//		val targetMovementVector = new LineSegment(currentTargetLocation, targetMovement.directionAngle, targetMovement.velocity)
//		val intersections = someBodyMovementRangeCircle.intersectionPoints(targetMovementVector.line)
//		return intersections.size match {
//			case 0 => None
//			case 1 => Some(intersections.head)
//			case 2 => Some(intersections.min(new PointDistanceOrdering(body.location)))
//		}
//	}
//
//	protected def interceptionPoint(currentTargetLocation: Point, targetMovement: Move): Option[Point] = {
//		interceptionPoint(body, currentTargetLocation, targetMovement)
//	}

	protected def teamMembersOrderedByBallDistance = teamMembers.toList.sorted(ballDistanceOrdering)

	//	protected def teamMemberNearestToGoal

//	protected def shortestBallInterception(someBodies: Iterable[Body]): Body = {
//		someBodies.map({ someBody: Body =>
//			interceptionVector(someBody, ball.location, ball.move) match {
//				case Some(lineSegment: LineSegment) => (someBody, lineSegment)
//			}
//		}).min(new BodyLineSegmentDistanceOrdering)._1
//
//	}

//	protected def shortestBallInterception: Body = {
//		shortestBallInterception(bodies)
//	}
//
//	protected def shortestBallInterceptionTeamMember: Body = {
//		shortestBallInterception(teamMembers)
//	}
//
//	protected def shortestBallInterceptionTeamMemberOrMe: Body = {
//		shortestBallInterception(teamMembersAndMe)
//	}

//	protected def isShortestBallInterceptionTeamMember: Boolean = {
//		body == shortestBallInterceptionTeamMemberOrMe
//	}

	protected def isInsideField(point: Point) = field.isInsideField(point)

	protected def interceptionPointWithBall(someBody: Body) = {
		//		val timeInterval = (ball.location.x - someBody.location.x) / (matchConfiguration.ballFriction * ) 
	}

	//	protected def prognoseBallPosition(timeInterval: Double) = {
	//		val ballMovementDistance = prognoseBallMovementDistance(timeInterval)
	//		new Point(ball.move.directionAngle.cos * ballMovementDistance, ball.move.directionAngle.sin * ballMovementDistance)
	//	}

	//	protected def prognoseBallMovementDistance(timeInterval: Double) = pow(matchConfiguration.ballFriction, timeInterval / cycleTimeInterval) * ball.move.velocity

	//	protected def nextBallInterceptingBody(bodies: Iterable[Body]) = bodies.map(someBody => (someBody, ballInterceptionTimeInterval(someBody))).min(new BodyDoubleOrdering)

	//	protected def orderBodiesByBallInterceptionTime(bodies: Iterable[Body]) = bodies.map(someBody => (someBody, ballInterceptionTimeInterval(someBody)))

	//	protected def ballInterceptionTimeInterval(someBody: Body): Double = {
	//		val p = -(body.location.x - ball.location.x) * 2 * ball.move.directionAngle.cos * ball.move.directionAngle.sin * ball.move.velocity
	//		val q = -(-pow(ball.location.y, 2) + pow(body.location.y, 2) - pow(ball.location.x, 2) - pow(body.location.x, 2) + body.location.x * ball.location.x) * ball.move.directionAngle.sin / (1 - pow(matchConfiguration.velocityMaximum, 2) * ball.move.directionAngle.sin)
	//		val intersectionTimeIntervals = quadraticSolution(p, q)
	//		if (0 < intersectionTimeIntervals._1 && 0 < intersectionTimeIntervals._2) {
	//			return min(intersectionTimeIntervals._1, intersectionTimeIntervals._2)
	//		} else {
	//			return max(intersectionTimeIntervals._1, intersectionTimeIntervals._2)
	//		}
	//	}

	protected def quadraticSolution(p: Double, q: Double): (Double, Double) = (-p / 2 + sqrt(p / 2 - q), -p / 2 - sqrt(p / 2 - q))

	//	protected moveToBallVector =

	protected def cycleTimeInterval: Double = 15000000

	//	protected def ballMovementTime(ballVelocity: Double) = logarithmBaseTransformation(matchConfiguration.ballFriction, ballVelocity) * cycleTimeInterval

	//	protected def ballMovementDistance(ballVelocity: Double) = logarithmBaseTransformation(matchConfiguration.ballFriction, ballVelocity) * cycleTimeInterval

	//	protected def remainingBallMovementTime = ballMovementTime(ball.move.velocity)

	//	protected def ballMovementTimeMaximum = ballMovementTime(matchConfiguration.kickForceMaximum)

	//	protected lazy val kickDistanceMaximum = prognoseBallMovementDistance(ballMovementTimeMaximum)

	//	protected def isTargetGoalInKickRange = {
	//		isInKickRange(shotOnGoalTargetPoint)
	//	}

	//	protected def isInKickRange(someBody: Body): Boolean = isInKickRange(someBody.location)

	//	protected def isInKickRange(point: Point): Boolean = location.distance(point) <= kickDistanceMaximum

	protected def logarithmBaseTransformation(base: Double, potency: Double) = log(potency) / log(base)

	protected def shotOnGoalTargetPoint = new Vector(ball.diameter * signum(targetGoal.leftPostLocation.x), 0).point(targetGoal.goalLine.middle)
	//	protected def shotOnGoalTargetPoint = if (body.location.distance(targetGoal.leftPostLocation) < body.location.distance(targetGoal.rightPostLocation)) {
	//		targetGoal.leftPostLocation
	//	} else {
	//
	//	}

	protected def teamMembersAndMeOrderedByTargetGoalDistance = teamMembersAndMe.toList.sortBy(_.location.distance(shotOnGoalTargetPoint))

	protected def oponents = bodies.filter(_.team != body.team)

	protected def averageDistanceToOponents(teamMember: Body) = oponents.map(_.location.distance(teamMember.location)).sum / numberOfTeamMembersWithMe

	protected def teamMemberWithMaximumAverageDistanceToOponents = teamMembers.map(teamMember => (teamMember, averageDistanceToOponents(teamMember))).max(new BodyDoubleOrdering)._1
	
//	protected def minimumKickForce(distance: Double) = distance /
	
	protected def ballMovementLine = new Line(ball.move.vector, ball.location)
	
//	protected def ballInterceptionPoint =

}