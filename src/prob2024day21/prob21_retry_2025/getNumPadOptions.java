package prob2024day21.prob21_retry_2025;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import probs2024.prob21state2;

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
	
	public static void testNumPad() { 

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
	

	public static ArrayList<String> getList(String key, String cur, int index) {
		
		ArrayList<String> ret = new ArrayList<String>();

		if(index == key.length()) {
			ret.add(cur);
			return ret;
		}
		
		char prevChar = 'A';
		char curChar = key.charAt(index);
		if(index > 0) {
			prevChar = key.charAt(index - 1);
		}
		
		for(int i=0; i<getNumWays(charToIndex(prevChar),  charToIndex(curChar)); i++) {
			String toAdd = getPath(charToIndex(prevChar), charToIndex(curChar), i);
			
			ret.addAll(getList(key, cur + toAdd, index + 1));
		}
		
		
		return ret;
	}
	
	public static ArrayList<String> getList(String key) {
		return getList(key, "", 0);
	}
	
	public static void printAllOptions(String key) {
		
		ArrayList<String> list = getList(key);
		
		for(int i=0; i<list.size(); i++) {
			sopl(list.get(i));
		}
	}
	
	
	public static void main(String args[]) {
		
		
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in21.txt"));
			//in = new Scanner(new File("in2024/prob2024in0.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";
	
			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}
			
			
	
			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				sopl(line + ":");
				printAllOptions(line);
				//TODO: make a transition list of the arrows plz.
				sopl();
				sopl();
			}
			
			
			sopl("Answer: " + cur);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}

		sopl("029A:");
		printAllOptions("029A");
		sopl();
		sopl();
		sopl("980A:");
		printAllOptions("980A");
		sopl();
		sopl();
		sopl("179A:");
		printAllOptions("179A");
		sopl();
		sopl();
		sopl("456A:");
		printAllOptions("456A");
		sopl();
		sopl();
		sopl("456A:");
		printAllOptions("456A");
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
