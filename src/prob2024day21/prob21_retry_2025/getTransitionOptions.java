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
	
	public static long[] addTransitionList(long transitionsCurLevel[], int transitionIndexToUse, int wayIndexToUse, long runningTotal[]) {
		
		for(int j=0; j<runningTotal.length; j++) {
			runningTotal[j] += transitionsCurLevel[transitionIndexToUse] * transitionsListNextLevel[transitionIndexToUse][j][wayIndexToUse];
		}
		return runningTotal;
	}
	
	public static long[][] getPossibleTransitionsNextLevel(long transitionsCurLevel[]) {
		
		
		/*//DEBUG
		long curSum2 = 0;
		for(int i=0; i<transitionsCurLevel.length; i++) {
			sopl("Transition index " + i + ": " + transitionsCurLevel[i]);
			curSum2 += transitionsCurLevel[i]; 
		}
		System.out.println("Number of transitions cur Level:");
		sopl(curSum2);
		//END DEBUG
		*/
		
		sopl("Start of getPossibleTransitionsNextLevel");
		int numWaysNaive = (int)(Math.pow(2, getNumDistinctTransitionsWithMultipleAnswers(transitionsCurLevel)));

		long ret[][] = new long[numWaysNaive][transitionsCurLevel.length];
		
		for(int i=0; i<ret.length; i++) {
			for(int j=0; j<ret[0].length; j++) {
				ret[i][j] = 0L;
			}
		}
		
		for(int numWayIndex= 0; numWayIndex<numWaysNaive; numWayIndex++) {
			
			
			long curDirectionCode = numWayIndex;
			for(int transitionIndexCurLevel=0; transitionIndexCurLevel<transitionsCurLevel.length; transitionIndexCurLevel++) {
				
				if(transitionsCurLevel[transitionIndexCurLevel] == 0) {
					//no transitions in current level means no transitions to add in the next level:
					continue;
				}
				
				if(transitionsListNextLevel[transitionIndexCurLevel][0].length == 2) {
					
					if(curDirectionCode % 2 == 0) {
						
						//transitionsListNextLevel[i][x][0]
						ret[numWayIndex] = addTransitionList(transitionsCurLevel, transitionIndexCurLevel, (int)(curDirectionCode % 2), ret[numWayIndex]);
					} else {
						//transitionsListNextLevel[i][x][1]
						ret[numWayIndex] = addTransitionList(transitionsCurLevel, transitionIndexCurLevel, (int)(curDirectionCode % 2), ret[numWayIndex]);
					}
					
					curDirectionCode /= 2;
				} else {
					//transitionsListNextLevel[i][x][0]
					ret[numWayIndex] = addTransitionList(transitionsCurLevel, transitionIndexCurLevel, 0, ret[numWayIndex]);
					
				}
			}
			
			//DEBUG
			/*long curSum = 0;
			for(int i=0; i<ret[numWayIndex].length; i++) {
				//sopl("Transition index " + i + ": " + ret[numWayIndex][i]);
				curSum += ret[numWayIndex][i]; 
			}
			System.out.println("Number of transitions:");
			sopl(curSum);
			*/
			//END DEBUG
			
			if(curDirectionCode > 0) {
				sopl("doh!");
				exit(1);
			}
		}
		
		//Sanity test diff result:
		
		/*sopl("Sanity Test ret:");
		for(int i=0; i<ret.length; i++) {
			
			long origResult = ret[i][0];
			for(int j=0; j<ret[0].length; j++) {
				
				if(ret[i][j] != origResult) {
					sopl(i + ", " + j + ": differs!");
				}
			}
			
		}
		sopl("END Sanity Test ret:");
		*/
		return ret;
	}
	
	//TODO: have function eliminate dominated transition list options
	//TODO: eventually have a rule of thumb for eliminating unpromissing transition list options.
	
	public static void main(String[] args) {
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
		
		
		
		//TODO: Have program create the possible paths based on input like: "029A"
		//String pathExamples[] = new String[] {"<A^A>^^AvvvA", "<A^A^>^AvvvA", "<A^A^^>AvvvA"};
		
		//TODO: For part 2, don't do i, j, k, l... Do recursion!
		//Go from 2 dirArraw pads from here to 25 dirArraw pads from here:
		for(int i=0; i<1; i++) {
			
			long nextLevel[][] = getPossibleTransitionsNextLevel("<A^A>^^AvvvA");
			
			long shortestNextLevel = getLowestNumberTransitions(nextLevel);
			
			sopl();
			sopl("Shortest for the next level: " + shortestNextLevel);

			//From AOC
			//v<<A>>^A<A>AvA<^AA>A<vAAA>^A
			//39-11 = 28
			
			long bestShortest = Long.MAX_VALUE;
			sopl("Iterate over next level:");
			for(int j=0; j<nextLevel.length; j++) {

				long nextLevel2[][] = getPossibleTransitionsNextLevel(nextLevel[j]);
				
				long shortestNextLevel2 = getLowestNumberTransitions(nextLevel2);
				
				if(shortestNextLevel2 < bestShortest) { 
					bestShortest = shortestNextLevel2;
				}
			}
			
			sopl("Shortest for 2 levels down (part 1): " + bestShortest);
			//From AOC:
			// <vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A
			// 84-16 = 68
			// It matches!
			
			//That matches what my algo found! Holy crap I might be getting somewhere!
		}
	}
	

	public static long getLowestNumberTransitions(long ret[][]) {
		
		long answer = Long.MAX_VALUE;
		
		for(int numWayIndex=0; numWayIndex<ret.length; numWayIndex++) {
			
			long curSum = 0;
			for(int i=0; i<ret[numWayIndex].length; i++) {
				//sopl("Transition index " + i + ": " + ret[numWayIndex][i]);
				curSum += ret[numWayIndex][i]; 
			}
			
			if(curSum < answer) {
				answer = curSum;
			}
		}
		return answer;
		
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
