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

public class prob16p2 {

	/*
	 * 
	public static int getOptimisticFlow(ArrayList <prob16node2> node, boolean opened[], int FlowRate, int minutesLeft) {
		
		return FlowRate + minutesLeft * (maxFlowPerMinute - FlowRate);
	}
	
	 */
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2022/prob2022in16.txt"));
			//in = new Scanner(new File("in2022/prob2022in17.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}
			
			ArrayList<prob16node2Trial2> nodes = new ArrayList<prob16node2Trial2>();

			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(" ");
				
				String label = token[1];
				
				int flowRate = pint(token[4].split("=")[1].split(";")[0]);
				
				ArrayList<String> connections = new ArrayList<String>();
				
				for(int j=9; j<token.length; j++) {
					connections.add(token[j].split(",")[0]);
				}
				
				nodes.add(new prob16node2Trial2(label, flowRate, connections));
			}
			
			int numMinutesPart2 = 26;
			
			int max = prob16node2Trial2.getMaxFlow(nodes, prob16node2Trial2.findIndex(nodes, "AA"), numMinutesPart2);
			
			
			sopl("Answer: " + max);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
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
