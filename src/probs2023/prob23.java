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

public class prob23 {

	//4434
	
	//day1 part 1
	//2:38.01
	
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
						
					} else if(c == '^') {
						table[i][j] = 10;
					} else if(c == '>') {
						table[i][j] = 11;
						
					} else if(c == 'v') {
						table[i][j] = 12;
						
					} else if(c == '<') {
						table[i][j] = 13;
						
					} else {
						exit(1);
					}
					//table[i][j] = 
				}
				
				
			}
			
			prob23obj.table= table;
			prob23obj.setNeighbours();
			
			prob23obj start = prob23obj.map[startI][startJ];

			cur = getAnwer(start);

			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static int getAnwer(prob23obj start) {
		
		boolean explored[][] = new boolean[prob23obj.map.length][prob23obj.map[0].length];
		return getAnwer(start, explored);
	}
	
	public static int getAnwer(prob23obj current, boolean explored[][]) {
		
		sopl(current.i + "," + current.j);
		if(current.reachedGoal()) {
			return 0;
		}
		
		ArrayList<prob23obj> ret =  current.getNodeNeighbours();
		
		explored[current.i][current.j] = true;
		int curMax = -1;
		for(int i=0; i<ret.size(); i++) {
			
			if(explored[ret.get(i).i][ret.get(i).j] == false) {
				int cur = getAnwer(ret.get(i), explored) + 1;

				if(cur > curMax) {
					curMax = cur;
				}
			
			}
			
		}

		explored[current.i][current.j] = false;
		if(curMax == -1) {
			return -10;
		}
		
		return curMax;
		
	}
	//4434
	
	public static boolean curBest;
	

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
