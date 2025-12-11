package prob2024day21.prob21_retry_2025;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class SolvePart1 {


	public static int NUM_NEXT_LEVEL_OPTIONS_PER_TRANSITION_LIST = -1;
	
	public static void main(String args[]) {
		
		//TODO: put in function
		// Initialize next level transition list:
		getTransitionOptions.createTransitionListNextLevel();
		NUM_NEXT_LEVEL_OPTIONS_PER_TRANSITION_LIST = (int)Math.pow(2, getTransitionOptions.getAllNumDistinctTransitionsWithMultipleAnswers());
		//END Initialize
		
		sopl("----------");
		
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in21.txt"));
			//in = new Scanner(new File("in2024/prob2024in0.txt"));

			String line = "";
	
			
			ArrayList <String>lines = new ArrayList<String>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}
			
			
			long cur = 0L;
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				long numericPartOfLine = getNumericPartOfLine(line);
				
				sopl(line + ":");
				sopl("Numeric part of line: " + numericPartOfLine);
				
				ArrayList<String> list = getNumPadOptions.getList(line);
				getNumPadOptions.printAllOptions(line);
				
				
				long firstLevelTransitions[][] = new long[list.size()][];
				for(int j=0; j<list.size(); j++) {
					firstLevelTransitions[j] = getTransitionOptions.getTransitionListGivenPath(list.get(j));
				}

				sopl("Old num transitions: " + firstLevelTransitions.length);
				firstLevelTransitions = RemoveDominatedOptions(firstLevelTransitions);
				sopl("New num transitions: " + firstLevelTransitions.length);
				
				sopl();
				sopl();
				
				long curLevelTrasitions[][] = firstLevelTransitions;
				
				for(int j=0; j<2; j++) {
					
					curLevelTrasitions = getNextLevelTransitions(curLevelTrasitions);
					sopl("Current number of transitions after getNextLevelTransitions iteration " + j + ": " + curLevelTrasitions.length);
				}
				
				long shortestLength = Long.MAX_VALUE;
				
				for(int j=0; j<curLevelTrasitions.length; j++) {
					
					long tmpLength = getPathLengthBasedOnTransitions(curLevelTrasitions[j]);
					
					if(tmpLength < shortestLength) {
						shortestLength = tmpLength;
					}
				}
				
				long curLineAnswer = numericPartOfLine * shortestLength;
				sopl("Answer for this line: " + curLineAnswer);
				cur += curLineAnswer;
				
				sopl();
				sopl();
			}
			
			
			sopl("Answer: " + cur);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}

		
	}
	
	public static long getPathLengthBasedOnTransitions(long transitions[]) {
		
		long ret = 0L;
		
		for(int i=0; i<transitions.length; i++) {
			ret += transitions[i];
		}
		
		
		return ret;
	}
	
	//The hard part:
	public static long[][] getNextLevelTransitions(long curLevelTrasitions[][]) {
		
		long ret[][] = new long[NUM_NEXT_LEVEL_OPTIONS_PER_TRANSITION_LIST * curLevelTrasitions.length][];
		
		int curRetIndex = 0;
		
		for(int i=0; i<curLevelTrasitions.length; i++) {
			long tmp[][] = getTransitionOptions.getPossibleTransitionsNextLevel(curLevelTrasitions[i]);
			
			for(int j=0; j<tmp.length; j++, curRetIndex++) {
				ret[curRetIndex] = tmp[j];
				
			}
		}
		
		sopl("Remove dominated options:");
		ret = RemoveDominatedOptions(ret);
		
		return ret;
	}
	
	public static long getNumericPartOfLine(String line) {
		long ret = 0L;
		
		for(int i=0; i<line.length(); i++) {
			if( (int)(line.charAt(i)) >= (int)('0') && (int)(line.charAt(i)) <= (int)('9')) {
				ret = 10 * ret + (int)(line.charAt(i) - '0');
			}
		}
		return ret;
	}
	
	public static long[][] RemoveDominatedOptions(long firstLevelTransitions[][]) {
		long ret[][];
		boolean dominated[] = getDominated(firstLevelTransitions);
		
		int count = 0;
		int dominatedCountDEBUG = 0;
		for(int i=0; i<dominated.length; i++) {
			if( ! dominated[i]) {
				count++;
			} else {
				dominatedCountDEBUG++;
			}
		}
		ret = new long[count][];
		sopl("Found " + dominatedCountDEBUG + " dominated options");
		sopl();
		
		int index = 0;
		for(int i=0; i<dominated.length; i++) {
			if( ! dominated[i]) {
				
				ret[index] = firstLevelTransitions[i];
				index++;
			}
		}
		
		
		return ret;
	}
	
	public static boolean[] getDominated(long transitions[][]) {

		boolean dominated[] = new boolean[transitions.length];
		
		NEXT_I:
		for(int i=0; i<transitions.length; i++) {
			for(int j=i+1; j<transitions.length; j++) {
				
				if(isADominatingB(transitions[i], transitions[j])) {

					if(isASameAsB(transitions[i], transitions[j])) {
						//sopl("Same IJ!");
					} else {
						//sopl("Different J!");
					}
					//sopl("Dominated J!");
					dominated[j] = true;
					
				} else if(isADominatingB(transitions[j], transitions[i])) {
					sopl("Dominated I!");
					dominated[i] = true;
					continue NEXT_I;
				}
			}
		}
		
		return dominated;
	}
	
	public static boolean isASameAsB(long transitionA[], long transitionB[]) {
		
		boolean ret = true;
		
		for(int i=0; i<transitionA.length; i++) {
			if(transitionA[i] != transitionB[i]) {
				ret = false;
				break;
			}
		}
		
		return ret;
		
	}
	
	public static boolean isADominatingB(long transitionA[], long transitionB[]) {
		
		boolean ret = true;
		
		for(int i=0; i<transitionA.length; i++) {
			if(transitionA[i] > transitionB[i]) {
				ret = false;
				break;
			}
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

	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}
}
