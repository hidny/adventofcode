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

public class prob18 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in18.txt"));
			 in = new Scanner(new File("in2021/prob2021in19.txt"));
			 
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
			//firstTests(lines);
			
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			
			prob18tree sum = null;
			
			for(int i=0; i<lines.size(); i++) {
				String line = lines.get(i);
				
				
				prob18tree a = new prob18tree(line, 0 , null);
				
				if(sum == null) {
					sum = a;
					
					prob18tree.printTree(sum);
					sopl();
					
					sopl();
					sopl();
					
					sopl("---------");
					
				} else {
					sum = new prob18tree(sum, a);
					prob18tree.printTree(sum);
					sopl();
					sopl("Reduce:");
					sum = prob18tree.reduce2(sum);
					prob18tree.printTree(sum);
					sopl();
					
					sopl();
					sopl();
					
					sopl("---------");
					
				}
			}
			long answer = prob18tree.getMagnitude(sum);
			
			
			sopl("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static void firstTests(ArrayList <String>lines) {

		for(int i=0; i<lines.size(); i++) {
			
			//int temp = Integer.parseInt(lines.get(i));
			//count+=temp;
			
			String line = lines.get(i);
			
			
			prob18tree a = new prob18tree(line, 0 , null);
			
			sopl(line);
			prob18tree.printTree(a);
			sopl();
			sopl("reduce:");
			prob18tree b = prob18tree.reduce2(a);
			prob18tree.printTree(b);
			sopl();
			
			sopl();
			sopl();
			
			sopl("---------");
			
		}
	}
	
	public static String sum(String a, String b) {
		
		String sumNaive = "[" + a +"," + b + "]";
		
		
		return sumNaive;
	}
	
	
	//public static String explode(String a, int index) {
		
		
	//}

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
