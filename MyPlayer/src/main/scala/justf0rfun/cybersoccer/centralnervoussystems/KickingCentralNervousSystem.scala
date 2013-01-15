package justf0rfun.cybersoccer.centralnervoussystems
import justf0rfun.cybersoccer.model.Kick
import justf0rfun.mathematics.geometry.Point
import justf0rfun.mathematics.geometry.linear.Vector
import justf0rfun.mathematics.geometry.linear.LineSegment

trait KickingCentralNervousSystem extends SensoringCentralNervousSystem {

	protected def shootOnGoal = kickBall(shotOnGoalTargetPoint)

	protected def kickBall(targetPoint: Point): Unit = kickBall(new LineSegment(ball.location, targetPoint).vector)
	
	protected def kickBall(kickVector: Vector): Unit = if (isBallInRange) peripheralNervousSystem.kickBall(new Kick(kickVector)) else throw new RuntimeException("Kick impossible, because ball is out of range.")	

}