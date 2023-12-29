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

public class prob24WithCommentHelp {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in24.txt"));
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
			int table[][] = new int[LIMIT][LIMIT];
			
			
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
			
			ArrayList <prob24obj> trajs = new ArrayList <prob24obj>();
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String tokens[] = line.trim().split("@");
				
				String token1[] = tokens[0].trim().split(",");

				String token2[] = tokens[1].trim().split(",");
				
				prob24obj tmp = new prob24obj();
				for(int j=0; j<token1.length; j++) {
					tmp.start[j] = plong(token1[j].trim());
				}
				for(int j=0; j<token2.length; j++) {
					tmp.vel[j] = plong(token2[j].trim());
				}
				
				trajs.add(tmp);
			}
			
			ArrayList<String> vars = new ArrayList<String>();

			vars.add("xs");
			vars.add("ys");

			vars.add("dxs");
			vars.add("dys");
			vars.add("c");
			
			vars.add("result");
			
			int numEquations = 5;
			long matrix[][] = new long[numEquations][numEquations + 1];
			
			
			int xIndex = 0;
			
			//I just had this be 1 for solving y and have this be
			// 2 for solving for z.
			for(int secondAxis = 1; secondAxis<=2; secondAxis++) {
			
				if(secondAxis == 1) {
					sopl("Getting the matrix for x and y:");
				} else {
					sopl("Getting the matrix for x and z:");
				}
				
				//TODO AHA: no need for z
				
				int offset = 0;
				
				for(int i=0; i<numEquations; i++) {
					
					prob24obj curHail = trajs.get(i + offset);
					
					matrix[i][symbolToIndex("xs", vars)]  =  curHail.vel[secondAxis];
					matrix[i][symbolToIndex("ys", vars)]  = -curHail.vel[xIndex];
					matrix[i][symbolToIndex("dxs", vars)] = -curHail.start[secondAxis];
					matrix[i][symbolToIndex("dys", vars)] =  curHail.start[xIndex];
					matrix[i][symbolToIndex("c", vars)] =  1L;
					matrix[i][symbolToIndex("result", vars)]  =  curHail.start[xIndex] * curHail.vel[secondAxis] - curHail.start[secondAxis] * curHail.vel[xIndex];
				}
				
	
				sopl();
				for(int i=0; i<matrix.length; i++) {
					for(int j=0; j<matrix[0].length; j++) {
						sop(matrix[i][j] + ", ");
					}
					sopl();
				}
				sopl();
				
				sopl("Python matrix:");
				sopl(convertToPythonFormat(matrix));
				
				sopl(matrix.length + " by " + matrix[0].length);

				sopl("Wolfram matrix:");
				sopl(convertToWolframFormat(matrix));
				
				sopl();
				sopl();
			}
			in.close();
			
			//Test:
			//xs = 24
			//ys = 13
			//zs = 10
			
			//sum: 47
			
			//Mine:
			//From python:
			//xs = 131246724405205
			//ys = 399310844858926
			//zs = 277550172142625
			//sum = 808107741406756
			//It's correct!
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static String convertToPythonFormat(long matrix[][]) {
		String ret = "[";
		
		for(int i=0; i<matrix.length; i++) {
			
			ret += "[";
			
			for(int j=0; j<matrix[0].length; j++) {
				
				if(j<matrix[0].length - 1) {
					ret += " " + matrix[i][j] + ",";
				} else {
					ret += " " + matrix[i][j];
				}
			}
			
			if(i< matrix.length - 1) {
				ret += "],";
			} else {
				ret += "]";
			}
		}
		
		ret += "]";
		
		return ret;
	}
	

	public static String convertToWolframFormat(long matrix[][]) {
		String ret = "{";
		
		for(int i=0; i<matrix.length; i++) {
			
			ret += "{";
			
			for(int j=0; j<matrix[0].length; j++) {
				
				if(j<matrix[0].length - 1) {
					ret += " " + matrix[i][j] + ",";
				} else {
					ret += " " + matrix[i][j];
				}
			}
			
			if(i< matrix.length - 1) {
				ret += "},";
			} else {
				ret += "}";
			}
		}
		
		ret += "}";
		
		return ret;
	}
	
	
	public static int symbolToIndex(String symbol, ArrayList<String> labels) {
		for(int i=0; i<labels.size(); i++) {
			if(labels.get(i).equals(symbol)) {
				return i;
			}
		}
		
		return -1;
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

}
