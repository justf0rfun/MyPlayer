package justf0rfun.cybersoccer.centralnervoussystems

trait OffensivePlayer extends CentralNervousSystem with MovingCentralNervousSystem with PassingCentralNervousSystem {

	protected def attack = {
		val orderedTeamMembers = teamMembersAndMeOrderedByTargetGoalDistance
		val myIndex = orderedTeamMembers.indexOf(body)
		if (myIndex == 0) {
			shootOnGoal
		} else {
			passTo(orderedTeamMembers(myIndex - 1))
		}
	}
	
	//TODO find better place
	protected def defendBall = passTo(teamMemberWithMaximumAverageDistanceToOponents)

}