package prob2025;
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

public class prob6b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2025/prob2025in6.txt"));
			//in = new Scanner(new File("in2025/prob2025in0.txt"));
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
			
			int numTokens = lines.get(0).split("\\s+").length;
			
			int opLine = lines.size()-1;

			char curOp = '+';
			
			long curSum = 0L;
			long curMult = 1L;
			for(int j=0; j<lines.get(0).length(); j++) {
				
				
				if(lines.get(opLine).charAt(j) != ' ') {
					
					if(curOp == '*') {
						sopl("-----");
						cur += curMult;
						sopl(curMult);
						sopl();
						sopl();
					} else if(curOp == '+') {
						cur += curSum;
						sopl("-----");
						sopl(curSum);
						sopl();
						sopl();
					} else {
						exit(1);
					}
					curOp = lines.get(opLine).charAt(j);
					
					curSum = 0L;
					curMult = 1L;
				}
				
				int curVert = 0;
				boolean foundDigit = false;
				for(int i=0; i<lines.size()-1; i++) {
					if(lines.get(i).charAt(j) != ' ') {
						curVert = 10*curVert + ((int)(lines.get(i).charAt(j) - '0'));
						foundDigit = true;
					}
				}
				sopl(curVert);
				curSum += curVert;
				if(foundDigit) {
					curMult *= curVert;
				}
				
			}
			

			if(curOp == '*') {
				cur += curMult;
			} else if(curOp == '+') {
				cur += curSum;
			} else {
				exit(1);
			}

			//15041238

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
