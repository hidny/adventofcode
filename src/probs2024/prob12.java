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

public class prob12 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			//in = new Scanner(new File("in2024/prob2024in12.txt"));
			in = new Scanner(new File("in2024/prob2024in12.txt"));
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

			char map[][] =  getCharTable(lines);

			boolean table1[][] = new boolean[map.length][map[0].length];
			boolean table2[][] = new boolean[map.length][map[0].length];
			
			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[0].length; j++) {
					
					if(table1[i][j] == false) {
						int area = getArea(map, table1, i, j);
						int perim = getPerim(map, table2, i, j);
						sopl("Area " + i + ", " + j + ": " + area);
						sopl("Perim " + i + ", " + j + ": " + perim);
						sopl();
						
						cur += area * perim;
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
	
	public static int getArea(char map[][], boolean used[][], int i, int j) {
		int a = 0;
		
		char start = map[i][j];
		a++;
		used[i][j] = true;
		
		for(int i2=i-1; i2<=i+1; i2++) {
			for(int j2=j-1; j2<=j+1; j2++) {
				if(i2 >=0 && j2>=0 && i2<map.length && j2 <map[0].length) {
					if((i2 == i && j2 !=j) || (j2 == j && i2 != i)) {
						
						if(used[i2][j2] == false && start == map[i2][j2]) {
							
							a += getArea(map, used, i2, j2);
						}
					}
				}
			}
		}
		
		return a;
	}
	
	public static int getPerim(char map[][], boolean used[][], int i, int j) {
		int a = 0;
		
		char start = map[i][j];
		
		
		
		used[i][j] = true;
		
		for(int i2=i-1; i2<=i+1; i2++) {
			for(int j2=j-1; j2<=j+1; j2++) {
				if((i2 == i && j2 !=j) || (j2 == j && i2 != i)) {
					if(i2 >=0 && j2>=0 && i2<map.length && j2 <map[0].length) {
						
						
						
						if(start != map[i2][j2]) {
							a++;
							sopl(i + "," + j + ": " + i2 + ", " + j2);
						} else if(used[i2][j2] == false && start == map[i2][j2]) {
							
							a += getPerim(map, used, i2, j2);
						}
					} else {
				
						a++;
						sopl("edge: " + i + "," + j + ": " + i2 + ", " + j2);
					
					}
						
				}
			}
		}
		
		return a;
	}
	
	/*
	public static int getPerimOutside(int starti, int startj, char map[][], int i, int j, int orientation, boolean start) {
		
		if(! start && i == starti && j == startj) {
			return 0;
		}
		
		char cur = map[i][j];
		
		for(int rot = 0; rot < 4; rot++) {
			
			int orient = (orientation + 3 + rot) % 4;
			
			if(orient == 0 && i-1 >=0 && map[i-1][j] == map[i][j]) {
				return 1 + getPerimOutside(starti, startj, map, i-1, j, (orient + 3)%4, false);
			
			} else if(orient == 1 && j+1 <map[0].length && map[i][j+1] == map[i][j]) {
	
				return 1 + getPerimOutside(starti, startj, map, i-1, j, (orient + 3)%4, false);
			
			} else if(orient == 2 && i+1 <map.length && map[i+1][j] == map[i][j]) {
	
				return 1 + getPerimOutside(starti, startj, map, i+1, j, (orient + 3)%4, false);
			
			} else if(orient == 3 && j-1 >=0 && map[i][j-1] == map[i][j]) {
	
				return 1 + getPerimOutside(starti, startj, map, i, j-1, (orient + 3)%4, false);
			}
		}
		
		return 
		
	}*/
	

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
	
	public static int[][] getIntTable(ArrayList<String> lines) {
		int grid[][] = new int[lines.size()][lines.get(0).length()];
		
		String line;
		for(int i=0; i<lines.size(); i++) {
			
			
			line = lines.get(i);
			
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
