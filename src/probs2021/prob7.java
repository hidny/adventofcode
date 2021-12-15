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

public class prob7 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in7.txt"));
			 //in = new Scanner(new File("in2021/prob2021in8.txt
			
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
			
			String line = lines.get(0);
			String token[] = line.split(",");
			
			int num = token.length;
			int sum = 0;
			int max = 0;
			for(int i=0; i<token.length; i++) {
				sum += Math.abs((pint(token[i]))) *(Math.abs((pint(token[i]))) + 1) / 2;
				if(pint(token[i]) > max) {
					max = pint(token[i]);
				}
			}
			
			long bestAnswer = sum;
			
			
			for(int h=0; h<max; h++) {
				
				int curSum = 0;
				for(int i=0; i<token.length; i++) {
					curSum += Math.abs((pint(token[i]) - h)) *(Math.abs((pint(token[i]) - h)) + 1) / 2;
					
				}
				
				if(curSum < bestAnswer) {
					sopl("Next best: " + curSum);
					bestAnswer = curSum;
				}
				
			}
			
			sopl("Answer: " + bestAnswer);
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
