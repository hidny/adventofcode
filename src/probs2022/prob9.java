package probs2022;
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

public class prob9 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2022/prob2022in9.txt"));
			//in = new Scanner(new File("in2022/prob2022in10.txt"));
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
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			ArrayList ints = new ArrayList<Integer>();
			
			
			HashSet<String> taken = new HashSet<String>();
			

			
			int currentHI = 0;
			int currentHJ = 0;
			
			
			int currentTI = 0;
			int currentTJ = 0;
			

			taken.add(currentTI + "," + currentTJ);
			
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(" ");
				
				String dir = token[0];
				int amount = pint(token[1]);
				
				int iDir = 0;
				int jDir = 0;
				
				if(dir.equals("U")) {
					iDir = -1;
					
				} else if(dir.equals("R")) {
					jDir = 1;
					
				} else if(dir.equals("D")) {
					iDir = 1;
					
				} else if(dir.equals("L")) {
					jDir = -1;
					
				} else{
					sopl("doh");
				}
				
				for(int k=0; k<amount; k++) {
					
					currentHI += iDir;
					currentHJ += jDir;
					
					
					if(Math.abs(currentHI - currentTI) > 1
							|| Math.abs(currentHJ - currentTJ) > 1) {
						
						//Move:
						
						if(currentHI > currentTI) {
							currentTI += 1;
						} else if(currentHI < currentTI) {
							currentTI -= 1;
						}
						

						if(currentHJ > currentTJ) {
							currentTJ += 1;
						} else if(currentHJ < currentTJ) {
							currentTJ -= 1;
						}
						
						taken.add(currentTI + "," + currentTJ);
					}
					
				}
				
			}
			
			
			sopl("Answer: " + taken.size());
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
