package practice.openQuestionTree.bigInt;

import java.math.BigInteger;
import java.util.HashMap;

public class JustPrintPerm {

	public static void main(String args[]) {
		

		int length = 31;
		
		String perm[] = new String[length];
		
		for(int i=0; i<perm.length; i++) {
			perm[i] = i + "";
		}
		
		String curPerm[] = PermutationBigInt.generatePermutation(perm, new BigInteger("11324534674398054814562457600"));
		
		for(int i=0; i<curPerm.length; i++) {
			System.out.println(curPerm[i]);
		}
		
	}
}
