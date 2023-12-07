package probs2023;
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

public class prob6 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in6.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
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
			
			/*
			 * Time:        61     67     75     71
			   Distance:   430   1036   1307   1150
			 */
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int times[] = new int[]{61,     67,     75 ,    71};
			int distances[] = new int[]{430,   1036,   1307,   1150};
			int most = 0;
			int most2 = 0;
			int most3 = 0;
			int cur = 0;
			ArrayList ints = new ArrayList<Integer>();
			
			cur = 1;
			for(int i=0; i<times.length; i++) {
				
				int numWays = 0;
				
				for(int hold = 0; hold<times[i]; hold++) {
					int speed= hold;
					int timeLeft = times[i] - hold;
					
					int dist = speed * timeLeft;
					
					if(dist > distances[i]) {
						numWays++;
					}
				}
				
				cur *= numWays;
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
