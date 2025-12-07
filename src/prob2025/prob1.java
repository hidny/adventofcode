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

public class prob1 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			//in = new Scanner(new File("in2025/prob2025in0.txt"));
			in = new Scanner(new File("in2025/prob2025in1.txt"));

			 
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
			ArrayList ints = new ArrayList<Integer>();

			int cur = 50;
			
			int cur2 = 0;
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				char dir = line.charAt(0);
				
				int num = pint(line.substring(1));
				
				if(dir == 'L') {
					

					int numTimes = 0;
					if (cur - num <=0) {
						
						numTimes = -(-1 + ((cur - num) / 100));
						cur2 += numTimes;
						
						sopl("---");
						if(cur == 0) {
							cur2--;
							numTimes--;
							//sopl("The dial is rotated L" + num + " to point at " + ((cur + 100 - num) % 100) +" during this rotation, it points at 0 " + numTimes + " times");

							sopl(" " + cur);
							sopl("The dial is rotated L" + num + " to point at " + (((cur - num) % 100 + 100) % 100) +" during this rotation, it points at 0 " + numTimes + " times");
							
							System.out.println("AAH");
							//System.exit(1);
						}

						sopl("click");
					}
					sopl(" " + cur);
					sopl("The dial is rotated L" + num + " to point at " + (((cur - num) % 100 + 100) % 100) +" during this rotation, it points at 0 " + numTimes + " times");
					

					cur = ((cur - num) % 100 + 100) % 100;
					sopl("l cur : " + cur);
					
				} else if(dir == 'R') {

					int numTimes = 0;
					sopl("---");
					if (cur + num >=100 ) {
						
						numTimes = (num +cur) / 100;
						cur2 += numTimes;

						sopl(" " + cur);
						
					}
					sopl("The dial is rotated R" + num + "  to point at " + (((cur + num) % 100)) + " ; during this rotation, it points at 0 " + numTimes + " times");
					sopl("click");
					cur = ((cur + num) % 100);
				}
				
				
				
			}


			sopl("Answer: " + cur2);
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
