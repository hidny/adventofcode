package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob8 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in8.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
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
			
			String dir = lines.get(0);
			
			HashMap<String, prob8obj> map = new HashMap<String, prob8obj>();
			
			for(int i=1; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				if(line.equals("") == false) {
					
					sopl(line);
					String tokens[] = line.split(" ");
					String objLeft = tokens[2].substring(1, tokens[2].length() - 1);
					String objRight = tokens[3].substring(0, tokens[3].length() - 1);
					
					map.put(tokens[0], new prob8obj(objLeft, objRight));
					
				}
				
				
			}
			
			String current = "AAA";
			
			cur = 0;
			OUT:
			while(true) {
				for(int i=0; i<dir.length(); i++) {
				
					if(dir.charAt(i) == 'L') {
						current = map.get(current).left;
					} else {
						current = map.get(current).right;
					}
					cur++;
					
					if(current.equals("ZZZ")) {
						break OUT;
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
			sop("Error: (" + s + ") is not a number");
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
