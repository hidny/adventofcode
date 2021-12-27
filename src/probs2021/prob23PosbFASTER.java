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

public class prob23PosbFASTER implements AstarNode {

	
	String curPos = "";
	public prob23PosbFASTER(String pos) {
		curPos = pos;
		
	}
	
	
	public static String GOAL = "...........AAAABBBBCCCCDDDD";
	//public static String GOAL = "...........AABBCCDD";
	
	private Hashtable<String, prob23PosbFASTER> map = new Hashtable<String, prob23PosbFASTER>();

	
	public static int neighbours[][] = new int[GOAL.length()][GOAL.length()];
	public static int LENGTH_UP = 11;
	
	public static int pathLengths[][] = new int[GOAL.length()][GOAL.length()];

	public static int DEPTH_HOLE = 4;
	
	public static void main(String args[]) {
		
//Sample:
		//String curLocation = "...........BDDACCBDBBACDACA";
		
//Question:
		//String curLocation = "...........CBAABDDC";
		
		//My problem:
		//String puzzle =    "...........CDDBACBABBADDACC";
		String curLocation = "...........CDDBACBABBADDACC";
		
		//Marie:
		//String curLocation = "...........DDDBDCBCBBAAAACC";
		
		
		
		//String curLocation = "...........BACDBCDA";
		
		DEPTH_HOLE = (curLocation.length() - LENGTH_UP)/4;
		sopl(DEPTH_HOLE);
		
		
		getPathLengths();
		
		String GOAL2 = "...........";
		for(int i=0; i<4; i++) {
			for(int j=0; j<DEPTH_HOLE; j++) {
				GOAL2 += (char)('A' + i) + "";
			}
		}
		if(GOAL2.equals(GOAL) == false) {
			sopl("oops!");
			exit(1);
		}
		
		//String curLocation = "...........CBAABDDC";
		sopl(getDistanceToGoal(curLocation, 'A'));
		
		
		prob23PosbFASTER pos = new prob23PosbFASTER(curLocation);
		
		sopl("Map:");
		sopl(pos.getMap());
		
		
		sopl(pos.getAdmissibleHeuristic(null));
		
		pos.getNodeNeighbours();
		
		
		
		ArrayList<AstarNode> path = AstarAlgo.astar(pos, null);
		
		sopl("-------------------------");
		sopl("Path: " + path.size());
		
		int answer = 0;
		for(int i=0; i<path.size() - 1; i++) {
			answer += path.get(i).getCostOfMove(path.get(i+1));
			
			if(i== 0) {
				sopl(((prob23PosbFASTER)path.get(i)).getMap());
				
			} else {
				//I just want to make the solution look good:
				sopl(((prob23PosbFASTER)path.get(i)).getMapWithMovement((prob23PosbFASTER)path.get(i-1)));
			}
			
			sopl(path.get(i).getCostOfMove(path.get(i+1)));
		}
		
		//Print the last position, just for fun:
		sopl(((prob23PosbFASTER)path.get(path.size() - 1)).getMapWithMovement((prob23PosbFASTER)path.get(path.size() - 2)));
		
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
			
			
			
			for(int j=0; j<DEPTH_HOLE - 1; j++) {
				
				if(this.curPos.charAt(LENGTH_UP + DEPTH_HOLE*letterIndex + j) == letter) {
					boolean needToClearHole = false;

					for(int k=j+1; k<DEPTH_HOLE; k++) {
						if(this.curPos.charAt(LENGTH_UP + DEPTH_HOLE*letterIndex + k) != letter
								&& this.curPos.charAt(LENGTH_UP + DEPTH_HOLE*letterIndex + k) != '.') {
							needToClearHole = true;
							break;
						}
						
					}
					

					if(needToClearHole) {
						sum += 4 * energyCost[letterIndex] + 2*j*energyCost[letterIndex];
					}
				}
				
			}
			
			
		}
		
		
		
