package flipflop2026;
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

public class prob6a {

	//https://flipflop.slome.org/demo
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("inflipflop2026/prob2026in6.txt"));
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
			int table342[] = new int[LIMIT];
			
			
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

			int digits[] = new int[10];
			
			int curI = -1;
			int curJ = -1;
			
			int parity = -1;
			GET_START:
			for(int i=0; i<lines.size(); i++) {
				for(int j=0; j<lines.get(0).length(); j++) {
				
					char c = lines.get(i).charAt(j);
					
					if(c == 'S') {
						curI = i;
						curJ = j;
						parity = (i + j) % 2;
						break GET_START;
						
					}
				}
			}
			
			for(int i=0; i<lines.size(); i++) {
				for(int j=0; j<lines.get(0).length(); j++) {
					
					char c = lines.get(i).charAt(j);
					
					if(c == '*') {
						
						for(int i2=i-1; i2<=i+1; i2++) {
							for(int j2=j-1; j2<=j+1; j2++) {
								
								if(i2 < 0 || j2 < 0 || i2 >= lines.size() || j2 >= lines.get(0).length()) {
									continue;
								}
								
								if(i2 == i ^ j2 == j) {
									
									char c2 = lines.get(i2).charAt(j2);
									if(c2 == '#') {
										
										int parity2 = (i2 + j2) % 2;
										
										if(parity == parity2) {
											cur = 2*cur;
										} else {

											cur = 2*cur + 1;
										}
									}
								}
							}
						}
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
