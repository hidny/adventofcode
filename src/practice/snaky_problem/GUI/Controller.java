package practice.snaky_problem.GUI;

import practice.snaky_problem.AI.BasicRandomHandler;
import practice.snaky_problem.AI.EmergencyMoveHandler;
import practice.snaky_problem.AI.MoveHandlerInterface;
import practice.snaky_problem.logic.BoardStateStatic;

public class Controller {

	private DemoFrame frame;
	MoveHandlerInterface moveHandlerInterface;
	
	public Controller(MoveHandlerInterface moveHandlerInterface) {
		this.frame = new DemoFrame(this);
		this.moveHandlerInterface = moveHandlerInterface;
	}
	
	public void start() {
		
	}
	
	public static void main(String args[]) {
		//Controller c = new Controller(new BasicRandomHandler());
		Controller c = new Controller(new EmergencyMoveHandler());
	}

	
	
	public void insertGUIMove(int x, int y) {
		
		if(BoardStateStatic.gameOver) {
			return;
			
		}
		if(BoardStateStatic.get(y, x) != Constants.EMPTY) {
			return;
		}
		BoardStateStatic.put(y, x, Constants.PLAYER_1);
		
		if(BoardStateStatic.gameOver) {
			System.out.println("Game over! Player 1 wins!");
			return;
		}
		
		int player2Move[] = moveHandlerInterface.handleMove(y, x);
		
		if(player2Move[0] >=0) {
			if(BoardStateStatic.get(player2Move[0], player2Move[1]) != Constants.EMPTY) {
				
				System.out.println("ERROR: Player 2 made an illegal move!");
				System.exit(1);
			}
			BoardStateStatic.put(player2Move[0], player2Move[1], Constants.PLAYER_2);
			

			if(BoardStateStatic.gameOver) {
				System.out.println("Game over! Player 2 wins!");
			}
			
		} else {
			System.out.println("ERROR: Player 2 made an illegal move that is -1");
			System.exit(1);
		}
	}
}
