package justf0rfun.cybersoccer.centralnervoussystems
import scala.util.Random
import justf0rfun.mathematics.geometry.Point
import justf0rfun.cybersoccer.model.Move
import justf0rfun.mathematics.geometry.Angle

class RandomCentralNervousSystem(peripheralNervousSystem: PeripheralNervousSystem) extends CentralNervousSystem(peripheralNervousSystem) with MovingCentralNervousSystem {

	def think(): Unit = {
		moveInRandomDirection
	}

}