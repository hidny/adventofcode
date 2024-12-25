package probs2024;
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

public class prob25 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in25.txt"));
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
			
			boolean readyForNext = true;
			
			ArrayList ints = new ArrayList<Integer>();
			
			ArrayList<prob25obj> keys = new ArrayList<prob25obj>();
			ArrayList<prob25obj> locks = new ArrayList<prob25obj>();
			
			boolean firstLine = true;
			boolean islock = false;
			
			String curTable = "";
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				if(line.length() == 0) {
					
					prob25obj newkeyLock = new prob25obj();
					
					newkeyLock.addTable(curTable);
					
					newkeyLock.getHeights(islock);
					
					if(islock) {
						locks.add(newkeyLock);
					} else {
						keys.add(newkeyLock);
					}
					
					readyForNext = true;
					firstLine = true;
					curTable = "";
				} else {
					curTable += line + "\n";
					if(firstLine && line.contains(".") == false) {
						islock = false;
					} else {
						islock = true;
					}
					
					
				}
				
			}
			
			//Clumsily add the last one here:
			prob25obj newkeyLock = new prob25obj();
			
			newkeyLock.addTable(curTable);
			
			newkeyLock.getHeights(islock);
			
			if(islock) {
				locks.add(newkeyLock);
			} else {
				keys.add(newkeyLock);
			}
			//END Clumsily add the last one here:
			
			readyForNext = true;
			firstLine = true;
			curTable = "";

			for(int i=0; i<locks.size(); i++) {
				for(int j=0; j<keys.size(); j++) {
					
					if(keys.get(j).noOverlap(locks.get(i)) ) {
						cur++;
					}
				}
			}
			
			//7410

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
