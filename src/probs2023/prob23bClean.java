package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import aStar.AstarNode;
import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob23bClean {

	//4434
	
	//day1 part 1
	//2:38.01
	
	//getting the answer takes 10 mins
	//confirming the answer because the program is finished takes 1 hour.
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in23.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
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
			long cur = 0L;
			
			int table[][] = new int[lines.size()][lines.get(0).length()];
			
			int startI = -1;
			int startJ = -1;
			
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				for(int j=0; j<line.length(); j++) {
					
					char c = line.charAt(j);
					if(c == '.' && (i == 0 || j == 0)) {
							table[i][j] = 0;
							startI = i;
							startJ = j;
					
					} else if(c == '.') {
						table[i][j] = 0;
					} else if(c == '#') {
						table[i][j] = 1;
					
					//Slopes are just black space:
					} else if(c == '^') {
						table[i][j] = 0;
					} else if(c == '>') {
						table[i][j] = 0;
						
					} else if(c == 'v') {
						table[i][j] = 0;
						
					} else if(c == '<') {
						table[i][j] = 0;
						
					} else {
						exit(1);
					}
				}
				
				
			}
			
			prob23objClean.table= table;
			prob23objClean.setNeighbours();
			
			prob23objClean start = prob23objClean.map[startI][startJ];

			getAnwer(start);

			cur = maxDepth - 1;
			
			sopl("Answer: " + cur);
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				for(int j=0; j<line.length(); j++) {
					
					if(curBest[i][j]) {
						sop("O");
					} else {
						sop(line.charAt(j));
					}
				}
				sopl();
			}
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static void getAnwer(prob23objClean start) {

		int orderMap[][] = new int[prob23objClean.map.length][prob23objClean.map[0].length];
		boolean explored[][] = new boolean[prob23objClean.map.length][prob23objClean.map[0].length];
		getAnwer(start, explored);
	}
	
	public static int maxDepth = 0;
	
	
	public static void getAnwer(prob23objClean start, boolean explored[][]) {
		
		prob23objClean current = start;
		int curNum = 1;
		
		orderToI.put(curNum, current.i);
		orderToJ.put(curNum, current.j);
		
		int nextIndexToTry[][] = new int[prob23objClean.map.length][prob23objClean.map[0].length];
		
		OUTER_LOOP:
		while(true) {
			
			//sopl("Start iteration: " + current.i + "," + current.j + ", " + nextIndexToTry[current.i][current.j]);
			
			
			if(current.reachedGoal()) {
				
				if(curNum > maxDepth) {
					
					curBest = new boolean[explored.length][explored[0].length];
					for(int i=0; i<explored.length; i++) {
						for(int j=0; j<explored[0].length; j++) {
							curBest[i][j] = explored[i][j]; 
						}
					}
					maxDepth = curNum;
					
					sopl("maxDepth so far: " + curNum);
				}
				
				nextIndexToTry[current.i][current.j]++;
			}
			
			while(current.reachedGoal() || nextIndexToTry[current.i][current.j] >= current.getNodeNeighboursQuick().size()) {
				nextIndexToTry[current.i][current.j] = 0;

				orderToI.remove(curNum);
				orderToJ.remove(curNum);
				
				curNum--;

				explored[current.i][current.j] = false;

				//sopl("getPrev " + curNum);
				
				//sopl(current.i + "," + current.j);
				
				if(current == start) {
					break OUTER_LOOP;
				}
				current = prob23objClean.map[orderToI.get(curNum)][orderToJ.get(curNum)];
				//sopl(current.i + "," + current.j);

				
				if(curNum <= 1000 && curNum % 100 == 0) {
					sopl("curNum = " + curNum + ": " + current.i + "," + current.j);
				}
				//sopl(curNum);
			}
			
			ArrayList<prob23objClean> ret =  current.getNodeNeighboursQuick();
			
			explored[current.i][current.j] = true;

			for(int i=0; i<ret.size(); i++) {
				
				if(nextIndexToTry[current.i][current.j] == i) {
					
					nextIndexToTry[current.i][current.j]++;
					
					if(explored[ret.get(i).i][ret.get(i).j] == false) {
						
						
						curNum++;
						current = ret.get(i);
						
						if(orderToI.containsKey(curNum)) {
							sopl("doh hashtable...");
							exit(1);
						}
						orderToI.put(curNum, current.i);
						orderToJ.put(curNum, current.j);
						
						explored[current.i][current.j] = true;
					
					}
				}

				
			}
			
			
		}
		
	}
	
	public static Hashtable<Integer, Integer> orderToI =  new Hashtable<Integer, Integer>();
	public static Hashtable<Integer, Integer> orderToJ =  new Hashtable<Integer, Integer>();
	
	public static prob23objClean getPrev(int curNum, int orderMap[][]) {
		
		if(curNum <=0) {
			sopl("DOH! Get prev");
			exit(1);
		}
		for(int i=0; i<orderMap.length; i++) {
			for(int j=0; j<orderMap[0].length; j++) {
				if(curNum == orderMap[i][j]) {
					return prob23objClean.map[i][j];
				}
			}
		}
		
		exit(1);
		return null;
	}
	//4434
	
	public static boolean curBest[][];
	

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
