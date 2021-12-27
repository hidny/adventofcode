package probs2021;

import java.math.BigInteger;
import java.util.ArrayList;

import aStar.AstarAlgo;
import aStar.AstarNode;
import number.IsNumber;

public class prob23UtilFunctions {



	//TODO: move to main class:
	//public static String GOAL = "...........AAAABBBBCCCCDDDD";
	//public static String GOAL = "...........AABBCCDD";
	//TODO RM:
	public static int GOAL_INT[];
	
	
	public static int neighbours[][];
	
	public static int pathLengths[][];
	
	public static int LENGTH_UP = 11;
	public static int DEPTH_HOLE = -1;
	
	public static int NUM_LETTERS = 4;
	
	
	public static void setupNeighbours() {
		addNeighbour(0, 1);
		addNeighbour(1, 2);
		
		for(int i=0; i<4; i++) {
			addNeighbour(2+2*i, LENGTH_UP + DEPTH_HOLE*i);
			addNeighbour(2+2*i, 3+2*i);
		
			addNeighbour(3+2*i, 4+2*i);
			
			for(int j=0; j<DEPTH_HOLE-1; j++) {
				addNeighbour(LENGTH_UP + DEPTH_HOLE*i + j, LENGTH_UP+ DEPTH_HOLE*i + j + 1);
			}
		}
		

		addNeighbour(9, 10);
		
		
	}
	
	
	public static void setupPathLengthsAndPaths() {

		setupNeighbours();
		
		paths = new  ArrayList[pathLengths.length][pathLengths.length];
		
		for(int i=0; i<pathLengths.length; i++) {
			for(int j=0; j<pathLengths.length; j++) {
				if(i == j) {
					pathLengths[i][j] = 0;
				} else {
					
					ArrayList<Integer>ret = getPath(i, j);
					
					sop(i + " ");
					for(int i2=0; i2<ret.size(); i2++) {
						sop(ret.get(i2) + " ");
					}
					sopl();
					pathLengths[i][j] = ret.size();
				}
			}
		}
		
		for(int i=0; i<pathLengths.length; i++) {
			for(int j=0; j<pathLengths.length; j++) {
				sop(pathLengths[i][j] + "  ");
				if(pathLengths[i][j] < 10) {
					sop(" ");
				}
				
			}
			sopl();
		}
	}
	
	
	public static boolean explored[];
	
	public static ArrayList<Integer>[][]paths;
	
	public static ArrayList<Integer> getPathFast(int a, int b) {
		return paths[a][b];
	}
	
	public static ArrayList<Integer> getPath(int a, int b) {
		
		//sopl(a + " to " + b);
		explored = new boolean[GOAL_INT.length];
		
		ArrayList<Integer> temp = getPathContinued(a, b);
		
		if(paths[a][b] == null) {
			paths[a][b] = temp;
		}
		return temp;
	}
	
	private static ArrayList<Integer> getPathContinued(int a, int b) {
		
		//sopl(a + ", " + b);
		if(a == b) {
			return  new ArrayList<Integer>();
		}
		//BREADTH search:
		
		ArrayList<Integer> ret = null;
		
		
		explored[a] = true;
		
		for(int i=0; i<neighbours.length; i++) {
			
			if(explored[i] == false) {
			
				if(neighbours[a][i] == 1) {
					ArrayList<Integer> tmp = getPathContinued(i, b);
					
					if(tmp != null) {
						
						if(ret == null) {
							ret = new ArrayList<Integer>();
							ret.add(i);
							ret.addAll(tmp);
						} else if(tmp.size() + 1 < ret.size()) {
							ret = new ArrayList<Integer>();
							ret.add(i);
							ret.addAll(tmp);
						}
						
					}
				}
			}
		}
		
		return ret;
	}
	
	
	public static void addNeighbour(int i, int j) {
		if(i == j) {
			sopl("oops");
			exit(1);
		} else {
			neighbours[i][j] = 1;
			neighbours[j][i] = 1;
			
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
