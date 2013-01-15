package justf0rfun.cybersoccer.centralnervoussystems
import justf0rfun.cybersoccer.model.Body

class BodyDoubleOrdering extends ExtendedOrdering[(Body, Double)] {
	
	override def compare(tupleA: (Body, Double), tupleB: (Body, Double)) = (1 * scala.math.signum(tupleA._2 - tupleB._2)).toInt
	
}