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

public class prob10 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in10.txt"));
			 //in = new Scanner(new File("in2021/prob2021in11.txt.test"));
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

			int array[] = new int[4];
			
			for(int i=0; i<lines.size(); i++) {

				sopl("line: " + i);
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String token[] = line.split(" ");
				
				
				String expectedClose = "";
				int index = 0;
				
				for(int j=0; j<line.length(); j++) {
					char next = line.charAt(j);
					
					sop(next);
					if(next == '(') {
						expectedClose = ")" +expectedClose;
					} else if (next == '[') {
						expectedClose = "]" +expectedClose;
						
					} else if (next == '{') {
						expectedClose = "}" +expectedClose;
						
					} else if (next == '<') {
						expectedClose = ">" +expectedClose;
						
					} else {
						
						if(expectedClose.length() > 0) {
	
							sop(next);
							boolean breakQuest = true;
							if(expectedClose.charAt(0) == next) {
								//good
								expectedClose = expectedClose.substring(1);
								breakQuest = false;
							} else if(next == ')') {
								array[0]++;
								
							} else if (next == ']') {
								array[1]++;
								
								
							} else if (next == '}') {
								array[2]++;
								
								
							} else if (next == '>') {
								array[3]++;
								
								
							} else {
								sopl("What? " +next);
							}
							
							if(breakQuest) {
								sopl("bad");
								break;
							}
						} else {
							//discard
							sopl("oops");
							break;
						}
					}
					
				}
				
			}
			
			for(int i=0; i<4; i++) {
				sopl(array[i]);
			}
			count = 3*array[0] + 57 * array[1] + 1197 * array[2] + 25137 * array[3];
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
