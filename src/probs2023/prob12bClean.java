package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

import number.IsNumber;

public class prob12bClean {

	//7025
	//423891
	//89497110
	//29761784350
	
	public static final int NUM_DUPLICATES = 5;
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in12.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));

			String line = "";
			ArrayList <String>lines = new ArrayList<String>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			long cur = 0;
			
			for(int i=0; i<lines.size(); i++) {
				
				line = lines.get(i);
				
				
				String puzzle2 = "";
				String tokens2 = "";
				
				for(int j=0; j<NUM_DUPLICATES; j++) {
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
				
				//Takes just over 30 seconds
				cur += tmp;
				
			}

			sopl("Answer for part 2: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static long getNumSolution(String line, int sequence[]) {
		
		ArrayList<Integer> curSequence = new ArrayList<Integer>();
		
		int curNumRow = 0;
		boolean lastWasOn = false;
		
		ArrayList<String> hashQuestionIslands = new ArrayList<String>();
		

		ArrayList<Boolean> curSequenceMustExist = new ArrayList<Boolean>();
		boolean curIndexMustExist = false;
		
		for(int j=0; j<line.length(); j++) {
			
			if(lastWasOn == false) {
				if(line.charAt(j) == '#' || line.charAt(j) == '?') {
					lastWasOn = true;
					curNumRow = 1;
					
					if(line.charAt(j) == '#') {
						curIndexMustExist = true;
					}

				} else if(line.charAt(j) == '.') {
					//Do nothing
	
				}
			} else if(lastWasOn) {
				
				if(line.charAt(j) == '#' || line.charAt(j) == '?') {
					curNumRow++;

					if(line.charAt(j) == '#') {
						curIndexMustExist = true;
					}
					
				} else if(line.charAt(j) == '.') {
					

					hashQuestionIslands.add(line.substring(j-curNumRow, j));
					
					curSequence.add(curNumRow);
					
					curSequenceMustExist.add(curIndexMustExist);
					
					lastWasOn = false;
					curNumRow = 0;
					curIndexMustExist = false;
					
				}
			}
		}
		
		if(curNumRow > 0 ) {
			
			hashQuestionIslands.add(line.substring(line.length()-curNumRow, line.length()));

			curSequence.add(curNumRow);
			curSequenceMustExist.add(curIndexMustExist);
			
		}
		
		
		int minSequence[] = new int[curSequence.size()];
		boolean curminSequenceMustExistArray[] = new boolean[curSequenceMustExist.size()];
		
		for(int i=0; i<minSequence.length; i++) {
			minSequence[i] = curSequence.get(i);
			curminSequenceMustExistArray[i] = curSequenceMustExist.get(i);
		}
		
		return getNumSolutionInSequence(sequence, minSequence, curminSequenceMustExistArray, hashQuestionIslands);
		
		
	}

	public static boolean memoizationFound[][];
	public static long memoizationOutput[][];
	

	public static boolean memoizationUsed2[][][][] = null;
	public static long memoization2[][][][] = null;
	
	
	public static final int LIMIT_NUM_TO_PUT = 36;
	public static final int LIMIT_INDEX_1 = 35;
	public static final int LIMIT_INDEX_2 = 35;
	public static final int LIMIT_CUR_INDEX = 150;
	
	
	public static long getNumSolutionInSequence(int sequence[], int minSequence[], boolean curSequenceMustExist[], ArrayList<String> hashQuestionIslands) {
		
		memoizationFound = new boolean[sequence.length + 1][minSequence.length + 1];
		memoizationOutput = new long[sequence.length + 1][minSequence.length + 1];
		for(int i=0; i<memoizationFound.length; i++) {
			for(int j=0; j<memoizationFound[0].length; j++) {
				memoizationFound[i][j] = false;
				memoizationOutput[i][j] = 0L;
			}
		}
		
		
		memoizationUsed2 = new boolean[LIMIT_NUM_TO_PUT][LIMIT_INDEX_1][LIMIT_INDEX_2][LIMIT_CUR_INDEX];
		memoization2 = new long[LIMIT_NUM_TO_PUT][LIMIT_INDEX_1][LIMIT_INDEX_2][LIMIT_CUR_INDEX];
		
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
		
		return getNumSolutionInSequence(sequence, minSequence, 0, 0, curSequenceMustExist, hashQuestionIslands);
		
	}
	
	public static long getNumSolutionInSequence(int sequence[], int minSequence[], int index1, int index2, boolean curSequenceMustExist[], ArrayList<String> hashQuestionIslands) {
		
		if(index1<sequence.length && index2<minSequence.length && memoizationFound[index1][index2]) {
			return memoizationOutput[index1][index2];
		}
		
		//Set up memoization:
		if(index1<=sequence.length && index2<=minSequence.length) {
			memoizationFound[index1][index2] = true;
			memoizationOutput[index1][index2] = 0L;
		}
		
		//Handle base cases:
		if(index1 >= sequence.length) {
			
			for(int i=index2; i<minSequence.length; i++) {
				
				if(curSequenceMustExist[i]) {
					memoizationOutput[index1][index2] = 0L;
					return 0L;
				}
			}
			memoizationOutput[index1][index2] = 1L;
			return 1L;
		}
		
		if(index1 < sequence.length && index2 >= minSequence.length) {
			memoizationOutput[index1][index2] = 0L;
			return 0L;
		}
		
		if(index2 >= minSequence.length) {
			memoizationOutput[index1][index2] = 0L;
			return 0L;
		}
		//End handle base cases
		
		//Recursive case:
		long ret = 0L;
		
		if(curSequenceMustExist[index2] == false && getNumSolutionInSequence(sequence, minSequence, index1, index2 + 1, curSequenceMustExist, hashQuestionIslands) > 0L) {
			ret += getNumSolutionInSequence(sequence, minSequence, index1, index2 + 1, curSequenceMustExist, hashQuestionIslands);
		}
		
		if(sequence[index1] == minSequence[index2]) {
			
			ret += getNumSolutionInSequence(sequence, minSequence, index1 + 1, index2 + 1, curSequenceMustExist, hashQuestionIslands);
		
		} else if(sequence[index1] > minSequence[index2]) {
	
			ret += 0L;

		} else if(sequence[index1] < minSequence[index2]) {
			
			for(int numToPutIn = 1; index1 + numToPutIn <= sequence.length ; numToPutIn++) {
				
				long numWays1st = numWaysPutSmallIntoBig(numToPutIn, sequence, minSequence, index1, index2, hashQuestionIslands.get(index2));
				
				ret += numWays1st * getNumSolutionInSequence(sequence, minSequence, index1 + numToPutIn, index2 + 1, curSequenceMustExist, hashQuestionIslands);
			}
			
		}
		
		memoizationOutput[index1][index2] = ret;
		//sopl(index1 + "," + index2 +": " + memoizationOutput[index1][index2]);
		return memoizationOutput[index1][index2];
		
	}
	
	
	public static long numWaysPutSmallIntoBig(int numtoPut, int sequence[], int minSequence[], int index1, int index2, String hashQuestionIsland) {
		return numWaysPutSmallIntoBig(numtoPut, sequence, minSequence, index1, index2, hashQuestionIsland, 0);
	}
	
	public static long numWaysPutSmallIntoBig(int numtoPut, int sequence[], int minSequence[], int index1, int index2, String hashQuestionIsland, int curIndex) {
		
		if(memoizationUsed2[numtoPut][index1][index2][curIndex]) {
			return memoization2[numtoPut][index1][index2][curIndex];
		}

		if(numtoPut == 0) {
			for(int i=curIndex; i<hashQuestionIsland.length(); i++) {
				if(hashQuestionIsland.charAt(i) == '#') {
					

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
		for(int j=curIndex; j<=minSequence[index2] - sequence[index1]; j++) {
			
			if(j + sequence[index1] >= minSequence[index2] ||
					hashQuestionIsland.charAt(j + sequence[index1]) != '#') {
				ret += numWaysPutSmallIntoBig(numtoPut - 1, sequence,  minSequence, index1 + 1, index2, hashQuestionIsland, j + sequence[index1] + 1);
			}
			
			if(hashQuestionIsland.charAt(j) == '#') {
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
