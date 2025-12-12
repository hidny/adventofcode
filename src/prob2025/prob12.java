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

public class prob12 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2025/prob2025in12.txt"));
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
			ArrayList ints = new ArrayList<Integer>();
			
			
			ArrayList<prob12Shape> shapes = new ArrayList<prob12Shape>();
			
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String tokens[] = line.split(":");
				
				//sopl(line);
				if(line.endsWith(":")) {
					
					sopl("shape");
					boolean shape[][] = new boolean[3][3];
					
					i++;
					
					for(int j=0; j<3; j++) {
						String curLine = lines.get(i + j);
						
						for(int k=0; k<3; k++) {
							if(curLine.charAt(k) == '#') {
								shape[j][k] = true;
							} else {
								shape[j][k] = false;
							}
						}
						
					}
					
					i = i+3;
					
					shapes.add(new prob12Shape(shape));
					
				}
				
				
				if(tokens.length > 1 && tokens[1].length() > 2) {
					
					sopl();
					sopl("Test: " + line);
					int y = pint(tokens[0].split("x")[0]);
					int x = pint(tokens[0].split("x")[1]);
					
					int boxArea = x*y;
					
					int shapeArea = 0;
					
					String amountTokens[] = tokens[1].trim().split(" ");
					for(int j=0; j<amountTokens.length; j++) {
						shapeArea += pint(amountTokens[j]) * shapes.get(j).area;
					}
					
					if(shapeArea > boxArea) {
						sopl("Already know it's a loser!");
						continue;
					}
					
					sopl("Holes allowed:");
					sopl(boxArea - shapeArea);
					
					cur++;
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
