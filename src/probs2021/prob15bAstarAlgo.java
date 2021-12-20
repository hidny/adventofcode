package probs2021;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import aStar.AstarAlgo;
import aStar.AstarNode;
import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob15bAstarAlgo {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in15.txt"));
			//in = new Scanner(new File("in2021/prob2021in16.txt"));
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

			prob15bAstarPos map[][] = new prob15bAstarPos[5 * lines.size()][ 5 * lines.get(0).length()];
			int goali = map.length - 1;
			int goalj = map[0].length - 1;
			
			
			prob15bAstarPos start = new prob15bAstarPos(0, 0, map, lines, goali, goalj);
			
			ArrayList<AstarNode> path = AstarAlgo.astar(start, null);

			long answer = 0;
			
			for(int i=0; i<path.size() - 1; i++) {
				answer += path.get(i).getCostOfMove( path.get(i + 1));
			}
			
			
			boolean trace[][] = new boolean[map.length][map[0].length];
			
			

			int maxI=0;
			int maxJ=0;
			for(int i=0; i<path.size(); i++) {
				
				int curI = ((prob15bAstarPos)path.get(i)).getI();
				int curJ = ((prob15bAstarPos)path.get(i)).getJ();
				trace[curI][curJ] = true;
				
				if(curI < maxI) {
					sopl("Check  (" + curI + ", " + curJ + ")");
				} else if(curI > maxI) {
					maxI = curI;
				}
				

				if(curJ < maxJ) {
					sopl("Check  (" + curI + ", " + curJ + ")");
				} else if(curJ > maxJ) {
					maxJ = curJ;
				}
			}
			
			sopl();
			
			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[0].length; j++) {
					
					if(trace[i][j]) {
						sop("*");
					} else {

						if(map[i][j] != null) {
							sop(map[i][j].getCostOfMove(map[i][j]));
						}
					}
				}
				sopl();
			}
			sopl();
			
			
			sopl();
			sopl("Answer: " + answer);
			
			//2970
			//sopl("Answer: " + riskLevel[riskLevel.length - 1][riskLevel[0].length-1]);
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
