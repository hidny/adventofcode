package utilsFromProjectEuler;

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
			
}
