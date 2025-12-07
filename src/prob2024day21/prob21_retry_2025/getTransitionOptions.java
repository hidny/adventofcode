package prob2024day21.prob21_retry_2025;

public class getTransitionOptions {


	public static final char dirPad[][] = {{' ', '^', 'A'}, {'<', 'v', '>'}};
	public static final char targetList[] = {'^', 'A', '<', 'v', '>'};
	
	
	//For every transition say '^' to '>', there's up to 2 ways to do it.
	// Both ways lead to a different transition list.
	public static long transitionsListNextLevel[][][] = new long[targetList.length * targetList.length][targetList.length * targetList.length][];
	
	public static int charToIndex(char button) {
		for(int j=0; j<targetList.length; j++) {
			if(targetList[j] == button) {
				return j;
			}
		}
		return -1;
	}
	
	private static int[] getCoordDirPad(char button) {
		
		for(int i=0; i<dirPad.length; i++) {
			for(int j=0; j<dirPad[0].length; j++) {
				if(dirPad[i][j] == button) {
					return new int[] {i, j};
				}
			}
		}
		return null;
	}
	
	private static long getManhattanDist(char start, char end) {
		
		int distI = getCoordDirPad(end)[0] - getCoordDirPad(start)[0];
		int distJ = getCoordDirPad(end)[1] - getCoordDirPad(start)[1];
		
		return Math.abs(distI) + Math.abs(distJ);
	}
	
	public static int getNumWays(char start, char end) {
		int distI = getCoordDirPad(end)[0] - getCoordDirPad(start)[0];
		int distJ = getCoordDirPad(end)[1] - getCoordDirPad(start)[1];
		
		if(Math.abs(distI) > 0 && Math.abs(distJ) > 0) {
			if(Math.abs(distJ) == 1 && (start =='<' || end =='<')) {
				//The exception:
				return 1;
			}
			return 2;
		} else {
			return 1;
		}
	}
	public static int getNumWays(int indexI, int indexJ) {
		return getNumWays(targetList[indexI], targetList[indexJ]);
	}
	
	public static String getPath(int startI, int endI, int indexPath) {
		String ret = "";
		char start = targetList[startI];
		char end = targetList[endI];
		
		int distI = getCoordDirPad(end)[0] - getCoordDirPad(start)[0];
		
		if(start == end) {
			return "A";
		}
		
		
		if(indexPath == 0) {
			
			String endString = "";
			if(start == '<') {
				ret += ">";
				start = 'v';
			} else if(end == '<') {
				endString += "<";
				end = 'v';
			}
			int distJ = getCoordDirPad(end)[1] - getCoordDirPad(start)[1];
			

			char iterateOnChar = ' ';
			if(distJ < 0) {
				iterateOnChar = '<';
			} else {
				iterateOnChar = '>';
			}

			for(int j=0; j<Math.abs(distJ); j++) {
				ret += iterateOnChar;
			}
			
			if(distI < 0) {
				iterateOnChar = '^';
			} else {
				iterateOnChar = 'v';
			}

			for(int j=0; j<Math.abs(distI); j++) {
				ret += iterateOnChar;
			}
			
			
			
			ret += endString;
			
			
		} else {
			
			if(distI ==0 || getCoordDirPad(end)[1] == getCoordDirPad(start)[1]) {
				exit(1);
			}
			
			String endString = "";
			if(start == '<') {
				ret += ">";
				start = 'v';
			} else if(end == '<') {
				endString += "<";
				end = 'v';
			}
			int distJ = getCoordDirPad(end)[1] - getCoordDirPad(start)[1];
			
			char iterateOnChar = ' ';
			if(distI < 0) {
				iterateOnChar = '^';
			} else {
				iterateOnChar = 'v';
			}

			for(int j=0; j<Math.abs(distI); j++) {
				ret += iterateOnChar;
			}
			
			
			if(distJ < 0) {
				iterateOnChar = '<';
			} else {
				iterateOnChar = '>';
			}

			for(int j=0; j<Math.abs(distJ); j++) {
				ret += iterateOnChar;
			}
			
			
			ret += endString;
		}
		
		return ret + "A";
	}
	
