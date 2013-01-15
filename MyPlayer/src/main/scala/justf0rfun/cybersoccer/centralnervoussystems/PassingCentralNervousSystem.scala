package justf0rfun.cybersoccer.centralnervoussystems
import scala.util.Random
import justf0rfun.cybersoccer.model.Body
import justf0rfun.mathematics.geometry.Point
import justf0rfun.mathematics.geometry.linear.LineSegment
import justf0rfun.mathematics.geometry.linear.Vector
import justf0rfun.cybersoccer.model.Kick

trait PassingCentralNervousSystem extends KickingCentralNervousSystem {

	//	protected def passTo(body: Body) = {
	//		val passTargetPoint = interceptionPoint()
	//		kickBall(targetPoint: Point) = if (isBallInRange) peripheralNervousSystem.kickBall(new Kick(new LineSegment(ball.location, targetPoint).angle, matchConfiguration.kickForceMaximum)) else throw new RuntimeException("Kick to %s impossible, because ball is out of range.".format(targetPoint))
	//	}

	protected def passTo(someBody: Body) = {
//		val passTargetPoint = someBody.location
		peripheralNervousSystem.kickBall(new Kick(new Vector(new LineSegment(ball.location, someBody.location).angle, body.kickForceMaximum * .8)))
//		kickBall(passTargetPoint: Point) //= if (isBallInRange) peripheralNervousSystem.kickBall(new Kick(new LineSegment(ball.location, targetPoint).angle, matchConfiguration.kickForceMaximum)) else throw new RuntimeException("Kick to %s impossible, because ball is out of range.".format(targetPoint))
	}

	protected def passToNearestTeamMember = nearestTeamMember match {
		case Some(body) => passTo(body)
		case _ =>
	}
	
	protected def passToRandomTeamMember = {
		val teamMembers = teamMembersInsideField
		if (!teamMembers.isEmpty) {
			passTo(teamMembers.toList(Random.nextInt(teamMembers.size)))
		}
	}
	
}