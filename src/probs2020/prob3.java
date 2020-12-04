package probs2020;
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

public class prob3 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in3.txt"));
			 //in = new Scanner(new File("in2020/prob2020in1.txt.test"));
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
				String line2 = lines.get(i);
				
				for(int j=0; j<100 * line2.length(); j++) {
					if(line2.charAt(j % line2.length()) == '#') {
						table[i][j] = false;
					} else {
						table[i][j] = true;
					}
				}
			}
			

			long answer = 1;
			
			for(int i=0; i<lines.size(); i++) {
				String line2 = lines.get(i);
				if(line2.charAt((3*i) % line2.length()) == '#') {
					count++;
				}
			}
			answer *= count;
			count = 0;

			for(int i=0; i<lines.size(); i++) {
				String line2 = lines.get(i);
				if(line2.charAt((i) % line2.length()) == '#') {
					count++;
				}
			}
			answer *= count;
			count = 0;
			

			for(int i=0; i<lines.size(); i++) {
				String line2 = lines.get(i);
				if(line2.charAt((5*i) % line2.length()) == '#') {
					count++;
				}
			}
			answer *= count;
			count = 0;
			

			for(int i=0; i<lines.size(); i++) {
				String line2 = lines.get(i);
				if(line2.charAt((7*i) % line2.length()) == '#') {
					count++;
				}
			}
			answer *= count;
			count = 0;
			

			for(int i=0; i<lines.size(); i+=2) {
				String line2 = lines.get(i);
				if(line2.charAt((i/2) % line2.length()) == '#') {
					count++;
				}
			}
			answer *= count;
			count = 0;
			
			
			sopl("Answer: " + answer);
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
