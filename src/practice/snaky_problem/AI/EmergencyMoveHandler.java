package practice.snaky_problem.AI;

import practice.snaky_problem.AI.utils.positionCheckUtils;
import practice.snaky_problem.GUI.Constants;

public class EmergencyMoveHandler  implements MoveHandlerInterface {

	public EmergencyMoveHandler() {
		this(Constants.PLAYER_2);
	}
	
	private BasicRandomHandler backupMover;
	
	public EmergencyMoveHandler(int playerIndex) {

		this.backupMover = new BasicRandomHandler();
		
		if(playerIndex == Constants.PLAYER_1) {
			System.out.println("ERROR: The EmergencyMoveHandler AI is not meant to be player 1.");
			System.exit(1);
		}
	}
	
	@Override
	public int[] handleMove(int i, int j) {

		
		//End the game if possible:
		if(positionCheckUtils.is1Away(Constants.PLAYER_2)) {
			System.out.println("hello 1");
			System.out.println("winning move");
			return positionCheckUtils.get1Away(Constants.PLAYER_2);
		}

		//Stop player 1 from ending the game
		if(positionCheckUtils.is1Away(Constants.PLAYER_1)) {
			System.out.println("hello 2");
			System.out.println("stop winning move");
			return positionCheckUtils.get1Away(Constants.PLAYER_1);
		}
		
		System.out.println("Regualar move");
		return backupMover.handleMove(i, j);
	}

}
