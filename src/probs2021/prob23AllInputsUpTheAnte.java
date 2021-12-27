package probs2021;

import java.math.BigInteger;
import java.util.ArrayList;

import aStar.AstarAlgo;
import aStar.AstarNode;
import number.IsNumber;

public class prob23AllInputsUpTheAnte {



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
	
	//TODO: setup main.
	public static void main(String args[]) {
		
		String puzzle = "...........CDDBACBABBADDACC";
		//String puzzle = "AB..........AAA.BBBCCCCDDDD";
		
		DEPTH_HOLE = (puzzle.length() - LENGTH_UP) / NUM_LETTERS;
		
		
		GOAL_INT = new int[puzzle.length()];
		
		for(int i=0; i<LENGTH_UP; i++) {
			GOAL_INT[i] = prob23PosbEvenFaster.EMPTY;
		}
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<DEPTH_HOLE; j++) {
				GOAL_INT[LENGTH_UP + DEPTH_HOLE * i + j] = (i+1);
			}
		}
		
		
		neighbours = new int[GOAL_INT.length][GOAL_INT.length];
		setupNeighbours();
		
		pathLengths = new int[GOAL_INT.length][GOAL_INT.length];
		setupPathLengthsAndPaths();
		
		

		int start[] = prob23PosbEvenFaster.getIntArray(puzzle);

		
		prob23PosbEvenFaster.setupAdjustments(GOAL_INT);

		BigInteger initialCode = prob23PosbEvenFaster.getCodeFromScratch(start);
		
		BigInteger finalCode = prob23PosbEvenFaster.getCodeFromScratch(GOAL_INT);
		
		prob23PosbEvenFaster pos = new prob23PosbEvenFaster(initialCode, start);
		
		sopl("Map:");
		sopl(pos.getMap());

		prob23PosbEvenFaster finalMap = new prob23PosbEvenFaster(finalCode, GOAL_INT);
		sopl("Final map:");
		sopl(finalMap.getMap());
		
		
		sopl(pos.getAdmissibleHeuristic(null));
		
		pos.getNodeNeighbours();
		
		
		
		ArrayList<AstarNode> path = AstarAlgo.astar(pos, null);
		
		sopl("-------------------------");
		sopl("Path: " + path.size());
		
		int answer = 0;
		for(int i=0; i<path.size() - 1; i++) {
			answer += path.get(i).getCostOfMove(path.get(i+1));
			
			if(i== 0) {
				sopl(((prob23PosbEvenFaster)path.get(i)).getMap());
				
			} else {
				//I just want to make the solution look good:
				sopl(((prob23PosbEvenFaster)path.get(i)).getMapWithMovement((prob23PosbEvenFaster)path.get(i-1)));
			}
			
			sopl(path.get(i).getCostOfMove(path.get(i+1)));
		}
		
		//Print the last position, just for fun:
		sopl(((prob23PosbEvenFaster)path.get(path.size() - 1)).getMapWithMovement((prob23PosbEvenFaster)path.get(path.size() - 2)));
		
		sopl("Answer: " + answer);
	}
	
	
	

	
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
