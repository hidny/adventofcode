package practice.snaky_problem.AI;

import practice.snaky_problem.AI.utils.PatternChecker;
import practice.snaky_problem.GUI.Constants;
import practice.snaky_problem.logic.BoardStateStatic;

public class PracticalMoveHandler implements MoveHandlerInterface {

	public PracticalMoveHandler() {
		this(Constants.PLAYER_2);
	}
	
	private EmergencyMoveHandler backupMover;
	private BasicRandomHandler basicMover;
	
	public PracticalMoveHandler(int playerIndex) {

		this.backupMover = new EmergencyMoveHandler();
		this.basicMover = new BasicRandomHandler();
		
		if(playerIndex == Constants.PLAYER_1) {
			System.out.println("Assuming player 2 for now.");
			System.exit(1);
		}
		
		
		
	}

	@Override
	public int[] handleMove(int i, int j) {
		
		//Commented out because of the handling of double threats...
		if(this.backupMover.hasEmergencyMove()) {
			return this.backupMover.handleMove(i, j);
		}
		
		System.out.println("hello PracticalMoveHandler");
		if(PatternChecker.hasNInARow(BoardStateStatic.state, Constants.PLAYER_2, 4, true)) {
			System.out.println("Making 5 in a row for myself/player2");
			return PatternChecker.getOpenEndOfNInARow(BoardStateStatic.state, Constants.PLAYER_2, 4);
		}

		if(PatternChecker.hasNInARow(BoardStateStatic.state, Constants.PLAYER_1, 4, true)) {
			System.out.println("Stoping 5 in a row for you (player1)");
			return PatternChecker.getOpenEndOfNInARow(BoardStateStatic.state, Constants.PLAYER_1, 4);
		}

		if(PatternChecker.getDoubleOpenEndOfNInARow(BoardStateStatic.state, Constants.PLAYER_2, 3) != null) {
			return PatternChecker.getDoubleOpenEndOfNInARow(BoardStateStatic.state, Constants.PLAYER_2, 3);
		}
		
		if(PatternChecker.getDoubleOpenEndOfNInARow(BoardStateStatic.state, Constants.PLAYER_1, 3) != null) {
			return PatternChecker.getDoubleOpenEndOfNInARow(BoardStateStatic.state, Constants.PLAYER_1, 3);
		}
		

		if(PatternChecker.getDoubleOpenEndOfNInARow(BoardStateStatic.state, Constants.PLAYER_2, 2) != null) {
			return PatternChecker.getDoubleOpenEndOfNInARow(BoardStateStatic.state, Constants.PLAYER_2, 2);
		}
		
		if(PatternChecker.getDoubleOpenEndOfNInARow(BoardStateStatic.state, Constants.PLAYER_1, 2) != null) {
			return PatternChecker.getDoubleOpenEndOfNInARow(BoardStateStatic.state, Constants.PLAYER_1, 2);
		}
		
		
		return basicMover.handleMove(i, j);
	}

}
