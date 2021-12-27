package probs2021;

import java.math.BigInteger;
import java.util.ArrayList;

import aStar.AstarAlgo;
import aStar.AstarNode;
import number.IsNumber;

public class prob23AllInputsUpTheAnte {



	
	//TODO: setup main.
	public static void main(String args[]) {
		
		String puzzle = "...........CDDBACBABBADDACC";
		//String puzzle = "AB..........AAA.BBBCCCCDDDD";
		
		prob23UtilFunctions.DEPTH_HOLE = (puzzle.length() - prob23UtilFunctions.LENGTH_UP) / prob23UtilFunctions.NUM_LETTERS;
		
		
		prob23UtilFunctions.GOAL_INT = new int[puzzle.length()];
		
		for(int i=0; i<prob23UtilFunctions.LENGTH_UP; i++) {
			prob23UtilFunctions.GOAL_INT[i] = prob23PosbEvenFaster.EMPTY;
		}
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<prob23UtilFunctions.DEPTH_HOLE; j++) {
				prob23UtilFunctions.GOAL_INT[prob23UtilFunctions.LENGTH_UP + prob23UtilFunctions.DEPTH_HOLE * i + j] = (i+1);
			}
		}
		
		
		prob23UtilFunctions.neighbours = new int[prob23UtilFunctions.GOAL_INT.length][prob23UtilFunctions.GOAL_INT.length];
		prob23UtilFunctions.setupNeighbours();
		
		prob23UtilFunctions.pathLengths = new int[prob23UtilFunctions.GOAL_INT.length][prob23UtilFunctions.GOAL_INT.length];
		prob23UtilFunctions.setupPathLengthsAndPaths();
		
		

		int start[] = prob23PosbEvenFaster.getIntArray(puzzle);

		
		prob23PosbEvenFaster.setupAdjustments(prob23UtilFunctions.GOAL_INT);

		BigInteger initialCode = prob23PosbEvenFaster.getCodeFromScratch(start);
		
		BigInteger finalCode = prob23PosbEvenFaster.getCodeFromScratch(prob23UtilFunctions.GOAL_INT);
		
		prob23PosbEvenFaster pos = new prob23PosbEvenFaster(initialCode, start);
		
		sopl("Map:");
		sopl(pos.getMap());

		prob23PosbEvenFaster finalMap = new prob23PosbEvenFaster(finalCode, prob23UtilFunctions.GOAL_INT);
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
	
	

	//TODO: setup main.
	public static void mainOld(String args[]) {
		
		String puzzle = "...........CDDBACBABBADDACC";
		//String puzzle = "AB..........AAA.BBBCCCCDDDD";
		
		prob23UtilFunctions.DEPTH_HOLE = (puzzle.length() - prob23UtilFunctions.LENGTH_UP) / prob23UtilFunctions.NUM_LETTERS;
		
		
		prob23UtilFunctions.GOAL_INT = new int[puzzle.length()];
		
		for(int i=0; i<prob23UtilFunctions.LENGTH_UP; i++) {
			prob23UtilFunctions.GOAL_INT[i] = prob23PosbEvenFaster.EMPTY;
		}
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<prob23UtilFunctions.DEPTH_HOLE; j++) {
				prob23UtilFunctions.GOAL_INT[prob23UtilFunctions.LENGTH_UP + prob23UtilFunctions.DEPTH_HOLE * i + j] = (i+1);
			}
		}
		
		
		prob23UtilFunctions.neighbours = new int[prob23UtilFunctions.GOAL_INT.length][prob23UtilFunctions.GOAL_INT.length];
		prob23UtilFunctions.setupNeighbours();
		
		prob23UtilFunctions.pathLengths = new int[prob23UtilFunctions.GOAL_INT.length][prob23UtilFunctions.GOAL_INT.length];
		prob23UtilFunctions.setupPathLengthsAndPaths();
		
		

		int start[] = prob23PosbEvenFaster.getIntArray(puzzle);

		
		prob23PosbEvenFaster.setupAdjustments(prob23UtilFunctions.GOAL_INT);

		BigInteger initialCode = prob23PosbEvenFaster.getCodeFromScratch(start);
		
		BigInteger finalCode = prob23PosbEvenFaster.getCodeFromScratch(prob23UtilFunctions.GOAL_INT);
		
		prob23PosbEvenFaster pos = new prob23PosbEvenFaster(initialCode, start);
		
		sopl("Map:");
		sopl(pos.getMap());

		prob23PosbEvenFaster finalMap = new prob23PosbEvenFaster(finalCode, prob23UtilFunctions.GOAL_INT);
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
