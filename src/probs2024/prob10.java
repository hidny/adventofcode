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

public class prob10 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			//814
			in = new Scanner(new File("in2024/prob2024in10.txt"));
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
			
			int grid[][] = new int[lines.size()][lines.get(0).length()];
			
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

			cur = 0L;
			
			for(int i=0; i<grid.length; i++) {
				for(int j=0; j<grid[0].length; j++) {
					if(grid[i][j] == 0) {
						cur += getNumBranches(grid, i, j);
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
	
	public static long getNumBranches(int grid[][], int i, int j) {
		 boolean used[][] = new boolean[grid.length][grid[0].length];
		 
		 if(grid[i][j] != 0) {
			 sopl("doh");
			 exit(1);
		 }
		 
		 boolean result[][] = new boolean[grid.length][grid[0].length];
		 
		 result =  getNumBranches(grid, i, j, 0, used, result);
		 
		 long cur=0;
		 for(int i2=0; i2<result.length; i2++) {
			 for(int j2=0; j2<result[0].length; j2++) {
				 if(result[i2][j2]) {
					 cur++;
				 }
			 }
		 }
		 return cur;
	}
	
	//2279
	
	public static boolean[][] getNumBranches(int grid[][], int i, int j, int curNum, boolean used[][], boolean result[][]) {
		
		if(curNum == 9) {
			result[i][j] = true;
			return result;
		}
		
		used[i][j] = true;
		long sum = 0;
		
		for(int i2=i-1; i2<=i+1; i2++) {
			for(int j2=j-1; j2<=j+1; j2++) {
				if((i2 == i && j2 != j || i2 != i && j2 == j ) && i2 >=0 && j2 >=0 && i2<grid.length && j2 <grid[0].length) {
					
					if(used[i2][j2] == false) {
						
						if(/*curNum == grid[i2][j2] ||*/ curNum+1 == grid[i2][j2]) {
							
							result = getNumBranches(grid, i2, j2, grid[i2][j2], used, result);
						}
					}
				}
			}
		}
		
		used[i][j] = false;
		
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
