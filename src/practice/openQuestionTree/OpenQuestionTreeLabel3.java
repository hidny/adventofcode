package practice.openQuestionTree;

import number.IsNumber;

public class OpenQuestionTreeLabel3 {

	//TODO: don't use longs...
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String perm[] = new String[13];
		
		for(int i=0; i<perm.length; i++) {
			perm[i] = i + "";
		}
		
		
		long numIterations = utilsPE.Permutation.getSmallFactorial(perm.length);
		
		int actualIterations = 0;
		
		for(int j=0; j<perm.length; j++) {
			
			if(j>0) {
				sopl(j + " links to " + ((j+2)/3 - 1));
			}
		}
		
		for(long p=0; p<numIterations; p++) {
			
			actualIterations++;
			if(actualIterations % 10000000 == 0) {
				sopl("actualIterations = " + actualIterations);
				sopl("p = " + p);
			}

			//sopl("p = " + p);
			
			
			String curPerm[] = utilsPE.Permutation.generatePermutation(perm, p);
			
			boolean diffsFound[] = new boolean[curPerm.length];
			
			boolean stillGood = true;
			int indexFail = -1;
			for(int j=0; j<curPerm.length; j++) {
				
				if(j > 0) {
					
					int curDiff = Math.abs(pint(curPerm[j]) - pint(curPerm[(j+2)/3 - 1]));
					
					if(diffsFound[curDiff]) {
						
						stillGood = false;
						indexFail = j;
						break;
					} else {
						diffsFound[curDiff] = true;
					}
					
					if(j % 3 != 1 && j > 0 && pint(curPerm[j-1]) > pint(curPerm[j])) {

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
			} else {
				long numToJump = utilsPE.Permutation.getSmallFactorial(perm.length - indexFail - 1);
				
				p += numToJump - 1;
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
}
