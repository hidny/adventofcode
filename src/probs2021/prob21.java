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

public class prob21 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in21.txt"));
			// in = new Scanner(new File("in2021/prob2021in22.txt"));
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
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String token[] = line.split(" ");
				
				sopl(token[0]);
				
			}
			
			int score1 = 0;
			int score2 = 0;
			
			int pos1 = 3 -1;
			int pos2 = 5 -1;
			
			int dice =1;
			
			
			while(score1 < 1000 && score2 < 1000) {
				
				pos1 += dice;	
				pos1 += dice+1;	
				pos1 += dice+2;
				dice += 3;
				
				score1 += (pos1 % 10) + 1;
				
				if(score1 >= 1000) {
					break;
				}

				pos2 += dice;	
				pos2 += dice+1;	
				pos2 += dice+2;
				dice += 3;
				
				score2 += (pos2 % 10) + 1;
				
				
			}
			

			int numRolls = dice -1;
			
			if(numRolls % 3 != 0) {
				sopl("oops");
				exit(1);
			}
			
			if(score1 >= 1000) {
				count = score2 * numRolls;
			} else {
				count = score1 * numRolls;
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
