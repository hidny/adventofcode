package utilsPE;

import java.math.BigInteger;

public class Combination {
	
	//get the next combonation of true values given current array
		public static boolean[] getNextCombination(boolean current[]) {
			/*Example: series of executions:
			 *  11000
				10100
				10010
				10001
				01100
				01010
				01001
				00110
				00101
				00011
			 */
			//we know that we are going to readjust at least 1 element:
			int numToReadjust = 1;
			
			boolean foundSpaceToFill = false;
			
			int spaceToFill;
			
			//this loops counts the number of elements we have to readjust
			//and finds out if there exist a space to fill.
			for(spaceToFill=current.length - 1; spaceToFill>=0; spaceToFill--) {
				if(current[spaceToFill] == false) {
					foundSpaceToFill = true;
					break;
				} else {
					numToReadjust++;
				}
			}
			
			if(foundSpaceToFill) {
				//Find the rightmost 1 that we will have to move to the right:
				int indexToMove;
				for(indexToMove = spaceToFill-1; indexToMove>=0; indexToMove--) {
					if(current[indexToMove] == true) {
						break;
					}
				}
				
				if(indexToMove>=0) {
					current[indexToMove] = false;
					//goal:
					//got from: 00010111
					//to        00001111
					int startInput1s = indexToMove+1;
					int stopInput1s = startInput1s + numToReadjust;
					for(int i=startInput1s; i<stopInput1s; i++) {
						current[i] = true;
					}
					//input 0s for the rest:
					for(int i=stopInput1s; i<current.length; i++) {
						current[i] = false;
					}
					
				} else {
					//This should only happen is we've gone through all of the combinations
					//or there is no element in current that is true.
					return null;
				}
				
			} else {
				//This should only happen if current[] is filled with only true.
				return null;
			}
			
			return current;
		}
		
		public static BigInteger getAPowerPmodMOD(BigInteger a, BigInteger pow, BigInteger MOD) {
			//base case for power:
			if(pow.compareTo(BigInteger.ZERO) == 0) {
				if(a.compareTo(BigInteger.ZERO) == 0 ) {
					System.out.println("0^0 is I don't know!!!");
					System.exit(1);
				} else if(pow.compareTo(BigInteger.ZERO) < 0 ) {
					System.out.println("No negative powers!" +  a + " to the power of " + pow + "?" );
					System.exit(1);
				}
				return BigInteger.ONE;
			} else if(a.compareTo(BigInteger.ZERO) == 0) {
				return BigInteger.ZERO;
			}
			
			//System.out.println(a + " to the power of " + pow);
			
			int lengthPowTable = 0;
			BigInteger current = BigInteger.ONE;
			while(current.compareTo(pow) <= 0) {
				lengthPowTable++;
				current = current.multiply(new BigInteger("2"));
			}
			
			//System.out.println("Length: " + lengthPowTable);
			
			//Setup the power of 2 table
			BigInteger pow2Table[] = new BigInteger[lengthPowTable];
			pow2Table[0] = a;
			
			
			for(int i=1; i<lengthPowTable; i++) {
				pow2Table[i] = (pow2Table[i-1].multiply(pow2Table[i-1])).mod(MOD);
			}
			//End setup the power of 2 table.
		
			current = pow;
			BigInteger answer = BigInteger.ONE;
			
			
			for(int i=0; i<lengthPowTable && current.compareTo(BigInteger.ZERO) > 0; i++) {
				if(current.mod(new BigInteger("2")).compareTo(BigInteger.ONE) == 0) {
					answer = (answer.multiply(pow2Table[i])).mod(MOD);
					current = current.subtract(BigInteger.ONE);
				}
				current = current.divide(new BigInteger("2"));
			}
			
			return answer;
		}
}
