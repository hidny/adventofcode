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

public class prob16node2Trial2 {
	public String label = "";
	
	public int flowRate = -1;
	
	public ArrayList<String> connectionsString = new ArrayList<String>();
	public ArrayList<Integer> connectionsIndex = new ArrayList<Integer>();

	
	

	public prob16node2Trial2(String label, int flowRate, ArrayList<String> connections) {
		super();
		this.label = label;
		this.flowRate = flowRate;
		this.connectionsString = connections;
	}
	
	
	
	public static void getConnectionIndexes(ArrayList <prob16node2Trial2> node) {
		for(int i=0; i<node.size(); i++) {
			for(int j=0; j<node.get(i).connectionsString.size(); j++) {
				node.get(i).connectionsIndex.add(findIndex(node, node.get(i).connectionsString.get(j)));
			}
		}
	}
	
	
	public static int getMaxFlow(ArrayList <prob16node2Trial2> node, int indexStart, int minutesLeft) {
		boolean opened[] = new boolean[node.size()];
		
		
		//Make it slightly faster by not dealing with strings:
		getConnectionIndexes(node);
		
		int numToOpen = getNumToOpen(node);
		
		sopl("Num open: " + numToOpen);
		
		int answer = getMaxFlow(node, indexStart, indexStart, minutesLeft, 0, opened, numToOpen, new HashSet<Integer>(), new HashSet<Integer>());
		
		sopl("Debug");
		
		return answer;
	}
	
	public static int bestSoFar = 2000;
	public static int num = 0;
	
	//TODO: reset part 2 with a better estimate for getOptimisticFlow
	//TODO: prev index should be a hashset that grows
	
	public static int getMaxFlow(ArrayList <prob16node2Trial2> node, int curIndex, int elephantIndex, int minutesLeft, int totalFlow, boolean opened[], int numLeftToOpen, HashSet<Integer> prevIndexes, HashSet<Integer> prevIndexesElephant) {
		
		
		if(minutesLeft == 0) {
			return totalFlow;
		} else if(numLeftToOpen == 0) {
			return totalFlow;
		}


		if(getOptimisticFlow(node, opened, totalFlow, minutesLeft, curIndex, elephantIndex) <= bestSoFar) {
			//sopl("sup");
			return 0;
		}
		
		
		
		if(minutesLeft > 20) {
			sopl(curIndex + ", " + minutesLeft);
		}
		
		
		boolean mustOpenValve = false;
		
		int bestTrial = 0;
		if(! opened[curIndex] && node.get(curIndex).flowRate > 0) {
			

			
			
			int AmountAdded = node.get(curIndex).flowRate * (minutesLeft - 1);
			if(AmountAdded < 0) {
				AmountAdded = 0;
			}
			
			opened[curIndex] = true;
			
			int maxFlow = getMaxFlowYetToOpen(node, opened);
			if(AmountAdded > maxFlow * (minutesLeft - 2)) {
				mustOpenValve = true;
			}/*
			if(AmountAdded > maxFlow * (minutesLeft - 1)) {
				mustOpenValve = true;
			}*/
			
			bestTrial = getMaxFlowElephant(node, curIndex, elephantIndex, minutesLeft, totalFlow + AmountAdded, opened, numLeftToOpen - 1, new HashSet<Integer>(), prevIndexesElephant);
			opened[curIndex] = false;
		}
		
		if(mustOpenValve == false) {

			for(int i=0; i<node.get(curIndex).connectionsIndex.size(); i++) {
				
				int tmpIndex = node.get(curIndex).connectionsIndex.get(i);
				
				if(prevIndexes.contains(tmpIndex)) {
					continue;
				}
				
				prevIndexes.add(curIndex);
				
				int curTrial = getMaxFlowElephant(node, tmpIndex, elephantIndex, minutesLeft, totalFlow, opened, numLeftToOpen, prevIndexes, prevIndexesElephant);
	
				prevIndexes.remove(curIndex);
				
				if(curTrial > bestTrial) {
					bestTrial = curTrial;
				}
			}
		}
		
		
		return bestTrial;
	}
	
	
	public static int getMaxFlowElephant(ArrayList <prob16node2Trial2> node, int curIndex, int elephantIndex, int minutesLeft, int totalFlow, boolean opened[], int numLeftToOpen, HashSet<Integer> prevIndexes, HashSet<Integer> prevIndexesElephant) {
		
		
		if(minutesLeft == 0) {
			return totalFlow;
		} else if(numLeftToOpen == 0) {
			return totalFlow;
		}
		
		if(getOptimisticFlow(node, opened, totalFlow, minutesLeft, curIndex, elephantIndex) <= bestSoFar) {
			//sopl("sup");
			return 0;
		}
		
		
		if(minutesLeft > 20) {
			sopl(elephantIndex + ", " + minutesLeft + ", elephant");
		}
		
		
		boolean mustOpenValve = false;
		
		int bestTrial = 0;
		if(! opened[elephantIndex] && node.get(elephantIndex).flowRate > 0) {
			

			
			
			int AmountAdded = node.get(elephantIndex).flowRate * (minutesLeft - 1);
			if(AmountAdded < 0) {
				AmountAdded = 0;
			}
			
			opened[elephantIndex] = true;
			
			int maxFlow = getMaxFlowYetToOpen(node, opened);
			if(AmountAdded > maxFlow * (minutesLeft - 2)) {
			mustOpenValve = true;
			}/*
			if(AmountAdded > maxFlow * (minutesLeft - 1)) {
				mustOpenValve = true;
			}*/
			
			bestTrial = getMaxFlow(node, curIndex, elephantIndex, minutesLeft -1, totalFlow + AmountAdded, opened, numLeftToOpen - 1, prevIndexes, new HashSet<Integer>());
			opened[elephantIndex] = false;
		}
		
		if(mustOpenValve == false) {

			for(int i=0; i<node.get(elephantIndex).connectionsIndex.size(); i++) {
				
				int tmpIndex = node.get(elephantIndex).connectionsIndex.get(i);
				
				if(prevIndexesElephant.contains(tmpIndex)) {
					continue;
				}
				
				prevIndexesElephant.add(elephantIndex);
				
				int curTrial = getMaxFlow(node, curIndex, tmpIndex, minutesLeft -1, totalFlow, opened, numLeftToOpen, prevIndexes, prevIndexesElephant);
	
				prevIndexesElephant.remove(elephantIndex);
				
				if(curTrial > bestTrial) {
					bestTrial = curTrial;
				}
			}
		}
		
		
		return bestTrial;
	}
	
