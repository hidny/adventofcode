package practice.snaky_problem.tests;

import practice.snaky_problem.logic.BoardStateStatic;

public class TestStaticBoard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}

	
	public static void testWinningPositions() {
		
		//BoardStateStatic.state = new int[][];
		
		BoardStateStatic.resetBoardState();
		
		
		BoardStateStatic.put(4, 1, 1);
		BoardStateStatic.put(4, 2, 1);
		BoardStateStatic.put(4, 3, 1);
		BoardStateStatic.put(4, 4, 1);
		BoardStateStatic.put(3, 4, 1);
		BoardStateStatic.put(3, 5, 1);
		
		if(BoardStateStatic.hasAWin(-1, -1) == false) {
			System.out.println("Test 1 failed");
		}
		
		
		BoardStateStatic.resetBoardState();
		
		

		BoardStateStatic.put(4, 1, 1);
		BoardStateStatic.put(4, 2, 1);
		BoardStateStatic.put(4, 3, 1);
		//Missing
		BoardStateStatic.put(3, 4, 1);
		BoardStateStatic.put(3, 5, 1);
		
		if(BoardStateStatic.hasAWin(-1, -1) == true) {
			System.out.println("Test 2 failed");
		}
		
		
		System.out.println("End of program");
	}
}
