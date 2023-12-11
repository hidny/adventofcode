package probs2023;
import java.io.File;

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

public class prob11b {

	//day1 part 1
	//2:38.01
	
	//I missed the fact that it's linear!
	// the 1st expansion is the 2nd term
	// the 10x one is the 10th term
	// therefore f(1000000) = (1000000-2)*(f(10)-f(2))/8 + f(2)
	
	//f(2) = 10276166
	//f(10) = 15065638
	
	//(1000000-2)*(15065638-10276166)/8 + 10276166
	//=598693078798 (which is the right answer)
	
	
	public static void main(String[] args) {
		Scanner in;
		try {
			
			//1030
			
			in = new Scanner(new File("in2023/prob2023in11.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
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
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			long x[][];
			long y[][]; 
			
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
			ArrayList ints = new ArrayList<Integer>();


			x = new long[lines.size()][lines.get(0).length()];
			y = new long[lines.size()][lines.get(0).length()];
			
			for(int i=0; i<x.length; i++) {
				for(int j=0; j<y[0].length; j++) {
					
					x[i][j] = j;
					y[i][j] = i;
				}
			}
			long FACTOR = 10 - 1;
			
			int curNumFactors = 0;
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				boolean expandRow = true;
				for(int j=0; j<line.length(); j++) {
					if(line.charAt(j) != '.') {
						expandRow = false;
						break;
					}
				}
				
				
				if(expandRow) {
					curNumFactors++;
					
					
				}
				
				for(int j=0; j<lines.get(i).length(); j++) {
					
					y[i][j] = y[i][j] + curNumFactors * FACTOR;
				}
			}
			
			

			curNumFactors = 0;
			
			for(int j=0; j<lines.get(0).length(); j++) {
				
				boolean curExpandCol = true;
				
				for(int i=0; i<lines.size(); i++) {
					if(lines.get(i).charAt(j) != '.') {
						curExpandCol = false;
						break;
					}
				}
				
				if(curExpandCol) {
					curNumFactors++;
				}
				
				for(int i=0; i<lines.size(); i++) {
					
					x[i][j] = x[i][j] + curNumFactors * FACTOR;
				}
			}
			
			
			
			long cur = 0;
			int count2 = 0;
			for(int i=0; i<lines.size(); i++) {
				
				line = lines.get(i);
				for(int j=0; j<line.length(); j++) {
					
					if(line.charAt(j) != '.') {
						for(int i2=i; i2<lines.size(); i2++) {
							
							String line2 = lines.get(i2);
							
							for(int j2=0; j2<line2.length(); j2++) {
								
								
								if(i == i2 && j >= j2) {
									continue;
								} else if(line2.charAt(j2) != '.') {
									
									cur += Math.abs(x[i][j] - x[i2][j2]) + Math.abs(y[i][j] - y[i2][j2]) ;
									count2++;
									sopl(count2 + ": " +( Math.abs(x[i][j] - x[i2][j2]) + Math.abs(y[i][j] - y[i2][j2])));
								}
							}
						}
						
					}
				}
			}

			//1692624654
			
			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	//(598693078798 - 10276166) / (1000000 - 2)

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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
