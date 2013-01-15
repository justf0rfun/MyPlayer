package justf0rfun.cybersoccer.centralnervoussystems
import justf0rfun.cybersoccer.model.Body

class Formation_442 extends Formation {

	override def targetPoint(body: Body) = body.index match {
		case 0 => body.location
	}
	
}