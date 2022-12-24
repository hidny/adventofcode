package probs2022;
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

public class prob24b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2022/prob2022in24.txt"));
			//in = new Scanner(new File("in2022/prob2022in25.txt"));
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

			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(" ");
				
				
			}
			
			//Initialize: (should be in prob24node, but whatever!
			prob24node.lines = lines;
			
			for(int i=0; i<20; i++) {
				sopl("Drawing blizzard map for Minute " + i);
				prob24node.blizzMapArray.add(prob24node.createBlizzMap(i));
			}
			
			prob24node start = new prob24node(0, 1, 0);
			prob24node.goali = lines.size() - 1;
			prob24node.goalj = lines.get(0).length() - 2;
			
			//prob24node.goali = 2;
			//prob24node.goalj = 1;
			
			//1000 minutes??
			prob24node.spots = new prob24node[1000][lines.size()][lines.get(0).length()];
			//End init
			
			
			ArrayList <AstarNode> path = AstarAlgo.astar(start, null);
			
			sopl("Path:");

			for(int i=0; i<path.size(); i++) {
				sopl("minute " + i + ": (" + ((prob24node)path.get(i)).i + ", " + ((prob24node)path.get(i)).j + ")" );
			}
			
			

			int minutesDoneSoFar = (path.size() - 1);
			
			sopl("Answer part1: " + minutesDoneSoFar);
			
			//Reinit for going back:
			start = new prob24node(lines.size() - 1, lines.get(0).length() - 2, minutesDoneSoFar);
			prob24node.goali = 0;
			prob24node.goalj = 1;
			
			ArrayList <AstarNode> path2 = AstarAlgo.astar(start, null);
			
			minutesDoneSoFar = (path2.size() - 1) + (path.size() - 1);
			
			//Init a third time for going from start to finish again:
			start = new prob24node(0, 1, minutesDoneSoFar);
			prob24node.goali = lines.size() - 1;
			prob24node.goalj = lines.get(0).length() - 2;
			
			ArrayList <AstarNode> path3 = AstarAlgo.astar(start, null);
			
			minutesDoneSoFar = (path3.size() - 1) + (path2.size() - 1) + (path.size() - 1);
			
			
			sopl("Answer part 2: " + minutesDoneSoFar);
			
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
