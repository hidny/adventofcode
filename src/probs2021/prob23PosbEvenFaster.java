package probs2021;
import java.io.File;
import java.math.BigInteger;
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

public class prob23PosbEvenFaster implements AstarNode {

	

	public static int LENGTH_UP = 11;
	public static int DEPTH_HOLE = 4;
	

	private Hashtable<BigInteger, prob23PosbEvenFaster> map = new Hashtable<BigInteger, prob23PosbEvenFaster>();


	private BigInteger curPos;
	private int pos[];
	public prob23PosbEvenFaster(BigInteger curPos, int pos[]) {
		this.pos = pos;
		this.curPos = curPos;
		
	}
	
	public static final int EMPTY = 0;
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
			int letterNum = letterIndex + 1;
			
			sum += energyCost[letterIndex] * getDistanceToGoal(this.pos, letterNum);
			
			
			
			for(int j=0; j<DEPTH_HOLE - 1; j++) {
				
				if(this.pos[LENGTH_UP + DEPTH_HOLE*letterIndex + j] == letterNum) {
					boolean needToClearHole = false;

					for(int k=j+1; k<DEPTH_HOLE; k++) {
						if(this.pos[LENGTH_UP + DEPTH_HOLE*letterIndex + k] != letterNum
								&& this.pos[LENGTH_UP + DEPTH_HOLE*letterIndex + k] != EMPTY) {
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

	

	public static BigInteger adjustments[][];
	
	public static void setupAdjustments(int goal[]) {
		adjustments = new BigInteger[goal.length][4];
		
		
		BigInteger MULT = new BigInteger("" + (4 + 1));
		
		for(int i=goal.length - 1; i>=0; i--) {
			if(i == goal.length - 1) {
				for(int j=0; j<adjustments[0].length; j++) {
					adjustments[i][j] = new BigInteger("" + (j+ 1));
				}
			} else {
				for(int j=0; j<adjustments[0].length; j++) {
					adjustments[i][j] =  adjustments[i+1][j].multiply(MULT);
				}
			}
		}
		
		sopl("Adjustments set up");
	}

	
	public static BigInteger getCodeFromScratch(int cur[]) {
		
		BigInteger ret = BigInteger.ZERO;
		
		for(int i=0; i<cur.length; i++) {
			if(cur[i] != EMPTY) {
				
				ret = ret.add(adjustments[i][cur[i] - 1]);
			}
		}
		
		return ret;
	}

	
	public static int count = 0;
	
	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {

		
		//Ignore dead-lock positions:
		for(int i=0; i<LENGTH_UP; i++) {
			if(this.pos[i] != EMPTY) {
				for(int j=i+1; j<LENGTH_UP; j++) {

					if(this.pos[j] != EMPTY) {
						if(this.pos[i] > this.pos[j]) {

							int letterIndex1 = (int)(this.pos[i] - 1);
							int letterIndex2 = (int)(this.pos[j] - 1);
							
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
		
		NEXT_I:
		for(int i=0; i<this.pos.length; i++) {
			if(this.pos[i] == EMPTY) {
				continue;
			}
			
			BigInteger tmpCurPosCode = this.curPos.subtract(adjustments[i][pos[i] - 1]);
			
			//UP TO HERE
			int letterIndex = this.pos[i] - 1;
			
			int possibleLandings[] = new int[prob23AllInputsUpTheAnte.GOAL_INT.length];
			if(i < LENGTH_UP) {
				
				//Up to down
				int slotAcceptingIndex = slotIsAcceptingIndex(letterIndex, this.pos);
				
				if(slotAcceptingIndex >= 0) {
					possibleLandings[LENGTH_UP + DEPTH_HOLE*letterIndex + slotAcceptingIndex] = 1;
				}
				
				
			} else {
				
				int slotIndex = (i - LENGTH_UP) / DEPTH_HOLE;
				
				for(int j=LENGTH_UP+slotIndex*DEPTH_HOLE; j<i; j++) {
					if(this.pos[j] != EMPTY) {
						continue NEXT_I;
					}
				}
				
				//Down to up
				possibleLandings = new int[prob23AllInputsUpTheAnte.GOAL_INT.length];
				for(int j=0; j<LENGTH_UP; j++) {
					possibleLandings[j] = 1;
					
				}
				for(int j=0; j<4; j++) {
					possibleLandings[2+2*j] = 0;
				}
				
				
				if(i >= LENGTH_UP + DEPTH_HOLE*letterIndex && i <  LENGTH_UP + DEPTH_HOLE*(letterIndex+1)) {
					
					boolean isDone = true;
					for(int j=i+1; j<LENGTH_UP + DEPTH_HOLE*(letterIndex+1); j++) {
						if(this.pos[j] != this.pos[i]
								&& this.pos[j] != EMPTY) {
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

				if(this.pos[j] == EMPTY && possibleLandings[j] == 1) {

					ArrayList<Integer> path = prob23AllInputsUpTheAnte.getPath(i, j);
					
					boolean couldDoIt = true;
					for(int k=0; k<path.size(); k++) {
						if(this.pos[path.get(k)] != EMPTY) {
							couldDoIt = false;
						}
					}

					if(couldDoIt) {
						

						BigInteger newLocationCode = tmpCurPosCode.add(adjustments[j][pos[i] - 1]);
						
						prob23PosbEvenFaster nextNeighbour = null;
						//TODO
						if(map.containsKey(newLocationCode)) {
							//sopl("Already found");
							nextNeighbour = map.get(newLocationCode);
							
							//exit(1);
						} else {
							
							int newLocation[] = swap(this.pos, i, j);
							
							//TODO: use big int
							nextNeighbour = new prob23PosbEvenFaster(newLocationCode, newLocation);
							map.put(newLocationCode, nextNeighbour);
							
							//TESTING
							/*BigInteger codeCopy = getCodeFromScratch(newLocation);
							
							if(codeCopy.equals(newLocationCode) == false) {
								sopl("Doh!");
								exit(1);
							}*/
							//END TESTING
							count++;
							
							if(count % 10000 == 0) {
								sopl("Neighbour 1:");
								sopl("Count: " + count);
								sopl("Cur:\n" + this.getMap());
								sopl("Dist: " + this.getAdmissibleHeuristic(null));
								sopl("to:");
								sopl("next neighbour:\n" + nextNeighbour.getMap());
								sopl("Dist: " + nextNeighbour.getAdmissibleHeuristic(null));
								sopl();
								sopl("Cost of move: " + this.getCostOfMove(nextNeighbour));
								sopl();
							
								
								sopl();
								sopl();
								
							}
							
						}
						
						
						
						if(this.getAdmissibleHeuristic(null) > nextNeighbour.getAdmissibleHeuristic(null) + this.getCostOfMove(nextNeighbour)
							&& nextNeighbour.getAdmissibleHeuristic(null) >=0) {
							
							sopl("------------");
							sopl();
							sopl("Neighbour:");
							sopl("Count: " + count);
							sopl("Cur:\n" + this.getMap());
							sopl("Dist: " + this.getAdmissibleHeuristic(null));
							sopl("to:");
							sopl("next neighbour:\n" + nextNeighbour.getMap());
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
							
							if(slotIsAccepting(letterIndex, nextNeighbour.pos)) {
								ArrayList<Integer> path2 = prob23AllInputsUpTheAnte.getPath(j, LENGTH_UP + DEPTH_HOLE * letterIndex);
								
								//LENGTH_UP + DEPTH_HOLE*letterIndex + slotAcceptingIndex
								
								boolean couldDoIt2 = true;
								for(int k=0; k<path2.size(); k++) {
									if(this.pos[k] != EMPTY) {
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
		
		
		//if(count >= 1000) {
		//sopl();
		//exit(1);
		//}
		
		/*if(count % 1 == 0) {
			sopl("Count: " + count);
			sopl("Dist: " + this.getAdmissibleHeuristic(null));
			
			sopl(this.getMap());
			sopl();
			sopl();
		}*/
		
		return ret;
	}
	
	
	public static boolean slotIsAccepting(int letterIndex, int pos[]) {
		
		return slotIsAcceptingIndex(letterIndex, pos) >= 0;
	}
	
	public static int slotIsAcceptingIndex(int letterIndex, int pos[]) {
		int ret = -1;
		
		int letterNum = letterIndex + 1;
		
		for(int j=0; j<DEPTH_HOLE; j++) {
			if( pos[LENGTH_UP + DEPTH_HOLE*letterIndex + j] == EMPTY) {
				
				if(j == 0) {
					ret = j;
					
				} else if(j>0 && ret == j -1) {
					ret = j;
					
				}
				
			} else if( pos[LENGTH_UP + DEPTH_HOLE*letterIndex + j] != letterNum) {
				
				ret = -1;
				break;
			}
		
		}
		
		return ret;
	}
	
	
	
	public String getMap() {
		
		String curPos = this.getStringRep();
		
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
	
	public String getMapWithMovement(prob23PosbEvenFaster prev) {
		
		String curPosString = this.getStringRep();
		String prevPosString = prev.getStringRep();
		
		String posWithMotion = "";
		
		for(int i=0; i<curPosString.length(); i++) {
			if(curPosString.charAt(i) == '.' && prevPosString.charAt(i) != '.') {
				
				posWithMotion = curPosString.substring(0, i);
				posWithMotion += "_";
				
				if(i+1 < curPosString.length()) {
					posWithMotion += curPosString.substring(i+1, curPosString.length());
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
	
	public String getStringRep() {
		String ret ="";
		
		if(this.pos.length <= 11) {
			sopl("ERROR!");
			exit(1);
		}
		for(int i=0; i<this.pos.length; i++) {
			if(this.pos[i] == EMPTY) {
				ret += ".";
			} else {
				ret += (char)('A' + this.pos[i] - 1) + "";
			}
		}
		
		return ret;
	}
	
	public static int[] getIntArray(String puzzle) {
		int ret[] = new int[puzzle.length()];
		
		for(int i=0; i<ret.length; i++) {
			if(puzzle.charAt(i) >= 'A' && puzzle.charAt(i) <= 'D') {
				ret[i] = (int)(puzzle.charAt(i) - 'A' + 1);
			}
		}
		
		return ret;
	}
	
	public static long ENERGY_COSTS[] = new long[] {0, 1, 10, 100, 1000};
	@Override
	public long getCostOfMove(AstarNode nextPos) {
		// TODO Auto-generated method stub


		int location1 = -1;
		int location2 = -1;
		
		int letterNum = 0;
		
		for(int i=0; i<pos.length; i++) {
			if(pos[i] != ((prob23PosbEvenFaster)nextPos).pos[i]) {
				
				letterNum = Math.max(pos[i], ((prob23PosbEvenFaster)nextPos).pos[i]);
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
			return  ENERGY_COSTS[letterNum] * prob23AllInputsUpTheAnte.pathLengths[location1][location2];
		}
		
	}
	

	
	public int[] swap(int start[], int i, int j) {
		
		int swapped[] = new int[start.length];
		
		for(int k=0; k<swapped.length; k++){ 
			swapped[k] = start[k];
		}
		swapped[i] = start[j];
		swapped[j] = start[i];
		
		return swapped;
		
	}
	
	
	public static int getDistanceToNeighbour(int curLocation[], int letterNum, int neighbourLocation[]) {
		
		int location1 = -1;
		int location2 = -1;
		
		for(int i=0; i<curLocation.length; i++) {
			if(curLocation[i] != neighbourLocation[i]) {
				if(curLocation[i] != letterNum && neighbourLocation[i] != letterNum) {
					return 0;
				}
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
			return  prob23AllInputsUpTheAnte.pathLengths[location1][location2];
		}
		
		
	}

	public static int getDistanceToGoal(int pos[], int letterNum) {
		
		//int firstGoal = otherLocation.indexOf(letter);
		int currentTargetGoal =  LENGTH_UP + 4*letterNum - 1;
		
		int sum = 0;
		
		for(int i=0; i<pos.length; i++) {
			if(pos[i] == letterNum) {
				if(prob23AllInputsUpTheAnte.GOAL_INT[i] == letterNum) {
					sum += 0;
				}  else {
					
					while(pos[currentTargetGoal] == letterNum) {
						currentTargetGoal--;
					}
					
					sum += prob23AllInputsUpTheAnte.pathLengths[i][currentTargetGoal];
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
