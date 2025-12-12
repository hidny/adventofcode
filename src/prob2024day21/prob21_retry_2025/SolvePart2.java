package prob2024day21.prob21_retry_2025;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class SolvePart2 {


	public static int MAX_NUM_NEXT_LEVEL_OPTIONS_PER_TRANSITION_LIST = -1;

	//For now, I'll test with only allowing some combination of 2 codes out of 64.
	//public static int NUM_NEXT_LEVEL_OPTIONS_PER_TRANSITION_LIST = 2;
	
	//The testing of only allowing 2 codes out of 64 suggested that only 1 code is needed. It happens to be code index 19.
	public static int NUM_NEXT_LEVEL_OPTIONS_PER_TRANSITION_LIST = 1;
	
	
	public static long triangle[][] = getNumPadOptions.createPascalTriangle(100);
	public static void initializeTransitionObjectAndTriangle() {
		// Initialize next level transition list:
		getTransitionOptions.createTransitionListNextLevel();
		
		MAX_NUM_NEXT_LEVEL_OPTIONS_PER_TRANSITION_LIST = (int)Math.pow(2, getTransitionOptions.getAllNumDistinctTransitionsWithMultipleAnswers());
		
	}
	
	public static void main(String args[]) {
		
		initializeTransitionObjectAndTriangle();
		
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

				long shortestLength = Long.MAX_VALUE;
				
				boolean comboCodesToUse[] = new boolean[MAX_NUM_NEXT_LEVEL_OPTIONS_PER_TRANSITION_LIST];
				
				for(int j=0; j<NUM_NEXT_LEVEL_OPTIONS_PER_TRANSITION_LIST; j++) {
					comboCodesToUse[j] = true;
				}
				
				ArrayList <String> bestCodesDebug = new ArrayList <String>();
				
				while(comboCodesToUse != null) {
					
					//Testing only using a combination of 2 of 64 codes only, because more codes is computationally hard.
					// Used 1 of 64 codes, because testing showed that code 19 is key. TODO: what are the rules of code 19?
					
					//Testing int NUM_NEXT_LEVEL_OPTIONS_PER_TRANSITION_LIST = 2;
					//3: 417214
					//4: 1032210 (Number of codes found: 192 out of 2016)
					//5: 2542056 (Number of codes found: 70 out of 2016) --> 19?
					//6: 6336218 (Number of codes found: 63 out of 2016) --> It's all 19!
					//7: 15719370 (Number of codes found: 63 out of 2016) --> It's all 19!
					
					
					long curLevelTrasitions[][] = firstLevelTransitions;
					
					//for(int j=0; j<2; j++) {
					//for(int j=0; j<7; j++) {
					for(int j=0; j<25; j++) {
						
						curLevelTrasitions = getNextLevelTransitions(curLevelTrasitions, comboCodesToUse);
						sopl("Current number of transitions after getNextLevelTransitions iteration " + j + ": " + curLevelTrasitions.length);
					}
					
					boolean debugFoundShortCombo = false;
					for(int j=0; j<curLevelTrasitions.length; j++) {
						
						long tmpLength = getPathLengthBasedOnTransitions(curLevelTrasitions[j]);
						
						if(tmpLength < shortestLength) {
							shortestLength = tmpLength;
							bestCodesDebug = new ArrayList <String>();
							
						}
						
						if(tmpLength <= shortestLength) {

							debugFoundShortCombo =true;
							
							
						}
					}
					
					if(debugFoundShortCombo) {
						String bestCodesString = "";
						//Get index codes for debug:
						for(int k=0; k<comboCodesToUse.length; k++) {
							if(comboCodesToUse[k]) {
								bestCodesString += k;
							}
						}
						bestCodesDebug.add(bestCodesString);
						//End get index codes for debug.
					}
	
					//END wrapping up search with a combination of 2 of 64 codes allow (or combination of just 1 of 64 codes allowed)
					
					comboCodesToUse = utilsPE.Combination.getNextCombination(comboCodesToUse);
				}
				
				sopl("Best codes found:");
				for(int j=0; j<bestCodesDebug.size(); j++) {
					sopl(bestCodesDebug.get(j));
				}
				
				sopl("Number of codes found: " + bestCodesDebug.size() + " out of " + triangle[MAX_NUM_NEXT_LEVEL_OPTIONS_PER_TRANSITION_LIST][NUM_NEXT_LEVEL_OPTIONS_PER_TRANSITION_LIST]);
				long curLineAnswer = numericPartOfLine * shortestLength;
				sopl("Answer for this line: " + curLineAnswer);
				cur += curLineAnswer;
				
				sopl();
				sopl();
			}
			
			
			sopl("Answer for part 2: " + cur);
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
		return getNextLevelTransitions(curLevelTrasitions, null);
	}
	public static long[][] getNextLevelTransitions(long curLevelTrasitions[][], boolean allowed_codes_to_use[]) {
		
		int multPerLevel = 0;
		for(int i=0; i<allowed_codes_to_use.length; i++) {
			if(allowed_codes_to_use[i]) {
				multPerLevel++;
			}
		}
		sopl("Mult per level: " + multPerLevel);
		
		//Only 1-2 options at a time because all 64 is not computationally attainable:
		long ret[][] = new long[multPerLevel * curLevelTrasitions.length][];
		
		int curRetIndex = 0;
		
		for(int i=0; i<curLevelTrasitions.length; i++) {
			long tmp[][] = getTransitionOptions.getPossibleTransitionsNextLevel(curLevelTrasitions[i], allowed_codes_to_use);
			
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

		sopl("Num transitions found before getDominated: " + transitions.length);
		boolean dominated[] = new boolean[transitions.length];
		
		NEXT_I:
		for(int i=0; i<transitions.length; i++) {
			if(i % 10000 == 0) {
				sopl(i);
			}
			if(dominated[i]) {
				continue;
			}
			for(int j=i+1; j<transitions.length; j++) {
				
				if(dominated[j]) {
					continue;
				}
				
				if(isADominatingB(transitions[i], transitions[j])) {

					if(isASameAsB(transitions[i], transitions[j])) {
						//sopl("Same IJ!");
					} else {
						//sopl("Different J!");
					}
					//sopl("Dominated J!");
					dominated[j] = true;
					
				} else if(isADominatingB(transitions[j], transitions[i])) {
					//sopl("Dominated I!");
					dominated[j] = true;
					transitions[i] = transitions[j];
					i--;
					
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
