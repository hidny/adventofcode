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

public class prob3 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			//in = new Scanner(new File("in2023/prob2023in3.txt"));
			in = new Scanner(new File("in2023/prob2023in3.txt"));
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
				
				boolean foundNumber  = false;
				int curNumber = 0;
				int startIndex = 0;
				
				for(int j=0; j <=line.length(); j++) {
					
					if(j < line.length() && line.charAt(j) >='0' && line.charAt(j) <= '9') {
						
						if(foundNumber == false) {
							startIndex = j;
							foundNumber  = true;
						}
						curNumber = 10 * curNumber + (int)(line.charAt(j) - '0');
						
					} else {
						
						if(foundNumber) {
							
							//look for symbol around...
							
							FOUND_SYMBOL:
							for(int i2 = Math.max(i-1, 0); i2 <= Math.min(i+1, lines.size() - 1); i2++) {
								
								for(int j2 = Math.max(startIndex-1, 0); j2 <= Math.min(j, line.length() - 1); j2++) {
									
									char tmp = lines.get(i2).charAt(j2);
									if(tmp != '.' && !(tmp >='0' && tmp <= '9')) {
										cur += curNumber;
										break FOUND_SYMBOL;
									}
									//539590
								}
							}
							
						}
						
						foundNumber  = false;
						curNumber = 0;
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
