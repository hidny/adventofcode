package practice.snaky_problem.logic;

import practice.snaky_problem.GUI.Constants;

public class BoardStateStatic {

	
	public static int state[][] = new int[Constants.NUM_CELLS_VERT][Constants.NUM_CELLS_HORI];
	public static int state2[][] = new int[Constants.NUM_CELLS_VERT][Constants.NUM_CELLS_HORI];
	public static int numAdded = 0;
	
	public static int get(int i, int j) {
		return state[i][j];
	}
	
	public static boolean gameOver = false;
	
	public static void put(int i, int j, int num) {
		
		numAdded++;
		
		if(i >= 0 && j>=0 && i<Constants.NUM_CELLS_VERT && j <Constants.NUM_CELLS_HORI) {
			state[i][j] = num;
		} else {
			System.out.println("Out of bounds in BoardStateStatic!");
		}
		
		state2[i][j] = numAdded;
		
		if(hasAWin(i, j)) {
			//TODO: print board state with ordering of moves
			
			gameOver = true;
		}
	}
	
	public static final boolean snake[][]= {{false, false, false, true, true},
			                           {true, true,  true,  true, false}
	                          };
	
	public static boolean allSnakes[][][] = null;
	
	//Inefficient, but whatever:
	public static boolean hasAWin(int puti, int putj) {
		
		if(allSnakes == null) {
			setupAllSnakes();
		}
		
		
		for(int i2=0; i2<Constants.NUM_CELLS_VERT; i2++) {
			for(int j2=0; j2<Constants.NUM_CELLS_HORI; j2++) {
				
				if(hasWinAtLocation(i2, j2, Constants.PLAYER_1)) {
					return true;
				}
				if(hasWinAtLocation(i2, j2, Constants.PLAYER_2)) {
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	public static boolean hasWinAtLocation(int i2, int j2, int playerIndex) {
		
		NEXTK:
		for(int k=0; k<allSnakes.length; k++) {
			
			boolean matchesSoFar = true;
			
			for(int i3=0; i3<allSnakes[k].length; i3++) {
				for(int j3=0; j3<allSnakes[k][0].length; j3++) {
					
					if(i2 + i3 >= Constants.NUM_CELLS_VERT || j2 + j3>=Constants.NUM_CELLS_HORI) {
						matchesSoFar = false;
						continue;
					}
					
					if(allSnakes[k][i3][j3] == true && get(i2 + i3, j2 + j3) != playerIndex) {
						matchesSoFar = false;
						continue;
					}
				}
			}
			
			if(matchesSoFar) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public static void setupAllSnakes() {
		
		allSnakes = new boolean[8][][];
		
		for(int i=0; i<4; i++) {
			allSnakes[i] = new boolean[snake.length][snake[0].length];
		}
		
		for(int i2=0; i2<allSnakes[0].length; i2++) {

			for(int j2=0; j2<allSnakes[0][0].length; j2++) {
				
				for(int k=0; k<4; k++) {
					
					if(k==0) {
						allSnakes[k][i2][j2] = snake[i2][j2];
					} else if(k == 1) {
						allSnakes[k][i2][j2] = snake[snake.length - 1 - i2][j2];
						
					} else if(k == 2) {
						allSnakes[k][i2][j2] = snake[i2][snake[0].length - 1 - j2];
						
					} else if(k == 3) {
						allSnakes[k][i2][j2] = snake[snake.length - 1 - i2][snake[0].length - 1 - j2];
						
					} else {
						System.out.println("DOH");
						System.exit(1);
					}
				}
			}
		}
					
		for(int i=0; i<4; i++) {
			allSnakes[4 + i] = new boolean[snake[0].length][snake.length];
		}
		
		for(int i2=0; i2<allSnakes[4].length; i2++) {

			for(int j2=0; j2<allSnakes[4][0].length; j2++) {
				
				for(int k=4; k<8; k++) {
					
					if(k==4) {
						allSnakes[k][i2][j2] = snake[j2][i2];
					} else if(k == 5) {
						allSnakes[k][i2][j2] = snake[j2][snake[0].length - 1 - i2];
						
					} else if(k == 6) {
						allSnakes[k][i2][j2] = snake[snake.length - 1 - j2][i2];
						
					} else if(k == 7) {
						allSnakes[k][i2][j2] = snake[snake.length - 1 - j2][snake[0].length - 1 - i2];
						
					} else {
						System.out.println("DOH 2");
						System.exit(1);
					}
				}
			}
		}
		

	}

}
