package justf0rfun.cybersoccer.centralnervoussystems
import justf0rfun.mathematics.geometry.linear.LineSegment
import justf0rfun.cybersoccer.model.RefereeDecision

class PassingPlayer(peripheralNervousSystem: PeripheralNervousSystem) extends CentralNervousSystem(peripheralNervousSystem) with MovingCentralNervousSystem with PassingCentralNervousSystem with OffensivePlayer {

	lazy private val kickOffPreparationPosition = randomPointInOwnFieldHalf

	override def think = {
		nextOrLastestRefereeDecision match {
			case Some(RefereeDecision.KickOff(someTeam)) => {
				moveTo(kickOffPreparationPosition)
				if (new LineSegment(location, kickOffPreparationPosition).distance < 1) {
					latestRefereeDecision = None
				}
			}
			case Some(RefereeDecision.Goal(someTeam)) => {
				moveTo(kickOffPreparationPosition)
				if (new LineSegment(location, kickOffPreparationPosition).distance < 1) {
					latestRefereeDecision = None
				}
			}
			case _ => {
				if (isNearestTeamMemberToBall) {
					if (isBallInRange) {
						//						passToNearestTeamMember
						//						passToRandomTeamMember
//						nextTeamMember match {
//							case Some(teamMember) => passTo(teamMember)
//							case None =>
//						}
						attack
					} else {
						moveToBall
					}
				} else {
					if (nearestToBall.team == body.team) {
						moveTo(shotOnGoalTargetPoint)
					} else {
						val offeringDistance: Double = 300
						formationAroundBall(offeringDistance)
					}
				}
			}
		}
	}
}