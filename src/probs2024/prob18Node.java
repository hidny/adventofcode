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

public class prob18Node implements AstarNode {


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

	
	public static void init(boolean mapChar[][]) {
		
		map = mapChar;
		
		mapUsed = new prob18Node[map.length][map[0].length];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				mapUsed[i][j] = new prob18Node(i, j);
			}
		}
	}
	
	public prob18Node(int i, int j) {
		this.i = i;
		this.j = j;
	}
	//TODO: init
	public static boolean map[][];
	public static prob18Node mapUsed[][];
	public int i;
	public int j;
	
	@Override
	public long getAdmissibleHeuristic(AstarNode goal) {
		// TODO Auto-generated method stub
		if(i==70 && j==70) {
			return -1;
		}
		return Math.abs(70 - i) + Math.abs(70-j);
	}

	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		// TODO Auto-generated method stub
		
		ArrayList<AstarNode> ret = new ArrayList<AstarNode>();
		
		for(int i2=i-1; i2<=i+1; i2++) {
			for(int j2=j-1; j2<=j+1; j2++) {
				if((i2 !=i && j2 == j) || (i2 ==i && j2 != j)) {
					
					if(i2 >= 0 && j2 >= 0 && i2 <map.length && j2 <map[0].length) {
						
						if(map[i2][j2] == false) {
							ret.add(mapUsed[i2][j2]);
						}
					}
					
				}
			}
		}
		
		return ret;
	}

	@Override
	public long getCostOfMove(AstarNode nextPos) {
		// TODO Auto-generated method stub
		return 1;
	}

}
