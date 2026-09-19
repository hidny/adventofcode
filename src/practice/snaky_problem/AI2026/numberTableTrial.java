package practice.snaky_problem.AI2026;

public class numberTableTrial {

	public static final int NUM_PLAYERS = 2;
	public static final int GRID_SIZE = 30;
	public static final int table[][][] = new int[NUM_PLAYERS][GRID_SIZE][GRID_SIZE];
	
	public static final boolean snaky_table[][] = {{false, true, true, true, true},
							                     {true, true, false, false, false}};
	
	public static final int snaky_length = snaky_table[0].length;

	public static final int NUM_ROTATIONS = 4;
	public static final int NUM_REFLECTIONS = 2;
	public static final int NUM_ORIENTATION = NUM_REFLECTIONS * NUM_ROTATIONS;


	public static boolean snaky_table_all_sym[][][] = new boolean[NUM_ORIENTATION][][];

	//public static boolean relativeEffectTable[][][] = new boolean[ORIENTATION][snaky_length][snaky_length];
	
	
	public static int numTreats[][][][] = new int[NUM_PLAYERS][NUM_ORIENTATION][GRID_SIZE][GRID_SIZE];
	
	static {
		
		int index = 0;
		
		for(int re=0; re<NUM_REFLECTIONS; re++) {
			for(int r=0; r<NUM_ROTATIONS; r++) {
				
				if(r % 2 == 1) {
					
					snaky_table_all_sym[index] = new boolean[snaky_table[0].length][snaky_table.length];
					
					boolean flipHori = false;
					boolean flipVert = true;
					
					if(r == 3) {
						flipHori = !flipHori;
						flipVert = !flipVert;
					}
					
					if(re == 1 ) {
						flipHori = !flipHori;
					}
					
					for(int i=0; i<snaky_table.length; i++) {
						for(int j=0; j<snaky_table[0].length; j++) {
							
							int newi = j;
							int newj = i;
							if(flipHori) {
								newj = snaky_table.length - 1 - i;
							}
							
							if(flipVert) {
								newi = snaky_table[0].length - 1 - j;
							}
							System.out.println(" " + i + ", " + j + ", " + newi + ", " + newj);
							
							snaky_table_all_sym[index][newi][newj] = snaky_table[i][j];
						}
					}
					
				} else {
					snaky_table_all_sym[index] = new boolean[snaky_table.length][snaky_table[0].length];
					
					boolean flipHori = false;
					boolean flipVert = false;
					
					if(r == 2) {
						flipHori = !flipHori;
						flipVert = !flipVert;
					}
					
					if(re == 1 ) {
						flipHori = !flipHori;
					}
					
					for(int i=0; i<snaky_table.length; i++) {
						for(int j=0; j<snaky_table[0].length; j++) {
							
							int newi = i;
							int newj = j;
							if(flipVert) {
								newj = snaky_table[0].length - 1 - j;
							}
							
							if(flipHori) {
								newi = snaky_table.length - 1 - i;
							}
							
							snaky_table_all_sym[index][newi][newj] = snaky_table[i][j];
						}
					}
				}
				
				
				index++;
			}
		}
		
		//Print it and test it:
		for(int n=0; n<snaky_table_all_sym.length; n++) {
			
			for(int i=0; i<snaky_table_all_sym[n].length; i++) {

				for(int j=0; j<snaky_table_all_sym[n][i].length; j++) {
					
					if(snaky_table_all_sym[n][i][j]) {
						System.out.print("#");
					} else {
						System.out.print("_");
					}
				}
				System.out.println();
			}
			
			System.out.println();
		}
		
	}
	
	public static void adjustTreatsAfterAdd(boolean isPlayer0, int i, int j) {
		adjustTreats(isPlayer0, i, j, true);
	}

	public static void adjustTreatsAfterRemove(boolean isPlayer0, int i, int j) {
		adjustTreats(isPlayer0, i, j, false);
	}
	
	public static void adjustTreats(boolean isPlayer0, int i, int j, boolean dirAdd) {
		
		
		if( ! (0<=i && i<GRID_SIZE && 0<=j && j<GRID_SIZE) ) {
			System.out.println("Oops!");
			return;
		}
		
		int indexPlayer = isPlayer0 ? 0 : 1;
		int dirTreat = dirAdd ? 1 : -1;
		
		for(int n=0; n<snaky_table_all_sym.length; n++) {
			
			for(int i2=0; i2<snaky_table_all_sym[n].length && i - i2 > 0; i2++) {

				for(int j2=0; j2<snaky_table_all_sym[n][i2].length && j - j2 > 0; j2++) {
					
					if(snaky_table_all_sym[n][i2][j2]) {
						
						numTreats[indexPlayer][n][i-i2][j-j2] += dirTreat;
						
					}
				}
			}
			
		}
		
	}
	
	public static boolean snakeInOne(boolean isPlayer0) {
		
		int indexPlayer = isPlayer0 ? 0 : 1;
		int indexOpponent = isPlayer0 ? 1 : 0;
		
		for(int n=0; n<NUM_ORIENTATION; n++) {			
			for(int i=0; i<numTreats[indexPlayer][n].length; i++) {
				for(int j=0; j<numTreats[indexPlayer][n][i].length; j++) {

					if(numTreats[indexPlayer][n][i][j] == 5 && numTreats[indexOpponent][n][i][j] == 0) {
						return true;
					}

				}
			}
		}
		
		return false;
	}
	
	public static void main(String args[]) {
		
		adjustTreatsAfterAdd(true, 5, 5);
		adjustTreatsAfterAdd(true, 5, 6);

		adjustTreatsAfterAdd(true, 4, 6);
		adjustTreatsAfterAdd(true, 4, 7);
		

		if(snakeInOne(true)) {
			System.out.println("snakeInOne False test failed!");
			System.exit(1);
			
		} else {
		}
		
		adjustTreatsAfterAdd(true, 4, 8);
		
		if(snakeInOne(true)) {
			
		} else {
			System.out.println("snakeInOne True test failed!");
			System.exit(1);
		}
		//adjustTreatsAfterAdd(true, 4, 9);

		//adjustTreatsAfterRemove(true, 5, 5);
		//adjustTreatsAfterRemove(true, 5, 6);

		//adjustTreatsAfterRemove(true, 4, 6);
		//adjustTreatsAfterRemove(true, 4, 7);
		//adjustTreatsAfterRemove(true, 4, 8);
		//adjustTreatsAfterRemove(true, 4, 9);
		
		System.out.println("----------------------------");
		boolean foundIt = false;
		for(int n=0; n<NUM_ORIENTATION; n++) {
			
			for(int i=0; i<snaky_table_all_sym[n].length; i++) {

				for(int j=0; j<snaky_table_all_sym[n][i].length; j++) {
					
					if(snaky_table_all_sym[n][i][j]) {
						System.out.print("#");
					} else {
						System.out.print("_");
					}
				}
				System.out.println();
			}
			
			System.out.println();
			
			foundIt = false;
			for(int i=0; i<GRID_SIZE; i++) {
				for(int j=0; j<GRID_SIZE; j++) {
					System.out.print(numTreats[0][n][i][j]);
					
					if(numTreats[0][n][i][j] == 6) {
						foundIt = true;
					}
				}
				System.out.println();
			}
			if(foundIt) {
				System.out.println("found it!");
			}
			System.out.println();
			System.out.println();
		}
	}
}
