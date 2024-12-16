package prob2024afterStars;
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

public class prob14b2 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in14.txt"));
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
			
			ArrayList<prob14obj> robs = new ArrayList<prob14obj>();
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String tokens[] = line.split(" ");
				
				String ps[] = tokens[0].split("=")[1].split(",");
				String vs[] = tokens[1].split("=")[1].split(",");
				
				long p[] = new long[2];
				long v[] = new long[2];
				p[0] = pint(ps[0]);
				p[1] = pint(ps[1]);
				
				v[0] = pint(vs[0]);
				v[1] = pint(vs[1]);
				
				robs.add(new prob14obj(p, v));
				
				
			}
			sopl(robs.size());

			int height = 103;
			int width = 101;
			
			
			int repeatFactor = width * height;
			
			Scanner in2= new Scanner(System.in);
			int sec = 0;
			
			for(int k=0; k<repeatFactor; k++) {
				sec++;
				
				boolean table[][] = new boolean[height][width];
				
				for(int i=0; i<robs.size(); i++) {
					robs.get(i).tick(1);
					
					table[(int)prob14obj.mod(robs.get(i).p[1], table.length)][(int)prob14obj.mod(robs.get(i).p[0], table[0].length)] = true;
					
				}
				
				/*boolean show = true;
				for(int i=0; i<table.length; i++) {
					for(int j=0; j<2; j++) {
						if(table[i][j] == true) {
							show = false;
						}
					}
				}*/
				boolean show = false;
				
				//It's much easier when you know what to look for:
				int numInARow = 0;
				for(int i=0; i<table.length; i++) {
					for(int j=0; j<table[0].length; j++) {
						if(table[i][j] == true) {
							numInARow++;
							if(numInARow > 15) {
								show = true;
							}
						} else {
							numInARow = 0;
						}
					}
				}
				
				numInARow = 0;
				for(int i=0; i<table.length; i++) {
					for(int j=0; j<table[0].length; j++) {
						if(table[i][j] == true) {
							numInARow++;
							if(numInARow > 15) {
								show = true;
							}
						} else {
							numInARow = 0;
						}
					}
				}
				
				
				if(show) {
					sopl("seconds: " + sec);
					for(int i=0; i<table.length; i++) {
						for(int j=0; j<table[0].length; j++) {
							if(table[i][j]) {
								sop("#");
							} else {
								sop(".");
							}
						}
						sopl();
					}
					sopl();
					sopl();
					sopl();
				}
				
				//in2.next();
			}
				
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
