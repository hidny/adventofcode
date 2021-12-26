package probs2021;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob22a {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in22.txt"));
			// in = new Scanner(new File("in2021/prob2021in23.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			//int LIMIT = 20000;
			//boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			int SIZE = 101;
			
			boolean cubes[][][] = new boolean[SIZE][SIZE][SIZE];
			int BLOCK = 50;
			
			
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			
			for(int a=0; a<lines.size(); a++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(a);
				String token[] = line.split(" ");
				
				boolean setting = false;
				if(token[0].equals("on")) {
					setting = true;
				}
				
				String token2[] = token[1].split(",");
				
				int mins[] = new int[3];
				int maxes[] = new int[3];
				
				for(int i=0; i<3; i++) {
					String token3 = token2[i].split("=")[1];
					String token4[] = token3.split("\\.\\.");
					
					mins[i] = pint(token4[0]);
					maxes[i] = pint(token4[1]);
					sopl(mins[i]);
					sopl(maxes[i]);
					sopl();
					
				}
				
				for(int i=Math.max(-50, mins[0]); i<=Math.min(50, maxes[0]); i++) {
					for(int j=Math.max(-50, mins[1]); j<=Math.min(50, maxes[1]); j++) {
						for(int k=Math.max(-50, mins[2]); k<=Math.min(50, maxes[2]); k++) {
							
							cubes[i+BLOCK][j+BLOCK][k+BLOCK] = setting;
						}
					}
					
				}
				
				
			}
			count=0;
			for(int i=-50; i<=50; i++) {
				for(int j=-50; j<=50; j++) {
					for(int k=-50; k<=50; k++) {
						
						if(cubes[i+BLOCK][j+BLOCK][k+BLOCK]) {
							count++;
						}
					}
				}
				
			}
			sopl("Answer: " + count);
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
			sop("Error: (" + s + " is not a number");
			return -1;
		}
	}

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
		} else {
			sop("Error: (" + s + " is not a number");
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
