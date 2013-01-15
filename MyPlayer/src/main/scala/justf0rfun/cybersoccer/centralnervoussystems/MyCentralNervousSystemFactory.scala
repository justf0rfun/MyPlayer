package justf0rfun.cybersoccer.centralnervoussystems

class MyCentralNervousSystemFactory extends CentralNervousSystemFactory {

	def createCentralNervousSystem(peripheralNervousSystem: PeripheralNervousSystem) = new MyCentralNervousSystem(peripheralNervousSystem)
	
	def teamName = "Judäische Volksfront"
	
}