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

public class prob14 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2021/prob2021in14.txt"));
			 //in = new Scanner(new File("in2021/prob2021in15.txt"));
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
			
			
			ArrayList<String> formulas = new ArrayList<String>();
			ArrayList<String> lhs = new ArrayList<String>();
			ArrayList<String> rhs = new ArrayList<String>();
			
			String start = lines.get(0);
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String token[] = line.split(" ");
				
				if(lines.get(i).contains("->")) {
					formulas.add(lines.get(i));
					lhs.add(lines.get(i).split("-")[0].trim());
					rhs.add(lines.get(i).split(">")[1].trim());
				}
				
			}
			
			String cur = start;
		
			for(int step=0; step<10; step++) {
				
				//sopl(cur);
				String next = "";
				for(int j=0; j<cur.length() - 1; j++) {
					
					next += cur.charAt(j);
					
					String tmp = cur.charAt(j) + "" + cur.charAt(j+1);
					
					for(int k=0; k<lhs.size(); k++) {
						if(tmp.equals(lhs.get(k))) {
							next += rhs.get(k);
						}
					}
				}
				
				next += cur.charAt(cur.length() - 1);
				
				cur = next;
			}

			int array[] = new int[26];
			
			for(int i=0; i<cur.length(); i++) {
				array[cur.charAt(i) - 'A']++;
			}
			
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			
			for(int i=0; i<array.length; i++) {
				
				if(array[i] > max) {
					max = array[i];
				} else if(array[i] < min && array[i] > 0) {
					min = array[i];
				}
			}
			
			sopl(max);
			sopl(min);
			
			sopl("Answer: " + (max - min));
			in.close();
			
			//543328405
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	
	public static ArrayList<String> function(ArrayList<String> lines, int depth, String cur) {
		ArrayList<String> ret = new ArrayList<String>();
		
		
		return ret;
		
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
