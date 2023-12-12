package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

import number.IsNumber;

public class prob12b {

	//day1 part 1
	//2:38.01
	
	//TODO: debug: 
	//??.????#?#?..?#.?
	
	//7025
	//423891
	//89497110
	//29761784350
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in12.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));

			String line = "";

			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			long cur = 0;
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				
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
				
				sop(puzzle2 + " and " + tokens2);
				long tmp = getNumSolution(puzzle2, sequence);
				sopl(": " + tmp);
				
				cur += tmp;
				
				//exit(1);
			}


			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static long getNumSolution(String line, int sequence[]) {
		
		int curSequence[] = new int[10 * sequence.length];
		
		int curNumRow = 0;
		boolean lastWasOn = false;
		int indexToUse = 0;
		
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
						
						
						indexToUse++;
						
						
					}
	
				}
			}
		}
		
		if(curNumRow > 0 ) {
			
			maxIslands.add(line.substring(line.length()-curNumRow, line.length()));
			
			curSequence[indexToUse] = curNumRow;
			indexToUse++;
		}
		
		
		//Could decompose:
		
		int newCurSequence[] = new int[indexToUse];
		
		for(int i=0; i<newCurSequence.length; i++) {
			newCurSequence[i] = curSequence[i];
		}
		
		return getNumSolutionInSequence(sequence, newCurSequence, curSequenceMustExist, maxIslands);
		
		
	}

	public static boolean memoizationFound[][];
	public static long memoizationOutput[][];
	

	public static boolean memoizationUsed2[][][][] = null;
	public static long memoization2[][][][] = null;
	
	
	public static long getNumSolutionInSequence(int sequence[], int maxSequence[], boolean curSequenceMustExist[], ArrayList<String> maxIslands) {
		
		memoizationFound = new boolean[sequence.length][maxSequence.length];
		memoizationOutput = new long[sequence.length][maxSequence.length];
		for(int i=0; i<memoizationFound.length; i++) {
			for(int j=0; j<memoizationFound[0].length; j++) {
				memoizationFound[i][j] = false;
				memoizationOutput[i][j] = 0L;
			}
		}
		memoizationUsed2 = new boolean[36][35][35][150];
		memoization2 = new long[36][35][35][150];
		
		for(int i=0; i<memoizationUsed2.length; i++) {
			for(int j=0; j<memoizationUsed2[0].length; j++) {
				for(int k=0; k<memoizationUsed2[0][0].length; k++) {
					for(int m=0; m<memoizationUsed2[0][0][0].length; m++) {
						memoizationUsed2[i][j][k][m] = false;
						memoization2[i][j][k][m] = 0L;
					}
				}
			}
		}
		
		return getNumSolutionInSequence(sequence, maxSequence, 0, 0, curSequenceMustExist, maxIslands);
		
	}
	
	public static long getNumSolutionInSequence(int sequence[], int maxSequence[], int index1, int index2, boolean curSequenceMustExist[], ArrayList<String> maxIslands) {
		
		if(index1<sequence.length && index2<maxSequence.length && memoizationFound[index1][index2]) {
			return memoizationOutput[index1][index2];
		}
		
		if(index1<sequence.length && index2<maxSequence.length) {
			memoizationFound[index1][index2] = true;
			memoizationOutput[index1][index2] = 0L;
		}
		/*if(index1 == 2 && index2 == 1) {
			sopl("debug2");
		}*/
		
		if(index1 >= sequence.length) {
			
			for(int i=index2; i<maxSequence.length; i++) {
				
				if(curSequenceMustExist[i]) {

					return 0L;
				}
			}
			return 1L;
		}
		
		if(index1 < sequence.length && index2 >= maxSequence.length) {
			return 0L;
		}
		
		if(index2 >= maxSequence.length) {
			return 0L;
		}

		long ret = 0L;
		
		if(curSequenceMustExist[index2] == false && getNumSolutionInSequence(sequence, maxSequence, index1, index2 + 1, curSequenceMustExist, maxIslands) > 0L) {
			ret += getNumSolutionInSequence(sequence, maxSequence, index1, index2 + 1, curSequenceMustExist, maxIslands);
		}
		
		if(sequence[index1] == maxSequence[index2]) {
			
			ret += getNumSolutionInSequence(sequence, maxSequence, index1 + 1, index2 + 1, curSequenceMustExist, maxIslands);
		
		} else if(sequence[index1] > maxSequence[index2]) {
	
			ret += 0L;

		} else if(sequence[index1] < maxSequence[index2]) {
			
			if(index1==0 || maxIslands.get(index2).equals("?#?#?##??")) {
				
				//sopl("debug");
			}
			for(int numToPutIn = 1; index1 + numToPutIn <= sequence.length ; numToPutIn++) {
				
				//TODO: find num ways to put small seq into bigger one...
				// if # then it's forced...
				
				long numWays1st = numWaysPutSmallIntoBig(numToPutIn, sequence, maxSequence, index1, index2, maxIslands.get(index2));
				
				//sopl("In numWays " + numToPutIn + ": " + index1 + "," + index2 +": " + numWays1st);
				//sopl("ret: " + ret);
				//sopl();
				ret += numWays1st * getNumSolutionInSequence(sequence, maxSequence, index1 + numToPutIn, index2 + 1, curSequenceMustExist, maxIslands);
			}
			
		}
		
		memoizationOutput[index1][index2] = ret;
		//sopl(index1 + "," + index2 +": " + memoizationOutput[index1][index2]);
		return memoizationOutput[index1][index2];
		
	}
	

	
	
	public static long numWaysPutSmallIntoBig(int numtoPut, int sequence[], int maxSequence[], int index1, int index2, String island) {
		return numWaysPutSmallIntoBig(numtoPut, sequence, maxSequence, index1, index2, island, 0);
	}
	
	public static long numWaysPutSmallIntoBig(int numtoPut, int sequence[], int maxSequence[], int index1, int index2, String island, int curIndex) {
		
		if(memoizationUsed2[numtoPut][index1][index2][curIndex]) {
			return memoization2[numtoPut][index1][index2][curIndex];
		}

		if(numtoPut == 0) {
			for(int i=curIndex; i<island.length(); i++) {
				if(island.charAt(i) == '#') {
					

					memoizationUsed2[numtoPut][index1][index2][curIndex] = true;
					memoization2[numtoPut][index1][index2][curIndex] = 0L;
					
					return 0L;
				}
			}
			

			memoizationUsed2[numtoPut][index1][index2][curIndex] = true;
			memoization2[numtoPut][index1][index2][curIndex] = 1L;
			
			return 1L;
		}
		
		if(index1 >= sequence.length) {

			memoizationUsed2[numtoPut][index1][index2][curIndex] = true;
			memoization2[numtoPut][index1][index2][curIndex] = 0L;
			return 0L;
		}
		
		long ret = 0L;
		for(int j=curIndex; j<=maxSequence[index2] - sequence[index1]; j++) {
			
			if(j + sequence[index1] >= maxSequence[index2] ||
					island.charAt(j + sequence[index1]) != '#') {
				ret += numWaysPutSmallIntoBig(numtoPut - 1, sequence,  maxSequence, index1 + 1, index2, island, j + sequence[index1] + 1);
			}
			
			if(island.charAt(j) == '#') {
				break;
			}
			
		}

		memoizationUsed2[numtoPut][index1][index2][curIndex] = true;
		memoization2[numtoPut][index1][index2][curIndex] = ret;
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
