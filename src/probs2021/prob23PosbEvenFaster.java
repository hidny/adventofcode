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
	

	private static Hashtable<BigInteger, prob23PosbEvenFaster> map = new Hashtable<BigInteger, prob23PosbEvenFaster>();


	private BigInteger curPos;
	private int pos[];
	public prob23PosbEvenFaster(BigInteger curPos, int pos[]) {
		this.pos = pos;
		this.curPos = curPos;
		
	}
	
	public static final int EMPTY = 0;
	@Override
	public long getAdmissibleHeuristic(AstarNode goal) {
		
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
		
		
		//Check for forcing moves (up to down moves or slot to slot moves)
		for(int i=0; i<this.pos.length; i++) {
			if(this.pos[i] == EMPTY) {
				continue;
			}
			
			int letterIndex = this.pos[i] - 1;
			
			if(i >= LENGTH_UP && (i - LENGTH_UP)/DEPTH_HOLE == letterIndex ) {
				continue;
			}
			
			//Up to down
			int slotAcceptingIndex = slotIsAcceptingIndex(letterIndex, this.pos);
			
			if(slotAcceptingIndex >= 0) {
				
				ArrayList<Integer> path = prob23UtilFunctions.getPathFast(i, LENGTH_UP + DEPTH_HOLE*letterIndex);
				
				boolean couldDoIt = true;
				for(int k=0; k<path.size(); k++) {
					if(this.pos[path.get(k)] != EMPTY) {
						couldDoIt = false;
					}
				}
				
				if(couldDoIt) {
					
					int landingIndex = LENGTH_UP + DEPTH_HOLE*letterIndex + slotAcceptingIndex;
					BigInteger newLocationCode = this.curPos.subtract(adjustments[i][pos[i] - 1]).add(adjustments[landingIndex][pos[i] - 1]);
					
					prob23PosbEvenFaster nextNeighbour = addToMapAndGetNeighbour(this, newLocationCode, this.pos, i, landingIndex);
					
					ret.add(nextNeighbour);
					
					return ret;
				}
				
			} else {
				continue;
			}
		}
		//END CHECK FORCING
		
		
		//CHECK DOWN TO UP & NON-FORCING:
		NEXT_I:
		for(int i=LENGTH_UP; i<this.pos.length; i++) {
			if(this.pos[i] == EMPTY) {
				continue;
			}
			
			BigInteger tmpCurPosCode = this.curPos.subtract(adjustments[i][pos[i] - 1]);
			
			
			//Check if no insect above:
			int slotIndex = (i - LENGTH_UP) / DEPTH_HOLE;
			
			for(int j=LENGTH_UP+slotIndex*DEPTH_HOLE; j<i; j++) {
				if(this.pos[j] != EMPTY) {
					continue NEXT_I;
				}
			}

			int letterIndex = this.pos[i] - 1;
			
			//Check if insect is already done:
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
			
			//Setup possible Landings (basic)
			int possibleLandings[] = new int[prob23UtilFunctions.GOAL_INT.length];
			
			possibleLandings = new int[prob23UtilFunctions.GOAL_INT.length];
			for(int j=0; j<LENGTH_UP; j++) {
				possibleLandings[j] = 1;
				
			}
			for(int j=0; j<4; j++) {
				possibleLandings[2+2*j] = 0;
			}
			
			
			//Go thru possible landings more thoroughly:
			
			for(int j=0; j<possibleLandings.length; j++) {

				//Check if landing is free and is feasible:
				if(this.pos[j] == EMPTY && possibleLandings[j] == 1) {

					
					//Shortcut 1:
					//Don't lift insect up for no reason:
					
					int leftOfSlotIndex = 1 + 2*slotIndex;
					int rightOfSlotIndex = 3 + 2*slotIndex;
					
					if(j == leftOfSlotIndex && this.pos[rightOfSlotIndex] != EMPTY) {
						if(this.pos[rightOfSlotIndex] != slotIndex + 1) {
							//sopl("Short cut 1");
							continue;
						} else if(isSlotAcceptingAfter1Move(slotIndex, this.pos) == false) {
							//sopl("Short cut 2");
							continue;
							
						}
					} else if(j == rightOfSlotIndex && this.pos[leftOfSlotIndex] != EMPTY) {
						if(this.pos[leftOfSlotIndex] != slotIndex + 1) {
							//sopl("Short cut 3");
							continue;
						} else if(isSlotAcceptingAfter1Move(slotIndex, this.pos) == false) {
							//sopl("Short cut 4");
							continue;
							
						}
					}
					//End shortcut 1


					//Check that path isn't blocked:
					ArrayList<Integer> path = prob23UtilFunctions.getPathFast(i, j);
					
					boolean couldDoIt = true;
					for(int k=0; k<path.size(); k++) {
						if(this.pos[path.get(k)] != EMPTY) {
							couldDoIt = false;
						}
					}
					//END Check that path isn't blocked
					
					
					if(couldDoIt) {

						//ADD UNFORCED MOVE
						
						
						//Shortcut #2:
						int leftOfSlotIndexBy2 = 1 + 2*slotIndex - 2;
						int rightOfSlotIndexBy2 = 3 + 2*slotIndex + 2;
						
						if(slotIndex == 0) {
							leftOfSlotIndexBy2 = leftOfSlotIndexBy2 + 1;
			
						} else if(slotIndex == 3) {
							rightOfSlotIndexBy2 = rightOfSlotIndexBy2 -1;
						}
						
						if(j == leftOfSlotIndexBy2 && this.pos[rightOfSlotIndex] != EMPTY) {
							
							int numMovesAcceptMin = quickGetNumMoveUntilSlotAccepting(slotIndex);
							
							if(this.pos[rightOfSlotIndex] == slotIndex + 1) {
								numMovesAcceptMin--;
							}
							
							if(slotIndex > 0 && numMovesAcceptMin > 1) {
								numMovesAcceptMin = Math.min(numMovesAcceptMin, quickGetNumMoveUntilSlotAccepting(slotIndex-1));
							}
							
							if(numMovesAcceptMin > 1) {
								//sopl("continue1");
								continue;
							}
							
						} else if(j == rightOfSlotIndexBy2 && this.pos[leftOfSlotIndex] != EMPTY) {
							
							int numMovesAcceptMin = quickGetNumMoveUntilSlotAccepting(slotIndex);
							
							if(this.pos[leftOfSlotIndex] == slotIndex + 1) {
								numMovesAcceptMin--;
							}
							
							if(slotIndex < 3 && numMovesAcceptMin > 1) {
								numMovesAcceptMin = Math.min(numMovesAcceptMin, quickGetNumMoveUntilSlotAccepting(slotIndex+1));
							}
							
							if(numMovesAcceptMin > 1) {
								//sopl("continue2");
								continue;
							}
						}
						
						//END shortcut #2

						//TODO: other short cut move out by 2:
						//END TODO

						BigInteger newLocationCode = tmpCurPosCode.add(adjustments[j][pos[i] - 1]);
						
						prob23PosbEvenFaster nextNeighbour = addToMapAndGetNeighbour(this, newLocationCode, this.pos, i, j);
						
						
						/*
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
						}*/

						ret.add(nextNeighbour);
						
							
						//int letterIndex = (int)(letter - 'A');
						
						//sopl("next neighbour: " + nextNeighbour.curPos);
						//sopl("Cost of move: " + this.getCostOfMove(nextNeighbour));
						//sopl("Dist: " + nextNeighbour.getAdmissibleHeuristic(null));
						
					} 
					//END ADD UNFORCED MOVE
					
					
				}//END: Check if landing is free and is feasible:
			
			}//END go through non-forcing landings 
		}//END go through all start positions for non-forcing
		
		
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
	
	public static prob23PosbEvenFaster addToMapAndGetNeighbour(prob23PosbEvenFaster initialPos, BigInteger newLocationCode, int pos[], int startIndex, int endIndex) {
		
		prob23PosbEvenFaster nextNeighbour;
		
		if(map.containsKey(newLocationCode)) {
			//sopl("Already found");
			nextNeighbour = map.get(newLocationCode);
			
			//exit(1);
		} else {
			
			int newLocation[] = swap(pos, startIndex, endIndex);
			
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
			
			if(count % 1000 == 0) {
				sopl("Neighbour 1:");
				sopl("Count: " + count);
				sopl("Cur:\n" + initialPos.getMap());
				sopl("Dist: " + initialPos.getAdmissibleHeuristic(null));
				sopl("to:");
				sopl("next neighbour:\n" + nextNeighbour.getMap());
				sopl("Dist: " + nextNeighbour.getAdmissibleHeuristic(null));
				sopl();
				sopl("Cost of move: " + initialPos.getCostOfMove(nextNeighbour));
				sopl();
			
				
				sopl();
				sopl();
				
			}
			
		}
		
		return nextNeighbour;
	}
	
	public static boolean slotIsAccepting(int letterIndex, int pos[]) {
		
		return slotIsAcceptingIndex(letterIndex, pos) >= 0;
	}
	
	public static int slotIsAcceptingIndex(int letterIndex, int pos[]) {
		int ret = -1;
		
		int letterNum = letterIndex + 1;
		
		for(int j=0; j<DEPTH_HOLE; j++) {
			if( pos[LENGTH_UP + DEPTH_HOLE*letterIndex + j] == EMPTY) {
				
				ret = j;
				
			} else if( pos[LENGTH_UP + DEPTH_HOLE*letterIndex + j] != letterNum) {
				
				ret = -1;
				break;
			}
		
		}
		
		return ret;
	}
	
	//Only return true if slot is accepting, or if slot will be accepting after 1 move:
	public static boolean isSlotAcceptingAfter1Move(int letterIndex, int pos[]) {
		
		return slotIsAcceptingAfter1Move(letterIndex, pos) >= 0;
	}
	
	public static int slotIsAcceptingAfter1Move(int letterIndex, int pos[]) {
		int ret = -1;
		
		int letterNum = letterIndex + 1;
		
		boolean foundTopInsect = false;
		
		for(int j=0; j<DEPTH_HOLE; j++) {
			if( pos[LENGTH_UP + DEPTH_HOLE*letterIndex + j] == EMPTY) {
				
				if(j == 0) {
					ret = j;
					
				} else if(j>0 && ret == j -1) {
					ret = j;
					
				}
				
			} else if( pos[LENGTH_UP + DEPTH_HOLE*letterIndex + j] != letterNum) {
				
				if(foundTopInsect == false) {
					foundTopInsect = true;
				} else {
					ret = -1;
					break;
				}
			} else if(pos[LENGTH_UP + DEPTH_HOLE*letterIndex + j] == letterNum) {
				foundTopInsect = true;
			}
		
		}
		
		return ret;
	}
	
	private int numMoveUntilSlotAcceptingPlusOne[] = new int[4];
	
	public int quickGetNumMoveUntilSlotAccepting(int letterIndex) {
		
		if(numMoveUntilSlotAcceptingPlusOne[letterIndex] == 0) {
			
			int temp = getNumMoveUntilSlotAccepting(letterIndex, this.pos);
			numMoveUntilSlotAcceptingPlusOne[letterIndex] = temp + 1;
			return temp;
			
		} else {
			return numMoveUntilSlotAcceptingPlusOne[letterIndex];
		}
	}
	
	public static int getNumMoveUntilSlotAccepting(int letterIndex, int pos[]) {
		
		int letterNum = letterIndex + 1;
		
		
		int firstWrong = -1;
		
		
		
		for(int j=DEPTH_HOLE - 1; j>=0; j--) {
			if( pos[LENGTH_UP + DEPTH_HOLE*letterIndex + j] == EMPTY) {
				
				if(firstWrong == -1) {
					return 0;
				} else {

					return firstWrong - j;
				}
				
			} else if(firstWrong == -1 && pos[LENGTH_UP + DEPTH_HOLE*letterIndex + j] != letterNum ) {
					firstWrong =  j;
				
				
			}
		
		}
		
		return firstWrong + 1;
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
			return  ENERGY_COSTS[letterNum] * prob23UtilFunctions.pathLengths[location1][location2];
		}
		
	}
	

	
	public static int[] swap(int start[], int i, int j) {
		
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
			return  prob23UtilFunctions.pathLengths[location1][location2];
		}
		
		
	}

	public static int getDistanceToGoal(int pos[], int letterNum) {
		
		//int firstGoal = otherLocation.indexOf(letter);
		int currentTargetGoal =  LENGTH_UP + 4*letterNum - 1;
		
		int sum = 0;
		
		for(int i=0; i<pos.length; i++) {
			if(pos[i] == letterNum) {
				if(prob23UtilFunctions.GOAL_INT[i] == letterNum) {
					sum += 0;
				}  else {
					
					while(pos[currentTargetGoal] == letterNum) {
						currentTargetGoal--;
					}
					
					sum += prob23UtilFunctions.pathLengths[i][currentTargetGoal];
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
