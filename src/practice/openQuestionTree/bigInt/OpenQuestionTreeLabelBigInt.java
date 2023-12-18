package practice.openQuestionTree.bigInt;

import java.math.BigInteger;

import number.IsNumber;

public class OpenQuestionTreeLabelBigInt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String perm[] = new String[15];
		
		for(int i=0; i<perm.length; i++) {
			perm[i] = i + "";
		}
		
		
		BigInteger maxPermIndex = PermutationBigInt.getFactorial(perm.length);
		
		int actualIterations = 0;
		
		for(BigInteger curPermIndex = BigInteger.ZERO; curPermIndex.compareTo(maxPermIndex) < 0; ) {
			
			actualIterations++;
			if(actualIterations % 10000000 == 0) {
				sopl("actualIterations = " + actualIterations);
				sopl("curPermIndex = " + curPermIndex);
			}

			String curPerm[] = PermutationBigInt.generatePermutation(perm, curPermIndex);
			
			boolean diffsFound[] = new boolean[curPerm.length];
			
			boolean stillGood = true;
			int indexFail = -1;
			for(int j=0; j<curPerm.length; j++) {
				
				if(j > 0) {
					
					int curDiff = Math.abs(pint(curPerm[j]) - pint(curPerm[(j+1)/2 - 1]));
					
					if(diffsFound[curDiff]) {
						
						stillGood = false;
						indexFail = j;
						break;
					} else {
						diffsFound[curDiff] = true;
					}
					
					if(j % 2 == 0 && j > 0 && pint(curPerm[j-1]) > pint(curPerm[j])) {

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


	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			System.out.println("Error: (" + s + ") is not a number");
			return -1;
		}
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
	
	/*//21 vertices (1, 5, 21)
	 * 0, 1, 3, 19, 20, 5, 10, 16, 18, 8, 11, 14, 15, 9, 12, 13, 17, 2, 4, 6, 7, 

0, 1, 3, 19, 20, 5, 10, 16, 18, 9, 11, 13, 15, 8, 12, 14, 17, 2, 4, 6, 7, 

0, 1, 3, 19, 20, 5, 14, 16, 18, 9, 10, 12, 13, 7, 8, 11, 17, 2, 4, 6, 15, 

0, 1, 3, 19, 20, 5, 15, 16, 18, 8, 9, 12, 14, 6, 7, 11, 17, 2, 4, 10, 13, 

0, 1, 3, 19, 20, 5, 15, 16, 18, 8, 10, 12, 13, 6, 7, 11, 17, 2, 4, 9, 14, 

0, 1, 3, 19, 20, 6, 10, 15, 18, 7, 11, 14, 16, 9, 12, 13, 17, 2, 4, 5, 8, 

0, 1, 3, 19, 20, 6, 10, 16, 18, 5, 11, 14, 17, 9, 12, 13, 15, 2, 4, 7, 8, 

0, 1, 3, 19, 20, 6, 10, 16, 18, 9, 11, 13, 14, 5, 12, 15, 17, 2, 4, 7, 8, 

0, 1, 3, 19, 20, 6, 13, 15, 18, 7, 10, 12, 16, 8, 9, 11, 17, 2, 4, 5, 14, 

0, 1, 3, 19, 20, 6, 13, 16, 18, 5, 10, 12, 17, 8, 9, 11, 15, 2, 4, 7, 14, 

0, 1, 3, 19, 20, 6, 14, 15, 18, 9, 10, 12, 13, 7, 8, 11, 17, 2, 4, 5, 16, 

0, 1, 3, 19, 20, 6, 14, 16, 18, 5, 9, 12, 17, 7, 8, 11, 15, 2, 4, 10, 13, 

0, 1, 3, 19, 20, 6, 14, 16, 18, 7, 9, 12, 15, 5, 8, 11, 17, 2, 4, 10, 13, 

0, 1, 3, 19, 20, 6, 15, 17, 18, 5, 7, 13, 16, 4, 10, 11, 12, 2, 8, 9, 14, 

0, 1, 3, 19, 20, 6, 15, 17, 18, 5, 9, 13, 14, 4, 10, 11, 12, 2, 7, 8, 16, 

0, 1, 3, 19, 20, 6, 15, 17, 18, 5, 10, 12, 14, 4, 9, 11, 13, 2, 7, 8, 16, 

0, 1, 3, 19, 20, 7, 10, 13, 18, 8, 11, 14, 16, 9, 12, 15, 17, 2, 4, 5, 6, 

0, 1, 3, 19, 20, 7, 10, 16, 18, 5, 11, 13, 17, 6, 12, 14, 15, 2, 4, 8, 9, 

0, 1, 3, 19, 20, 7, 10, 17, 18, 5, 11, 13, 16, 4, 12, 14, 15, 2, 6, 8, 9, 

0, 1, 3, 19, 20, 7, 13, 15, 18, 8, 10, 12, 14, 6, 9, 11, 17, 2, 4, 5, 16, 

0, 1, 3, 19, 20, 7, 14, 17, 18, 5, 8, 13, 15, 4, 10, 11, 12, 2, 6, 9, 16, 

0, 1, 3, 19, 20, 7, 15, 17, 18, 5, 10, 12, 13, 4, 6, 11, 14, 2, 8, 9, 16, 

0, 1, 3, 19, 20, 8, 10, 12, 18, 7, 11, 15, 16, 9, 13, 14, 17, 2, 4, 5, 6, 

0, 1, 3, 19, 20, 8, 10, 12, 18, 9, 11, 13, 16, 7, 14, 15, 17, 2, 4, 5, 6, 

0, 1, 3, 19, 20, 8, 11, 16, 18, 5, 7, 15, 17, 6, 10, 13, 14, 2, 4, 9, 12, 

0, 1, 3, 19, 20, 8, 11, 17, 18, 5, 7, 15, 16, 4, 10, 13, 14, 2, 6, 9, 12, 

0, 1, 3, 19, 20, 8, 12, 17, 18, 5, 9, 13, 15, 4, 10, 11, 14, 2, 6, 7, 16, 

0, 1, 3, 19, 20, 8, 13, 16, 18, 7, 9, 12, 14, 5, 6, 11, 17, 2, 4, 10, 15, 

0, 1, 3, 19, 20, 9, 11, 15, 18, 8, 10, 12, 14, 6, 7, 13, 17, 2, 4, 5, 16, 

0, 1, 3, 19, 20, 9, 11, 16, 18, 5, 7, 14, 17, 6, 10, 12, 13, 2, 4, 8, 15, 

0, 1, 3, 19, 20, 9, 11, 16, 18, 7, 10, 12, 14, 5, 6, 13, 17, 2, 4, 8, 15, 

0, 1, 3, 19, 20, 9, 11, 17, 18, 5, 7, 14, 16, 4, 10, 12, 13, 2, 6, 8, 15, 

0, 1, 3, 19, 20, 9, 11, 17, 18, 5, 8, 14, 15, 4, 10, 12, 13, 2, 6, 7, 16, 

0, 1, 3, 19, 20, 9, 11, 17, 18, 5, 10, 12, 15, 4, 8, 13, 14, 2, 6, 7, 16, 

0, 1, 3, 19, 20, 10, 12, 16, 18, 5, 8, 11, 17, 6, 7, 9, 15, 2, 4, 13, 14, 

0, 1, 3, 19, 20, 10, 12, 16, 18, 7, 8, 11, 15, 5, 6, 9, 17, 2, 4, 13, 14, 

0, 1, 3, 19, 20, 10, 12, 17, 18, 5, 8, 11, 16, 4, 7, 9, 15, 2, 6, 13, 14, 

0, 1, 3, 19, 20, 10, 13, 16, 18, 5, 7, 11, 17, 6, 8, 9, 12, 2, 4, 14, 15, 

0, 1, 3, 19, 20, 10, 13, 17, 18, 5, 7, 11, 16, 4, 8, 9, 12, 2, 6, 14, 15, 

0, 1, 3, 19, 20, 10, 14, 17, 18, 5, 9, 11, 13, 4, 7, 8, 12, 2, 6, 15, 16, 

0, 1, 3, 19, 20, 10, 15, 17, 18, 5, 8, 11, 13, 4, 6, 7, 12, 2, 9, 14, 16, 

0, 1, 3, 19, 20, 11, 15, 17, 18, 5, 8, 9, 14, 4, 6, 7, 10, 2, 12, 13, 16, 
*/
}
