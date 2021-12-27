package probs2021;

import java.math.BigInteger;
import java.util.ArrayList;

import aStar.AstarAlgo;
import aStar.AstarNode;
import number.IsNumber;

public class prob23AllInputsUpTheAnte {

//Challenge:
//https://www.reddit.com/r/adventofcode/comments/rmryir/2021_day_23_part_12_solve_for_all_possible_start/
//Computer can figure it out in 10 minutes!
	
	//TODO: setup main.
	public static void main(String args[]) {
		
		String initPuzzle = "...........ADDABCBBCBACDACD";
		//String puzzle = "...........CDDBACBABBADDACC";
		//String puzzle = "AB..........AAA.BBBCCCCDDDD";
		
		prob23UtilFunctions.DEPTH_HOLE = (initPuzzle.length() - prob23UtilFunctions.LENGTH_UP) / prob23UtilFunctions.NUM_LETTERS;
		
		
		prob23UtilFunctions.GOAL_INT = new int[initPuzzle.length()];
		
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
		
		
		
		prob23PosbEvenFaster.setupAdjustments(prob23UtilFunctions.GOAL_INT);

		BigInteger finalCode = prob23PosbEvenFaster.getCodeFromScratch(prob23UtilFunctions.GOAL_INT);
		
	
		prob23PosbEvenFaster finalMap = new prob23PosbEvenFaster(finalCode, prob23UtilFunctions.GOAL_INT);
		sopl("Final map:");
		sopl(finalMap.getMap());

		
		int maxEnergy = 0;
		int sumEnergy = 0;
		
		
		String initArray[] = getInitialPermArray(initPuzzle);
		
		//
		
		//***************************
		
		//Check perm valid (1 before 2)
		
		//create puzzle string
		
		int curNumToSkip = 0;
		/*
		sopl("Print perms:");
		for(int perm=0; perm<utilsPE.Permutation.getSmallFactorial(8); perm += curNumToSkip) {
			
			String permTable[] = utilsPE.Permutation.generatePermutation(initArray, perm);
			
			
			
			int firstIndex = getIndexFirstInvalidPair(permTable);
			
			if(firstIndex >= 0) {
				curNumToSkip = (int)utilsPE.Permutation.getSmallFactorial(8 - firstIndex - 1);
				continue;
			} else {
				curNumToSkip = 1;
			}
			
			for(int i=0; i<permTable.length; i++) {
				sop(permTable[i] + ", ");
			}
			sopl();
		}
		exit(0);
		*/
		
		
		int debugNumIterations = 0;
		int numFailed = 0;
		
		for(int perm=0; perm<utilsPE.Permutation.getSmallFactorial(8); perm += curNumToSkip) {
			
			String permTable[] = utilsPE.Permutation.generatePermutation(initArray, perm);
			
			int firstIndex = getIndexFirstInvalidPair(permTable);
			
			if(firstIndex >= 0) {
				curNumToSkip = (int)utilsPE.Permutation.getSmallFactorial(8 - firstIndex - 1);
				continue;
			} else {
				curNumToSkip = 1;
			}
			
			String curPuzzle = setupPuzzle(initPuzzle, permTable);
			//TODO: check if perm valid...
			
			int start[] = prob23PosbEvenFaster.getIntArray(curPuzzle);
			
			BigInteger initialCode = prob23PosbEvenFaster.getCodeFromScratch(start);
			
			prob23PosbEvenFaster pos = new prob23PosbEvenFaster(initialCode, start);
	
			//sopl("Map:");
			//sopl(pos.getMap());
			
			sopl(pos.getAdmissibleHeuristic(null));
			
			//sopl("First call to neighbours:");
			//pos.getNodeNeighbours();
			//sopl("End first call to neighbours");
			
			
			ArrayList<AstarNode> path = AstarAlgo.astar(pos, null);

			prob23PosbEvenFaster.refreshMap();
			
			sopl("-------------------------");
			if(path != null) {
				sopl("Path: " + path.size());
				
				int answer = 0;
				for(int i=0; i<path.size() - 1; i++) {
					answer += path.get(i).getCostOfMove(path.get(i+1));
					
					if(i== 0) {
						sopl("Start:");
						sopl(((prob23PosbEvenFaster)path.get(i)).getMap());
						
					} else {
						//I just want to make the solution look good:
						//sopl(((prob23PosbEvenFaster)path.get(i)).getMapWithMovement((prob23PosbEvenFaster)path.get(i-1)));
					}
					
					//sopl(path.get(i).getCostOfMove(path.get(i+1)));
				}
				
				//Print the last position, just for fun:
				//sopl(((prob23PosbEvenFaster)path.get(path.size() - 1)).getMapWithMovement((prob23PosbEvenFaster)path.get(path.size() - 2)));
				
				sopl("Answer: " + answer);
				
				
				if(maxEnergy < answer) {
					maxEnergy = answer;
				} else if(maxEnergy == answer) {
					sopl("DEBUG: Max energy tied at: " + maxEnergy);
				}
				
				sumEnergy += answer;
			} else {
				numFailed++;
				
				sopl("Failed position:");
				sopl(pos.getMap());
				sopl("Num failed: " + numFailed);
				
			}
		//*****************************

			debugNumIterations++;
			sopl("Num iterations: " + debugNumIterations);
		}
		
		sopl("Max energy: " + maxEnergy);
		sopl("Sum energy: " + sumEnergy);
	}
	
	
	public static String[] getInitialPermArray(String initPuzzle) {
		
		String ret[] = new String[8];
		
		int curAddIndex = 0;
		
		for(int i=0; i<initPuzzle.length(); i++) {
			
			if( i < prob23UtilFunctions.LENGTH_UP) {
				continue;
			}
			
			if((i - prob23UtilFunctions.LENGTH_UP) % prob23UtilFunctions.DEPTH_HOLE == 0
					|| (i - prob23UtilFunctions.LENGTH_UP) % prob23UtilFunctions.DEPTH_HOLE == prob23UtilFunctions.DEPTH_HOLE - 1) {
				ret[curAddIndex] = initPuzzle.charAt(i) + "";
				curAddIndex++;
			}
		}
		
		if(curAddIndex != 8) {
			sopl("Oops!");
			exit(1);
		}
		boolean seen[] = new boolean[4];
		
		for(int i=0; i<ret.length; i++) {
			
			int seenIndex = (int)(ret[i].charAt(0) - 'A');
			
			if(seen[seenIndex] == false) {
				ret[i] = ret[i] + "1";
				seen[seenIndex] = true;
			} else {
				ret[i] = ret[i] + "2";
			}
			
			sop(ret[i] + ", ");
		}
		sopl();
		
		return ret;
		
	}
	
