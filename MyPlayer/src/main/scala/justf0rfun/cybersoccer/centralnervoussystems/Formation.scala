package justf0rfun.cybersoccer.centralnervoussystems
import justf0rfun.mathematics.geometry.Point
import justf0rfun.cybersoccer.model.Body

trait Formation {
	
	def targetPoint(body: Body): Point

}