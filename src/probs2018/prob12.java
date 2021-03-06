package probs2018;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob12 {

	static int LENGTH = 1000;
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in12.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			int NUM_GEN = 20;
			
			line = lines.get(0).split(" ")[2];
			
			boolean table[][] = new boolean[NUM_GEN + 1][LENGTH];
			
			table[0] = convertBig(line);
			
			for(int gen = 1; gen<=NUM_GEN; gen++) {
				for(int i=2; i<lines.size(); i++) {
					line = lines.get(i);
					
					boolean check[] =convert(line.split(" ")[0]);
					boolean result;
					if(line.split(" ")[2].equals("#")) {
						result = true;
					} else {
						result = false;
					}
					
					for(int j=2; j<table[0].length - 2; j++) {
						boolean nope = false;
						
						for(int k=0; k<5; k++) {
							if(table[gen-1][j - 2 + k] != check[k]) {
								nope = true;
								break;
							}
						}

						if(nope ==false) {
							table[gen][j] = result;
						}
					}
					
					
				}
				for(int i=0; i<table[gen].length; i++) {
					if(table[gen][i]) {
						System.out.print("#");
					} else {

						System.out.print(",");
					}
				}
				int prevCount = count;
				count = 0;
				sop("");
				//sop(table[0].length);
				for(int i=0; i<table[0].length; i++) {
					if(table[gen][i]) {
						count += i - LENGTH/2;
					}
				}
				sop(count-prevCount);
				sop(count);
				
			}
			count = 0;
			
			//sop(table[0].length);
			for(int i=0; i<table[0].length; i++) {
				if(table[NUM_GEN][i]) {
					count += i - LENGTH/2;
				}
			}
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean[] convertBig(String arg) {
		//sop(arg);
		boolean ret[] = new boolean[LENGTH];
		for(int i=0; i<arg.length(); i++) {
			if(arg.charAt(i) == '#') {
				ret[LENGTH/2 + i] =true;
			} else {
				ret[LENGTH/2 + i] = false;
			}
		}
		return ret;
	}
	public static boolean[] convert(String arg) {
		//sop(arg);
		boolean ret[] = new boolean[arg.length()];
		for(int i=0; i<arg.length(); i++) {
			if(arg.charAt(i) == '#') {
				ret[i] =true;
			} else {
				ret[i] = false;
			}
		}
		return ret;
	}
	
	public static void sop(Object a) {
		System.out.println(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
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
