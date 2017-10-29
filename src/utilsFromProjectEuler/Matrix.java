package utilsFromProjectEuler;

public class Matrix {
	
	//TODO: copy it back to project euler
	//You fixed a bug in it
	//Or... nah
	
	//START OF MATRIX FUNCTIONS:
			public static Fraction[] solveMatrixSlow(Fraction matrix[][]) {
				Fraction result[][] = hardCopyMatrix(matrix);
				result = getRowEchelonForm(result);
				result = getReducedRowEchelonForm(result);
				
				Fraction answer[] = new Fraction[result.length];
				
				for(int i=0; i<answer.length; i++) {
					answer[i] = result[i][result[0].length - 1];
				}
				
				return answer;
			}
			
			private static Fraction[][] hardCopyMatrix(Fraction matrix[][]) {
				Fraction copy[][] = new Fraction[matrix.length][matrix[0].length];
				for(int i=0; i<matrix.length; i++) {
					for(int j=0; j<matrix[0].length; j++) {
						copy[i][j] = matrix[i][j];
					}
				}
				
				return copy;
			}
			
			private static Fraction[][] getRowEchelonForm(Fraction matrix[][]) {
				Fraction result[][] = hardCopyMatrix(matrix);
				
				boolean foundPivot = false;
				
				for(int i=0; i<result.length && i<result[0].length; i++) {
					
					//Make sure the pivot? is non zero...
					if(result[i][i].compareTo(Fraction.ZERO) == 0) {
						
						for(int j=i+1; j<result.length; j++) {
							
							if(result[j][i].compareTo(Fraction.ZERO) != 0) {
								foundPivot = true;
								swapRows(result, i, j);
								break;
							}
						}
					} else {
						foundPivot = true;
					}
					
					if(foundPivot == false) {
						System.out.println("ERROR: couldn't find pivot!");
						System.exit(0);
					}
					
					
					Fraction pivot = result[i][i];
					if(pivot.compareTo(Fraction.ZERO) != 0) {
						result[i] = multiplyRow(result[i], Fraction.divide(Fraction.ONE, pivot));
					
						for(int j=i+1; j<result.length; j++) {
							result[j] = addRows(result[j], multiplyRow(result[i], Fraction.mult(new Fraction(-1, 1), result[j][i])));
						}
					}
				}
				
				return result;
			}
			
			private static Fraction[][] getReducedRowEchelonForm(Fraction matrix[][]) {
				Fraction result[][] = hardCopyMatrix(matrix);
				
				for(int i=0; i<result.length && i<result[0].length; i++) {
					if(result[i][i].compareTo(Fraction.ONE) == 0) {
						for(int j=i-1; j>=0; j--) {
							result[j] = addRows(result[j], multiplyRow(result[i], Fraction.mult(new Fraction(-1, 1), result[j][i])));
						}
					}
				}
				
				return result;
			}
			
			private static Fraction[] multiplyRow(Fraction matrix[], Fraction mult) {
				Fraction ret[] = new Fraction[matrix.length];
				
				for(int i=0; i<ret.length; i++) {
					ret[i] = Fraction.mult(matrix[i], mult);
				}
				return ret;
			}

			private static Fraction[] addRows(Fraction row1[], Fraction row2[]) {
				Fraction ret[] = new Fraction[row1.length];
				for(int i=0; i<row1.length; i++) {
					ret[i] = Fraction.plus(row1[i], row2[i]);
				}
				return ret;
			}
			
			private static void swapRows(Fraction matrix[][], int i, int j) {
				Fraction tempRow[];
				tempRow = matrix[i];
				matrix[i] = matrix[j];
				matrix[j] = tempRow;
			}
			
			public static void printMatrix(Fraction matrix[][]) {
				for(int i=0; i<matrix.length; i++) {
					for(int j=0; j<matrix[0].length; j++) {
						System.out.print(matrix[i][j] + "   ");
					}
					System.out.println();
				}
			}
			//END OF MATRIX FUNCTIONS
}
