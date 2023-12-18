package practice.openQuestionTree.bigInt;

import java.math.BigInteger;

public class PermutationBigInt {

	public static String[] generatePermutation(String objects[], BigInteger permNumber) {
		String result[] = new String[objects.length];
		
		int nthObject;
		
		boolean objectsUsed[] = new boolean[objects.length];
		
		
		for(int i=0; i<objects.length; i++) {
			nthObject = (int)(permNumber.divide(getFactorial(objects.length - 1 - i)).longValue());
		
			permNumber = permNumber.divideAndRemainder(getFactorial(objects.length - 1 - i))[1];
			
			result[i] = getNthuntakenObject(objects, nthObject, objectsUsed);
			
		}
		return result;
	}
	
	//get the nth untaken object and updates the objects taken array:
	private static String getNthuntakenObject(String objects[], int n, boolean objectsTaken[]) {
		int numUntakenSoFar = 0;
		for(int i=0; i<objectsTaken.length; i++) {
			if(objectsTaken[i] == false) {
				if(n == numUntakenSoFar) {
					objectsTaken[i] = true;
					return objects[i];
				}
				numUntakenSoFar++;
				continue;
			}
		}
		System.out.println("WTF. Ran out of permutations.");
		return null;
	}

	
	public static BigInteger factorial[] = new BigInteger[100];
	
	public static BigInteger getFactorial(int n) {
		
		if(factorial[n] != null) {
			return factorial[n];
		}
		BigInteger result = BigInteger.ONE;
		for(int i=1; i<=n; i++) {
			result = result.multiply(new BigInteger(i + ""));
		}
		
		factorial[n] = result;
		
		return result;
	}
}