	public static long[] getTransitionListGivenPath(String path) {

		long transitionResult[] = new long[transitionsListNextLevel.length];
		
		for(int k=0; k<transitionResult.length; k++) {
			transitionResult[k] = 0;
		}
		
		char prevChar = 'A';
		for(int k=0; k<path.length(); k++) {
			
			int transitionIndexToUse = targetList.length * charToIndex(prevChar) + charToIndex(path.charAt(k));
			transitionResult[transitionIndexToUse]++;
			
			prevChar = path.charAt(k);
		}
		
		return transitionResult;
	}
	
	public static void createTransitionListNextLevel() {
		
		for(int i=0; i<transitionsListNextLevel.length; i++) {
			int startI = i / targetList.length;
			int endI = i % targetList.length;
			
			int numWays = getNumWays(startI, endI);
			
			for(int j1=0; j1<transitionsListNextLevel.length; j1++) {
				transitionsListNextLevel[i][j1] = new long[numWays];
			}
			
			sopl("" + targetList[startI] + " to " + targetList[endI] + ": ");
			for(int wayIndex=0; wayIndex<numWays; wayIndex++) {
				String path = getPath(startI, endI, wayIndex);
				sopl("Index Path: " + wayIndex + " : " + path);
				
				long transitionResult[] = getTransitionListGivenPath(path);
				
				for(int j1=0; j1<transitionResult.length; j1++) {
					transitionsListNextLevel[i][j1][wayIndex] = transitionResult[j1];
				}
				
				
			}
			sopl();
			
			
		}
	}

	
	public static long getNumWaysCurLevel(String path) {
		
		long transitionResult[] = getTransitionListGivenPath(path);
		
		return getNumWaysCurLevel(transitionResult);
	}
	
	public static long getNumWaysCurLevel(long transitions[]) {
		
		long ret = 0L;
		for(int i=0; i<transitions.length; i++) {
			ret += transitions[i];
		}
		return ret;
	}
	
	//Get possible transitions next level...
	public static long[][] getPossibleTransitionsNextLevel(String path) {
		
		long transitionResult[] = getTransitionListGivenPath(path);
		
		return getPossibleTransitionsNextLevel(transitionResult);
	}
	
	
	public static int getNumDistinctTransitionsWithMultipleAnswers(long transitions[]) {
		
		int ret = 0;
		for(int i=0; i<transitions.length; i++) {
			
			if(transitions[i] == 0) {
				continue;
			}
			
			if(transitionsListNextLevel[i][0].length == 2) {
				ret++;
				
			}
		}
		
		return ret;
		
	}
	//TODO:
	public static long[][] getPossibleTransitionsNextLevel(long transitions[]) {
		
		int numWaysNaive = (int)(Math.pow(2, getNumDistinctTransitionsWithMultipleAnswers(transitions)));

		long ret[][] = new long[numWaysNaive][transitions.length];
		
		for(int numWayIndex= 0; numWayIndex<numWaysNaive; numWayIndex++) {
			
			long curDirectionCode = numWayIndex;
			for(int i=0; i<transitions.length; i++) {
				
				if(transitions[i] == 0) {
					continue;
				}
				
				if(transitionsListNextLevel[i][0].length == 2) {
					
					if(curDirectionCode % 2 == 0) {
						
						//TODO
					} else {
						
						//TODO
					}
					
					curDirectionCode /= 2;
				}
			}
			
			if(curDirectionCode > 0) {
				sopl("doh!");
				exit(1);
			}
		}
		
		
		return ret;
	}
	
	//TODO: have function eliminate dominated transition list options
	//TODO: eventually have a rule of thumb for eliminating unpromissing transition list options.
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createTransitionListNextLevel();
		
		sopl("---------");
		String path = "A";
		sopl("Test path=(" + path + "): " + getNumWaysCurLevel(path));
		

		path = "A>^>^";
		sopl("Test path=(" + path + "): " + getNumWaysCurLevel(path));

		path = "AAA>^>^";
		sopl("Test path=(" + path + "): " + getNumWaysCurLevel(path));
		
		long fakeTransitionList[] = new long[25];
		for(int i=0; i<fakeTransitionList.length; i++) {
			fakeTransitionList[i] = 1;
		}
		sopl("Number of transitions with 2 options: " + getNumDistinctTransitionsWithMultipleAnswers(fakeTransitionList));
		
		
		for(int i=0; i<fakeTransitionList.length; i++) {
			fakeTransitionList[i] = 0;
		}
		sopl("Expect number under 0: " + getNumDistinctTransitionsWithMultipleAnswers(fakeTransitionList));
		
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
