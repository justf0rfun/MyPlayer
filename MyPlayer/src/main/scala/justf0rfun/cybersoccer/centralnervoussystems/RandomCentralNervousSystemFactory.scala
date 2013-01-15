package justf0rfun.cybersoccer.centralnervoussystems

class RandomCentralNervousSystemFactory extends CentralNervousSystemFactory {

	def teamName = "random"

	def createCentralNervousSystem(peripheralNervousSystem: PeripheralNervousSystem) = new RandomCentralNervousSystem(peripheralNervousSystem)

}