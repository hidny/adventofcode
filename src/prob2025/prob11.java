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

public class prob11 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2025/prob2025in11.txt"));
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
			
			
			
			int adjency[][] = new int[lines.size() + 1][lines.size() + 1];
			Hashtable<String, Integer> labelToInt = new  Hashtable<String, Integer>();
			String labels[] = new String[lines.size() + 1];
			

			long tableCur[] = new long[lines.size() + 1];
			
			for(int i=0; i<lines.size(); i++) {
				line = lines.get(i);
					
				String lhs = line.split(":")[0];
				labelToInt.put(lhs, i);
				labels[i] = lhs;
				
				tableCur[i] = 0;
				sopl("label: " + lhs);
			}
			
			labelToInt.put("out", lines.size());
			labels[lines.size()] = "out";
			
			
			for(int i=0; i<lines.size(); i++) {
				line = lines.get(i);
					
				//String lhs = line.split(":")[0];
				
				String tokens[] = line.split(":")[1].trim().split(" ");
				
				
				for(int j=0; j<tokens.length; j++) {
					adjency[labelToInt.get(tokens[j])][i]++;
				}
					
			}
			

			tableCur[labelToInt.get("you")] = 1;
			
			for(int i=0; i<600; i++) {
				
				cur += tableCur[labelToInt.get("out")];
				
				tableCur = matrixMult(adjency, tableCur, labels);
				
				//sopl(cur);
			}
			
			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static long[] matrixMult(int adjency[][], long tableCur[], String labels[]) {
		
		long result[] = new long[tableCur.length];
		
		for(int i=0; i<tableCur.length; i++) {
			long cur = 0L;
			
			for(int j=0; j<result.length; j++) {
				cur += tableCur[j] * adjency[i][j];
				
				if(adjency[i][j] > 0 && tableCur[j] > 0) {
					sopl("plus " + labels[i] + " from " + labels[j] + ", ");
				}
			}
			
			if(cur > 0) {
				sopl("add to " + labels[i]);
			}
			result[i] = cur;
		}
		
		return result;
		
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
