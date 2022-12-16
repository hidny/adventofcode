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

public class prob16node2 {
	public String label = "";
	
	public int flowRate = -1;
	
	public ArrayList<String> connections = new ArrayList<String>();
	
	

	public prob16node2(String label, int flowRate, ArrayList<String> connections) {
		super();
		this.label = label;
		this.flowRate = flowRate;
		this.connections = connections;
	}
	
	
	

	
	//public static int recordCurFlow[][][];
	public static int recordTotalFlow[][][];
	
	public static int getMaxFlow(ArrayList <prob16node2> node, int indexStart) {
		boolean opened[] = new boolean[node.size()];
		

		int numToOpen = getNumToOpen(node);
		//recordCurFlow = new int[31][node.size()][(int)(Math.pow(2, numToOpen))];
		
		recordTotalFlow = new int[27][node.size()][node.size()];
		
		for(int i=0; i<recordTotalFlow.length; i++) {
			for(int j=0; j<recordTotalFlow[0].length; j++) {
				for(int k=0; k<recordTotalFlow[0][0].length; k++) {
						recordTotalFlow[i][j][k] = -1;
				}
			}
		}
		
		sopl("Num open: " + numToOpen);
		
		
		
		int answer = getMaxFlow(node, indexStart, indexStart, 26, 0, opened, numToOpen, false);
		
		sopl("Debug");
		
		return answer;
	}
	
	public static int bestSoFar = 1800;
	public static int num = 0;
	
	public static int getMaxFlow(ArrayList <prob16node2> node, int curIndex, int elephantIndex, int minutesLeft, int totalFlow, boolean opened[], int numLeftToOpen, boolean elephantTurn) {
		

		
		if(getOptimisticFlow(node, opened, totalFlow, minutesLeft, curIndex, elephantIndex) <= bestSoFar) {
			//sopl("sup");
			return 0;
		}
		//sopl("opt: " + getOptimisticFlow(node, opened, totalFlow, minutesLeft, curIndex, elephantIndex) + " minutes: " + minutesLeft);
		
		if(minutesLeft == 0) {
			return totalFlow;
		}
		
		//sopl(numLeftToOpen);
		

		if(numLeftToOpen == 0) {
			
			
			if(totalFlow > recordTotalFlow[minutesLeft][curIndex][elephantIndex]) {
				num++;
				//sopl("Num inserted: " + num);
				if(totalFlow > bestSoFar) {
					bestSoFar = totalFlow;
					sopl("Best so far: " + bestSoFar);
				}
				recordTotalFlow[minutesLeft][curIndex][elephantIndex] = totalFlow;
			}
			
			return totalFlow;
		}


		if(minutesLeft - numLeftToOpen < 0) {
			//sopl("HA");
			return 0;
		}
		
		if(recordTotalFlow[minutesLeft][curIndex][elephantIndex] >= totalFlow) {
			
			//sopl("BOOM!");
			
			return 0;
		}
		for(int min=minutesLeft + 1; min<recordTotalFlow.length; min++) {
			if(recordTotalFlow[minutesLeft][curIndex][elephantIndex] >= totalFlow) {
				
				sopl("BOOM!2");
				
				return 0;
			}
		}
		
		
		
		
		if(minutesLeft > 23) {
			sopl(curIndex + ", " + totalFlow + ", " + minutesLeft);
		}
		
		int bestTrial = 0;
		
		
		if(elephantTurn) {
			
			if(! opened[elephantIndex] && node.get(elephantIndex).flowRate > 0) {
				
				int AmountAdded = node.get(elephantIndex).flowRate * (minutesLeft - 1);
				if(AmountAdded < 0) {
					AmountAdded = 0;
				}
				if(minutesLeft == 29) {
					sopl("Debug");
				}
				opened[elephantIndex] = true;
				
				bestTrial = getMaxFlow(node, curIndex, elephantIndex, minutesLeft -1, totalFlow + AmountAdded, opened, numLeftToOpen - 1, !elephantTurn);
				
				
				opened[elephantIndex] = false;
			}
			
			for(int i=0; i<node.get(elephantIndex).connections.size(); i++) {
				
				
				int curTrial = getMaxFlow(node, curIndex, findIndex(node, node.get(elephantIndex).connections.get(i)), minutesLeft -1, totalFlow, opened, numLeftToOpen, !elephantTurn);
				
				if(curTrial > bestTrial) {
					bestTrial = curTrial;
				}
			}
			
		} else {

			if(! opened[curIndex] && node.get(curIndex).flowRate > 0) {
				
				int AmountAdded = node.get(curIndex).flowRate * (minutesLeft - 1);
				if(AmountAdded < 0) {
					AmountAdded = 0;
				}
				opened[curIndex] = true;
				
				
				bestTrial = getMaxFlow(node, curIndex, elephantIndex, minutesLeft, totalFlow + AmountAdded, opened, numLeftToOpen - 1, !elephantTurn);
				
				
				opened[curIndex] = false;
			}
			for(int i=0; i<node.get(curIndex).connections.size(); i++) {
				
				
				int curTrial = getMaxFlow(node, findIndex(node, node.get(curIndex).connections.get(i)), elephantIndex, minutesLeft, totalFlow, opened, numLeftToOpen, !elephantTurn);
				
				if(curTrial > bestTrial) {
					bestTrial = curTrial;
				}
			}
		}
		
		
		return bestTrial;
	}
	
	public static int getOptimisticFlow(ArrayList <prob16node2> node, boolean opened[], int FlowRate, int minutesLeft, int ind1, int ind2) {
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
	
	public static int findIndex(ArrayList <prob16node2> node, String label) {
		
		for(int i=0; i<node.size(); i++) {
			if(node.get(i).label.equals(label)) {
				return i;
			}
		}
		sopl("oops2");
		exit(1);
		return -1;
	}
	
	public static int getNumToOpen(ArrayList <prob16node2> node) {
		int ret = 0;
		
		for(int i=0; i<node.size(); i++) {
			if(node.get(i).flowRate > 0) {
				ret++;
			}
		}
		return ret;
	}
	
	public static int getPow2(ArrayList <prob16node2> node, int index) {
		int cur = 0;
		
		for(int i=0; i<index; i++) {
			if(node.get(i).flowRate > 0) {
				cur++;
			}
		}
		
		
		return (int)(Math.pow(2, cur));
	}
	
	public static int getMaxFlow(ArrayList <prob16node2> node) {
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
