package probs2023;
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

public class prob1 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in1.txt"));
			//in = new Scanner(new File("in2023/prob2023in2.txt"));
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

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			int cur = 0;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				int num1 = 0;
				for(int j=0; j<line.length(); j++) {
					if(line.charAt(j) >= '0' && line.charAt(j) <='9') {
						num1 = (int)(line.charAt(j) - '0');
						break;
					} else if(line.substring(j).startsWith("one")) {
						num1 = 1;
						break;
					} else if(line.substring(j).startsWith("two")) {
						num1 = 2;
						break;
					} else if(line.substring(j).startsWith("three")) {
						num1 = 3;
						break;
					} else if(line.substring(j).startsWith("four")) {
						num1 = 4;
						break;
					} else if(line.substring(j).startsWith("five")) {
						num1 = 5;
						break;
					} else if(line.substring(j).startsWith("six")) {
						num1 = 6;
						break;
					} else if(line.substring(j).startsWith("seven")) {
						num1 = 7;
						break;
					} else if(line.substring(j).startsWith("eight")) {
						num1 = 8;
						break;
					} else if(line.substring(j).startsWith("nine")) {
						num1 = 9;
						break;
					}
				}
				int num2 = 0;
				for(int j=line.length()-1; j>=0; j--) {
					if(line.charAt(j) >= '0' && line.charAt(j) <='9') {
						num2 = (int)(line.charAt(j) - '0');
						break;
					} else if(line.substring(j).startsWith("one")) {
						num2 = 1;
						break;
					} else if(line.substring(j).startsWith("two")) {
						num2 = 2;
						break;
					} else if(line.substring(j).startsWith("three")) {
						num2 = 3;
						break;
					} else if(line.substring(j).startsWith("four")) {
						num2 = 4;
						break;
					} else if(line.substring(j).startsWith("five")) {
						num2 = 5;
						break;
					} else if(line.substring(j).startsWith("six")) {
						num2 = 6;
						break;
					} else if(line.substring(j).startsWith("seven")) {
						num2 = 7;
						break;
					} else if(line.substring(j).startsWith("eight")) {
						num2 = 8;
						break;
					} else if(line.substring(j).startsWith("nine")) {
						num2 = 9;
						break;
					}
				}
				
				cur += num1 * 10 + num2;
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
