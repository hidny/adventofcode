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

public class prob24 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in24.txt"));
			 
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
			
			
			//ArrayList ints = new ArrayList<Integer>();
			
			
			Hashtable<String, Integer> tiles = new Hashtable<String, Integer>();
			
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String lineCopy = String.copyValueOf(line.toCharArray());

				int x=0;
				int y = 0;
				
				
				for(int j=0; j<line.length(); j++) {
				
					sopl("Test: " + y + "," + x);
					boolean noNOrS = false;
					if(line.charAt(j) =='n') {

						y--;
						if(Math.abs(y) % 2 == 0) {
							x--;
						}
						j++;
						
						
						
					} else if(line.charAt(j) =='s') {
						y++;
						j++;
						
						if(Math.abs(y) % 2 == 0) {
							x--;
						}
						
					} else {
						//Nothing...
						noNOrS = true;
					}
					
					
					if(line.charAt(j) =='e') {
						x++;
						
					} else if(line.charAt(j) =='w') {
						//Do nothing if it's nw or sw:
						if(noNOrS) {
							x--;
						}
						
					} else {
						sopl("ahh");
						exit(1);
					}
					
					
					
				}
				
				String tile =y + "," + x;
				
				if(tiles.containsKey(tile)) {
					sopl("RM: " + tile);
					tiles.remove(tile);
					count--;
				} else {
					sopl("ADD: " + tile);
					tiles.put(tile, 1);
					count++;
				}
				
				
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
