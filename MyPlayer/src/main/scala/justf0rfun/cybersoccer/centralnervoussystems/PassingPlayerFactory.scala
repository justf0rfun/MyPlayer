package justf0rfun.cybersoccer.centralnervoussystems

class PassingPlayerFactory extends CentralNervousSystemFactory {
	
	def createCentralNervousSystem(peripheralNervousSystem: PeripheralNervousSystem) = new PassingPlayer(peripheralNervousSystem)
	
	def teamName = "PassingPlayers"

}