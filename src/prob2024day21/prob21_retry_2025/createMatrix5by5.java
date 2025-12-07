package prob2024day21.prob21_retry_2025;

public class createMatrix5by5 {

	public static final char dirPad[][] = {{' ', '^', 'A'}, {'<', 'v', '>'}};
	public static final char targetList[] = {'^', 'A', '<', 'v', '>'};
	
	
	private static int[] getCoordDirPad(char button) {
		
		for(int i=0; i<dirPad.length; i++) {
			for(int j=0; j<dirPad[0].length; j++) {
				if(dirPad[i][j] == button) {
					return new int[] {i, j};
				}
			}
		}
		return null;
	}
	
	private static long getManhattanDist(char start, char end) {
		
		int distI = getCoordDirPad(end)[0] - getCoordDirPad(start)[0];
		int distJ = getCoordDirPad(end)[1] - getCoordDirPad(start)[1];
		
		return Math.abs(distI) + Math.abs(distJ);
	}
	public static long[][] getMatrixBasedOnDirPad() {
		
		
		long ret[][] = new long[targetList.length][targetList.length];
		
		for(int i=0; i<ret.length; i++) {
			for(int j=0; j<ret[0].length; j++) {
				
				
				long dist = getManhattanDist(targetList[i], targetList[j]);
				
				
				//Add action button:
				dist += 1;
				
				ret[i][j] = dist;
				
			}
		}
		
		//Print it:
		sopl();
		sopl("5x5 matrix:");
		for(int i=0; i<ret.length; i++) {
			for(int j=0; j<ret[0].length; j++) {
				sop(ret[i][j] + "        ");
			}
			sopl();
		}
		sopl();
		sopl();
		
		return ret;
	}

	
	//Mult vector by matrix:
	public static long[] matrixMultiply(long matrix[][], long vector[]) {
		
		long ret[] = new long[vector.length];
		for(int i=0; i<vector.length; i++) {
			ret[i] = 0;
			for(int j=0; j<vector.length; j++) {
				

				ret[i] += vector[j] * matrix[i][j];
			}
		}
		return ret;
	}
	
	public static void printVector(long vector[]) {

		sopl();
		sopl("Vector:");
		for(int i=0; i<vector.length; i++) {
			sopl(vector[i]);
		}
		sopl();
	}
	
	public static long sumVector(long vector[]) {
		
		long ret = 0;
		for(int i=0; i<vector.length; i++) {
			ret += vector[i];
		}
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
	
	public static void main(String args[]) {
		long origMatrix[][] = getMatrixBasedOnDirPad();
		
		long vector[] = new long[] {1, 1, 1, 1, 1};
		
		for(int i=0; i<25; i++) {
			vector = matrixMultiply(origMatrix, vector);
			
			printVector(vector);
			sopl("New answer:");
			sopl(sumVector(vector));
		}
	}
}
