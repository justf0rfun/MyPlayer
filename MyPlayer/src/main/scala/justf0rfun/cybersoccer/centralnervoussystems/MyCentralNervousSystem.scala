package justf0rfun.cybersoccer.centralnervoussystems
import justf0rfun.mathematics.geometry.linear.LineSegment
import justf0rfun.cybersoccer.model.RefereeDecision

class MyCentralNervousSystem(peripheralNervousSystem: PeripheralNervousSystem) extends CentralNervousSystem(peripheralNervousSystem) with MovingCentralNervousSystem with PassingCentralNervousSystem {

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
//						if (isTargetGoalInKickRange) {
//							shootOnGoal
//						} else {
//							//						passToNearestTeamMember
//							passToRandomTeamMember
//						}
						shootOnGoal
					} else {
						moveToBall
						//						interceptBall
					}
				} else {
					val offeringDistance: Double = 220
					formationAroundTeamMemberNearestToBall(offeringDistance)
					//					nearestTeamMemberToBall match {
					//						case Some(teamMember) if (location.distance(teamMember.location) < offeringDistance) => holdDistanceTo(teamMember.location, offeringDistance)
					//						case _ => offerForPass
					//					}
					//					nearestTeamMemberToBall match {
					//						case Some(teamMember) => {
					//							if (!teamMember.isBallInRange(ball)) {
					////								moveTo(teamMember)
					//								moveInRandomDirection
					//							}
					//						}
					//						case _ =>
					//					}
				}

			}
		}
	}

}