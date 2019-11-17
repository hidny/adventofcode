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

public class prob20 {

	static int LIMIT = 500;
	static int SPACE = 0;
	static int WALL = 1;
	
	static int starti =LIMIT/2;
	static int startj =LIMIT/2;

	//TODO: for print:
	static int mini=starti;
	static int minj=startj;
	
	static int maxi=starti;
	static int maxj=startj;

	static ArrayList <Integer> nextis[] = new ArrayList[1000];
	static ArrayList <Integer> nextjs[] = new ArrayList[1000];
	
	public static void main(String[] args) {
		
		for(int i=0; i<nextjs.length; i++) {
			nextis[i] = new ArrayList<Integer>();
			nextjs[i] = new ArrayList<Integer>();
			
		}
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in20.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
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

			
		
			
			//Wall everything off:
			for(int i=0; i<table.length; i++) {
				for(int j=0; j<table.length; j++) {
					table[i][j] = WALL;
				}
			}
			
			for(int i=starti%2; i<table.length; i+=2) {
				for(int j=startj%2; j<table.length; j+=2) {
					table[i][j] = 0;
				}
			}

			ArrayList <Integer> is = new ArrayList<Integer>();
			ArrayList <Integer> js = new ArrayList<Integer>();
			
			is.add(starti);
			js.add(startj);
			
			table = discoverMaze(table, line, is, js, 0);
			//TODO: print
			
			
			for(int i=0; i<table.length; i++) {
				for(int j=0; j<table[0].length; j++) {
					if(i==starti && j == startj) {
						sop("X");
					} else 
					if(table[i][j] == WALL) {
						sop("#");
					} else if(i%2 == starti%2 && j%2 != startj%2){
						sop("|");
					} else if(j%2 == startj%2 && i%2 != starti%2) {
						sop("-");
					} else {
						sop(".");
					}
				}
				sopl();
			}
			sopl();
			sopl();
			
			
			
			//Graph search
			
			int MARKED = 1;
			
			int tableMark[][] = new int[LIMIT][LIMIT];
			
			tableMark[starti][startj] = MARKED;
			int tableMarkNext[][];
			
			int answer = 0;
			
			boolean keepGoing=true;
			
			
			while( keepGoing) {
				tableMarkNext = new int[LIMIT][LIMIT];
				for(int i=0; i<LIMIT; i++) {
					for(int j=0; j<LIMIT; j++) {
						tableMarkNext[i][j] = tableMark[i][j];
					}
				}
				answer++;
				sopl(answer);
				keepGoing=false;
				for(int i=starti%2; i<table.length; i+=2) {
					for(int j=startj%2; j<table[0].length; j+=2) {
							if(tableMark[i][j] == 0 && i >= 2 && tableMark[i-2][j] > 0 && table[i-1][j] == SPACE) {
								keepGoing=true;
								tableMarkNext[i][j] = answer;
								
							}
							if(tableMark[i][j]== 0 && i<tableMark.length - 2 && tableMark[i+2][j] > 0  && table[i+1][j] == SPACE) {
								keepGoing=true;
								tableMarkNext[i][j] = answer;
								
							}
							if(tableMark[i][j] == 0 && j<tableMark[0].length - 2 && tableMark[i][j+2] > 0  && table[i][j+1] == SPACE) {
								keepGoing=true;
								tableMarkNext[i][j] = answer;
								
							}
							
							if(tableMark[i][j] == 0 && j >= 2 && tableMark[i][j-2] > 0  && table[i][j-1] == SPACE) {
								keepGoing=true;
								tableMarkNext[i][j] = answer;
								
							}
						
					}
				}
				
				tableMark = tableMarkNext;
				
				
				/*
				for(int i=0; i<table.length; i++) {
					for(int j=0; j<table[0].length; j++) {
						if(i==starti && j == startj) {
							sop("X");
						} else 
						if(table[i][j] == WALL) {
							sop("#");
						} else if(i%2 == starti%2 && j%2 != startj%2){
							sop("|");
						} else if(j%2 == startj%2 && i%2 != starti%2) {
							sop("-");
						} else {
							sop(tableMark[i][j] % 10);
						}
					}
					sopl();
				}
				sopl();*/
			}
			
			for(int i=0; i<table.length; i++) {
				for(int j=0; j<table[0].length; j++) {
					if(i==starti && j == startj) {
						sop("X");
					} else 
					if(table[i][j] == WALL) {
						sop("#");
					} else if(i%2 == starti%2 && j%2 != startj%2){
						sop("|");
					} else if(j%2 == startj%2 && i%2 != starti%2) {
						sop("-");
					} else {
						sop(tableMark[i][j] % 10);
					}
				}
				sopl();
			}
			
			
			answer--;
			
			sopl("Answer: " + answer);
			
			answer = 0;
			for(int i=0; i<table.length; i++) {
				for(int j=0; j<table[0].length; j++) {
					if(tableMark[i][j]>= 1000) {
						answer++;
					}
				}
			}
			sopl("Answer part2 " + answer);
			in.close();
			
			/*
			 * Answer: 4406
Answer part2 8468

			 */
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	static int index = 1;
	
	
	public static int[][] discoverMaze(int table[][], String line, ArrayList <Integer> is, ArrayList <Integer> js, int brackets) {
		
		ArrayList <Integer> curis = new ArrayList<Integer>();
		ArrayList <Integer> curjs = new ArrayList<Integer>();
		
		for(int i=0; i<is.size(); i++) {
			curis.add(is.get(i));
			curjs.add(js.get(i));
			
		}
		
		
		for(; index<line.length() -1; index++) {
			sopl(index + " size: " + curis.size());
			char dir = line.charAt(index);
			
			if(dir == 'N') {
				for(int i=0; i<curis.size(); i++) {
					table[curis.get(i) - 1][curjs.get(i)] = SPACE;
					curis.set(i, curis.get(i) - 2);

				}
				
			} else if(dir == 'S') {
				for(int i=0; i<curis.size(); i++) {
					table[curis.get(i) + 1][curjs.get(i)] = SPACE;
					curis.set(i, curis.get(i) + 2);

				}
				
				
			} else if(dir == 'E') {
				
				for(int i=0; i<curis.size(); i++) {
					table[curis.get(i)][curjs.get(i) + 1] = SPACE;
					curjs.set(i, curjs.get(i) + 2);

				}
				
				
			} else if(dir == 'W') {
				
				for(int i=0; i<curis.size(); i++) {
					table[curis.get(i)][curjs.get(i) - 1] = SPACE;
					curjs.set(i, curjs.get(i) - 2);

				}
				
				
			} else if(dir == '(') {
				index++;

				nextis[brackets+1] = new ArrayList<Integer>();
				nextjs[brackets+1] = new ArrayList<Integer>();
				
				table = discoverMaze(table, line, curis, curjs, brackets+1);
				
				
				curis = nextis[brackets+1];
				curjs = nextjs[brackets+1];

				nextis[brackets+1] = new ArrayList<Integer>();
				nextjs[brackets+1] = new ArrayList<Integer>();
				
				//RM DUPS
				for(int i=0; i<curis.size(); i++) {
					for(int j=i+1; j<curis.size(); j++) {
						if(curis.get(i).equals(curis.get(j)) && curjs.get(i).equals(curjs.get(j))) {
							sopl("rm dumps");
							curis.remove(j);
							curjs.remove(j);
							j--;
							sopl("removed");
						}
					}
				}
				
			} else if(dir =='|') {
				
				index++;
				
				nextis[brackets].addAll(curis);
				nextjs[brackets].addAll(curjs);
				
				table = discoverMaze(table, line, is, js, brackets);
				break;
			} else if(dir == ')') {
				

				nextis[brackets].addAll(curis);
				nextjs[brackets].addAll(curjs);

				break;
			}
			
		}
		
		return table;
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
