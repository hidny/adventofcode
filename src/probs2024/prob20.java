package probs2024;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import aStar.AstarAlgo;
import aStar.AstarNode;
import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob20 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in20.txt"));
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
				
			}

			char map[][] = getCharTable(lines);

			boolean mapBool[][] = new boolean[map.length][map[0].length];
			int starti = -5;
			int startj = -5;
			int goali = -5;
			int goalj = -5;
			
			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[0].length; j++) {

					mapBool[i][j] = false;
					if(map[i][j] == 'S') {
						starti = i;
						startj = j;
					} else if(map[i][j] == 'E') {
						goali = i;
						goalj = j;
						
					} else if(map[i][j] == '.') {
						
					} else {
						mapBool[i][j] = true;
					}
				}
			}
			
			prob20Node.init(mapBool);
			
			prob20Node.goali = goali;
			prob20Node.goalj = goalj;
			
			
			ArrayList<AstarNode> ret = AstarAlgo.astar(prob20Node.mapUsed[starti][startj], null);
			
			int firstPath = ret.size() -1;
			
			cur = 0L;
			for(int i=0; i<map.length; i++) {
				sopl("i: " + i);
				for(int j=0; j<map[0].length; j++) {
					
					if(mapBool[i][j] == false) {
						continue;
					}
					sopl("j: " + j);
					prob20Node.excepti = i;
					prob20Node.exceptj = j;
					

					ArrayList<AstarNode> ret2 = AstarAlgo.astar(prob20Node.mapUsed[starti][startj], null);
					
					int firstPath2 = ret2.size() -1;
					
					if(firstPath2 +100 <= firstPath) {
						cur++;
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
