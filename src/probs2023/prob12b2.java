package probs2023;
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

public class prob12b2 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in12.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
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
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				
				
				String puzzle = line.split(" ")[0];
				String tokens[] = line.split(" ")[1].split(",");
				
				String puzzle2 = "";
				String tokens2 = "";
				
				for(int j=0; j<5; j++) {
					puzzle2 += line.split(" ")[0] + "?";
					tokens2 += line.split(" ")[1] + ",";
				}
				puzzle2 = puzzle2.substring(0, puzzle2.length() - 1);
				tokens2 = tokens2.substring(0, tokens2.length() - 1);
				String tokens3[] = tokens2.split(",");
				
				
				int sequence[] = new int[tokens3.length];
				
				for(int j=0; j<sequence.length; j++) {
					sequence[j] = pint(tokens3[j]);
				}
				
				cur += f(puzzle2, sequence);
				sopl(puzzle2 + " and " + tokens2 + ": " + f(puzzle2, sequence));
			}


			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static long f(String line, int sequence[]) {
		
		
		return f(line, sequence, 0);
	}
	
	
	public static boolean fpartial3(String line, int sequence[], int curIndex) {
		
		int curSequence[] = new int[10 * sequence.length];
		
		int curNumRow = 0;
		boolean lastWasOn = false;
		int indexToUse = 0;
		
		boolean stillgood = true;
		
		ArrayList<String> maxIslands = new ArrayList<String>();
		

		boolean curSequenceMustExist[] = new boolean[10 * sequence.length];
		for(int i=0; i<curSequenceMustExist.length; i++) {
			curSequenceMustExist[i] = false;
		}
		
		for(int j=0; j<line.length(); j++) {
			
			if(lastWasOn == false) {
				if(line.charAt(j) == '#' || line.charAt(j) == '?') {
					lastWasOn = true;
					curNumRow = 1;
					
					if(line.charAt(j) == '#') {
						curSequenceMustExist[indexToUse] = true;
					}

				} else if(line.charAt(j) == '.') {
					//Do nothing
	
				}
			} else if(lastWasOn) {
				
				if(line.charAt(j) == '#' || line.charAt(j) == '?') {
					curNumRow++;

					if(line.charAt(j) == '#') {
						curSequenceMustExist[indexToUse] = true;
					}
					
				} else if(line.charAt(j) == '.') {
					
					if(indexToUse < curSequence.length) {
						

						maxIslands.add(line.substring(j-curNumRow, j));
						curSequence[indexToUse] = curNumRow;
						lastWasOn = false;
						curNumRow = 0;
						
						if(j<=curIndex && indexToUse <sequence.length && curSequence[indexToUse] != sequence[indexToUse]) {

							stillgood = false;
							break;
						}
						indexToUse++;
						
						
					} else {
						if(j<=curIndex) {
							stillgood = false;
						}
					}
	
				}
			}
		}
		
		if(curNumRow > 0 ) {
			
			maxIslands.add(line.substring(line.length()-curNumRow, line.length()));
			
			curSequence[indexToUse] = curNumRow;
			indexToUse++;
		}
		

		
		
		if(stillgood == false) {
			return false;
		}
		
		int sumSequence = 0;
		for(int i=0; i<sequence.length; i++) {
			sumSequence += sequence[i] + 1;
		}
		sumSequence--;
		
		int sumSequence2 = 0;
		for(int i=0; i<indexToUse; i++) {
			sumSequence2 += curSequence[i] + 1;
		}
		sumSequence2--;
		
		
		if(sumSequence > sumSequence2) {
			//sopl(line);
			return false;
		}
		
		//Could decompose:
		
		int newCurSequence[] = new int[indexToUse];
		
		for(int i=0; i<newCurSequence.length; i++) {
			newCurSequence[i] = curSequence[i];
		}
		
		if(stillgood && couldFitInSequence(sequence, newCurSequence, curSequenceMustExist, maxIslands)) {
			//sopl("true:");
			//sopl(line);
			return true;
		} else {
			//sopl("false:");
			//sopl(line);
			return false;
			//return true;
		}
		
		
	}

	public static boolean memoizationFound[][];
	public static boolean memoizationOutput[][];
	
	public static boolean couldFitInSequence(int sequence[], int maxSequence[], boolean curSequenceMustExist[], ArrayList<String> maxIslands) {
		
		memoizationFound = new boolean[sequence.length][maxSequence.length];
		memoizationOutput = new boolean[sequence.length][maxSequence.length];
		for(int i=0; i<memoizationFound.length; i++) {
			for(int j=0; j<memoizationFound[0].length; j++) {
				memoizationFound[i][j] = false;
				memoizationOutput[i][j] = false;
			}
		}
		
		return couldFitInSequence(sequence, maxSequence, 0, 0, curSequenceMustExist, maxIslands);
		
	}
	
	public static boolean couldFitInSequence(int sequence[], int maxSequence[], int index1, int index2, boolean curSequenceMustExist[], ArrayList<String> maxIslands) {
		
		if(index1<sequence.length && index2<maxSequence.length && memoizationFound[index1][index2]) {
			return memoizationOutput[index1][index2];
		}
		
		if(index1<sequence.length && index2<maxSequence.length) {
			memoizationFound[index1][index2] = true;
		}
		/*if(index1 == 2 && index2 == 1) {
			sopl("debug2");
		}*/
		
		if(index1 == sequence.length) {
			
			for(int i=index2; i<maxSequence.length; i++) {
				
				if(curSequenceMustExist[index2]) {

					return false;
				}
			}
			return true;
		}
		
		if(index1 < sequence.length && index2 >= maxSequence.length) {
			return false;
		}
		
		if(curSequenceMustExist[index2] == false && couldFitInSequence(sequence, maxSequence, index1, index2 + 1, curSequenceMustExist, maxIslands)) {

			memoizationOutput[index1][index2] = true;
			return true;
		}
		
		if(sequence[index1] == maxSequence[index2]) {
			memoizationOutput[index1][index2] = couldFitInSequence(sequence, maxSequence, index1 + 1, index2 + 1, curSequenceMustExist, maxIslands);
			
			return memoizationOutput[index1][index2];
		
		} else if(sequence[index1] > maxSequence[index2]) {
			memoizationOutput[index1][index2] = false;
			return false;

		} else if(sequence[index1] < maxSequence[index2]) {
			
			int curSpaceTaken = sequence[index1];
			for(int numToPutIn = 1; true; numToPutIn++) {
				
				if(curSpaceTaken <= maxSequence[index2]) {
					
					if(couldFitInSequence(sequence, maxSequence, index1 + numToPutIn, index2 + 1, curSequenceMustExist, maxIslands)) {

						memoizationOutput[index1][index2] = true;
						return true;
					}

					if(index1 + numToPutIn >= sequence.length) {
						break;
					}
					
					//int nextSpot = 1;
					//TODO: get next empty space index:
					
					String island = maxIslands.get(index2);
					
					if(island.length() != maxSequence[index2]) {
						sopl("oops");
						sopl(island + "," + maxSequence[index2]);
						exit(1);
					}
					//Rough optimization:
					int numSpaces = 1;
					for(int i=curSpaceTaken; i<island.length(); i++) {
						
						if(island.charAt(i) == '?') {
							break;
						} else {
							numSpaces++;
						}
					}
					
					
					//curSpaceTaken += numSpaces + sequence[index1 + numToPutIn];
					curSpaceTaken += numSpaces + sequence[index1 + numToPutIn];
					
					
					
				} else {
					break;
				}
				
			}

			memoizationOutput[index1][index2] = false;
			return false;
		}
		exit(1);
		memoizationOutput[index1][index2] = false;
		return false;
		
	}
	
	/*
	 * ??#??#??##.#????: 512
.#?#.???#?.??: 5184
#???.#???#?.?.??.?: 32805
	 */
	
	public static long f(String line, int sequence[], int curIndex) {
		
		
		long ret = 0L;
		if(curIndex < line.length()) {
			
			if(line.charAt(curIndex) == '?') {
				String line2 = line.substring(0, curIndex) + "." + line.substring(curIndex + 1, line.length());
				
				
				if(fpartial3(line2, sequence, curIndex)) {
					ret += f(line2, sequence, curIndex + 1);
				}
				
				line2 = line.substring(0, curIndex) + "#" + line.substring(curIndex + 1, line.length());
				if(fpartial3(line2, sequence, curIndex)) {
					ret += f(line2, sequence, curIndex + 1);
				}
				
			} else {
				ret += f(line, sequence, curIndex + 1);
			}
			//43387
		} else {
			
			//sopl(line);
			int curSequence[] = new int[sequence.length];
			
			int curNumRow = 0;
			boolean lastWasOn = false;
			int indexToUse = 0;
			
			boolean stillgood = true;
			
			for(int j=0; j<line.length(); j++) {
				
				if(lastWasOn == false && line.charAt(j) == '#') {
					lastWasOn = true;
					curNumRow = 1;
				} else if(lastWasOn) {
					
					if(line.charAt(j) == '#') {
						curNumRow++;
					} else {
						
						if(indexToUse < curSequence.length) {
							curSequence[indexToUse] = curNumRow;
							lastWasOn = false;
							curNumRow = 0;
							
							if(curSequence[indexToUse] != sequence[indexToUse]) {

								stillgood = false;
							}
							indexToUse++;
							
						} else {
							stillgood = false;
						}
					}
				}
			}
			
			if(lastWasOn == true) {
				if(indexToUse < curSequence.length) {
					curSequence[indexToUse] = curNumRow;
					lastWasOn = false;
					curNumRow = 0;
					if(curSequence[indexToUse] != sequence[indexToUse]) {
	
						stillgood = false;
					}
					indexToUse++;
				} else {
					stillgood = false;
				}
			}
			
			if(indexToUse == sequence.length && stillgood) {
				ret++;
			}
			//216
			
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
