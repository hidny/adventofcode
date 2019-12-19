package probs2019;
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
import probs2018.prob22pos;
import utils.Mapping;
import utils.Sort;

public class prob18 {

	//4672 still too high!
	
	//4908 too high...
	public static void main(String[] args) {
		Scanner in;
		try {
			
			String filename = "in2019/prob2019in18.txt";
			 //in = new Scanner(new File("in2019/prob2019in18.txt"));
			 in = new Scanner(new File(filename));
			 
			 sopl("Very best start: " + veryBestFound);
			 
			sopl(filename);
			
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
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
	
	//public static int veryBestFound = 4676;
	
	public static int veryBestFound = 99999;
	public static int getBestPath(prob18state startState) {

		ArrayList<prob18goal> goals = startState.getGoals();
		ArrayList<String> doorsCurrentlyClosedAndKeys = startState.getDoorsAndKeys();
		
		//sopl("Doors closed: ");
		//for(int i=0; i<doorsCurrentlyClosed.size(); i++) {
		//	sop(doorsCurrentlyClosed.get(i) + ", ");
		//}
		//sopl();
		
		if(goals.size() == 0) {
			return startState.numMovesToGetToStartOfGoal;
		}
		
		
		ArrayList<Comparable> nextStepStates= new ArrayList<Comparable>();
		
		for(int i=0; i<goals.size(); i++) {

			//blocked.add(listOfBlackedDoors.get(i).toKey());
			startState.setGoal(goals.get(i).j, goals.get(i).i);
			
			boolean lockedOut = false;
			for(int j=0; j<doorsCurrentlyClosedAndKeys.size(); j++) {
				
				//sopl("DEBUG trial for goal " + i + ": " + startState.coordX + "," + startState.coordY + "," + goals.get(i).j + "," + goals.get(i).i + "," + doorsCurrentlyClosed.get(j));
				if(blocked.contains(startState.coordX + "," + startState.coordY + "," + goals.get(i).j + "," + goals.get(i).i + "," + doorsCurrentlyClosedAndKeys.get(j))) {
					lockedOut = true;
					break;
				}
			}
			
			if(lockedOut == false) {
				int numMoves = distances.get(startState.coordX + "," + startState.coordY + "," + goals.get(i).j + "," + goals.get(i).i);
				nextStepStates.add(new prob18state(startState.getStateMapAfterGoal(), startState.numMovesToGetToStartOfGoal + numMoves, startState.getGoalsAfterAttainingSingleGoal(), startState.getDoorsAndKeysAfterAttainingSingleGoal()));
				
			}
			
		}
		
		
		//if(startState.numMovesToGetToStartOfGoal > 0) {
		//	sopl("debug");
		//}
		Object list[] = Sort.sortList(nextStepStates);
		
		int best = 9999999;
		
		for(int i=0; i<list.length; i++) {
			
			//TODO: put it back... took away for testing
			if(veryBestFound <= minNumMovesNeededForPathGivenCurrentTrailSpanningTree((prob18state)list[i])) {
				//sopl("Denied!");
				continue;
			}
			
			
			//sopl("Trying: " + (prob18state)list[i]);
			
			int cur = getBestPath((prob18state)list[i]);
			
			if(cur < best) {
				
				best = cur;
				if(best < veryBestFound ) {
					veryBestFound = best;
					sopl("very Best found: " + veryBestFound);
				}
			}
			
		}
		
		//sopl("test list");
		
		//sopl(best);
		return best;
	}
	
	public static int minNumMovesNeededForPathGivenCurrentTrailSpanningTree(prob18state startState) { 
		
		ArrayList<prob18goal> goals = startState.getGoals();
		
		//If 1 more goal, then whatever
		if(goals.size() <= 1) {
			return 0;
		}
		
		int smallestDist[] = new int[goals.size() + 1];
		for(int i=0; i<smallestDist.length; i++) {
			smallestDist[i] = 100000;
		}
		
		
		ArrayList<Comparable> edges = new ArrayList<Comparable>();
		
		//TODO:
		for(int i=0; i<goals.size(); i++) {
			int dist = distances.get(startState.coordX + "," + startState.coordY + "," + goals.get(i).j + "," + goals.get(i).i);
			
			if(dist == 0) {
				sopl("DEBUG dist 1");
				exit(1);
			}
			edges.add(new prob18edge(i, goals.size(), dist));
			
		}
		
		for(int i=0; i<goals.size(); i++) {
			for(int j=i+1; j<goals.size(); j++) {
					
				int dist = distances.get(goals.get(j).j + "," + goals.get(j).i + "," + goals.get(i).j + "," + goals.get(i).i);
				
				if(dist == 0) {
					sopl("DEBUG dist 2");
					exit(1);
				}
				edges.add(new prob18edge(i, j, dist));
			}
		}
		
		
		//TODO: TEST
		Object sortedEdges[] = utils.Sort.sortList(edges);
		
		boolean taken[] = new boolean[goals.size() + 1];
		
		int numEdgesNeeded = taken.length - 1;
		
		int numEdgesUsed = 0;
		
		int minSpanningTreeWeight = 0;
		
		boolean connections[][] = new boolean[taken.length][taken.length];
		
		for(int i=0; numEdgesUsed < numEdgesNeeded; i++) {
			prob18edge tmp = (prob18edge)sortedEdges[i];
			if(connections[tmp.getI()][tmp.getJ()] == false) {
				minSpanningTreeWeight += tmp.getWeigth();
				
				//START Adjust connections:
				connections[tmp.getI()][tmp.getJ()] = true;
				connections[tmp.getJ()][tmp.getI()] = true;
				
				for(int x=0; x<connections.length; x++) {
					if(connections[tmp.getI()][x] == true) {
						connections[tmp.getJ()][x] = true;
						connections[x][tmp.getJ()] = true;
						
					} else if(connections[tmp.getJ()][x] == true) {
						connections[tmp.getI()][x] = true;
						connections[x][tmp.getI()] = true;
					}
				}
				//END Adjust connections
				
				if(taken[tmp.getI()] == false) {
					taken[tmp.getI()] = true;
					
				}
				
				if(taken[tmp.getJ()] == false) {
					taken[tmp.getJ()] = true;
				}

				
				numEdgesUsed++;
			}
			
		}
		//END TODO TEST
		
		
		return startState.numMovesToGetToStartOfGoal + minSpanningTreeWeight;
	}
	
	
	

	public static Hashtable<String, Integer> distances = new Hashtable<String, Integer>();
	
	public static ArrayList<prob18DistWithConstraint> listOfBlackedDoors = new ArrayList<prob18DistWithConstraint>();
	

	public static HashSet<String> blocked = new HashSet<String>();
	
	public static void setDistanceBetweenPlayerAndGoals(char origMap[][]) {
		
		//ugly quad loops...
		for(int i=0; i<origMap.length; i++) {
			for(int j=0; j<origMap[0].length; j++) {
				if((origMap[i][j] >= 'a' && origMap[i][j] <= 'z') || origMap[i][j] == '@') {
					
					for(int i2=i; i2<origMap.length; i2++) {
						if(i2 == 3) {
							sopl("Debug i2");
						}
						for(int j2=0; j2<origMap[0].length; j2++) {
							if(i2 == i && j2<j) {
								j2 = j;
								continue;
							}
							if((origMap[i2][j2] >= 'a' && origMap[i2][j2] <= 'z') || origMap[i2][j2] == '@') {
								
								ArrayList<AstarNode> path = AstarAlgo.astar(new prob18stateAtoB(origMap, j, i, j2, i2), null);
								
								int dist = path.size() - 1;
								
								
								
								//sopl(j + "," + i + "," + j2 + "," + i2);
								//sopl(j2 + "," + i2 + "," + j + "," + i);
								
								distances.put(j + "," + i + "," + j2 + "," + i2, dist);
								distances.put(j2 + "," + i2 + "," + j + "," + i, dist);
								
								
								int numDoorContraintDEBUG = 0;
								//TODO: consider doors...
								for(int p=1; p< path.size() - 1; p++) {
									char spot = origMap[((prob18stateAtoB)path.get(p)).coordY][((prob18stateAtoB)path.get(p)).coordX] ;
									if((spot>= 'A' && spot <= 'Z') || (spot>= 'a' && spot <= 'z')) {
										numDoorContraintDEBUG++;
										if(numDoorContraintDEBUG >= 2) {
											sopl(j + "," + i + "," + j2 + "," + i2 + " has " + numDoorContraintDEBUG + " door/key constraints");
										}
										ArrayList<String> singleConstraint = new ArrayList<String>();
										singleConstraint.add(spot + "");
										
										ArrayList<AstarNode> pathConstraint = AstarAlgo.astar(new prob18stateAtoB(origMap, j, i, j2, i2, singleConstraint), null);
										
										int dist2 = -1;
										if(pathConstraint == null) {
											dist2 = -1;
										} else {
											dist2 = pathConstraint.size() - 1;
											
											sopl("Constraint key ERROR: " + new prob18DistWithConstraint(dist2, j, i, j2, i2, singleConstraint).toKey());
											
											//No door delays trip ever... huh...
											sopl("pathConstraint: " + dist2);
											exit(1);
										}
										
										sopl("Constraint key: " + new prob18DistWithConstraint(dist2, j, i, j2, i2, singleConstraint).toKey());
										sopl("Constraint key: " + new prob18DistWithConstraint(dist2, j2, i2, j, i, singleConstraint).toKey());
										
										listOfBlackedDoors.add(new prob18DistWithConstraint(dist2, j, i, j2, i2, singleConstraint));
										listOfBlackedDoors.add(new prob18DistWithConstraint(dist2, j2, i2, j, i, singleConstraint));
										
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		for(int i=0; i<listOfBlackedDoors.size(); i++) {
			if(listOfBlackedDoors.get(i).dist == -1) {
				blocked.add(listOfBlackedDoors.get(i).toKey());
				sopl("BLOCKED:" + listOfBlackedDoors.get(i).toKey());
			}
		}
		
		sopl("-------");
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
