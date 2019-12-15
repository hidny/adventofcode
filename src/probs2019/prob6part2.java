package probs2019;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob6part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in6.txt"));
			 //in = new Scanner(new File("in2019/prob2019in6.txt.test"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			

			Hashtable<String, String> trail = new Hashtable<String, String>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				sopl(line);
				
				trail.put(line.split(",")[1], line.split(",")[0]);
			}
			
			ArrayList youTrail =new ArrayList<String>();
			
			String cur = "YOU";
			youTrail.add(cur);
			
			while(trail.containsKey(cur)) {
				cur = trail.get(cur);
				youTrail.add(cur);
			}

			ArrayList santaTrail =new ArrayList<String>();
			
			cur = "SAN";
			santaTrail.add(cur);
			
			while(trail.containsKey(cur)) {
				cur = trail.get(cur);
				santaTrail.add(cur);
			}
			
			OUT:
			for(int i=0; i<youTrail.size(); i++) {
				for(int j=0; j<santaTrail.size(); j++) {
					if(youTrail.get(i).equals(santaTrail.get(j))) {
						sopl(i + j - 2);
						break OUT;
					}
				}
			}
			
			
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
