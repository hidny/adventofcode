package probs2019;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;

import aStar.AstarAlgo;
import aStar.AstarNode;
import number.IsNumber;
import utils.Sort;
import utils.graph.*;

public class prob18 {

	public static void main(String[] args) {
		Scanner in;
		try {
			
			String filename = "in2019/prob2019in18.txt";
			in = new Scanner(new File(filename));
			 
			sopl("Very best start: " + veryBestFound);
			 
			sopl(filename);
			
			String line = "";

			ArrayList <String>lines = new ArrayList<String>();
			
			
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				sopl(line);
				
			}
			
			char origMap[][] = new char[lines.size()][lines.get(0).length()];
			

			for(int i=0; i<origMap.length; i++) {
				for(int j=0; j<origMap[0].length; j++) {
					origMap[i][j] = lines.get(i).charAt(j);
					
				}
			}
			
			//Set distances for my optimization...
			setDistanceBetweenPlayerAndGoals(origMap);
			
			//START
			prob18state startState = new prob18state(origMap);
			
			int answer = getBestPath(startState);
			
			sopl("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

	
	public static Hashtable<String, Integer> distances = new Hashtable<String, Integer>();
	
	public static ArrayList<prob18DistWithConstraint> listOfPartiallyBlackingKeysOrDoors;
	
	public static HashSet<String> listOfCompletelyBlackingKeysOrDoors = new HashSet<String>();

	public static void setDistanceBetweenPlayerAndGoals(char origMap[][]) {
		setDistanceBetweenPlayerAndGoals(origMap, 0);
	}
	
	public static void setDistanceBetweenPlayerAndGoals(char origMap[][], int mapIndex) {

		listOfPartiallyBlackingKeysOrDoors = new ArrayList<prob18DistWithConstraint>();
		
		//ugly quad loops...
		for(int i=0; i<origMap.length; i++) {
			for(int j=0; j<origMap[0].length; j++) {
				if((origMap[i][j] >= 'a' && origMap[i][j] <= 'z') || origMap[i][j] == '@') {
					
					for(int i2=i; i2<origMap.length; i2++) {
						
						for(int j2=0; j2<origMap[0].length; j2++) {
							if(i2 == i && j2<j) {
								//Skip to start scanning after an (i, j) location
								j2 = j;
								continue;
							}
							if((origMap[i2][j2] >= 'a' && origMap[i2][j2] <= 'z') || origMap[i2][j2] == '@') {
								
								ArrayList<AstarNode> path = AstarAlgo.astar(new prob18stateAtoB(origMap, j, i, j2, i2), null);
								
								int dist = path.size() - 1;
								
								distances.put(mapIndex + "," + j + "," + i + "," + j2 + "," + i2, dist);
								distances.put(mapIndex + "," + j2 + "," + i2 + "," + j + "," + i, dist);
								
								for(int p=1; p< path.size() - 1; p++) {

									char spot = origMap[((prob18stateAtoB)path.get(p)).coordY][((prob18stateAtoB)path.get(p)).coordX];

									if((spot>= 'A' && spot <= 'Z') || (spot>= 'a' && spot <= 'z')) {
										
										ArrayList<String> singleConstraint = new ArrayList<String>();
										singleConstraint.add(spot + "");
										
										ArrayList<AstarNode> pathConstraint = AstarAlgo.astar(new prob18stateAtoB(origMap, j, i, j2, i2, singleConstraint), null);
										
										int dist2 = -1;
										if(pathConstraint == null) {
											dist2 = -1;
										} else {
											dist2 = pathConstraint.size() - 1;
											
											sopl("ERROR: door only acts as a shortcut... the Advent of code problem didn't have this, so I didn't think about it.");
											
											exit(1);
										}
										
										sopl("Constraint key: " + new prob18DistWithConstraint(dist2, mapIndex, j, i, j2, i2, singleConstraint).toKey());
										sopl("Constraint key: " + new prob18DistWithConstraint(dist2, mapIndex, j2, i2, j, i, singleConstraint).toKey());
										
										listOfPartiallyBlackingKeysOrDoors.add(new prob18DistWithConstraint(dist2, mapIndex, j, i, j2, i2, singleConstraint));
										listOfPartiallyBlackingKeysOrDoors.add(new prob18DistWithConstraint(dist2, mapIndex, j2, i2, j, i, singleConstraint));
										
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		for(int i=0; i<listOfPartiallyBlackingKeysOrDoors.size(); i++) {
			if(listOfPartiallyBlackingKeysOrDoors.get(i).dist == -1) {
				listOfCompletelyBlackingKeysOrDoors.add(listOfPartiallyBlackingKeysOrDoors.get(i).toKey());
				sopl("BLOCKED:" + listOfPartiallyBlackingKeysOrDoors.get(i).toKey());
			}
		}
		
		sopl("-------");
	}
	
	
	
	public static int veryBestFound = Integer.MAX_VALUE;

	public static int getBestPath(prob18state startState) {

		ArrayList<prob18goal> goals = startState.getGoals();
		ArrayList<String> doorsCurrentlyClosedAndKeys = startState.getDoorsAndKeys();
		
		if(goals.size() == 0) {
			return startState.numMovesToGetToStartOfGoal;
		}
		
		ArrayList<Comparable> nextStepStates= new ArrayList<Comparable>();
		
		for(int i=0; i<goals.size(); i++) {

			startState.setGoal(goals.get(i).j, goals.get(i).i);
			
			boolean lockedOut = false;
			for(int j=0; j<doorsCurrentlyClosedAndKeys.size(); j++) {
				
				if(listOfCompletelyBlackingKeysOrDoors.contains(startState.getMapIndex() + "," + startState.coordX + "," + startState.coordY + "," + goals.get(i).j + "," + goals.get(i).i + "," + doorsCurrentlyClosedAndKeys.get(j))) {
					lockedOut = true;
					break;
				}
			}
			
			if(lockedOut == false) {
				int numMoves = distances.get(startState.getMapIndex() + "," + startState.coordX + "," + startState.coordY + "," + goals.get(i).j + "," + goals.get(i).i);
				nextStepStates.add(new prob18state(startState.getStateMapAfterGoal(), startState.numMovesToGetToStartOfGoal + numMoves, startState.getGoalsAfterAttainingSingleGoal(), startState.getDoorsAndKeysAfterAttainingSingleGoal()));
				
			}
			
		}
		
		Object list[] = Sort.sortList(nextStepStates);
		
		int best = 9999999;
		
		for(int i=0; i<list.length; i++) {
			
			if(veryBestFound <= minPossibleNumMovesNeededForPath((prob18state)list[i])) {
				continue;
			}
			
			int cur = getBestPath((prob18state)list[i]);
			
			if(cur < best) {
				
				best = cur;
				if(best < veryBestFound ) {
					veryBestFound = best;
					sopl("very Best found: " + veryBestFound);
				}
			}
			
		}
		
		return best;
	}
	
	public static int minPossibleNumMovesNeededForPath(prob18state startState) { 
		
		ArrayList<prob18goal> goals = startState.getGoals();
		
		//If 2 goals or less, the calculation for the minimum num moves is simple:
		if(goals.size() <= 2) {
			if(goals.size() == 2) {
				int d1 = distances.get(startState.getMapIndex() + "," + startState.coordX + "," + startState.coordY + "," + goals.get(0).j + "," + goals.get(0).i);
				int d2 = distances.get(startState.getMapIndex() + "," + startState.coordX + "," + startState.coordY + "," + goals.get(1).j + "," + goals.get(1).i);
				int d3 = distances.get(startState.getMapIndex() + "," + goals.get(0).j + "," + goals.get(0).i + "," + goals.get(1).j + "," + goals.get(1).i);
				
				return startState.numMovesToGetToStartOfGoal + d3 + Math.min(d1, d2);

			} else if(goals.size() == 1) {
				return startState.numMovesToGetToStartOfGoal + distances.get(startState.getMapIndex() + "," + startState.coordX + "," + startState.coordY + "," + goals.get(0).j + "," + goals.get(0).i);

			} else {
				return startState.numMovesToGetToStartOfGoal;
			}
		}
		
//If more than 2 goals, then find min spanning tree with the algo:

		ArrayList<Comparable> edges = new ArrayList<Comparable>();
		
		//Add edges from the player's current location to the goal nodes:
		for(int i=0; i<goals.size(); i++) {
			//sopl("DEBUG: " + startState.getMapIndex() + "," + startState.coordX + "," + startState.coordY + "," + goals.get(i).j + "," + goals.get(i).i);
			//sopl(startState.getMapIndex());
			int dist = distances.get(startState.getMapIndex() + "," + startState.coordX + "," + startState.coordY + "," + goals.get(i).j + "," + goals.get(i).i);
			
			if(dist == 0) {
				sopl("ERROR #1 dist 0");
				exit(1);
			}
			edges.add(new GraphEdge(i, goals.size(), dist));
			
		}
		
		//Add edges between the goal nodes:
		for(int i=0; i<goals.size(); i++) {
			for(int j=i+1; j<goals.size(); j++) {
					
				int dist = distances.get(startState.getMapIndex() + "," + goals.get(j).j + "," + goals.get(j).i + "," + goals.get(i).j + "," + goals.get(i).i);
				
				if(dist == 0) {
					sopl("ERROR #2 dist 0");
					exit(1);
				}
				edges.add(new GraphEdge(i, j, dist));
			}
		}
		
		
		int minSpanningTreeWeight = GraphUtils.getMinWeightSpanningTree(goals.size() + 1, edges);
		
		return startState.numMovesToGetToStartOfGoal + minSpanningTreeWeight;
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
