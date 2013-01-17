package justf0rfun.cybersoccer.centralnervoussystems

import justf0rfun.cybersoccer.model.Kick
import justf0rfun.mathematics.geometry.linear.Vector
import justf0rfun.mathematics.geometry.Angle
import scala.util.Random

class RandomDirectionKicker(peripheralNervousSystem: PeripheralNervousSystem) extends CentralNervousSystem(peripheralNervousSystem) with KickingCentralNervousSystem with MovingCentralNervousSystem {

	override def think = {
		if (isBallInRange) {
			kickBall(Vector.createPolarVector(Angle.createByDegree(Random.nextDouble() * 360), body.kickForceMaximum))
		} else {
			moveTo(ball.location)
		}
	}

}