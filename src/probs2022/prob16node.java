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

public class prob16node {
	public String label = "";
	
	public int flowRate = -1;
	
	public ArrayList<String> connections = new ArrayList<String>();
	
	

	public prob16node(String label, int flowRate, ArrayList<String> connections) {
		super();
		this.label = label;
		this.flowRate = flowRate;
		this.connections = connections;
	}
	
	
	

	
	public static int recordTotalFlow[][][];
	
	public static int getMaxFlow(ArrayList <prob16node> node, int indexStart, int minutesLeft) {
		boolean opened[] = new boolean[node.size()];
		

		int numToOpen = getNumToOpen(node);
		recordTotalFlow = new int[minutesLeft + 1][node.size()][(int)(Math.pow(2, numToOpen))];
		
		for(int i=0; i<recordTotalFlow.length; i++) {
			for(int j=0; j<recordTotalFlow[0].length; j++) {
				for(int k=0; k<recordTotalFlow[0][0].length; k++) {
					recordTotalFlow[i][j][k] = -1;
				}
			}
		}
		
		sopl("Num open: " + numToOpen);
		
		
		
		int answer = getMaxFlow(node, indexStart, minutesLeft, 0, opened, numToOpen, 0, new HashSet<Integer>());
		
		sopl("Debug");
		
		return answer;
	}
	
	public static int bestSoFar = 0;
	public static int num = 0;
	
	//TODO: reset part 2 with a better estimate for getOptimisticFlow
	//TODO: prev index should be a hashset that grows
	
	public static int getMaxFlow(ArrayList <prob16node> node, int curIndex, int minutesLeft, int totalFlow, boolean opened[], int numLeftToOpen, int patternOpen, HashSet<Integer> prevIndexes) {
		
		
		if(minutesLeft == 0) {
			return totalFlow;
		}
		
		if(/*recordCurFlow[minutesLeft][curIndex][patternOpen] > curFlow
				&& */recordTotalFlow[minutesLeft][curIndex][patternOpen] >= totalFlow) {
			
			//sopl("BOOM!");
			
			//TODO: remove for part2
			return 0;
		}
		for(int min=minutesLeft + 1; min<recordTotalFlow.length; min++) {
			if(recordTotalFlow[min][curIndex][patternOpen] >= totalFlow) {
				
				//sopl("BOOM!2");

				//TODO: remove for part2
				return 0;
			}
		}
		
		if(totalFlow > recordTotalFlow[minutesLeft][curIndex][patternOpen]) {
			num++;
			//sopl("Num inserted: " + num);
			if(totalFlow > bestSoFar) {
				bestSoFar = totalFlow;
				sopl("Best so far: " + bestSoFar);
			}
			recordTotalFlow[minutesLeft][curIndex][patternOpen] = totalFlow;
		} else {
			
			//TODO: remove for part2
			exit(1);
		}
		
		
		
		if(numLeftToOpen == 0) {
			return totalFlow;
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
			}
			
			int pow2ToAddToOpenPattern = getPow2(node, curIndex);
			
			bestTrial = getMaxFlow(node, curIndex, minutesLeft -1, totalFlow + AmountAdded, opened, numLeftToOpen - 1, patternOpen + pow2ToAddToOpenPattern, new HashSet<Integer>());
			opened[curIndex] = false;
		}
		
		if(mustOpenValve == false) {
			//TODO: don't go if you can open last valve
			// or cur valve to open is top valve or node.get(curIndex).flowRate * (minutesLeft - 1) > maxFlowRate* (minutesLeft - 2)
			for(int i=0; i<node.get(curIndex).connections.size(); i++) {
				
				int tmpIndex = findIndex(node, node.get(curIndex).connections.get(i));
				
				if(prevIndexes.contains(tmpIndex)) {
					continue;
				}
				
				prevIndexes.add(curIndex);
				
				int curTrial = getMaxFlow(node, findIndex(node, node.get(curIndex).connections.get(i)), minutesLeft -1, totalFlow, opened, numLeftToOpen, patternOpen, prevIndexes);
	
				prevIndexes.remove(curIndex);
				
				if(curTrial > bestTrial) {
					bestTrial = curTrial;
				}
			}
		}
		
		
		return bestTrial;
	}
	
	public static int findIndex(ArrayList <prob16node> node, String label) {
		
		for(int i=0; i<node.size(); i++) {
			if(node.get(i).label.equals(label)) {
				return i;
			}
		}
		sopl("oops2");
		exit(1);
		return -1;
	}
	
	public static int getNumToOpen(ArrayList <prob16node> node) {
		int ret = 0;
		
		for(int i=0; i<node.size(); i++) {
			if(node.get(i).flowRate > 0) {
				ret++;
			}
		}
		return ret;
	}
	
	public static int getMaxFlowYetToOpen(ArrayList <prob16node> node, boolean opened[]) {
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
	
	public static int getPow2(ArrayList <prob16node> node, int index) {
		int cur = 0;
		
		for(int i=0; i<index; i++) {
			if(node.get(i).flowRate > 0) {
				cur++;
			}
		}
		
		
		return (int)(Math.pow(2, cur));
	}
	
	public static int getMaxFlow(ArrayList <prob16node> node) {
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
