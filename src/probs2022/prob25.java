package probs2022;
import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob25 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2022/prob2022in25.txt"));
			 //in = new Scanner(new File("in2022/prob2022in26.txt"));
			 //in = new Scanner(new File("in2022/prob2022in27.txt"));
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

			long sum = 0L;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(" ");
				
				long cur = 0L;
				for(int j=0; j<line.length(); j++) {
					
					char next = line.charAt(j);
					
					long numRight = 0;
					
					if(next >= '0' && next <='2') {
						numRight = (long)(next - '0');
					} else if(next == '-') {
						numRight = -1;
					} else if(next == '=') {
						numRight = -2;
					}
					
					cur = 5 * cur + numRight;
				}
				
				sopl(line + ": " + cur);
				sum+=cur;
				
			}
			
			
			
			
			sopl("Answer decimal: " + sum);
			
			String answer = "";
			while(sum != 0) {
				
				long mod = sum % 5;
				if(mod < 0) {
					mod += 5;
				}
				
				if(mod == 0) {
					answer = "0" + answer;
				} else if(mod == 1) {
					answer = "1" + answer;
					
				} else if(mod == 2) {
					answer = "2" + answer;
					
				} else if(mod == 3) {
					answer = "=" + answer;
					
				} else if(mod == 4) {
					answer = "-" + answer;
					
				} else {
					sopl("DOH!");
					exit(1);
				}
				
				sum = (sum + 2)/5;
				
			}
			
			// 1-1=11=0=2-02---=222
			sopl("Answer: " + answer);
			
			line = answer;
			//Copy/paste code:
			long cur = 0L;
			for(int j=0; j<line.length(); j++) {
				
				char next = line.charAt(j);
				
				long numRight = 0;
				
				if(next >= '0' && next <='2') {
					numRight = (long)(next - '0');
				} else if(next == '-') {
					numRight = -1;
				} else if(next == '=') {
					numRight = -2;
				}
				
				cur = 5 * cur + numRight;
			}
			//End copy/paste code
			
			sopl("Check: " + line + ": " + cur);
			
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
