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

public class prob13b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in13.txt"));
			 //in = new Scanner(new File("in2021/prob2021in14.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

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
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			int maxX = 0;
			int maxY = 0;
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				
				String line = lines.get(i);
				String token[] = line.split(",");
				
				if(line.equals("") ) {
					break;
				}
				
				table[pint(token[0])][pint(token[1])] = true;
				
				if(pint(token[0]) > maxY) {
					maxY = pint(token[0]);
				}
				

				if(pint(token[1]) > maxX) {
					maxX = pint(token[1]);
				}
				
				/*int amount = pint(token[1]);
				
				if(token[0].equals("forward")) {
					h+= amount;
					d+= aim*amount;
				} else if(token[0].equals("down")) {
					//d+=amount;
					aim += amount;
					
				} else if(token[0].equals("up")) {
					//d-=amount;
					aim -= amount;
				} else {
					sopl("doh");
				}*/

				
				
			}
			
			//fold along x=655
			int xFold = 655;
			
			sopl(maxX);
			if(maxX - xFold > xFold) {
				boolean newTable[][] = new boolean[table.length][table[0].length - xFold];
				
				
				
			} else {
				boolean newTable[][] = new boolean[table.length][table[0].length - xFold];
				
				for(int i=0; i<table.length; i++) {
					for(int j=0; j<table[0].length; j++) {
						
						if(j>xFold) {
							newTable[i][j-xFold] =true;
						} else {
							newTable[i][j] = true;
						}
					}
				}
				for(int i=0; i<newTable.length; i++) {
					for(int j=0; j<newTable[0].length; j++) {
						
						if(newTable[i][j]) {
							count++;
						}
					}
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
