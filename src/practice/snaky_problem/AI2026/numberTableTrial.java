package practice.snaky_problem.AI2026;

public class numberTableTrial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int NUM_PLAYERS = 2;
	public static int GRID_SIZE = 30;
	public static int table[][][] = new int[NUM_PLAYERS][GRID_SIZE][GRID_SIZE];
	
	public static boolean snaky_table[][] = {{false, true, true, true, true},
							                 {true, true, false, false, false}};

	public static int ROTATIONS = 4;
	public static int REFLECTIONS = 2;
	public static int ORIENTATION = REFLECTIONS * ROTATIONS;
	static {
		boolean snaky_table_all_sym[][][] = new boolean[ORIENTATION][][];
		
		int index = 0;
		
		for(int re=0; re<REFLECTIONS; re++) {
			for(int r=0; r<ROTATIONS; r++) {
				
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
}
