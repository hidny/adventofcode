package prob2024afterStars;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;
import utilsPE.Fraction;
import utilsPE.Matrix;

public class prob13after {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in13.txt"));
			//in = new Scanner(new File("in2024/prob2024in0.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				if(line.startsWith("Button A")) {
					
					String rightPart = line.split(":")[1];
					String xPart = rightPart.split(",")[0];
					
					int x1 = pint(xPart.split("\\+")[1]);

					String yPart = rightPart.split(",")[1];
					int y1 = pint(yPart.split("\\+")[1]);
					
					i++;
					line = lines.get(i);
					
					rightPart = line.split(":")[1];
					xPart = rightPart.split(",")[0];
					
					int x2 = pint(xPart.split("\\+")[1]);

					yPart = rightPart.split(",")[1];
					int y2 = pint(yPart.split("\\+")[1]);
					
					i++;
					line = lines.get(i);
					rightPart = line.split(":")[1];
					xPart = rightPart.split(",")[0];
					
					long xp = pint(xPart.split("\\=")[1]);

					yPart = rightPart.split(",")[1];
					long yp = pint(yPart.split("\\=")[1]);
					
					
					sopl(x1 + ", " + y1);
					sopl(x2 + ", " + y2);
					sopl(xp + ", " + yp);
					sopl("-----");
					
					
					//solve matrix:
					Fraction matrix[][] = new Fraction[2][3];
					matrix[0][0] = new Fraction(x1 , 1);
					matrix[0][1] = new Fraction(x2 , 1);
					matrix[0][2] = new Fraction(xp , 1);
					
					matrix[1][0] = new Fraction(y1 , 1);
					matrix[1][1] = new Fraction(y2 , 1);
					matrix[1][2] = new Fraction(yp , 1);
					
					Fraction result[] = Matrix.solveMatrixSlow(matrix);
					
					sopl("result: ");
					if(result[0].getNumerator().divideAndRemainder(result[0].getDenominator())[1].compareTo(BigInteger.ZERO) == 0
						&& result[1].getNumerator().divideAndRemainder(result[1].getDenominator())[1].compareTo(BigInteger.ZERO) == 0
								
							) {
						sopl(result[0]);
						sopl(result[1]);
						sopl("-----");
						sopl("-----");
						
						if (result[0].getNumerator().divideAndRemainder(result[0].getDenominator())[0].longValue() < 0
								|| result[1].getNumerator().divideAndRemainder(result[1].getDenominator())[0].longValue() < 0) {
						//14752
						} else {
							cur += 3 * result[0].getNumerator().divideAndRemainder(result[0].getDenominator())[0].longValue() 
									+ result[1].getNumerator().divideAndRemainder(result[1].getDenominator())[0].longValue() ;
						}
					} else {
						sopl("Fraction");
						sopl(result[0]);
						sopl(result[1]);
						sopl("-----");
						sopl("-----");
					}
					
				}
			}


			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
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
	
	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + ") is not a number");
			return -1;
		}
	}
	

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
		} else {
			sop("Error: (" + s + ") is not a number");
			return -1;
		}
	}
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}
	
	public static int[][] getIntTable(ArrayList<String> lines) {
		int grid[][] = new int[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				if(lines.get(i).charAt(j) == '.') {
					grid[i][j] = -1;
				} else {
					grid[i][j] = (int)(lines.get(i).charAt(j) - '0');
				}
			}
		}
		
		return grid;
	}
	

	public static char[][] getCharTable(ArrayList<String> lines) {
		char grid[][] = new char[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j] = lines.get(i).charAt(j);

			}
		}
		
		return grid;
	}

}
