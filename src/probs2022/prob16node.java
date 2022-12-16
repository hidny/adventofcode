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
	
	
	

	
	//public static int recordCurFlow[][][];
	public static int recordTotalFlow[][][];
	
	public static int getMaxFlow(ArrayList <prob16node> node, int indexStart) {
		boolean opened[] = new boolean[node.size()];
		

		int numToOpen = getNumToOpen(node);
		//recordCurFlow = new int[31][node.size()][(int)(Math.pow(2, numToOpen))];
		recordTotalFlow = new int[31][node.size()][(int)(Math.pow(2, numToOpen))];
		
		for(int i=0; i<recordTotalFlow.length; i++) {
			for(int j=0; j<recordTotalFlow[0].length; j++) {
				for(int k=0; k<recordTotalFlow[0][0].length; k++) {
					recordTotalFlow[i][j][k] = -1;
				}
			}
		}
		
		sopl("Num open: " + numToOpen);
		
		
		
		int answer = getMaxFlow(node, indexStart, 30, 0, 0, opened, numToOpen, 0);
		
		sopl("Debug");
		
		return answer;
	}
	
	public static int bestSoFar = 0;
	public static int num = 0;
	
	public static int getMaxFlow(ArrayList <prob16node> node, int curIndex, int minutesLeft, int totalFlow, int curFlow, boolean opened[], int numLeftToOpen, int patternOpen) {
		

		//sopl(curIndex + ", " + curFlow + "," + totalFlow);

		if(totalFlow == 885 && minutesLeft == 24 && curIndex == 0) {
			sopl("debug");
		}
		if(totalFlow == 885 && minutesLeft == 23 && curIndex == 8) {
			sopl("debug");
		}

		if(totalFlow == 885 && minutesLeft == 22 && curIndex == 9) {
			sopl("debug");
		}
		
		if(minutesLeft == 0) {
			return totalFlow;
		}
		
		if(/*recordCurFlow[minutesLeft][curIndex][patternOpen] > curFlow
				&& */recordTotalFlow[minutesLeft][curIndex][patternOpen] >= totalFlow) {
			
			//sopl("BOOM!");
			
			return 0;
		}
		for(int min=minutesLeft + 1; min<recordTotalFlow.length; min++) {
			if(recordTotalFlow[min][curIndex][patternOpen] >= totalFlow) {
				
				//sopl("BOOM!2");
				
				return 0;
			}
		}
		
		/*if(curFlow > recordCurFlow[minutesLeft][curIndex][patternOpen]) {
			recordCurFlow[minutesLeft][curIndex][patternOpen] = curFlow;
		}*/
		if(totalFlow > recordTotalFlow[minutesLeft][curIndex][patternOpen]) {
			num++;
			//sopl("Num inserted: " + num);
			if(totalFlow > bestSoFar) {
				bestSoFar = totalFlow;
				sopl("Best so far: " + bestSoFar);
			}
			recordTotalFlow[minutesLeft][curIndex][patternOpen] = totalFlow;
		} else {
			exit(1);
		}
		
		
		
		if(numLeftToOpen == 0) {
			return totalFlow;
		}

		
		
		if(minutesLeft > 20) {
			sopl(curIndex + ", " + curFlow + ", " + minutesLeft);
		}
		
		
		int bestTrial = 0;
		if(! opened[curIndex] && node.get(curIndex).flowRate > 0) {
			
			int AmountAdded = node.get(curIndex).flowRate * (minutesLeft - 1);
			if(AmountAdded < 0) {
				AmountAdded = 0;
			}
			if(minutesLeft == 29) {
				sopl("Debug");
			}
			opened[curIndex] = true;
			
			int pow2ToAddToOpenPattern = getPow2(node, curIndex);
			
			bestTrial = getMaxFlow(node, curIndex, minutesLeft -1, totalFlow + AmountAdded, curFlow + node.get(curIndex).flowRate, opened, numLeftToOpen - 1, patternOpen + pow2ToAddToOpenPattern);
			opened[curIndex] = false;
		}
		//[[0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [1637, 1649, 1637, 1649, 1637, 1649, 1637, 1649, 1649, 1637], [1649, 1637, 1649, 1637, 1649, 1637, 1649, 1637, 1637, 1649], [1637, 1649, 1637, 1649, 1637, 1649, 1637, 1647, 1649, 1637], [1649, 1637, 1649, 1637, 1649, 1637, 1647, 1637, 1637, 1647], [1637, 1649, 1637, 1649, 1637, 1647, 1637, 1647, 1647, 1637], [1647, 1637, 1649, 1637, 1647, 1637, 1647, 1637, 1637, 1610], [1637, 1610, 1637, 1647, 1637, 1647, 1637, 1647, 1610, 1410], [1610, 1555, 1610, 1637, 1647, 1637, 1647, 1405, 1410, 1524], [1405, 1524, 1542, 1610, 1637, 1647, 1405, 1647, 1524, 1410], [1524, 1405, 1524, 1405, 1610, 1405, 1647, 1405, 1405, 1372], [1405, 1524, 1405, 1451, 1405, 1610, 1405, 1647, 1372, 1405], [1372, 1405, 1372, 1405, 1451, 1405, 1610, 1405, 1405, 1372], [1405, 1372, 1405, 1372, 1405, 1451, 1405, 1610, 1372, 1381], [1372, 1324, 1372, 1405, 1372, 1405, 1451, 1324, 1381, 1360], [1324, 1360, 1324, 1372, 1405, 1372, 1324, 1451, 1360, 1381], [1360, 1324, 1360, 1324, 1372, 1324, 1099, 1123, 1324, 1066], [1324, 1360, 1324, 1360, 1324, 1099, 1123, 1099, 1066, 1324], [1066, 1324, 1360, 1324, 1066, 1123, 1099, 1122, 1324, 1064], [1324, 1064, 1324, 1066, 1123, 1066, 1122, 1099, 1064, 1053], [1064, 1324, 1064, 1090, 1066, 1122, 1066, 1122, 1053, 1064], [931, 1064, 1069, 1064, 1090, 1066, 1122, 1066, 1064, 1053], [1064, 931, 1027, 931, 1027, 681, 1066, 1122, 924, 1064], [924, 885, 931, 1027, 681, 638, 560, 1066, 1064, 567], [885, 924, 885, 567, 638, 560, 638, 560, 567, 1064], [567, 885, 560, 638, 560, 638, 560, 0, 0, 567], [0, 560, 612, 560, 638, 560, 0, 0, 567, 0], [560, 0, 560, 0, 560, 0, 0, 0, 0, 567], [0, 364, 0, 560, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
		
		for(int i=0; i<node.get(curIndex).connections.size(); i++) {
			
			if(totalFlow == 885 && minutesLeft == 24 && curIndex == 0) {
				sopl(node.get(curIndex).connections.get(i));
				sopl("debug");
			}
			if(totalFlow == 885 && minutesLeft == 23 && curIndex == 8) {
				sopl(node.get(curIndex).connections.get(i));
				sopl("debug");
			}
			if(totalFlow == 885 && minutesLeft == 22 && curIndex == 9) {
				sopl(node.get(curIndex).connections.get(i));
				sopl("debug");
			}
			
			int curTrial = getMaxFlow(node, findIndex(node, node.get(curIndex).connections.get(i)), minutesLeft -1, totalFlow, curFlow, opened, numLeftToOpen, patternOpen);
			
			if(curTrial > bestTrial) {
				bestTrial = curTrial;
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