	public static int getIndexFirstInvalidPair(String permTable[]) {
		
		boolean seen[] = new boolean[4];
		
		for(int i=0; i<permTable.length; i++) {

			int seenIndex = (int)(permTable[i].charAt(0) - 'A');
			
			
			if(seen[seenIndex] == false) {
				seen[seenIndex] = true;
				
				if((int)(permTable[i].charAt(1) - '0') > 1) {
					return i;
				}
			}
		}
		
		return -1;
		
	}
	
	public static String setupPuzzle(String initialPuzzle, String perm[]) {
		
		String ret = "";
		
		int permIndexToUse = 0;
		
		for(int i=0; i<initialPuzzle.length(); i++) {
			if(i < prob23UtilFunctions.LENGTH_UP) {
				ret += initialPuzzle.charAt(i) + "";
			
			} else if( (i - prob23UtilFunctions.LENGTH_UP) % prob23UtilFunctions.DEPTH_HOLE == 0
					|| (i - prob23UtilFunctions.LENGTH_UP) % prob23UtilFunctions.DEPTH_HOLE == prob23UtilFunctions.DEPTH_HOLE - 1) {
				ret += perm[permIndexToUse].charAt(0) + "";
				permIndexToUse++;
			} else {
				ret += initialPuzzle.charAt(i) + "";
			}
		}
		
		return ret;
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
