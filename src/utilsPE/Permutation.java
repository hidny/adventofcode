package utilsPE;

public class Permutation {
		  //Another function for the permutation of objects in general. (not just digits)
		//Note that 0 counts as the original ordering.
		//generates an int length numDigits that is the permNumberth permutation
		//ex: numDigits = 3 permNumber = 0
		//return 123
	public static String[] generatePermutation(String objects[], long permNumber) {
		String result[] = new String[objects.length];
		
		int nthObject;
		
		boolean objectsUsed[] = new boolean[objects.length];
		
		
		for(int i=0; i<objects.length; i++) {
			nthObject = (int)(permNumber / getSmallFactorial(objects.length - 1 - i));
		
			permNumber = permNumber % getSmallFactorial(objects.length - 1 - i);
			
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

	public static long getSmallFactorial(int n) {
		long result = 1;
		for(int i=1; i<=n; i++) {
			result *= i;
		}
		
		return result;
	}
	
}
