package probs2022;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob16nodeNewStratpart2 {
	public String label = "";
	
	public int flowRate = -1;
	
	public ArrayList<String> connectionsStrings = new ArrayList<String>();
	public ArrayList<Integer> connectionsIndex = new ArrayList<Integer>();

	

	public prob16nodeNewStratpart2(String label, int flowRate, ArrayList<String> connections) {
		super();
		this.label = label;
		this.flowRate = flowRate;
		this.connectionsStrings = connections;
	}
	

	//SETUP CODE
	
	public static ArrayList <prob16nodeNewStratpart2> getConnectionIndexes(ArrayList <prob16nodeNewStratpart2> node) {
		
		
		for(int i=0; i<node.size(); i++) {
			
			node.get(i).connectionsIndex = new ArrayList<Integer>();
			
			for(int j=0; j<node.get(i).connectionsStrings.size(); j++) {
				node.get(i).connectionsIndex.add(findIndex(node, node.get(i).connectionsStrings.get(j)));
			}
		}
		
		return node;
		
	}
	
	
	public static int distances[][];
	
	public static ArrayList <prob16nodeNewStratpart2> simplifySetup(ArrayList <prob16nodeNewStratpart2> node, int indexStart) {
		
		getConnectionIndexes(node);
		
		int distancesOrig[][] = new int[node.size()][node.size()];
		
		for(int i=0; i<node.size(); i++) {
			for(int j=0; j<node.size(); j++) {
				
				distancesOrig[i][j] = getDist(node, i, j);
				if(distancesOrig[i][j] < 10) {
					sop(distancesOrig[i][j] + "  ");
				} else {
					sop(distancesOrig[i][j] + " ");
				}
			}
			sopl();
		}
		sopl();
		
		

		ArrayList <String> relevantLabels = new ArrayList <String>();
		
		for(int i=0; i<node.size(); i++) {
			if(i == indexStart) {
				relevantLabels.add(node.get(i).label);
				sopl(relevantLabels.get(relevantLabels.size() - 1));
			
			} else if(node.get(i).flowRate > 0) {
				relevantLabels.add(node.get(i).label);
				sopl(relevantLabels.get(relevantLabels.size() - 1));
			}
		}
		
		ArrayList <prob16nodeNewStratpart2>ret = new ArrayList<prob16nodeNewStratpart2>();
		
		distances = new int[relevantLabels.size()][relevantLabels.size()];

		for(int i=0; i<relevantLabels.size(); i++) {
			int origI = findIndex(node, relevantLabels.get(i));
			
			ArrayList<String> newConnections = new ArrayList<String>();
			//TODO
			prob16nodeNewStratpart2 newNode = new prob16nodeNewStratpart2(node.get(origI).label, node.get(origI).flowRate, newConnections);

			for(int j=0; j<relevantLabels.size(); j++) {

				int origJ = findIndex(node, relevantLabels.get(j));
				
				distances[i][j] = distancesOrig[origI][origJ];
			}
			
			ret.add(newNode);
			
		}
		
		for(int i=0; i<relevantLabels.size(); i++) {
			for(int j=0; j<relevantLabels.size(); j++) {
				
				if(distances[i][j] < 10) {
					sop(distances[i][j] + "  ");
				} else {
					sop(distances[i][j] + " ");
				}
			}
			sopl();
		}
		sopl();
		sopl("Done Setup");
		sopl();
		
		return getConnectionIndexes(ret);
	}
	
	
	public static int getDist(ArrayList <prob16nodeNewStratpart2> node, int indexStart, int indexEnd) {

		int ret = 0;
		while(getDist(node, indexStart, indexEnd, ret) == -1) {
			ret++;
		}
		
		return ret;
	}
	
	public static int getDist(ArrayList <prob16nodeNewStratpart2> node, int indexStart, int indexEnd, int maxDist) {
		
		if(maxDist < 0) {
			return -1;
		}
		
		if(indexStart == indexEnd) {
			return 0;
		}
		
		int best = -1;
		for(int i=0; i<node.get(indexStart).connectionsIndex.size(); i++) {
			int cur = getDist(node, node.get(indexStart).connectionsIndex.get(i), indexEnd, maxDist - 1);
			
			if(cur > best) {
				best = cur;
			}
		}
		
		return best;
	}
	
	

	//END SETUP CODE

	
	
	public static int getMaxFlow(ArrayList <prob16nodeNewStratpart2> node, String labelStart, int minutesLeft) {
		
		node = simplifySetup(node, findIndex(node, labelStart));
		
		int answer = getMaxFlow(node, findIndex(node, labelStart), minutesLeft);
		
		sopl("Debug");
		
		return answer;
	}
	
	public static int bestSoFar = 0;
	public static int num = 0;
	
	//TODO: reset part 2 with a better estimate for getOptimisticFlow
	//TODO: prev index should be a hashset that grows
	
	public static int getMaxFlow(ArrayList <prob16nodeNewStratpart2> node, int startIndex, int minutesLeft) {
		
		int numLeftToOpen = getNumToOpen(node);
		boolean opened[] = new boolean[node.size()];

		sopl("Num open: " + numLeftToOpen);
		
		return getMaxFlow(node, startIndex, minutesLeft, 0, opened, numLeftToOpen, startIndex, minutesLeft);
	}

	public static int getMaxFlow(ArrayList <prob16nodeNewStratpart2> node, int curIndex, int minutesLeft, int totalFlow, boolean opened[], int numLeftToOpen, int elephantIndex, int minutesLeftElephant) {
		
		if(Math.max(minutesLeft, minutesLeftElephant) <= 0) {
			return totalFlow;
		
		} else if(minutesLeft >= minutesLeftElephant) {
			return getMaxFlowYourTurn(node, curIndex, minutesLeft, totalFlow, opened, numLeftToOpen, elephantIndex, minutesLeftElephant);
		} else {
			return getMaxFlowElephantTurn(node, curIndex, minutesLeft, totalFlow, opened, numLeftToOpen, elephantIndex, minutesLeftElephant);
			
		}
	}
	public static int getMaxFlowYourTurn(ArrayList <prob16nodeNewStratpart2> node, int curIndex, int minutesLeft, int totalFlow, boolean opened[], int numLeftToOpen, int elephantIndex, int minutesLeftElephant) {
			
		if(numLeftToOpen == 0) {
			return totalFlow;
		}

		if(minutesLeft > 20) {
			sopl(curIndex + ", " + minutesLeft);
		}
		
		int bestTrial = 0;
		for(int j=0; j<node.size(); j++) {
			if(! opened[j] && node.get(j).flowRate > 0) {
				

				int minutesTaken = distances[curIndex][j] + 1;
				
				int AmountAdded = node.get(j).flowRate * (minutesLeft - minutesTaken);
				if(AmountAdded < 0) {
					AmountAdded = 0;
				}
				
				opened[j] = true;
				
				int curTrial = getMaxFlow(node, j, minutesLeft - minutesTaken, totalFlow + AmountAdded, opened, numLeftToOpen - 1, elephantIndex, minutesLeftElephant);
				
				if(curTrial > bestTrial) {
					bestTrial = curTrial;
				}
				opened[j] = false;
				
			}
		}
		
		//Stall out and let elephant finish edge-case.
		//This wasn't need in my case, but maybe it is for other puzzle inputs... I don't know.
		int curTrial = getMaxFlow(node, curIndex, 0, totalFlow, opened, numLeftToOpen, elephantIndex, minutesLeftElephant);
		if(curTrial > bestTrial) {
			sopl("HA! You must stall out.");
			bestTrial = curTrial;
		}
				
		return bestTrial;
	}
	
	public static int getMaxFlowElephantTurn(ArrayList <prob16nodeNewStratpart2> node, int curIndex, int minutesLeft, int totalFlow, boolean opened[], int numLeftToOpen, int elephantIndex, int minutesLeftElephant) {
		
		if(numLeftToOpen == 0) {
			return totalFlow;
		}

		if(minutesLeftElephant > 20) {
			sopl(elephantIndex + ", " + minutesLeftElephant + ", elephant");
		}
		
		int bestTrial = 0;
		for(int j=0; j<node.size(); j++) {
			if(! opened[j] && node.get(j).flowRate > 0) {
				

				int minutesTaken = distances[elephantIndex][j] + 1;
				
				int AmountAdded = node.get(j).flowRate * (minutesLeftElephant - minutesTaken);
				if(AmountAdded < 0) {
					AmountAdded = 0;
				}
				
				opened[j] = true;
				
				int curTrial = getMaxFlow(node, curIndex, minutesLeft, totalFlow + AmountAdded, opened, numLeftToOpen - 1, j, minutesLeftElephant - minutesTaken);
				
				if(curTrial > bestTrial) {
					bestTrial = curTrial;
				}
				opened[j] = false;
				
			}
		}
		
		
		//Stall out and let me finish edge-case.
		//This wasn't need in my case, but maybe it is for other puzzle inputs... I don't know.
		int curTrial = getMaxFlow(node, curIndex, minutesLeft, totalFlow, opened, numLeftToOpen, elephantIndex, 0);
		if(curTrial > bestTrial) {
			sopl("HA!");
			sopl("HA! You must stall out the elephant.");
			bestTrial = curTrial;
		}
		
		
		return bestTrial;
	}
	
	public static int findIndex(ArrayList <prob16nodeNewStratpart2> node, String label) {
		
		for(int i=0; i<node.size(); i++) {
			if(node.get(i).label.equals(label)) {
				return i;
			}
		}
		sopl("oops2");
		exit(1);
		return -1;
	}
	
	public static int getNumToOpen(ArrayList <prob16nodeNewStratpart2> node) {
		int ret = 0;
		
		for(int i=0; i<node.size(); i++) {
			if(node.get(i).flowRate > 0) {
				ret++;
			}
		}
		return ret;
	}
	
	public static int getMaxFlowYetToOpen(ArrayList <prob16nodeNewStratpart2> node, boolean opened[]) {
		int index = 0;
		
		int max = 0;
		
		for(int i=0; i<node.size(); i++) {
			if(node.get(i).flowRate > 0) {
				if(opened[index] == false) {
					
					if(node.get(i).flowRate > max) {
						max = node.get(i).flowRate;
					}
				}
				
				index++;
			}
		}
		return max;
	}
	
	public static int getPow2(ArrayList <prob16nodeNewStratpart2> node, int index) {
		int cur = 0;
		
		for(int i=0; i<index; i++) {
			if(node.get(i).flowRate > 0) {
				cur++;
			}
		}
		
		
		return (int)(Math.pow(2, cur));
	}
	
	public static int getMaxFlow(ArrayList <prob16nodeNewStratpart2> node) {
		int ret = 0;
		
		for(int i=0; i<node.size(); i++) {
			ret += node.get(i).flowRate;
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
