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

public class prob5b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			//in = new Scanner(new File("in2025/prob2025in0.txt"));
			in = new Scanner(new File("in2025/prob2025in5.txt"));
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
			
			
			int size = 0;
			for(int i=0; i<lines.size(); i++) {
				line = lines.get(i);
				if(line.contains("-")) {
					size++;
				}
			}
			
			sopl("Size: " + size);

			long ranges[][] = new long[2][size];
			
			
			
			for(int i=0; i<size; i++) {
				line = lines.get(i);
				if(line.contains("-")) {
					String tokens[] = line.split("-");
					ranges[0][i] = plong(tokens[0]);
					ranges[1][i] = plong(tokens[1]);
					sopl(ranges[0][i] + "  " + ranges[1][i]);
				}
			}
			
			for(int i=0; i<ranges[0].length; i++) {

				long range1L = ranges[0][i];
				long range1R = ranges[1][i];
				
				if(range1L == -1) {
					continue;
				}
				
				for(int j=i+1; j<ranges[0].length; j++) {
					

					long range2L = ranges[0][j];
					long range2R = ranges[1][j];
					
					if(range2L == -1) {
						continue;
					}
					
					if(range1R < range2L || range2R < range1L ) {
						continue;
						
					//Inside
					} else if(range1L <= range2L && range2R <= range1R) {
						ranges[0][j] = -1;
						ranges[1][j] = -1;
						
					} else if(range2L <= range1L && range1R <= range2R) {
						ranges[0][i] = ranges[0][j];
						ranges[1][i] = ranges[1][j];
						if(ranges[0][i] > ranges[1][i]) {
							sopl("doh1");
						}
						
						ranges[0][j] = -1;
						ranges[1][j] = -1;
						
						i--;
						break;
						
					//extend:
					}  else if(range1L <= range2L && range2L <= range1R && range1R <= range2R) {
						
						ranges[1][i] = range2R;
						if(ranges[0][i] > ranges[1][i]) {
							sopl("doh2");
						}
						ranges[0][j] = -1;
						ranges[1][j] = -1;
						i--;
						break;
						
					}  else if(range2L <= range1L && range1L <=range2R  && range2R <= range1R) {

						ranges[0][i] = range2L;
						if(ranges[0][i] > ranges[1][i]) {
							sopl("doh3");
						}
						ranges[0][j] = -1;
						ranges[1][j] = -1;
						i--;
						break;
					}
					
				}
				
				
			}
			for(int i=0; i<ranges[0].length; i++) {
				if(ranges[0][i] == -1) {
					sopl("cont");
					continue;
				} else {
					if(ranges[1][i] - ranges[0][i] < 0) {
						sopl("doh....");
					}
					sopl(ranges[1][i] - ranges[0][i] + 1);
					cur += ranges[1][i] - ranges[0][i] + 1;
				}
			}
			//360341832208314


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
