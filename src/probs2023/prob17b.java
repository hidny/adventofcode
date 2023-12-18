package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

import aStar.AstarAlgo;
import aStar.AstarNode;

public class prob17b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in17.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
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
				
				sopl(line);
			}
			
			int table[][] = new int[lines.size()][lines.get(0).length()];
			prob17Node2 map[][][][] = new prob17Node2[table.length][table[0].length][4][12];
			
			int goali = map.length -1;
			int goalj = map[0].length -1;
			sopl(goalj);
			
			for(int i=0; i<table.length; i++) {
				for(int j=0; j<table[0].length; j++) {
					table[i][j] = (int)(lines.get(i).charAt(j) - '0');
					
					for(int m=0; m<map[i][j].length; m++) {
						for(int n=0; n<map[i][j][m].length; n++) {
							map[i][j][m][n] = new prob17Node2();
							
							map[i][j][m][n].curi = i;
							map[i][j][m][n].curj = j;
		
							map[i][j][m][n].goali = goali;
							map[i][j][m][n].goalj = goalj;
							

							map[i][j][m][n].curDir = m;
							map[i][j][m][n].curInRow = n;
						}
					}
					
				}
			}
			
			prob17Node2.map = map;
			prob17Node2.heat = table;
			
			//TODO: AAAH! Turns out I had to start it going south! I forgot about that. I should've noted it down.
			ArrayList<AstarNode> ret = AstarAlgo.astar(map[0][0][2][0], null);
			
			
			cur = 0;
			for(int i=0; i<ret.size() - 1; i++) {
				cur += ret.get(i).getCostOfMove(ret.get(i + 1));
			}

			for(int i=0; i<ret.size() - 1; i++) {
				sopl(((prob17Node2)ret.get(i)).curi + "," + ((prob17Node2)ret.get(i)).curj +  "," + ((prob17Node2)ret.get(i)).curDir +  "," + ((prob17Node2)ret.get(i)).curInRow);
			}

			sopl(goali);
			sopl(goalj);
			sopl(((prob17Node2)ret.get(ret.size() - 1)).curj);
			
			//1345
			
			//1316 (too low) or 1365 (too high)
			
			sopl("Answer: " + cur);
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

}
