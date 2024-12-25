package prob2024day21;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import probs2024.prob21state2;

public class prob21memoize {

	
	//Plan:
	//TODO: figure out best of ^> or >^ depending on depth...
	//Do the same for all other up/down and left/right combos...
	
	//Then figure out the actual min with the info obtained.
	//I decided to be lazy and just bruteforce all of the shortest keypad solutions just in case one is better than others.
	
	
	public static void main(String args[]) {
		
		String line = "<^>vA";
		
		int num = 0;
		
		for(int j=0; j<line.length(); j++) {
			if(line.charAt(j) >= '0' && line.charAt(j) <= '9') {
				num = 10*num + (int)(line.charAt(j) - '0');
			}
		}
		
		sopl("Num: " + num);
		
		int TEST = 1;

		ArrayList<String> shortest = getShortestSequence(new prob21ouputArrows(TEST), line);
		
		sopl("Actual shortest:");
		sopl(shortest.get(0));
		sopl(shortest.get(0).length());
		sopl("test result:");
		testResult(shortest.get(0), TEST);
		
		
		//int N = 1;
		//ArrayList<String> shortestMinusN = getShortestSequence(new prob21state2(TEST - N), line);
		//String test2 = shortestMinusN.get(0);
		
		sopl();
	}

	public static void testResult(String sequence, int numRobots) {
		
		prob21ouputArrows curState = new prob21ouputArrows(numRobots);
		
		String curOutput = "";
		for(int i=0; i<sequence.length(); i++) {
			curState = curState.makeMove(getMoveIndex(sequence.charAt(i)));
			
			if(curState.badState) {
				sopl("reached bad state at i2 = " + i);
				sopl(sequence.substring(0, i + 1));
				exit(1);
			}
			
			if(curState.curOutput.length() > curOutput.length()) {
				sopl(sequence.substring(0, i + 1));
				sopl(curState.curOutput);
				sopl(sequence.substring(0, i + 1).length());
				curOutput = curState.curOutput;
			}
		}
		
		sopl("Output of sequence: " + curState.curOutput);
		
	}
	

	public static char nextChar[] = new char[]{'>', '^', 'v', '<', 'A'};


	public static int getMoveIndex(char move) {
		for(int i=0; i<nextChar.length; i++) {
			if(move == nextChar[i]) {
				return i;
			}
		}
		return -1;
	}

	
	public static ArrayList<String> getShortestSequence(prob21ouputArrows curState, String line) {
		
		
		LinkedList<prob21ouputArrows> queue = new LinkedList<prob21ouputArrows>();
		HashSet<String> listStates = new HashSet<String>();
		
		queue.add(curState);
		listStates.add(curState.toString());
		
		ArrayList<String> ret = new ArrayList<String>();
		int curShortest = -1;
		
		while( ! queue.isEmpty()) {
			
			
			prob21ouputArrows cur = queue.remove();
			
			for(int j=0; j<nextChar.length; j++) {
				prob21ouputArrows next =  cur.makeMove(getMoveIndex(nextChar[j]));
				
				if(next.badState) {
					//pass
				} else if(line.startsWith(next.curOutput) == false) {
					//pass

				} else if(curShortest != -1 && next.curInput.length() > curShortest){
					//pass
					
				} else if( ! listStates.contains(next.toString())) {
					listStates.add(next.toString());
					
					
					
					if(listStates.size() % 100000 == 0) {
						sopl("list States size: " + listStates.size());
					}
					
					if(next.curOutput.equals(line)) {
						
						if(curShortest == -1 || curShortest  == next.curInput.length()) {
							
							curShortest = next.curInput.length();
							ret.add(next.curInput);
						}
						
						if(curShortest == -1) {
							curShortest = next.curInput.length();
						}
					} else {

						queue.add(next);
					}
				}
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
	
	
		
	public static int getiCoord(char in) {
		if(in == '^' || in == 'A') {
			return 0;
		} else {
			return 1;
		}
	}
	public static int getjCoord(char in) {
		if(in == '<') {
			return 0;
		} else if(in == '^' || in == 'v') {
			return 1;
		} else {
			return 2;
		}
}
}
