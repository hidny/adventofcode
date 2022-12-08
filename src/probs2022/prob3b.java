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

public class prob3b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2022/prob2022in3.txt"));
			//in = new Scanner(new File("in2022/prob2022in4.txt"));
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
				
				String rug1 = line.substring(0, line.length() /2);
				String rug2 = line.substring(line.length() /2);
				
				if(rug1.length() != rug2.length()) {
					sopl("oops");
				}
				
				// 2* (int)(c - 'A' + 1);
				boolean taken1[] = new boolean[26 * 2];
				boolean taken2[] = new boolean[26 * 2];
				
				for(int j=0; j<rug1.length(); j++) {
					char c = rug1.charAt(j);
					
					if( c >= 'A' && c <= 'Z') {
						taken1[26 + (int)(c - 'A')] = true;
						
					} else {
						taken1[(int)(c - 'a')] = true;
					}
					
				}
				

				for(int j=0; j<rug2.length(); j++) {
					char c = rug2.charAt(j);
					
					if( c >= 'A' && c <= 'Z') {
						taken2[26 + (int)(c - 'A')] = true;

					} else {
						taken2[(int)(c - 'a')] = true;
					}
					
				}
				
				for(int j=0; j<26; j++) {
					if(taken1[j] && taken2[j]) {
						count += 1 + j;
						sopl(1 + j);
					}
				}
				
				for(int j=0; j<26; j++) {
					if(taken1[26 + j] && taken2[26 + j]) {
						count +=27 + j;
						sopl(27 + j);
					}
				}
				sopl();
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