	//TODO: Maybe this can be more constrained... 
	public static int getOptimisticFlow(ArrayList <prob16node2Trial2> node, boolean opened[], int FlowRate, int minutesLeft, int ind1, int ind2) {
		int index = 0;
		
		int ret = FlowRate;
		
		for(int i=0; i<node.size(); i++) {
			if(node.get(i).flowRate > 0) {
				if(opened[index] == false) {
					if(ind1 == i || ind2 == i) {
						ret += node.get(i).flowRate * (minutesLeft - 1);
					} else {
						ret += node.get(i).flowRate * (minutesLeft - 2);
						
					}
				}
				
				index++;
			}
		}
		return ret;
	}
	
	public static int findIndex(ArrayList <prob16node2Trial2> node, String label) {
		
		for(int i=0; i<node.size(); i++) {
			if(node.get(i).label.equals(label)) {
				return i;
			}
		}
		sopl("oops2");
		exit(1);
		return -1;
	}
	
	public static int getNumToOpen(ArrayList <prob16node2Trial2> node) {
		int ret = 0;
		
		for(int i=0; i<node.size(); i++) {
			if(node.get(i).flowRate > 0) {
				ret++;
			}
		}
		return ret;
	}
	
	public static int getMaxFlowYetToOpen(ArrayList <prob16node2Trial2> node, boolean opened[]) {
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
	
	public static int getPow2(ArrayList <prob16node2Trial2> node, int index) {
		int cur = 0;
		
		for(int i=0; i<index; i++) {
			if(node.get(i).flowRate > 0) {
				cur++;
			}
		}
		
		
		return (int)(Math.pow(2, cur));
	}
	
	public static int getMaxFlow(ArrayList <prob16node2Trial2> node) {
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
