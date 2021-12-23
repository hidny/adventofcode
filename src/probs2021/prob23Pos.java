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

public class prob23Pos implements AstarNode {

	
	String curPos = "";
	public prob23Pos(String pos) {
		curPos = pos;
		
	}
	
	public static final String GOAL = "...........AABBCCDD";
	
	private Hashtable<String, prob23Pos> map = new Hashtable<String, prob23Pos>();

	public void getDist(int i1, int i2) {
		
	}
	
	
	public static int neighbours[][] = new int[GOAL.length()][GOAL.length()];
	public static int LENGTH_UP = 11;
	
	public static int pathLengths[][] = new int[GOAL.length()][GOAL.length()];

	public static void main(String args[]) {
		
		
		getPathLengths();
		
		String curLocation = "...........BACDBCDA";
		//String curLocation = "...........CBAABDDC";
		sopl(getDistanceToGoal(curLocation, 'A'));
		
		
		prob23Pos pos = new prob23Pos(curLocation);
		
		sopl(pos.getAdmissibleHeuristic(null));
		
		pos.getNodeNeighbours();
		
		ArrayList<AstarNode> path = AstarAlgo.astar(pos, null);
		sopl("Path: " + path.size());
		
		int answer = 0;
		for(int i=0; i<path.size() - 1; i++) {
			answer += path.get(i).getCostOfMove(path.get(i+1));
			sopl(((prob23Pos)path.get(i)).curPos);
			sopl(path.get(i).getCostOfMove(path.get(i+1)));
		}
		sopl("Answer: " + answer);
	}
	
	
	public static void getPathLengths() {

		setupNeighbours();
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
	
	public static ArrayList<Integer> getPath(int a, int b) {
		
		//sopl(a + " to " + b);
		explored = new boolean[GOAL.length()];
		
		return getPathContinued(a, b);
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
	
	
	public static void setupNeighbours() {
		addNeighbour(0, 1);
		addNeighbour(1, 2);
		
		for(int i=0; i<4; i++) {
			addNeighbour(2*i+2, LENGTH_UP + 2*i);
			addNeighbour(2*i+2, 2*i+3);
		
			addNeighbour(2*i+3, 2*i+4);
			
			addNeighbour(LENGTH_UP + 2*i, LENGTH_UP+ 2*i + 1);
		}
		

		addNeighbour(9, 10);
		
		
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
	
	@Override
	public long getAdmissibleHeuristic(AstarNode goal) {
		// TODO Auto-generated method stub
		
		int sum =0;
		int energyCost[] = new int[4];
		energyCost[0] = 1;
		for(int i=1; i<4; i++) {
			energyCost[i] = 10 * energyCost[i-1];
		}
		
		for(int letterIndex=0; letterIndex<4; letterIndex++) {
			char letter = (char)('A' + letterIndex);
			
			sum += energyCost[letterIndex] * getDistanceToGoal(this.curPos, letter);
			
			
			if(this.curPos.charAt(LENGTH_UP + 2*letterIndex) == letter
					&& this.curPos.charAt(LENGTH_UP + 2*letterIndex + 1) != letter) {
				//Have to clear the way:
				sum += 4* energyCost[letterIndex];
			}
		}
		
		
		
		if(sum == 0) {
			return -1;
		}
		return sum;
		
	}
	

	public static int getDistanceToGoal(String curLocation, char letter) {
		return getDistanceToNeighbourOrGoal(curLocation, letter, GOAL);
	}
	

	public static int getDistanceToNeighbourOrGoal(String curLocation, char letter, String otherLocation) {
		//1: first letter to first goal, 2nd to second
		
		//2: second letter to first goal, 2nd to first
		
		int firstLetter = curLocation.indexOf(letter);
		int secondLetter =  curLocation.lastIndexOf(letter);
		
		int firstGoal = otherLocation.indexOf(letter);
		int secondGoal =  otherLocation.lastIndexOf(letter);
		
		int way1 = pathLengths[firstLetter][firstGoal] + pathLengths[secondLetter][secondGoal];
		int way2 = pathLengths[secondLetter][firstGoal] + pathLengths[firstLetter][secondGoal];
		
		
		if(Math.min(way1, way2) > 0) {
			
		}
		
		return Math.min(way1, way2);
	}
	
	public static int count = 0;
	
	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		// TODO Auto-generated method stub
		
		ArrayList<AstarNode> ret = new ArrayList<AstarNode>();
		
		for(int i=0; i<this.curPos.length(); i++) {
			if(this.curPos.charAt(i) == '.') {
				continue;
			}
			
			char letter = this.curPos.charAt(i);
			int letterIndex = (int)(letter - 'A');
			
			int possibleLandings[] = new int[GOAL.length()];
			if(i < LENGTH_UP) {
				//Up to down
				possibleLandings[LENGTH_UP + 2*letterIndex] = 1;
				possibleLandings[LENGTH_UP + 2*letterIndex + 1] = 1;
				
				if(this.curPos.charAt(LENGTH_UP + 2*letterIndex + 1) != '.'
						&& this.curPos.charAt(LENGTH_UP + 2*letterIndex + 1) != letter) {
					//sopl("nope!");
					continue;
				}
				
			} else {
				//Down to up
				possibleLandings = new int[GOAL.length()];
				for(int j=0; j<LENGTH_UP; j++) {
					possibleLandings[j] = 1;
					
				}
				for(int j=0; j<4; j++) {
					possibleLandings[2+2*j] = 0;
				}
				
				if(i == LENGTH_UP + 2*letterIndex + 1) {
					continue;
				} else if(i == LENGTH_UP + 2*letterIndex) {
					
					if(this.curPos.charAt(i + 1) == letter) {
						continue;
					} else if(this.curPos.charAt(i + 1) == '.') {
						continue;
					}
					
				}
				
				
			}
			
			

			for(int j=0; j<possibleLandings.length; j++) {

				if(this.curPos.charAt(j) == '.' && possibleLandings[j] == 1) {

					ArrayList<Integer> path = getPath(i, j);
					
					boolean couldDoIt = true;
					for(int k=0; k<path.size()-1; k++) {
						if(this.curPos.charAt(path.get(k)) != '.') {
							couldDoIt = false;
						}
					}

					if(couldDoIt) {
						String newLocation = swap(this.curPos, i, j);
						
						prob23Pos nextNeighbour = null;
						//TODO
						if(map.containsKey(newLocation)) {
							//sopl("Already found");
							nextNeighbour = map.get(newLocation);
							
							//exit(1);
						} else {
							nextNeighbour = new prob23Pos(newLocation);
							map.put(newLocation, nextNeighbour);
							count++;
							
							if(count % 10000 == 0) {
								sopl("Count: " + count);
								sopl("Cur: " + this.curPos);
								sopl("Dist: " + this.getAdmissibleHeuristic(null));
								sopl("to:");
								sopl("next neighbour: " + nextNeighbour.curPos);
								sopl("Dist: " + nextNeighbour.getAdmissibleHeuristic(null));
								sopl();
								sopl();
								
							}
							
						}
						
						if(this.getAdmissibleHeuristic(null) > nextNeighbour.getAdmissibleHeuristic(null) + this.getCostOfMove(nextNeighbour)) {
							
							sopl("Count: " + count);
							sopl("Cur: " + this.curPos);
							sopl("Dist: " + this.getAdmissibleHeuristic(null));
							sopl("to:");
							sopl("next neighbour: " + nextNeighbour.curPos);
							sopl("Dist: " + nextNeighbour.getAdmissibleHeuristic(null));
							sopl();
							sopl();
							if(nextNeighbour.getAdmissibleHeuristic(null) >=0) {
								sopl("Doh!123");
								exit(1);
							}
						}
						ret.add(nextNeighbour);
						//sopl("next neighbour: " + nextNeighbour.curPos);
						
						if(nextNeighbour.curPos.endsWith("C") == false) {
							//sopl("next neighbour: " + nextNeighbour.curPos);
							//sopl("Dist: " + nextNeighbour.getAdmissibleHeuristic(null));
							//exit(1);
						}
					}
				}
			
			}
		}
		
		//TODO: add to the map!
		
		//TODO: ahh!
		return ret;
	}
	
	
	public static String swap(String start, int i, int j) {
		int low = Math.min(i, j);
		int high = Math.max(i, j);
		
		String ret = start.substring(0, low) + start.charAt(high) + start.substring(low + 1, high) + start.charAt(low);
		
		if(high < start.length() - 1) {
			ret += start.substring(high + 1, start.length());
		}
		
		return ret;
		
	}
	
	
	@Override
	public long getCostOfMove(AstarNode nextPos) {
		// TODO Auto-generated method stub

		int sum =0;
		int energyCost[] = new int[4];
		energyCost[0] = 1;
		for(int i=1; i<4; i++) {
			energyCost[i] = 10 * energyCost[i-1];
		}
		
		for(int letterIndex=0; letterIndex<4; letterIndex++) {
			char letter = (char)('A' + letterIndex);
			
			sum += energyCost[letterIndex] * getDistanceToNeighbourOrGoal(this.curPos, letter, ((prob23Pos)nextPos).curPos);
			
		}
		
		
		//if(sum == 0) {
		//	return -1;
		//}
		
		//sopl("From: " + this.curPos + " to " + ((prob23Pos)nextPos).curPos);
		//sopl("Cost of move:");
		//sopl(sum);
		return sum;
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
