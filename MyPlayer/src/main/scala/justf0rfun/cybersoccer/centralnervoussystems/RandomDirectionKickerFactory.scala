package justf0rfun.cybersoccer.centralnervoussystems

class RandomDirectionKickerFactory extends CentralNervousSystemFactory {
	
	override def createCentralNervousSystem(peripheralNervousSystem: PeripheralNervousSystem) = new RandomDirectionKicker(peripheralNervousSystem)
	
	override def teamName = "RandomDirectionKickers"

}