package prob2025;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob10b3 {

	
	public static void main(String[] args) {
		sopl("Start");
		Scanner in;
		try {
			in = new Scanner(new File("in2025/prob2025in10.txt"));
			//in = new Scanner(new File("in2025/prob2025in0.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}
			
			long triangle[][] = createPascalTriangle(25);

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			
			NEXT_LINE:
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				int listCounters[] = getListCounters(line);
				
				
				String tokens[] = line.split(" ");
				
				long optionNumbers[] = new long[tokens.length -2];
				
				for(int j=1; j<tokens.length - 1; j++) {
					optionNumbers[j - 1] = getOptionNumber(tokens[j]);
					sopl("option number: " + optionNumbers[j-1]);
				}
				
				long ansLine =  getAnswer(optionNumbers, listCounters);
				
				sopl("Answer for line: " + ansLine);
				sopl();
				sopl();
				sopl();
				cur +=ansLine;
				
				
			}


			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	
	public static int getAnswer(long optionNumbers[], int listCounters[]) {

		return getAnswer(optionNumbers, listCounters, new int[listCounters.length], new boolean[listCounters.length], new boolean[optionNumbers.length] );
	}
	
	public static int getAnswer(long optionNumbers[], int goalCounters[], int usedCounters[], boolean usedIndex[], boolean usedOptionNumbers[]) {
		
		int numTimesIndexUsed[] = new int[goalCounters.length];
		
		for(int i=0; i<optionNumbers.length; i++) {
			if(usedOptionNumbers[i]) {
				continue;
			}
			for(int j=0; j<numTimesIndexUsed.length; j++) {
				
				if( (optionNumbers[i] & ( 1 << j)) != 0) {
					numTimesIndexUsed[j]++;
				}
			}
		}
		
		int leastUsedIndex = -1;
		int numLeastUsed = -1;
		for(int i=0; i<usedIndex.length; i++) {
			if(usedIndex[i] == false && (leastUsedIndex == -1 || numLeastUsed > numTimesIndexUsed[i])) {
				numLeastUsed = numTimesIndexUsed[i];
				leastUsedIndex = i;
			}
		}
		
		if(leastUsedIndex == -1) {
			//End condition:
			
			for(int i=0; i<goalCounters.length; i++) {
				if(goalCounters[i] != usedCounters[i]) {
					//big value:
					return 100000;
				}
			}
			//All good:
			return 0;
		}
		
		boolean newlyUsedIndex[] = new boolean[goalCounters.length];
		for(int i=0; i<newlyUsedIndex.length; i++) {
			newlyUsedIndex[i] = usedIndex[i];
		}
		newlyUsedIndex[leastUsedIndex] = true;
		
		boolean newlyUsedOptions[] = new boolean[optionNumbers.length];
		for(int i=0; i<usedOptionNumbers.length; i++) {
			newlyUsedOptions[i] = usedOptionNumbers[i];
		}
		
		
		int goalNumber = goalCounters[leastUsedIndex] - usedCounters[leastUsedIndex];
		
		int optionsCounter = 0;
		boolean optionsToUseThisIt[] = new boolean[optionNumbers.length];
		
		for(int i=0; i<optionNumbers.length; i++) {
			if(usedOptionNumbers[i]) {
				continue;
			}
			if( (optionNumbers[i] & ( 1 << leastUsedIndex)) != 0) {
				newlyUsedOptions[i] = true;
				
				optionsCounter++;
				optionsToUseThisIt[i] = true;
			}
		}

		int minAnswer = 1000000;
		
		if(goalNumber == 0) {
		
			minAnswer = getAnswer(optionNumbers, goalCounters, usedCounters, newlyUsedIndex, newlyUsedOptions);
			
		} else if(optionsCounter > 0) { 
			boolean combo[] = new boolean[goalNumber + optionsCounter - 1];
			

			for(int i=0; i<optionsCounter - 1; i++) {
				combo[i] = true;
			}
				
			
			NEXT_COMBO:
			while(combo != null) {
				
				int usedCounters2[] = new int[usedCounters.length];
				
				for(int i=0; i<usedCounters2.length; i++) {
					usedCounters2[i] = usedCounters[i];
				}
				
				int comboIndex = 0;
				
				for(int indexOption=0; indexOption<optionsToUseThisIt.length; indexOption++) {
					
					if(optionsToUseThisIt[indexOption]) {
						
						int numTimes = 0;
						while(comboIndex < combo.length && combo[comboIndex] == false) {
							
							//TODO: add to usedCounters2
							
							
							numTimes++;
							comboIndex++;
						}
						
						for(int k=0; k<usedCounters2.length; k++) {
							if( (optionNumbers[indexOption] & ( 1 << k)) != 0) {
								usedCounters2[k] += numTimes;
								
								if(usedCounters2[k] > goalCounters[k]) {
									//sopl("boom");
									combo = utilsPE.Combination.getNextCombination(combo);
									continue NEXT_COMBO;
								}
							}
						}
						
						comboIndex++;
					}
				}
				
				//At this point, check next recursion down:
				
				int newAnswer = goalNumber + getAnswer(optionNumbers, goalCounters, usedCounters2, newlyUsedIndex, newlyUsedOptions);
				minAnswer = Math.min(minAnswer, newAnswer);
				
				combo = utilsPE.Combination.getNextCombination(combo);
			}
			
			
		} else {
			
			//Something went wrong?
			minAnswer = 100000;
		}
		
		
		return minAnswer;
	}

	//Arg!
	public static int getAnswer(long optionNumbers[], int listCounters[], int indexOption, int counterTrial[]) {

		if(indexOption < 2) {
			sopl(indexOption);
			
		}
		
		if(indexOption == optionNumbers.length) {

			for(int k=0; k<counterTrial.length; k++) {
				if(counterTrial[k] != listCounters[k]) {
					return 100000;
				}
			}
			
			return 0;
		}
		
		int bestSoFar = -1;
		
		
	
		MAIN_LOOP:
		for(int i=0; true; i++) {
			
			for(int k=0; k<counterTrial.length; k++) {
				if( (optionNumbers[indexOption] & ( 1 << k)) != 0) {
					counterTrial[k]++;
					
					if(counterTrial[k] > listCounters[k]) {
						break MAIN_LOOP;
					}
				}
			}
			
			int counterTrial2[] = new int[counterTrial.length];
			for(int j=0; j<counterTrial.length; j++) {
				counterTrial2[j] = counterTrial[j];
			}
			
			int cur = i + getAnswer(optionNumbers, listCounters, indexOption + 1, counterTrial2);
			
			if(cur < bestSoFar) {
				bestSoFar = cur;
			}
			
		}
		return bestSoFar;
	}
	
	
	public static int[] getListCounters(String line) {
		
		String list = line.split("\\{")[1].split("\\}")[0];
		sopl("Target: " + list);
		
		String tokens[] = list.split(",");
		
		int ret[] = new int[tokens.length];
		
		for(int i=0; i<ret.length; i++) {
			ret[i] = pint(tokens[i]);
		}
		return ret;
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
	
	
	public static long getOptionNumber(String s) {
		
		sopl(s);
		String s2 = s.split("\\(")[1].split("\\)")[0];
		
		String tokens[] = s2.split(",");
		
		long ret=0L;
		
		for(int i=0; i<tokens.length; i++) {
			ret += Math.pow(2, pint(tokens[i]));
		}
		
		return ret;
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
	
	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + ") is not a number");
			return -1;
		}
	}
	

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
		} else {
			sop("Error: (" + s + ") is not a number");
			return -1;
		}
	}
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}
	
	public static int[][] getIntTable(ArrayList<String> lines) {
		int grid[][] = new int[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				if(lines.get(i).charAt(j) == '.') {
					grid[i][j] = -1;
				} else {
					grid[i][j] = (int)(lines.get(i).charAt(j) - '0');
				}
			}
		}
		
		return grid;
	}
	

	public static char[][] getCharTable(ArrayList<String> lines) {
		char grid[][] = new char[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j] = lines.get(i).charAt(j);

			}
		}
		
		return grid;
	}

}
