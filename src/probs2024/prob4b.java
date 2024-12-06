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

public class prob4b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in4.txt"));
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
			for(int i=1; i<lines.size() -1; i++) {
				
				
				for(int j=1; j<lines.get(i).length() -1; j++) {
					
					if(lines.get(i).charAt(j) == 'A') {
						
						if((lines.get(i-1).charAt(j-1) == 'M' && lines.get(i+1).charAt(j+1) == 'S') || (lines.get(i-1).charAt(j-1) == 'S' && lines.get(i+1).charAt(j+1) == 'M')) {
							
							if((lines.get(i+1).charAt(j-1) == 'M' && lines.get(i-1).charAt(j+1) == 'S') || (lines.get(i+1).charAt(j-1) == 'S' && lines.get(i-1).charAt(j+1) == 'M')) {
								cur++;
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
	
	public static boolean matches(char args[]) {
		if(args.length != 4) {
			return false;
		}
		if(args[0] == 'X' && args[1] == 'M' && args[2] == 'A' && args[3] == 'S') {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean matchesBackwards(char args[]) {
		if(args.length != 4) {
			return false;
		}
		if(args[3] == 'X' && args[2] == 'M' && args[1] == 'A' && args[0] == 'S') {
			return true;
		} else {
			return false;
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
