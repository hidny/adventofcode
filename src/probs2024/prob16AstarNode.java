package probs2024;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import aStar.AstarNode;
import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob16AstarNode implements AstarNode {

	//day1 part 1
	//2:38.01
	

	public static char map2[][];
	public static prob16AstarNode mapOut[][][];
	
	public int i;
	public int j;
	public int rot;
	
	public static int goali;
	public static int goalj;
	
	public prob16AstarNode(int i, int j, int r) {
		this.i = i;
		this.j = j;
		this.rot = r;
	}
	
	public static void mapOutAll(char map[][]) {
		map2 = map;
		
		mapOut = new prob16AstarNode[map.length][map[0].length][4];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				for(int r=0; r<4; r++) {
					mapOut[i][j][r] = new prob16AstarNode(i, j, r);
				}
			}
		}
	}
	
	public static void setGoal(int goaliin, int goaljin) {
		goali = goaliin;
		goalj = goaljin;
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
	@Override
	public long getAdmissibleHeuristic(AstarNode goal) {
		// TODO Auto-generated method stub
		
		if(Math.abs(goali-this.i) + Math.abs(goalj-this.j) == 0) {
			return -1;
		}
		
		return Math.abs(goali-this.i) + Math.abs(goalj-this.j);
	}
	
	public static HashSet<String> poisonNeighbour = new HashSet<String>();
	
	public static void resetPoisonNeighbour() {
		poisonNeighbour = new HashSet<String>();
	}
	public static void setPoisonNeighbour(int i1, int j1, int i2, int j2) {
		poisonNeighbour.add(i1+ "," + j1 + "," + i2 +"," + j2);
	}
	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		// TODO Auto-generated method stub
		
		ArrayList<AstarNode> ret = new ArrayList<AstarNode>();
		
		for(int i2=i-1; i2<=i+1; i2++) {
			for(int j2=j-1; j2<=j+1; j2++) {
				if((i2 == i && j2 !=j) || (j2 == j && i2 != i)) {
					if(i2 >=0 && i2<map2.length && j2 >=0 && j2 < map2[0].length) {
						
						if(map2[i2][j2] != '#') {
							
							int r = -1;
							if(i2 < i) {
								r = 0;
							} else if(i2 > i) {
								r = 2;
							} else if(j2 > j) {
								r = 1;
							} else if(j2 < j) {
								r = 3;
							} else {
								sopl("doh!");
							}
							
							
							if(poisonNeighbour.contains(i+ "," + j + "," + i2 +"," + j2)) {
								//System.out.println("Poison!");
								//pass
							} else {
								ret.add(mapOut[i2][j2][r]);
							}
						}
					}
				}
			}
		}
		return ret;
	}
	
	@Override
	public long getCostOfMove(AstarNode nextPos) {
		
		int cost = 1;
		if((((prob16AstarNode)nextPos).rot != this.rot)) {
			cost +=1000;
		}
		
		return cost;
	}

}
