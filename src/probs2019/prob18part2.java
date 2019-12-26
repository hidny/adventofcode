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

public class prob18part2 {

	public static void main(String[] args) {
		Scanner in;
		try {
			
			boolean part2 = true;
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
			
			char origMapPart1[][] = new char[lines.size()][lines.get(0).length()];
			
			//for part2:
			char origMaps[][][] = new char[4][][];

			int numStarts = 0;
			
			for(int i=0; i<origMapPart1.length; i++) {
				for(int j=0; j<origMapPart1[0].length; j++) {
					origMapPart1[i][j] = lines.get(i).charAt(j);
					
					if(part2 && lines.get(i).charAt(j) == '@') {
						numStarts++;
					}
					
				}
			}
			
			if(part2) {
				char adjustedMap[][] = new char[][] {
					{'@', '#', '@'},
					{'#', '#', '#'},
					{'@', '#', '@'}
				};
				
				
				int centerI = 0;
				int centerJ = 0;
				
			
				OUT:
				for(int i=0; i<origMapPart1.length; i++) {
					for(int j=0; j<origMapPart1[0].length; j++) {
						if(origMapPart1[i][j] == '@' && numStarts == 1) {
							sopl("in");
							centerI = i;
							centerJ = j;
							for(int i2=0; i2<adjustedMap.length; i2++) {
								for(int j2=0; j2<adjustedMap[i2].length; j2++) {
									origMapPart1[i-1 + i2][j-1 + j2] =adjustedMap[i2][j2];
								}
							}
							break OUT;
						} else if(origMapPart1[i][j] == '@' && numStarts == 4) {
							centerI += i;
							centerJ += j;
						}
						
					}
				}
				
				if(numStarts == 4) {
					centerI /= 4;
					centerJ /= 4;
				}
				

				sopl();
				
				for(int i=0; i<origMapPart1.length; i++) {
					for(int j=0; j<origMapPart1[0].length; j++) {
						sop(origMapPart1[i][j]);
					}
					sopl();
				}
				sopl();

				

				origMaps[0] = new char[centerI + 1][centerJ + 1];
				origMaps[1] = new char[centerI + 1][origMapPart1[0].length - centerJ];
				origMaps[2] = new char[origMapPart1.length - centerI][centerJ + 1];
				origMaps[3] = new char[origMapPart1.length - centerI][origMapPart1[0].length - centerJ];
				
				for(int k=0; k<origMaps.length; k++) {
					for(int i=0; i<origMaps[k].length; i++) {
						for(int j=0; j<origMaps[k][0].length; j++) {
							int iUsed = i;
							int jUsed = j;
							if(k >= 2) {
								iUsed += centerI;
							}
							
							if(k % 2 == 1) {
								jUsed += centerJ;
							}
								
							origMaps[k][i][j] = origMapPart1[iUsed][jUsed];
						}
					}
				}
				
				
				for(int k=0; k<origMaps.length; k++) {
					for(int i=0; i<origMaps[k].length; i++) {
						for(int j=0; j<origMaps[k][0].length; j++) {
							sop(origMaps[k][i][j]);
						}
						sopl();
					}
					sopl();
					sopl();
				}
			}
			
			if(part2) {
				//Set distances for my optimization...
				for(int i=0; i<origMaps.length; i++) {
					
					prob18.setDistanceBetweenPlayerAndGoals(origMaps[i], i);
				}
	
				prob18state startState[] = new prob18state[origMaps.length];
				//START
				for(int i=0; i<origMaps.length; i++) {
					startState[i] = new prob18state(origMaps[i]);
					startState[i].setMapIndex(i);

				}
				int answer = getBestPath(startState);
				

				sopl("Answer: " + answer);
			}
				
			//TODO: part 1
			
			
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

	
	public static int veryBestFound = 100000;

	public static int getBestPath(prob18state startState[]) {

		ArrayList<prob18goal> goals = new ArrayList<prob18goal>();
		ArrayList<String> doorsCurrentlyClosedAndKeys = new ArrayList<String>();
		int numMovesToGetToStartOfGoal = 0;

		
		for(int i=0; i<startState.length; i++) {
			goals.addAll(startState[i].getGoals());
			doorsCurrentlyClosedAndKeys.addAll(startState[i].getDoorsAndKeys());
			
			numMovesToGetToStartOfGoal += startState[i].numMovesToGetToStartOfGoal;
			
		}
		
		if(goals.size() == 0) {
			
			
			return numMovesToGetToStartOfGoal;
		}
		
		ArrayList<Comparable> nextStepStates= new ArrayList<Comparable>();
		
		for(int i=0; i<goals.size(); i++) {

			int mapIndex = goals.get(i).k;
			
			startState[mapIndex].setGoal(goals.get(i).j, goals.get(i).i);
			
			boolean lockedOut = false;
			
			for(int j=0; j<doorsCurrentlyClosedAndKeys.size() && lockedOut == false; j++) {
				
				if(prob18.listOfCompletelyBlackingKeysOrDoors.contains(mapIndex + "," + startState[mapIndex].coordX + "," + startState[mapIndex].coordY + "," + goals.get(i).j + "," + goals.get(i).i + "," + doorsCurrentlyClosedAndKeys.get(j))) {
					lockedOut = true;
					break;
				}
			}
			
			if(lockedOut == false) {
				int numMoves = prob18.distances.get(mapIndex + "," + startState[mapIndex].coordX + "," + startState[mapIndex].coordY + "," + goals.get(i).j + "," + goals.get(i).i);
				
				prob18state temp = new prob18state(startState[mapIndex].getStateMapAfterGoal(), startState[mapIndex].numMovesToGetToStartOfGoal + numMoves, startState[mapIndex].getGoalsAfterAttainingSingleGoal(), startState[mapIndex].getDoorsAndKeysAfterAttainingSingleGoal());
				temp.setMapIndex(mapIndex);
				nextStepStates.add(temp);
				
			}
			
		}
		
		Object list[] = Sort.sortList(nextStepStates);
		
		int best = 9999999;
		
		
		for(int i=0; i<list.length; i++) {
			
			/*int minNumMoves = numMovesToGetToStartOfGoal + minPossibleNumMovesLeft
					+ prob18.minPossibleNumMovesNeededForPath((prob18state)list[i]) 
					- minPossibleNumMovesLeftPerQuad[((prob18state)list[i]).getMapIndex()];
*/

			//Do manual count:
			int mapIndexOfKeyObtained = ((prob18state)list[i]).getMapIndex();
			
			int minNumMoves = 0;
			for(int k=0; k<startState.length; k++) {
				
				if(k == mapIndexOfKeyObtained) {
					minNumMoves += prob18.minPossibleNumMovesNeededForPath((prob18state)list[i]);
				} else {
					minNumMoves += prob18.minPossibleNumMovesNeededForPath(startState[k]);
					
				}
			}
			//End do manual count
			
			if(veryBestFound <= minNumMoves) {
				continue;
			}

			//2020 too high
			char doorOpened = startState[mapIndexOfKeyObtained].getDoorToOpen(((prob18state)list[i]).coordX, ((prob18state)list[i]).coordY);
			
			prob18state newStartState[] = new prob18state[startState.length];
			for(int k=0; k<newStartState.length; k++) {
				if(k == mapIndexOfKeyObtained) {
					newStartState[k] = (prob18state)list[i];
					newStartState[k].setMapIndex(k);
				} else {
					//Update Doors:
					newStartState[k] = new prob18state(startState[k].map, startState[k].numMovesToGetToStartOfGoal, startState[k].getGoals(), startState[k].getDoorsAndKeysAfterOpeningSingleDoor(doorOpened));
					newStartState[k].setMapIndex(k);
					
				}
			}
			
			//sopl("DEBUG minNumMoves: " + minNumMoves + " goal size: " + goals.size() );
			
			
			int cur = getBestPath(newStartState);
			
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
