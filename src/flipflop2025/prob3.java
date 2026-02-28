package flipflop2025;
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

public class prob3 {

	//https://flipflop.slome.org/demo
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("inflipflop2025/prob2025in3.txt"));
			//in = new Scanner(new File("inflipflop2025/prob2025in0.txt"));
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
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
			}
			
			String mostColour = lines.get(0);
			int besfFreq = 0;
			
			for(int i=0; i<lines.size(); i++) {
				int num = 1;
				String curColor = lines.get(i);
				for(int j=i+1; j<lines.size(); j++) {
					if(lines.get(j).equals(curColor)) {
						num++;
					}
				}
				
				if(num > besfFreq) {
					mostColour = curColor;
					besfFreq = num;
				}
			}
			
			int special = 0;
			int p2 = 0;
			int red = 0;
			int blue = 0;
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String tokens[] = line.split(",");
				
				int colours[] = new int[3];
				
				for(int j=0; j<tokens.length; j++) {
					colours[j] = pint(tokens[j]);
					
				}
				
				if(colours[0] == colours[1] || colours[1] == colours[2] || colours[0] == colours[2]) {
					special++;
				} else if(colours[1] > colours[0] && colours[1] > colours[2] && colours[0] != colours[2] ) {
					p2++;
				} else if(colours[0] > colours[1] && colours[0] > colours[2] && colours[1] != colours[2] ) {
					red++;
				} else if(colours[2] > colours[0] && colours[2] > colours[1] && colours[0] != colours[1] ) {
					blue++;
				} else {
					sopl("Doh!");
					exit(2);
				}
				
			}
			
			int price = 5 * red + 2 * p2 + 4 * blue + 10 * special;
			sopl("Answer p 1: " + mostColour);
			sopl("Answer p 2: " + p2);
			sopl("Answer p 3: " + price);
			
			
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
