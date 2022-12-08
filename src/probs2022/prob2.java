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

public class prob2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2022/prob2022in2.txt"));
			 //in = new Scanner(new File("in2022/prob2022in3.txt"));
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
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(" ");
				
				
				int score = 0;
				if(token[1].equals("X")) {
					score = 1;
				} else if(token[1].equals("Y")) {
					score =2;
					
				} else if(token[1].equals("Z")) {
					score =3;
					
				}
				
				int them = (int)(token[0].charAt(0) - 'A');
				int us = (int)(token[1].charAt(0) - 'X');
				
				if( (them - us + 3) % 3 == 0) {
					score +=3;
				} else if( (them - us + 3) % 3 == 1) {
					score +=0;
				} else if( (them - us + 3) % 3 == 2) {
					score +=6;
				}
				count += score;
				
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
