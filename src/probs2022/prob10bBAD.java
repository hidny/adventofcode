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

public class prob10bBAD {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2022/prob2022in10.txt"));
			 //in = new Scanner(new File("in2022/prob2022in11.txt"));
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

			long strenght = 0;
			int cycle = 0;
			int x = 1;
			
			boolean screen[][] = new boolean[6][40];
			
			
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				sopl(lines.get(i));
				
				line = lines.get(i);
				String token[] = line.split(" ");
				
				if(token[0].equals("addx")) {
					int value = pint(token[1]);
					for(int j=0; j<2; j++) {
						cycle++;
						
						if(x -1 == cycle%40 || x == cycle%40 || x + 1 == cycle%40) {
							int temp = cycle % (6*40);
							if(temp < 0) {
								temp += 6*40;
							}
							sopl("mark: " + temp);
							screen[temp / 40][temp % 40] = true;
						} else {
							int temp = cycle % (6*40);
							screen[temp / 40][temp % 40] = false;
						}
						
						/*if(j == 0 && cycle % 40 == 0) {
							strenght += cycle * x;
							sopl();
							sopl(cycle * x);
							sopl();
						}*/
					}
					x += value;
					
					int temp = x % (240);
					if(temp < 0) {
						temp += 6*40;
					}
					sopl("tmp: " + temp);
					screen[temp / 40][temp % 40] = true;
				} else if(token[0].equals("noop")) {
					cycle++;
					
				}
				
				if(x -1 == cycle%40 || x == cycle%40 || x + 1 == cycle%40) {
					int temp = cycle % (240);
					if(temp < 0) {
						temp += 6*40;
					}
					sopl("cycle: " + cycle);
					sopl("mark: " + temp);
					screen[temp / 40][temp % 40] = true;
				} else {
					int temp = cycle % (6*40);
					screen[temp / 40][temp % 40] = false;
				}
				/*if(cycle % 40 == 0) {
					strenght += cycle * x;
					sopl();
					sopl(cycle * x);
					sopl();
				}*/
				
				sopl(cycle + "--");
			}
			
			sopl();
			for(int i=0; i<screen.length; i++) {
				for(int j=0; j<screen[0].length; j++) {
					if(screen[i][j]) {
						sop("#");
					} else{
						sop(".");
					}
				}
				sopl();
			}
			//29580
			sopl("Answer: " + strenght);
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
