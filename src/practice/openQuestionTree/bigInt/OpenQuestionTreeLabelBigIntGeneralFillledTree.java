package practice.openQuestionTree.bigInt;

import java.math.BigInteger;
import java.util.HashMap;

import number.IsNumber;

public class OpenQuestionTreeLabelBigIntGeneralFillledTree {

	
	//TODO: use hashmap instead of pint...
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		BigInteger startIndex = BigInteger.ZERO;
		
		//startIndex = new BigInteger("563760734837617125639567809597112021760000000");
		
		HashMap <String, Integer> mapToInt = new HashMap <String, Integer>();
		int BRANCH_FACTOR = 5;
		int NUM_LEVELS = 3;
		//3, 4 is money?
		
		int length = 0;
		
		for(int i=0; i<NUM_LEVELS; i++) {
			length += (int)(Math.pow(BRANCH_FACTOR, i));
		}
		
		sopl("Branching factor: " + BRANCH_FACTOR);
		sopl("Num levels: " + NUM_LEVELS);
		System.out.println("Number of vertices: " + length);
		
		String perm[] = new String[length];
		
		for(int i=0; i<perm.length; i++) {
			perm[i] = i + "";
			mapToInt.put(perm[i], i);
		}
		

		boolean diffsFound[] = new boolean[length];
		
		String curPerm[];
		
		BigInteger maxPermIndex = PermutationBigInt.getFactorial(perm.length);
		
		int actualIterations = 0;
		
		for(BigInteger curPermIndex = startIndex; curPermIndex.compareTo(maxPermIndex) < 0; ) {
			
			actualIterations++;
			if(actualIterations % 10000000 == 0) {
				sopl("actualIterations = " + actualIterations);
				sopl("curPermIndex = " + curPermIndex);
			}

			curPerm = PermutationBigInt.generatePermutation(perm, curPermIndex);
			
			for(int j=0; j<diffsFound.length; j++) {
				diffsFound[j] = false;
			}
			
			boolean stillGood = true;
			int indexFail = -1;
			for(int j=0; j<curPerm.length; j++) {
				
				if(j > 0) {
					
					int curDiff = Math.abs(mapToInt.get(curPerm[j]) - mapToInt.get(curPerm[(j+BRANCH_FACTOR-1)/BRANCH_FACTOR - 1]));
					
					if(diffsFound[curDiff]) {
						
						stillGood = false;
						indexFail = j;
						break;
					} else {
						diffsFound[curDiff] = true;
					}
					
					if(j % BRANCH_FACTOR != 1 && j > 0 && mapToInt.get(curPerm[j-1]) > mapToInt.get(curPerm[j])) {

						stillGood = false;
						indexFail = j;
						break;
					}
					
				}
				
			}
			
			if(stillGood) {
				for(int j=0; j<curPerm.length; j++) {
					sop(curPerm[j] + ", ");
				}
				sopl();
				sopl();
				
				for(int j=0; j<diffsFound.length; j++) {
					if(j==0 && diffsFound[j]) {
						System.out.println("oops!");
					} else if(j > 0 && diffsFound[j] == false) {
						System.out.println("oops2!");
					}
				}
				
				curPermIndex = curPermIndex.add(BigInteger.ONE);
						
			} else {
				BigInteger numToJump = PermutationBigInt.getFactorial(perm.length - indexFail - 1);
				
				curPermIndex = curPermIndex.add(numToJump);
			}
		}
		
		sopl("DONE");
	}

/*
	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			System.out.println("Error: (" + s + ") is not a number");
			return -1;
		}
	}
*/

	public static void sop(Object a) {
		System.out.print(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}

	public static void sopl() {
		System.out.println();
	}
}
