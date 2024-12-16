package prob2024afterStars;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import aStar.AstarAlgo;
import aStar.AstarAlgo2;
import aStar.AstarNode;
import number.IsNumber;
import probs2024.prob16AstarNode;
import utils.Mapping;
import utils.Sort;

public class prob16bafter {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			//in = new Scanner(new File("in2024/prob2024in16.txt"));
			in = new Scanner(new File("in2024/prob2024in0.txt"));
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
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
			}
			
			char map[][] = getCharTable(lines);
			
			prob16AstarNode.mapOutAll(map);
			
			int starti=-1;
			int startj=-1;
			int startRot = 1;
			
			int endi=-1;
			int endj=-1;
			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[0].length; j++) {
					if(map[i][j] == 'S') {
						starti=i;
						startj=j;
						startRot =1;
					} else if(map[i][j] == 'E') {
						endi=i;
						endj=j;
					}
				}
			}
			
			prob16AstarNode.setGoal(endi, endj);
			
			
			ArrayList<AstarNode> ret = AstarAlgo.astar(prob16AstarNode.mapOut[starti][startj][startRot], null);
			
			
			long score = ret.size() - 1;
			
			for(int i=1; i<ret.size(); i++) {
				if(((prob16AstarNode)ret.get(i-1)).rot != ((prob16AstarNode)ret.get(i)).rot) {
					score+=1000;
				}
			}

			
			boolean table[][] = new boolean[map.length][map[0].length];
			
			for(int i=0; i<ret.size(); i++) {
				table[((prob16AstarNode)ret.get(i)).i][((prob16AstarNode)ret.get(i)).j] = true;
			}
			
			table =  getAltPaths(ret, score, table, starti, startj, startRot);
			
			cur = 0;
			for(int i=0; i<table.length; i++) {
				for(int j=0; j<table[0].length; j++) {
					if(table[i][j]) {
						cur++;
					}
				}
			}

			sopl("Answer 2: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean[][] getAltPaths(ArrayList<AstarNode> ret, long score, boolean table[][], int starti, int startj, int startRot) {

		for(int i=0; i<ret.size() - 1; i++) {
			
			prob16AstarNode.resetPoisonNeighbour();
			prob16AstarNode.setPoisonNeighbour(((prob16AstarNode)ret.get(i)).i, ((prob16AstarNode)ret.get(i)).j, ((prob16AstarNode)ret.get(i+1)).i, ((prob16AstarNode)ret.get(i+1)).j);
			
			boolean foundAlt = true;
			while(foundAlt) {
				foundAlt = false;
				
				ArrayList<AstarNode> start = new ArrayList<AstarNode>();
				
				for(int j=0; j<i+1; j++) {
					start.add(ret.get(j));
				}
				
				ArrayList<AstarNode> ret2 = AstarAlgo.astar(prob16AstarNode.mapOut[((prob16AstarNode)ret.get(i)).i][((prob16AstarNode)ret.get(i)).j][((prob16AstarNode)ret.get(i)).rot], null);

				if(ret2 == null) {
					sopl(((prob16AstarNode)ret.get(i)).i + ", " + ((prob16AstarNode)ret.get(i)).j + ", " + ((prob16AstarNode)ret.get(i)).rot);
					continue;
				}
				ret2.remove(0);
				start.addAll(ret2);
				ArrayList<AstarNode> wholePath = start;
				
				
				long newScore = wholePath.size() -1;
				
				for(int j=1; j<wholePath.size(); j++) {
					if(((prob16AstarNode)wholePath.get(j-1)).rot != ((prob16AstarNode)wholePath.get(j)).rot) {
						newScore+=1000;
					}
				}
				if(score > newScore) {
					sopl("Doh! Score");
					
				}
				
				if(score == newScore) {
					foundAlt=true;
					
					System.out.println("Second path!");
					for(int j=0; j<wholePath.size(); j++) {
						
						table[((prob16AstarNode)wholePath.get(j)).i][((prob16AstarNode)wholePath.get(j)).j] = true;
						
						
					}
					
					prob16AstarNode.setPoisonNeighbour(((prob16AstarNode)wholePath.get(i)).i, ((prob16AstarNode)wholePath.get(i)).j, ((prob16AstarNode)wholePath.get(i+1)).i, ((prob16AstarNode)wholePath.get(i+1)).j);
					
					
				} else {
					// Longer path
				}
			}
			
			
		}
		
		return table;
	}
	
	//417 too low...
	
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
			sop("Error: (" + s + ") is not a number");
			return -1;
		}
	}
	

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
		} else {
			sop("Error: (" + s + ") is not a number");
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
	
	public static int[][] getIntTable(ArrayList<String> lines) {
		int grid[][] = new int[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				if(lines.get(i).charAt(j) == '.') {
					grid[i][j] = -1;
				} else {
					grid[i][j] = (int)(lines.get(i).charAt(j) - '0');
				}
			}
		}
		
		return grid;
	}
	

	public static char[][] getCharTable(ArrayList<String> lines) {
		char grid[][] = new char[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j] = lines.get(i).charAt(j);

			}
		}
		
		return grid;
	}

}
