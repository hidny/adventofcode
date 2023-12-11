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

public class prob10 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in10.txt"));
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
			int cur = 0;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
			}

			int start[] = getS(lines);
			
			int numStepsNeeded = 0;
			int dirToUse = -1;
			
			for(int dirStart=0; dirStart<4; dirStart++) {
				
				numStepsNeeded = 0;
				int curDir = dirStart;
				int curi = start[0];
				int curj = start[1];
				
				if(dirStart == 0) {
					curi++;
				} else if(dirStart == 1) {
					curj--;
				} else if(dirStart == 2) {
					curi--;
				} else if(dirStart == 3) {
					curj++;
				}
				
				do {
					
					int ret[] = f(lines, curi, curj, curDir);
					curi = ret[0];
					curj = ret[1];
					curDir = ret[2];
					
					numStepsNeeded++;
					
				} while(curi >= 0 && curj>=0 && !(curi == start[0] && curj == start[1]));
				
				if(curi == start[0] && curj == start[1]) {
					
					dirToUse = dirStart;
					break;
				}
			}
			
			/*if(numStepsNeeded % 2 == 1) {
				sopl("doh!");
				exit(1);
			}*/
			
			sopl(numStepsNeeded);
			
			
			


			sopl("Answer: " + ((numStepsNeeded+1)/2));
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int[] getS(ArrayList <String>lines) {

		for(int i=0; i<lines.size(); i++) {
			sopl(lines.get(i));
			for(int j=0; j<lines.get(i).length(); j++) {
				if(lines.get(i).charAt(j) == 'S') {
					
					sopl("ahsdflef");
					
					return new int[] {i, j};
					
				}
			}
		}
		
		return new int[] {-5, -5};
	}
	
	public static int[] f(ArrayList <String>lines, int i, int j, int comingFrom) {
		
		char scan = lines.get(i).charAt(j);
		
		if(scan == '|' && comingFrom ==0) {
			return new int[] {i+1, j, 0};
			
		} else if(scan == '|' && comingFrom ==2) {
			return new int[] {i-1, j, 2};
			
		} else if(scan == '-' && comingFrom ==1) {
			return new int[] {i, j-1, 1};
			
		} else if(scan == '-' && comingFrom ==3) {
			return new int[] {i, j+1, 3};

		} else if(scan == 'L' && comingFrom ==0) {
			return new int[] {i, j+1, 3};
			
		} else if(scan == 'L' && comingFrom ==1) {
			return new int[] {i-1, j, 2};
			
		} else if(scan == 'J' && comingFrom ==0) {
			return new int[] {i, j-1, 1};
			
		} else if(scan == 'J' && comingFrom ==3) {
			return new int[] {i-1, j, 2};
			
		} else if(scan == '7' && comingFrom ==2) {
			return new int[] {i, j-1, 1};
			
		} else if(scan == '7' && comingFrom ==3) {
			return new int[] {i+1, j, 0};
		
		} else if(scan == 'F' && comingFrom ==1) {
			return new int[] {i+1, j, 0};
			
		} else if(scan == 'F' && comingFrom ==2) {
			return new int[] {i, j+1, 3};
			
		} else if(scan == 'S') {
			return new int[] {-5, -5, -5};
		
		} else if(scan == '.') {
			return new int[] {-10, -10, -10};
		}
		
		
		return new int[] {-1, -1, -1};
		
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
