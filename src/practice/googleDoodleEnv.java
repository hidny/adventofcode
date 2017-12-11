package practice;

public class googleDoodleEnv {


	public static int WALL = 1;
	public static int CARROT = 2;
	
	private int grid[][];
	private int iBunny;
	private int jBunny;
	// grid:
	//0 is empty
	//1 is wall
	//2 is food
	
	private int dir = 0;
	//0 is right
	//1 is up
	//2 is left
	//3 is down
	

	public googleDoodleEnv makeHardCopy() {
		return new googleDoodleEnv(this.grid, iBunny, jBunny, dir);
	}
	
	public googleDoodleEnv(int grid[][], int iBunny, int jBunny, int dir) {
		this.grid = new int[grid.length][grid[0].length];
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				this.grid[i][j] = grid[i][j];
			}
		}
		this.iBunny = iBunny;
		this.jBunny = jBunny;
		this.dir = dir;
	}
	
	
	public static int STRAIGHT = 0;
	public static int TURN_RIGHT = 1;
	public static int TURN_LEFT = 2;
	public static int END_LOOP4 = 3;
	public static int START_LOOP4 = 4;
	public static int NUM_INSTRUCTIONS = 5;
	
	
	public static googleDoodleEnv playInstructions(int instructions[], googleDoodleEnv start) {
		googleDoodleEnv currentPos = start.makeHardCopy();
		for(int i=0; i<instructions.length; i++) {
			
			if(instructions[i] == STRAIGHT) {
				currentPos = currentPos.moveForward();
				
			} else if(instructions[i] == TURN_RIGHT) {
				currentPos = currentPos.turnRight();
				
			} else if(instructions[i] == TURN_LEFT) {
				currentPos = currentPos.turnLeft();
				
			} else if(instructions[i] == END_LOOP4) {
				
				int loopInstructions[] = getIntructionsLoop(instructions, i);
				
				for(int redo=0; redo<4-1; redo++) {
					currentPos = playInstructions(loopInstructions, currentPos);
				}
				
			} else  if(instructions[i] == START_LOOP4) {
				//Do nothing,
			} else {
				System.out.println("ERROR: unknown instuction!");
				System.exit(1);
			}
		}
		
		return currentPos;
	}
	
	
	public static int[] getIntructionsLoop(int instructions[], int indexLoopIns) {
		//from ??
		//up to i-1
		int currentStart = indexLoopIns-1;
		int currentEnd = indexLoopIns-1;
		
		int amountOfLoopEnds = 1;
		
		for(; currentStart >=0; currentStart--) {
			if(instructions[currentStart] == END_LOOP4) {
				amountOfLoopEnds++;
			} else if(instructions[currentStart] == START_LOOP4) {
				amountOfLoopEnds--;
				if(amountOfLoopEnds == 0) {
					break;
				}
				
			}
		}
		
		int ret[] = new int[currentEnd - currentStart + 1];
		
		for(int i=0; i<ret.length; i++) {
			ret[i] = instructions[currentStart + i];
		}
		
		return ret;
	}
	
	private googleDoodleEnv moveForward() {
		
		googleDoodleEnv moved = new googleDoodleEnv(this.grid, iBunny, jBunny, dir);
		int iBun = this.iBunny;
		int jBun = this.jBunny;
		if(dir ==0 ) {
			jBun++;
		} else if(dir == 1) {
			iBun--;
		} else if(dir == 2) {
			jBun--;
		} else if(dir == 3) {
			iBun++;
		}
		
		if(isWall(iBun, jBun) == false) {
			moved.iBunny = iBun;
			moved.jBunny = jBun;
			moved.grid[iBun][jBun] = 0;
		}
		
		
		return moved;
	}
	
	public googleDoodleEnv turnLeft() {
		return new googleDoodleEnv(this.grid, iBunny, jBunny, (4 + dir + 1) %4);
	}
	
	public googleDoodleEnv turnRight() {
		return new googleDoodleEnv(this.grid, iBunny, jBunny, (4 + dir - 1) %4);
	}
	
	
	//TODO
	private boolean isWall(int i, int j) {
		if(i < 0 || j<0 | i>=grid.length || j>=grid[0].length) {
			return true;
		}
		if(grid[i][j] == WALL) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasCarrots() {
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j] == CARROT) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void print() {
		System.out.println(this);
		
	}
	
	public String toString() {
		String ret = "";
		ret += getBunnyDir(dir) + "\n";
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(i == iBunny && j == jBunny) {
					ret += "B";
				} else if(grid[i][j] == WALL) {
					ret += "W";
				} else if(grid[i][j] == CARROT) {
					ret += "C";
				} else {
					ret += " ";
				}
			}
			ret += "\n";
		}
		if(hasCarrots()) {
			ret += "There\'s still carrots" +"\n";
		} else {
			ret += "No carrots!" +"\n";
		}
		ret +="\n";
		
		return ret;
	}
	
	public static String getBunnyDir(int dir) {
		if(dir == 0) {
			return "R";
		} else if(dir == 1) {
			return "U";
		} else if(dir == 2) {
			return "L";
		} else if(dir == 3) {
			return "D";
		} else {
			System.out.println("WHAT DIR?");
			System.exit(1);
			return "AAAH";
		}
	}
}