		if(sum == 0) {
			return -1;
		}
		return sum;
		
	}
	


	
	public static int count = 0;
	
	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {

		
		//Ignore dead-lock positions:
		for(int i=0; i<LENGTH_UP; i++) {
			if(this.curPos.charAt(i) != '.') {
				for(int j=i+1; j<LENGTH_UP; j++) {

					if(this.curPos.charAt(j) != '.') {
						if(this.curPos.charAt(i) > this.curPos.charAt(j)) {

							int letterIndex1 = (int)(this.curPos.charAt(i) - 'A');
							int letterIndex2 = (int)(this.curPos.charAt(j) - 'A');
							
							if(j < 2 + letterIndex1*2 && i > 2 + letterIndex2*2) {
								
								/*sopl("Nope");
								
								sopl("Count: " + count);
								sopl("Cur: " + this.curPos);
								sopl("Dist: " + this.getAdmissibleHeuristic(null));
								
								sopl(this.getMap());
								sopl();
								sopl();*/
								//exit(1);
								return new ArrayList<AstarNode>();
							}
						
							
							
						}
					}
				}
			}
		}
		
		
		
		
		//sopl("TEST------------");
		
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
				int slotAcceptingIndex = slotIsAcceptingIndex(letterIndex, this.curPos);
				
				if(slotAcceptingIndex >= 0) {
					possibleLandings[LENGTH_UP + DEPTH_HOLE*letterIndex + slotAcceptingIndex] = 1;
				}
				
				
			} else {
				
				int slotIndex = (i - LENGTH_UP) / DEPTH_HOLE;
				
				for(int j=LENGTH_UP+slotIndex*DEPTH_HOLE; j<i; j++) {
					if(this.curPos.charAt(j) != '.') {
						continue;
					}
				}
				
				//Down to up
				possibleLandings = new int[GOAL.length()];
				for(int j=0; j<LENGTH_UP; j++) {
					possibleLandings[j] = 1;
					
				}
				for(int j=0; j<4; j++) {
					possibleLandings[2+2*j] = 0;
				}
				
				
				if(i >= LENGTH_UP + DEPTH_HOLE*letterIndex && i <  LENGTH_UP + DEPTH_HOLE*(letterIndex+1)) {
					
					boolean isDone = true;
					for(int j=i+1; j<LENGTH_UP + DEPTH_HOLE*(letterIndex+1); j++) {
						if(this.curPos.charAt(j) != letter
								&& this.curPos.charAt(j) != '.') {
							isDone = false;
							break;
						}
					}
					
					if(isDone) {
						continue;
					}
					
				}
				
				
			}
			
			

			for(int j=0; j<possibleLandings.length; j++) {

				if(this.curPos.charAt(j) == '.' && possibleLandings[j] == 1) {

					ArrayList<Integer> path = getPath(i, j);
					
					boolean couldDoIt = true;
					for(int k=0; k<path.size(); k++) {
						if(this.curPos.charAt(path.get(k)) != '.') {
							couldDoIt = false;
						}
					}

					if(couldDoIt) {
						String newLocation = swap(this.curPos, i, j);
						
						prob23PosbFASTER nextNeighbour = null;
						//TODO
						if(map.containsKey(newLocation)) {
							//sopl("Already found");
							nextNeighbour = map.get(newLocation);
							
							//exit(1);
						} else {
							nextNeighbour = new prob23PosbFASTER(newLocation);
							map.put(newLocation, nextNeighbour);
							count++;
							
							if(count % 10000 == 0) {
								sopl("Count: " + count);
								sopl("Cur: " + this.curPos);
								sopl("Dist: " + this.getAdmissibleHeuristic(null));
								sopl("to:");
								sopl("next neighbour: " + nextNeighbour.curPos);
								sopl("Dist: " + nextNeighbour.getAdmissibleHeuristic(null));
								
								sopl(nextNeighbour.getMap());
								sopl();
								sopl();
								
							}
							
						}
						
						
						
						if(this.getAdmissibleHeuristic(null) > nextNeighbour.getAdmissibleHeuristic(null) + this.getCostOfMove(nextNeighbour)
							&& nextNeighbour.getAdmissibleHeuristic(null) >=0) {
							
							sopl("------------");
							sopl();
							sopl();
							sopl("Count: " + count);
							sopl("Cur:\n" + this.curPos);
							sopl("Dist: " + this.getAdmissibleHeuristic(null));
							sopl("to:");
							sopl("next neighbour:\n" + nextNeighbour.curPos);
							sopl("Dist: " + nextNeighbour.getAdmissibleHeuristic(null));
							sopl();
							sopl("Cost of move: " + this.getCostOfMove(nextNeighbour));
							sopl();
							
							sopl("Doh! Impossible move!");
							exit(1);
						}

						ret.add(nextNeighbour);
						
						//Add forced moves:
						if(j >= LENGTH_UP) {
							//sopl("forced");
							//exit(1);
							return ret;
						
						} else if(j < LENGTH_UP) {
							
							//int letterIndex = (int)(letter - 'A');
							
							if(slotIsAccepting(letterIndex, nextNeighbour.curPos)) {
								ArrayList<Integer> path2 = getPath(j, LENGTH_UP + DEPTH_HOLE * letterIndex);
								
								//LENGTH_UP + DEPTH_HOLE*letterIndex + slotAcceptingIndex
								
								boolean couldDoIt2 = true;
								for(int k=0; k<path2.size(); k++) {
									if(this.curPos.charAt(path2.get(k)) != '.') {
										couldDoIt2 = false;
									}
								}
								
								if(couldDoIt2 && 
										this.getAdmissibleHeuristic(null) == nextNeighbour.getAdmissibleHeuristic(null) + this.getCostOfMove(nextNeighbour)) {
									
									//sopl(this.getMap());
									//sopl(nextNeighbour.getMap());
									
									//sopl("forced2");
									//exit(1);
									
									//TODO: did this break it?
									ret = new ArrayList<AstarNode>();
									ret.add(nextNeighbour);
									return ret;
									//END TODO
									
								}
								
							}
							
							//At this point... make sure it's not impossible???
							//TODO
						}
						
						//sopl("next neighbour: " + nextNeighbour.curPos);
						//sopl("Cost of move: " + this.getCostOfMove(nextNeighbour));
						//sopl("Dist: " + nextNeighbour.getAdmissibleHeuristic(null));
						
					}
					
					
				}
			
			}
		}
		
	//	if(count >= 1000) {
	//	sopl();
	//	exit(1);
	//	}
		//TODO: add to the map!
		
		/*if(count % 1 == 0) {
			sopl("Count: " + count);
			sopl("Cur: " + this.curPos);
			sopl("Dist: " + this.getAdmissibleHeuristic(null));
			
			sopl(this.getMap());
			sopl();
			sopl();
			
		}*/
		
		return ret;
	}
	
	
	public static boolean slotIsAccepting(int letterIndex, String pos) {
		
		return slotIsAcceptingIndex(letterIndex, pos) >= 0;
	}
	
	public static int slotIsAcceptingIndex(int letterIndex, String pos) {
		int ret = -1;
		
		char letter = (char)('A' + letterIndex);
		
		for(int j=0; j<DEPTH_HOLE; j++) {
			if( pos.charAt(LENGTH_UP + DEPTH_HOLE*letterIndex + j) == '.') {
				
				if(j == 0) {
					ret = j;
					
				} else if(j>0 && ret == j -1) {
					ret = j;
					
				}
				
			} else if( pos.charAt(LENGTH_UP + DEPTH_HOLE*letterIndex + j) != letter) {
				
				ret = -1;
				break;
			}
		
		}
		
		return ret;
	}
	
	
	
	public String getMap() {
		String ret = "";
		ret = "#" + curPos.substring(0, LENGTH_UP) + "#";
		ret += "\n";
		for(int i=0; i<DEPTH_HOLE; i++) {
			
			ret += "###";
			for(int j=0; j<4; j++) {
				ret += curPos.charAt(LENGTH_UP + j*DEPTH_HOLE  + i);
				ret+="#";
			}
			ret += "##";
			ret += "\n";
		}
		return ret;
	}
	
	public String getMapWithMovement(prob23PosbFASTER prev) {
		String posWithMotion = "";
		
		for(int i=0; i<curPos.length(); i++) {
			if(curPos.charAt(i) == '.' && prev.curPos.charAt(i) != '.') {
				
				posWithMotion = curPos.substring(0, i);
				posWithMotion += "_";
				
				if(i+1 < curPos.length()) {
					posWithMotion += curPos.substring(i+1, curPos.length());
				}
			}
		}
		
		String ret = "";
		ret = "#" + posWithMotion.substring(0, LENGTH_UP) + "#";
		ret += "\n";
		for(int i=0; i<DEPTH_HOLE; i++) {
			
			ret += "###";
			for(int j=0; j<4; j++) {
				ret += posWithMotion.charAt(LENGTH_UP + j*DEPTH_HOLE  + i);
				ret+="#";
			}
			ret += "##";
			ret += "\n";
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
			
			sum += energyCost[letterIndex] * getDistanceToNeighbour(this.curPos, letter, ((prob23PosbFASTER)nextPos).curPos);
			
		}
		
		
		//if(sum == 0) {
		//	return -1;
		//}
		
		//sopl("From: " + this.curPos + " to " + ((prob23Pos)nextPos).curPos);
		//sopl("Cost of move:");
		//sopl(sum);
		return sum;
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
	
	
	
	public static int getDistanceToNeighbour(String curLocation, char letter, String neighbour) {
		
		int location1 = -1;
		int location2 = -1;
		
		for(int i=0; i<curLocation.length(); i++) {
			if(curLocation.charAt(i) != neighbour.charAt(i)
					&& (curLocation.charAt(i) == letter || neighbour.charAt(i) == letter)) {
				
				if(location1 == -1) {
					location1 = i;
				} else if(location2 == -1){
					location2 = i;
				} else {
					sopl("oops! get distance to neighbour");
					exit(1);
				}
			}
		}
		
		if(location1 == -1) {
			return 0;
		} else {
			return  pathLengths[location1][location2];
		}
		
		
	}

	public static int getDistanceToGoal(String curLocation, char letter) {
		
		//int firstGoal = otherLocation.indexOf(letter);
		int currentTargetGoal =  GOAL.lastIndexOf(letter);
		
		int sum = 0;
		
		for(int i=0; i<curLocation.length(); i++) {
			if(curLocation.charAt(i) == letter) {
				if(GOAL.charAt(i) == letter) {
					sum += 0;
				}  else {
					
					while(curLocation.charAt(currentTargetGoal) == letter) {
						currentTargetGoal--;
					}
					
					sum += pathLengths[i][currentTargetGoal];
					currentTargetGoal--;
				}
			}
		}
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
