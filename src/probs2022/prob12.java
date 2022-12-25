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

public class prob12 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2022/prob2022in12.txt"));
			 //in = new Scanner(new File("in2022/prob2022in13.txt"));
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
			
			int elevation[][] = new int[lines.size()][lines.get(0).length()];
			
			int startI = -1;
			int startJ = -1;
			int endI = -1;
			int endJ = -1;
			
			for(int i=0; i<lines.size(); i++) {
				
				for(int j=0; j<lines.get(0).length(); j++) {
					
					if(lines.get(i).charAt(j) == 'S') {
						elevation[i][j] = 0;
						startI = i;
						startJ = j;
					} else if(lines.get(i).charAt(j) == 'E') {
						elevation[i][j] = 25;
						endI = i;
						endJ = j;
					} else {
						elevation[i][j] = (int)(lines.get(i).charAt(j) - 'a');
					}
				}
				
			}
			
			prob12AstarNode map[][] = new prob12AstarNode[elevation.length][elevation[0].length];
			
			for(int i=0; i<lines.size(); i++) {
				for(int j=0; j<lines.get(0).length(); j++) {
					map[i][j] = new prob12AstarNode(i, j, elevation[i][j]);
				}
			}
			prob12AstarNode.map = map;
			
			
			ArrayList<AstarNode> path = AstarAlgo.astar(map[startI][startJ], map[endI][endJ]);

			
			int best = (path.size() - 1);
			
			sopl("Answer part 1: " + best);
			
			
			//Part 2:
			for(int i=0; i<lines.size(); i++) {
				
				for(int j=0; j<lines.get(0).length(); j++) {
					
					if(elevation[i][j] == 0) {
						ArrayList<AstarNode> path2 = AstarAlgo.astar(map[i][j], map[endI][endJ]);
						
						if(path2 != null) {
							int cur = path2.size() - 1;
							
							if(cur < best) {
								best = cur;
							}
						}
					}
					
				}
			}
			
			sopl("Answer part 2: " + best);
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