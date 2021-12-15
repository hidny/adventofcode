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

public class prob10b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in10.txt"));
			 //in = new Scanner(new File("in2021/prob2021in11.txt.test"));
			int numTimes = 0;
			 
			long count = 0;
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
			//285077326
			
			ArrayList ints = new ArrayList<Long>();

			
			for(int i=0; i<lines.size(); i++) {

				sopl("line: " + i);
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String token[] = line.split(" ");
				
				
				boolean currupt = false;
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
							} else {
								currupt = true;
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
				
				if(currupt == false && expectedClose.length() > 0) {
					
					long cur = 0;
					
					for(int k=0; k<expectedClose.length(); k++) {
						
						cur = 5 * cur;
						
						char next = expectedClose.charAt(k);
						
						int basePoint = 0;
						if(next == ')') {
							basePoint = 1;
						} else if (next == ']') {
							basePoint = 2;
							
						} else if (next == '}') {
							basePoint = 3;
							
						} else if (next == '>') {
							basePoint = 4;
							
						} else {
							exit(1);
						}
						
						cur += basePoint;
						
					}
					
					
					sopl(cur);
					
					ints.add(cur);
				}
				
			}
			
			Object a[] = Sort.sortList(ints);
			
			sopl(a.length);
			count = (Long)a[a.length / 2];
			
			for(int i=0; i<a.length; i++) {
				sopl(a[i]);
			}
			//285077326
			
			
			
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
