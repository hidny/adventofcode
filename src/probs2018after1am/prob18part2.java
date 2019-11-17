package probs2018after1am;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob18part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in18.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 50;
			int table[][] = new int[LIMIT][LIMIT];
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			int START =10000;
			
			int repeat = -1;
			int check = -1;
			boolean foundRepeat = false;;
			int numRepeatCycle = -1;

			table = new int[lines.size()][line.length()];
			
			int OPEN = 0;
			int TREE = 1;
			int LUMBER = 2;
			
			int origCount = 0;
			for(int i=0; i<lines.size(); i++) {
				line = lines.get(i);
				for(int j=0; j<line.length(); j++) {
					if(line.charAt(j) == '#') {
						table[i][j] = LUMBER;
					} else if(line.charAt(j) == '|') {
						table[i][j] = TREE;
						
					}
				}
			}
			
			
			ArrayList resources = new ArrayList<Integer>();
			
			//TODO: loop
			
			//20 mod 28
			for(int minute = 0; true; minute++) {
				
				int tableNext[][] = new int[table.length][table[0].length];
				
				for(int i=0; i<table.length; i++) {
					for(int j=0; j<table[0].length; j++) {
						int numOpen = 0;
						int numTrees =0;
						int numLumber = 0;
						
						for(int m=i-1; m<=i+1; m++) {
							if(m<0 || m >= table.length) {
								continue;
							}
							
							
							for(int n=j-1; n<=j+1; n++) {
								if(n<0 || n >= table[0].length) {
									continue;
								}
								if(n == j && m == i) {
									continue;
								}
								
								if(table[m][n] == OPEN) {
									numOpen++;
								} else if(table[m][n] == TREE) {
									numTrees++;
								} else if(table[m][n] == LUMBER) {
									numLumber++;
								} else {
									sopl("??");
									exit(1);
								}
								
								
							}
						}
						
						//END of count
						//TODO: conditions
						
						if(table[i][j] == OPEN) {
							if( numTrees >= 3) {
								tableNext[i][j] = TREE;
							} else {
								tableNext[i][j] = OPEN;
							}
						} else if(table[i][j] == TREE) {
							if( numLumber >= 3) {
								tableNext[i][j] = LUMBER;
							} else {
								tableNext[i][j] = TREE;
							}
						} else if(table[i][j] == LUMBER){
							if(numLumber >= 1 && numTrees >= 1) {
								tableNext[i][j] = LUMBER;
							} else {
								tableNext[i][j] = OPEN;
							}
						} else {
							sopl("Huh?");
							exit(1);
						}
					}
				}
				table = tableNext;
				

				int numTrees =0;
				int numLumber = 0;
				
				for(int i=0; i<table.length; i++) {
					for(int j=0; j<table[0].length; j++) {
						if(table[i][j] == TREE) {
							numTrees++;
						} else if(table[i][j] == LUMBER) {
							numLumber++;
						}
					}
				}
				
				if(minute == 10000) {
					repeat = numTrees * 10000 + numLumber;
				} else if(minute > 10000 && foundRepeat == false) {
					
					for(int i=0; i<table.length; i++) {
						for(int j=0; j<table[0].length; j++) {
							if(table[i][j] == TREE) {
								sop("|");
							} else if(table[i][j] == LUMBER) {
								sop("#");
							} else {
								sop(".");
							}
						}
						sopl();
					}
					sopl();
					sopl();
					
					//check isn't precise, but it worked.
					check =  numTrees * 10000 + numLumber;
					if(check == repeat) {
						sopl("repeats every " + (minute-START) + " minutes");

						foundRepeat = true;
						numRepeatCycle = (minute-START);
					}
				} else if(foundRepeat) {
					if((minute + 1 ) % numRepeatCycle == (1000000000) % numRepeatCycle) {
						sopl();
						sopl("Answer: " + (numTrees * numLumber));
						break;
					}
				}
				
				sopl("minute: " + (minute + 1) + ": " + (numTrees * numLumber));
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
