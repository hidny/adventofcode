package prob2024day21.prob21_retry_2025;

public class getNumPadOptions {


	public static final char numPad[][] = { {'7', '8', '9'},
											{'4', '5', '6'},
											{'1', '2', '3'},
											{' ', '0', 'A'}};
	
	public static final char targetList[] = {'A', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public static int charToIndex(char button) {
		for(int j=0; j<targetList.length; j++) {
			if(targetList[j] == button) {
				return j;
			}
		}
		return -1;
	}
	
	private static int[] getCoordNumPad(char button) {
		
		for(int i=0; i<numPad.length; i++) {
			for(int j=0; j<numPad[0].length; j++) {
				if(numPad[i][j] == button) {
					return new int[] {i, j};
				}
			}
		}
		return null;
	}
	
	private static long getManhattanDist(char start, char end) {
		
		int distI = getCoordNumPad(end)[0] - getCoordNumPad(start)[0];
		int distJ = getCoordNumPad(end)[1] - getCoordNumPad(start)[1];
		
		return Math.abs(distI) + Math.abs(distJ);
	}
	
	public static boolean isOnLeft(char button) {
		return getCoordNumPad( button)[1] == 0;
	}
	
	public static boolean isOnBottom(char button) {
		return getCoordNumPad( button)[0] == 3;
	}
	

	public static long[][] createPascalTriangle(int size) {
		size = size+1;
		long pascalTriangle[][] = new long[size][size];
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				pascalTriangle[i][j] = 0;
			}
		}
		
		pascalTriangle[0][0] = 1;
				
		for(int i=1; i<size; i++) {
			for(int j=0; j<size; j++) {
				pascalTriangle[i][j] = pascalTriangle[i-1][j];
				if(j>0) {
					pascalTriangle[i][j] += pascalTriangle[i-1][j-1];
				}
			}
		}
		
		return pascalTriangle;
	}
	
	public static int getNumWays(char start, char end) {
		int distI = getCoordNumPad(end)[0] - getCoordNumPad(start)[0];
		int distJ = getCoordNumPad(end)[1] - getCoordNumPad(start)[1];
		
		long triangle[][] = createPascalTriangle(10);
		int numWays = (int)triangle[Math.abs(distI) + Math.abs(distJ)][Math.abs(distI)];
		

		if((isOnLeft(start) && isOnBottom(end)) || (isOnLeft(end) && isOnBottom(start))) {
			numWays--;
		}
		return numWays;
	}
	
	public static int getNumWays(int indexI, int indexJ) {
		return getNumWays(targetList[indexI], targetList[indexJ]);
	}
	
	public static String getPath(int startI, int endI, int indexPath) {
		String ret = "";
		char start = targetList[startI];
		char end = targetList[endI];
		
		int distI = getCoordNumPad(end)[0] - getCoordNumPad(start)[0];
		int distJ = getCoordNumPad(end)[1] - getCoordNumPad(start)[1];
		
		if(start == end) {
			return "A";
		}
		
		String leftRight = "";
		
		if(distJ > 0) {
			leftRight = ">";
		} else {
			leftRight = "<";
		}
		
		String upDown = "";

		if(distI > 0) {
			upDown = "v";
		} else {
			upDown = "^";
		}
		
		
		boolean combo[] = new boolean[Math.abs(distI) + Math.abs(distJ)];
		
		int curIndex = 0;
		if(getCoordNumPad(start)[0] < getCoordNumPad(end)[0]) {
			//right/left then down (to avoid combo with the corner that's not allowed)
			
			for(int i=0; i<Math.abs(distJ); i++) {
				combo[i] = true;
			}
			
			while(combo!= null && curIndex < indexPath) {
				combo = utilsPE.Combination.getNextCombination(combo);
				curIndex++;
			}
			
			for(int i=0; i<combo.length; i++) {
				if(combo[i]) {
					ret += leftRight;
				} else {
					ret += upDown;
				}
			}
			
		} else {
			//up then right/left (to avoid combo with the corner that's not allowed)
			for(int i=0; i<Math.abs(distI); i++) {
				combo[i] = true;
			}
			
			while(combo != null && curIndex < indexPath) {
				combo = utilsPE.Combination.getNextCombination(combo);
				curIndex++;
			}
			
			for(int i=0; i<combo.length; i++) {
				if(combo[i]) {
					ret += upDown;
				} else {
					ret += leftRight;
				}
			}
			
		}
		
		return ret + "A";
	}
	
	public static void main(String args[]) {
		
		for(int i=0; i<targetList.length; i++) {
			for(int j=0; j<targetList.length; j++) {
				
				if(getNumWays(targetList[i], targetList[j]) != getNumWays(targetList[j], targetList[i])) {
					sopl("oops!");
					exit(1);
				}
				sopl(targetList[i] + " to " + targetList[j] + ": (numWays: " + getNumWays(targetList[i], targetList[j]) + ")");
				
				for(int k=0; k<getNumWays(targetList[i], targetList[j]); k++) {
					sopl(getPath(i, j, k));
				}
				sopl();
				sopl();
				
			}
			sopl();
			sopl();
		}
	}
	

	public static void sop(Object a) {
		System.out.print(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}

	public static void sopl() {
		System.out.println();
	}

	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}
	
}
